package or.techtown.onelinediary_photo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.pedro.library.AutoPermissions;
import com.pedro.library.AutoPermissionsListener;

import org.jetbrains.annotations.NotNull;

import java.util.Date;

public class MainActivity extends AppCompatActivity
        implements OnTabItemSelectedListener, OnRequestListener, AutoPermissionsListener {

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

    private void getCurrentAddress() {

    }

    private void getCurrentWeather() {

    }

    private void println(String data) {
        Log.d("MainActivity", data);
    }
}