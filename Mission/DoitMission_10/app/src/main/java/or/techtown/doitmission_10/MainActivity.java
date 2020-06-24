package or.techtown.doitmission_10;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, FragmentCallback {

    Toolbar toolbar;

    Fragment1 fragment1;
    Fragment2 fragment2;
    Fragment3 fragment3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);

        DrawerLayout drawer = findViewById(R.id.layout_drawer);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,drawer,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        fragment1 = new Fragment1();
        getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment1).commit();

        fragment2 = new Fragment2();
        fragment3 = new Fragment3();

        BottomNavigationView bottomNavigationView = findViewById(R.id.nav_bottom);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                        switch(menuItem.getItemId()){
                            case R.id.tab1:
                                getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment1).commit();
                                return true;

                            case R.id.tab2:
                                getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment2).commit();
                                return true;

                            case R.id.tab3:
                                getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment3).commit();
                                return true;
                        }
                        return false;
                    }
                }
        );
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.layout_drawer);
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.END);
        } else{
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();

        if(id == R.id.menu1){
            Toast.makeText(getApplicationContext(),"첫 번째 화면",Toast.LENGTH_SHORT).show();
            onFragmentSelected(0,null);
        } else if(id == R.id.menu2){
            Toast.makeText(getApplicationContext(),"두 번째 화면",Toast.LENGTH_SHORT).show();
            onFragmentSelected(1,null);
        } else if(id == R.id.menu3){
            Toast.makeText(getApplicationContext(),"세 번째 화면",Toast.LENGTH_SHORT).show();
            onFragmentSelected(2,null);
        }

        DrawerLayout drawer = findViewById(R.id.layout_drawer);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentSelected(int position, Bundle bundle) {
        Fragment curFragment = null;

        if(position == 0){
            curFragment = fragment1;
            toolbar.setTitle("첫 번째 화면");
        } else if(position == 1){
            curFragment = fragment2;
            toolbar.setTitle("두 번째 화면");
        } else if(position == 2){
            curFragment = fragment3;
            toolbar.setTitle("세 번째 화면");
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.container,curFragment).commit();

    }
}

