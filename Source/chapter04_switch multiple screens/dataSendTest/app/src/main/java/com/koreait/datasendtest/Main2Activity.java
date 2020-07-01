package com.koreait.datasendtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        tv = findViewById(R.id.tv);

        // getIntent() 메소드로 이전 액티비티에서 Intent 객체에 저장해서 넘겨주는 데이터를 받는다.
        Intent intent = getIntent();
        // Intent 객체에 저장되서 넘어온 데이터를 받으려면 get자료형Extra() 메소드를 사용한다.
        String name = intent.getStringExtra("name");
        // if(name == null){
        //    name = "이름이 넘어오지 않았습니다.";
        // 예전 버전에서는 null일 경우 예외가 발생하였기 때문에 이와 같이 예외처리가 필요했었다.
        // }

        // getStringExtra() 메소드 이외의 메소드들은 넘어오는 데이터가 없을 경우 사용할 기본값을 두 번째 인수로 지정해야한다.

        int age = intent.getIntExtra("age",26);
        int count = intent.getIntExtra("count",0);

        String str = "이름은 " + name + ", 나이는 " + age + "살입니다. " + count + "번째 메세지입니다.";
        tv.setText(str);
    }

    public void goBack(View view) {
        finish();
    }
}
