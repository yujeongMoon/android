package com.koreait.lifecycletest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class Main2Activity extends AppCompatActivity {

    EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Log.e("LifeCycle", "Main2Activity onCreate()");

        et = findViewById(R.id.et);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("LifeCycle", "Main2Activity onPause()");

        // 데이터를 xml 파일로 안드로이드 기기에 저장한다. => 간단한 데이터 저장
        // 안드로이드는 앱이 종료되면 앱이 메모리에 가지고 있던 데이터가 사라지기 때문에 재 실행시 필요한 데이터라면
        // SharedPreferences 인터페이스 객체를 사용해 안드로이드 단말기 내부에 xml 파일 형태로 key와 value가 쌍으로
        // 구성되는 데이터를 저장할 수 있다. => Device File Explorer에 파일을 확인할 수 있다.

        // SharedPreferences는 Editor로 접근해서 데이터를 저장하고 SharedPreferences로 접근해서 데이터를 가져온다.
        // getSharedPreferences("xml 파일 이름",저장방법);
        // 저장 방법
        // MODE_PRIVATE => xml 파일을 새로 만들고 데이터를 저장한다.
        // MODE_APPEND => xml 파일이 존재할 경우 xml 파일에 데이터를 추가한다.
        // MODE_WORLD_READABLE => 다른 앱이 xml 파일을 읽을 수 있도록 허용한다.
        // MODE_WORLD_WRITEABLE => 다른 앱이 xml 파일을 쓸 수 있도록 허용한다.

        // LifeCycle.xml 파일을 새로 만들어 데이터를 저장할 수 있도록 준비한다.
        // 인터페이스(추상메소드를 가진 추상클래스 => 불안정함)이기 때문에 new 연산자를 사용해서 객체를 만들 수 없다.
        SharedPreferences sharedPreferences = getSharedPreferences("LifeCycle", MODE_PRIVATE);

        // LifeCycle.xml 파일에 데이터를 저장할 수 있도록 Editor를 이용해 편집할 수 있는 상태로 만든다.
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // LifeCycle.xml 파일에 데이터를 넣어준다. => 저장
        editor.putString("message","감사합니다.");

        // LifeCycle.xml 파일에 EditText에 입력한 데이터를 넣어준다.
        // editor.putString("data", String.valueOf(et.getText()));
        // editor.putString("data", et.getText() + "");
        editor.putString("data", et.getText().toString());

        // LifeCycle.xml 파일을 저장한다. => 작업 완료 => commit
        editor.commit();

        // 새로 만든 xml 파일 확인하는 법
        // Device File Explorer => data => data => 패키지 + 프로젝트명 => shared_prefs => xml 파일명
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("LifeCycle", "Main2Activity onResume()");

        // LifeCycle.xml 파일에서 데이터를 읽어와 복원한다.
        SharedPreferences sharedPreferences = getSharedPreferences("LifeCycle", MODE_PRIVATE);

        if(sharedPreferences != null && sharedPreferences.contains("message")){
            et.setText(sharedPreferences.getString("message","없어요"));
        }
    }

    public void goBack1(View view) {
        finish();
    }

    public void goBack2(View view) {
        SharedPreferences sharedPreferences = getSharedPreferences("LifeCycle", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        // LifeCycle.xml 파일의 message라는 name을 제거한다.
        // 실제로 name이 제거되는 것을 확인하려면 위의 onPause()를 주석처리하면 된다.

        editor.remove("message");
        editor.commit();

        finish();
    }
}
