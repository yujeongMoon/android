package com.koreait.datasendtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Main5Activity extends AppCompatActivity {

    TextView tv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        tv2 = findViewById(R.id.tv2);

        Intent intent = getIntent();

        // getParcelableExtra() : parcelable 인터페이스를 구현받아 직렬화된 클래스 객체를 받는다.
        // getParcelableExtra() 메소드는 parcelable 인터페이스를 구현받은 객체를 Serializable 인터페이스를 구현해 직렬화된 객체를 받을 때 처럼
        // 형변환 시키지 않아도 정상적으로 처리된다. => parcelable 인터페이스로 직렬화를 권장한다.
        // 만약, 에러가 발생된다면 형변환 시키면 된다.
        SimpleData simpleData = intent.getParcelableExtra("simpleData");
        tv2.setText(simpleData.toString());
    }

    public void goBack(View view) {

        finish();
    }
}
