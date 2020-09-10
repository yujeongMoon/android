package or.techtown.onelinediary_photo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.Request;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.pedro.library.AutoPermissions;
import com.pedro.library.AutoPermissionsListener;
import com.stanfy.gsonxml.GsonXml;
import com.stanfy.gsonxml.GsonXmlBuilder;
import com.stanfy.gsonxml.XmlParserCreator;

import org.jetbrains.annotations.NotNull;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import or.techtown.onelinediary_photo.data.WeatherItem;
import or.techtown.onelinediary_photo.data.WeatherResult;

public class MainActivity extends AppCompatActivity
        implements OnTabItemSelectedListener, OnRequestListener, AutoPermissionsListener
                    , MyApplication.OnResponseListener{

    Fragment1 fragment1;
    Fragment2 fragment2;
    Fragment3 fragment3;

    // 위치 기능
    // 현재 위치 정보
    Location currentLocation;
    // 위치 정보 수신
    GPSListener gpsListener;

    // 위치를 한 번 확인한 후 위치 요청을 취소할 수 있도록 위치 정보를 확인한 횟수를 기록
    int locationCount = 0;

    // 하단 탭을 사용하기 위한 하단 네비게이션 변수 선언
    BottomNavigationView bottomNavigationView;

    // 현재 날짜
    Date currentDate;
    String currentDateString;

    // 현재 위치 날씨
    String currentWeather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragment1 = new Fragment1();
        fragment2 = new Fragment2();
        fragment3 = new Fragment3();

        /* 하단 탭 */
        // FragmentManager 객체를 통해 첫 번째 프레임 레이아웃에 fragment1 추가함.
        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment1).commit();

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                        int id = menuItem.getItemId();
                        // 선택된 탭에 따라 지정된 화면 띄우기
                        switch (id){
                            case R.id.tab1:
                                getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment1).commit();
                                Toast.makeText(getApplicationContext(), "첫 번째 탭 선택됨", Toast.LENGTH_SHORT).show();
                                return true;

                            case R.id.tab2:
                                getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment2).commit();
                                Toast.makeText(getApplicationContext(), "두 번째 탭 선택됨", Toast.LENGTH_SHORT).show();
                                return true;

                            case R.id.tab3:
                                getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment3).commit();
                                Toast.makeText(getApplicationContext(), "세 번째 탭 선택됨", Toast.LENGTH_SHORT).show();
                                return true;
                        }
                        return false;
                    }
                }
        );

        AutoPermissions.Companion.loadAllPermissions(this, 101);
    }

    @Override
    public void onTabSelected(int position) {
        if(position == 0){
            bottomNavigationView.setSelectedItemId(R.id.tab1);
        } else if(position == 1){
            bottomNavigationView.setSelectedItemId(R.id.tab2);
        } else if(position == 2){
            bottomNavigationView.setSelectedItemId(R.id.tab3);
        }
    }







    /* 위치 기능 */
    @Override
    public void onRequest(String command) {
        if(command != null){
            if(command.equals("getCurrentLocation")){
                getCurrentLocation();
            }
        }
    }


    public void getCurrentLocation() {
        currentDate = new Date();
        currentDateString = AppConstants.dateFormat3.format(currentDate);

        if(fragment2 != null){
            fragment2.setDateString(currentDateString);
        }

        // LocationManager 객체 참조하기
        // 상수 LOCATION_SERVICE로 서비스 객체를 참조
        LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        try{
            // 이전에 확인했던 위치 정보 가져오기(최근 위치 정보 확인)
            // 매니페스트에 ACCESS_FINE_LOCATION 필요 권한 추가하기
            currentLocation = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

            if(currentLocation != null){
                // 위도와 경도 가져오기
                double latitude = currentLocation.getLatitude();
                double longitude = currentLocation.getLongitude();

                String message = "Last Location -> Latitude : " + latitude + "\nLongitude : " + longitude;
                println(message);

                getCurrentWeather();
                getCurrentAddress();
            }

            // 위치 리스너 객체 생성
            gpsListener = new GPSListener();
            long minTime = 10000; // 최소 시간 : 10초
            float minDistance = 0; // 최소 거리 : 0

            manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, minTime, minDistance, gpsListener);
            println("Current location requested.");

        }catch (SecurityException e){
            e.printStackTrace();
        }
    }

    public void stopLocationService(){
        LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        try{
            manager.removeUpdates(gpsListener);
            println("Current location requested");
        } catch (SecurityException e){
            e.printStackTrace();
        }
    }

    @Override
    public void onDenied(int requestCode, @NotNull String[] permissions) {
        Toast.makeText(this, "permissions denied : " + permissions.length, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onGranted(int requestCode, @NotNull String[] permissions) {
        Toast.makeText(this, "permissions granted : " + permissions.length, Toast.LENGTH_LONG).show();
    }

    @Override
    public void processResponse(int requestCode, int responseCode, String response) {

    }

    class GPSListener implements LocationListener{

        @Override
        public void onLocationChanged(Location location) {
            currentLocation = location;

            locationCount++;

            Double latitude = location.getLatitude();
            Double longitude = location.getLongitude();

            String message = "Last Location -> Latitude : " + latitude + "\nLongitude : " + longitude;
            println(message);

            getCurrentWeather();
            getCurrentAddress();
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) { }

        @Override
        public void onProviderEnabled(String provider) { }

        @Override
        public void onProviderDisabled(String provider) { }
    }

    // 현재 위치를 이용해 주소를 확인하기 위한 메소드
    private void getCurrentAddress() {
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        List<Address> addresses = null;
    }

    // 현재 위치를 이용하여 날씨 정보 가져오는 메소드
    private void getCurrentWeather() {

        // GtidUtil 클래스의 getGrid 메소드는 날씨 정보를 확인하고 싶은 지역의 좌표를 경위도 좌표에서 격자 번호로 변환해준다
        // 기상청에서 제공하는 날씨 데이터는 지역 정보를 격자 번호로 제공하고
        // 우리가 확인한 지역 정보는 경위도 좌표로 보이기 때문에 격자 번호로 변환과정이 필요하다.
        Map<String, Double> gridMap = GridUtil.getGrid(currentLocation.getLatitude(), currentLocation.getLongitude());

        double gridX = gridMap.get("x");
        double gridY = gridMap.get("y");

        println("x -> " + gridX + "y -> " + gridY);

        sendLocalWeatherReq(gridX, gridY);

    }

    // 기상청 날씨 서버로 요청을 전송함
    public void sendLocalWeatherReq(double gridX, double gridY){
        String url = "http://www.kma.go.kr/wid/queryDFS.jsp";
        url += "?gridx=" + Math.round(gridX);
        url += "?gridy=" + Math.round(gridY);

        Map<String, String> params = new HashMap<String, String>();

        MyApplication.send(AppConstants.REQ_WEATHER_BY_GRID, Request.Method.GET, url, params, this);
    }

    // 기상청에서 응답을 받으면 호출됨
    public void progressResponse(int requestCode,  int responseCode, String response){
        if(responseCode == 200){
            if(requestCode == AppConstants.REQ_WEATHER_BY_GRID){

                XmlParserCreator parserCreator = new XmlParserCreator() {
                    @Override
                    public XmlPullParser createParser() {
                        try{
                            return XmlPullParserFactory.newInstance().newPullParser();
                        }catch (Exception e){
                            throw new RuntimeException(e);
                        }
                    }
                };

                GsonXml gsonXml = new GsonXmlBuilder()
                        .setXmlParserCreator(parserCreator)
                        .setSameNameLists(true)
                        .create();

                WeatherResult weather = gsonXml.fromXml(response, WeatherResult.class);

                try {
                    Date tmDate = AppConstants.dateFormat.parse(weather.header.tm);
                    String tmDateText = AppConstants.dateFormat2.format(tmDate);
                    println("기준 시간 : " + tmDateText);

                    for (int i = 0; i < weather.body.datas.size(); i++) {
                        WeatherItem item = weather.body.datas.get(i);
                        println("#" + i + " 시간 : " + item.hour + "시, " + item.day + "일째");
                        println("  날씨 : " + item.wfKor);
                        println("  기온 : " + item.temp + " C");
                        println("  강수확률 : " + item.pop + "%");

                        println("debug 1 : " + (int) Math.round(item.ws * 10));
                        float ws = Float.valueOf(String.valueOf((int) Math.round(item.ws * 10))) / 10.0f;
                        println("  풍속 : " + ws + " m/s");
                    }

                    WeatherItem item = weather.body.datas.get(0);
                    currentWeather = item.wfKor;

                    if(fragment2 != null){
                        // fragment2.setWeather(item.wfKor);
                    }

                    if(locationCount > 0){
                        stopLocationService();
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }
            }else{
                println("Unknown request code : " + requestCode);
            }
        }else{
            println("Failure response code : " + responseCode);
        }
    }

    private void println(String data) {
        Log.d("MainActivity", data);
    }
}