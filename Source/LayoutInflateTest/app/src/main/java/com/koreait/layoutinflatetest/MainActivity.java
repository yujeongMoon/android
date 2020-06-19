package com.koreait.layoutinflatetest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    // 새로 만든 레이아웃(layout1.xml, layout2.xml, layout3.xml)을 전개할 LinearLayout 객체를 선언한다.
    LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 새로 만든 레이아웃을 적용시킬 빈 LinearLayout을 얻어온다.
        layout = findViewById(R.id.layout);
    }

    public void addLayout(View view) {
        // 레이아웃 파일 추가하기 : layout 폴더 우클릭 -> New -> Layout resource file 클릭
        int layoutId = 0; // 전개할 레이아웃의 id를 저장하는 변수

        // 어떤 버튼에서 addLayout() 메소드가 실행되었나 판단해서 레이아웃을 전개한다.
        int id = view.getId(); // 클릭한 버튼의 아이디를 얻어옴.

        switch (id){
            case R.id.btn1:
                layoutId = R.layout.layout1;
                break;
            case R.id.btn2:
                layoutId = R.layout.layout2;
                break;
            case R.id.btn3:
                layoutId = R.layout.layout3;
                break;
        }

        // 레이아웃을 전개하기 위해 레이아웃 전개자 LayoutInflater 객체를 생성한다.

        // 첫번째 방법
        // LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);

        // 두번재 방법
        LayoutInflater inflater = getLayoutInflater();

        // 레이아웃을 전개하기 전에 기존 레이아웃에 표시되던 내용을 제거해야 한다.
        // 제거 후 전개하기 때문에 같은 내용이 반복되지 않는다.
        layout.removeAllViews();

        // 레이아웃 전개자 객체에서 inflate() 메소드로 생성한 레이아웃을 전개한다.
        // inflate(전개할 레이아웃의 id, 레이아웃이 전개될 레이아웃의 객체, true);
        inflater.inflate(layoutId,layout,true);
    }
}
