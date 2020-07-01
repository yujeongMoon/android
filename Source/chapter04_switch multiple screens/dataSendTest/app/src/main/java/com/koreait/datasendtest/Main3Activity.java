package com.koreait.datasendtest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Main3Activity extends AppCompatActivity {

    EditText et1, et2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        et1 = findViewById(R.id.et1);
        et2 = findViewById(R.id.et2);
    }

    public void goBack(View view) {

        // EditText에 입력된 데이터를 Intent 객체에 담아 MainActivity로 넘겨주기 위해 입력된 데이터를 얻어온다.

        String name = et1.getText().toString();
        String age = String.valueOf(et2.getText());

        // resultCode를 설정한다.
        // 이름과 나이에 아무것도 입력되지 않았으면 resultCode = 0
        // 이름만 입력되었으면 resultCode = 1
        // 나이만 입력되었으면 resultCode = 2
        // 이름과 나이가 모두 입력되었으면 resultCode = 3

        int resultCode = 0;

        // trim()은 문자열에서 공백을 제거해줌.
        if(name != null && name.trim().length() > 0 && age != null && age.trim().length() > 0){
            resultCode = 3;
        } else if(name != null && name.trim().length() > 0){
            resultCode = 1;
        } else if(age != null && age.trim().length() > 0){
            resultCode = 2;
        }

        // 나이에 숫자가 입력되지 않았으면 정확한 나이가 입력되지 않은 것으로 가정하고 resultCode를 보정한다.
        int tempAge = 0;
        // 예외 처리
        try {
            // Integer.parseInt(age) : age에 저장된 문자열을 숫자로 변환해줌.
            tempAge = Integer.parseInt(age);
        }catch (NumberFormatException e){
            if(resultCode >= 2){
                resultCode -= 2;
            }
        }

        // MainActivity로 넘겨줄 데이터를 Intent 객체에 저장한다.
        Intent intent = new Intent();
        intent.putExtra("name", name);
        intent.putExtra("age", tempAge);


        //finish() 메소드가 실행되면 실행되는 콜백 메소드 onActivityResult() 메소드로 넘겨줄 데이터를
        // setResult() 메소드를 실행해서 resultCode와 넘겨줄 데이터가 저장된 Intent 객체를 넘겨준다.
        setResult(resultCode, intent);

        finish();
    }
}
