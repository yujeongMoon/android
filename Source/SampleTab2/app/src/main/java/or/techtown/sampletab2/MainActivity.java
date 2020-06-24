package or.techtown.sampletab2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    Fragment1 fragment1;
    Fragment2 fragment2;
    Fragment3 fragment3;
    BottomNavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragment1 = new Fragment1();
        fragment2 = new Fragment2();
        fragment3 = new Fragment3();

        getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment1);

        navigationView = findViewById(R.id.bottom_navigation);

        navigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                        switch(menuItem.getItemId()){
                            case R.id.tab1:
                                Toast.makeText(getApplicationContext(),"첫 번째 탭",Toast.LENGTH_SHORT).show();
                                getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment1).commit();
                                return true;

                            case R.id.tab2:
                                Toast.makeText(getApplicationContext(),"두 번째 탭",Toast.LENGTH_SHORT).show();
                                getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment2).commit();
                                return true;

                            case R.id.tab3:
                                Toast.makeText(getApplicationContext(),"세 번째 탭",Toast.LENGTH_SHORT).show();
                                getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment3).commit();
                                return true;
                        }
                        return false;
                    }
                }
        );

    }
}