package or.techtown.samplelayout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Layout1 layout1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layout1 = findViewById(R.id.layout1);

        layout1.setImage(R.drawable.profile1);
        layout1.setName("문유정");
        layout1.setMobile("010-1234-4567");

    }

    public void firstImage(View view) {
        layout1.setImage(R.drawable.profile1);
    }

    public void secondImage(View view) {
        layout1.setImage(R.drawable.profile2);
    }
}