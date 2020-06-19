package com.koreait.lifecycletest;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    // onCreate() : 액티비티가 최초로 실행될 때 딱 1번만 호출된다.
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.e("LifeCycle", "MainActivity onCreate()");
        // Toast.makeText(getApplicationContext(),"MainActivity onCreate()",Toast.LENGTH_SHORT).show();
        // 안드로이드 앱을 개발할 때 변수나 객체에 저장된 중간 결과를 보고 싶을 때 Log를 사용한다.
        // Log 클래스는 Logcat에 메세지를 출력하는 안드로이드의 유틸리티 클래스이고 다음과 같은 메소드를 사용할 수 있다.
        // v(String, String) : verbose
        // d(String, String) : debug
        // i(String, String) : information
        // w(String, String) : warning
        // e(String, String) : error
    }

    @Override
    // onStart() : 액티비티가 사용자에게 보여지기 직전에 실행된다.
    // 액티비티가 최초로 실행될 때 도는 다른 액티비티가 종료되서 현재 액티비티가 다시 화면에 나타날 때
    protected void onStart() {
        super.onStart();
        Log.e("LifeCycle", "MainActivity onStart()");
    }

    @Override
    // onResume() : 액티비티가 사용자와 상호 작용을 하기 직전에 실행된다.
    protected void onResume() {
        super.onResume();
        Log.e("LifeCycle", "MainActivity onResume()");
    }

    @Override
    // onPause() : 다른 액티비티가 실행되서 현재 액티비티를 가리기 시작하거나 현재 액티비티가 종료되기 시작하는 순간 실행된다.
    // 현재 액티비티가 안보이는 순간부터 실행
    protected void onPause() {
        super.onPause();
        Log.e("LifeCycle", "MainActivity onPause()");
    }

    @Override
    // onStop() : 다른 액티비티가 실행되서 현재 액티비티를 완전히 가려서 더 이상 보이지 않거나 현재 액티비티가 종료되서
    // 더 이상 화면에 보이지 않는 순간 실행된다.
    protected void onStop() {
        super.onStop();
        Log.e("LifeCycle", "MainActivity onStop()");
    }

    @Override
    // onDestroy() : 프로그램이 완전히 종료될 때 딱 1번 실행된다.
    protected void onDestroy() {
        super.onDestroy();
        Log.e("LifeCycle", "MainActivity onDestroy()");
    }

    @Override
    // onRestart() : 현재 액티비티가 중지(onStop())되고 난 후 다시 화면에 나타날 때 실행된다.
    protected void onRestart() {
        super.onRestart();
        Log.e("LifeCycle", "MainActivity onRestart()");
    }

    public void viewActivity(View view) {
        Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
        startActivity(intent);
    }
}
