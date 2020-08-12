package or.techtown.onelinediary_photo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements OnTabItemSelectedListener{

    Fragment1 fragment1;
    Fragment2 fragment2;
    Fragment3 fragment3;

    // 하단 탭을 사용하기 위한 하단 네비게이션 변수 선언
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragment1 = new Fragment1();
        fragment2 = new Fragment2();
        fragment3 = new Fragment3();

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
}