package com.koreait.datasendtest;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void dataSend(View view) {
        int id = view.getId();

        switch (id){
            case R.id.btn1:
                Intent intent1 = new Intent(getApplicationContext(), Main2Activity.class);

                // 다른 액티비티로 데이터를 전송하려면 데이터를 전송할 액티비티를 띄워줄 Intent를 만들고
                // putExtra() 메소드로 Intent에 데이터를 저장해서 넘겨준다.
                // putExtra("name", value) : value를 name에 저장해서 intent 객체에 넣어준다.
                intent1.putExtra("name", "홍길동");
                intent1.putExtra("age", 20);
                intent1.putExtra("count", ++count);

                startActivity(intent1); //-> 새로운 액티비티를 열어주는 역할만 함.
                //startActivityForResult(intent1, 10);
                // 액티비티를 열어주기도 하고 닫힐 때 어느 액티비티에서 결과가 돌아오는지 알 수 있도록 requestcode(마음대로 지정)를 사용함.
                break;
            case R.id.btn2:
                Intent intent2 = new Intent(getApplicationContext(), Main3Activity.class);
                // 아래와 같이 startActivity() 메소드를 사용해 다른 액티비티를 실행하면 실행된 액티비티가 닫힐 때 Intent에
                // 저장되서 돌아오는 데이터를 받을 수 없다. => 액티비티는 띄울 수 있지만 결과는 받을 수 없다.
                // startActivity(intent2);

                // 액티비티가 닫힐 때 닫히는 액티비티에서 Intent에 저장시킨 데이터를 받으려면 startActivitiyForResult() 메소드를
                // 사용해야 한다.
                // startActivityForResult(화면에 표시할 Intent 객체, 식별번호);
                // 식별 번호를 사용하는 이유는 현재 액티비티에서 여러 액티비티를 호출할 수 있고 호출된 여러 액티비티가 닫힐 때
                // 현재 액티비티로 데이터를 전송한다면 어떤 액티비티가 닫혔는가 파악하기 위해서 사용한다.
                startActivityForResult(intent2, 20);

                // startActivityForResult() 메소드를 사용해서 액티비티를 실행한 경우 실행된 액티비티에서 finish() 메소드가
                // 실행되면 자동으로 실행되는(콜백) 메소드 OnActivityResult()가 실행된다.
                break;
            case R.id.btn3:
                Intent intent3 = new Intent(getApplicationContext(), Main4Activity.class);
                Person person = new Person("홍길동", 20, true);
                // 클래스 객체를 Intent에 담아서 전송하려면 클래스를 Serializable 인터페이스나 Parcelable 인터페이스를
                // 구현 받아서 직렬화 시켜야 한다.
                // putExtra() 메소드로 Intent에 클래스의 객체를 저장할 때 직렬화가 되어있는 객체만 저장할 수 있다.
                intent3.putExtra("person", person);
                startActivity(intent3);
                break;
            case R.id.btn4:
                Intent intent4 = new Intent(getApplicationContext(), Main5Activity.class);
                SimpleData simpleData = new SimpleData("문유정", 26, false);
                intent4.putExtra("simpleData", simpleData);
                startActivity(intent4);
                break;
        }
    }

    // 이클립스에서 마우스 우클릭 => Source 메뉴에서 실행하는 기능의 안드로이드 스튜디오 단축키는 alt + insert 이다.
    // onActivityResult() 메소드는 startActivityForResult() 메소드를 사용햐 호출한 액티비티에서 finish() 메소드가 실행되면
    // 자동으로 호출되는 콜백 메소드
    // onActivityResult() 메소드의 인수
    // int requestCode : startActivityForResult() 메소드의 2번째 인수로 지정한 식별 번호를 받는다.
    // int resultCode : startActivityForResult() 메소드로 호출된 액티비티의 setResult() 메소드의 1번째 인수를 받는다.
    // @Nullable Intent data : startActivityForResult() 메소드로 호출된 액티비티의 setResult() 메소드의 2번째 인수를 받는다.(데이터)
    // resultCode는 startActivityForResult() 메소드로 호출된 액티비티에서 되돌려주는 상태 코드이고
    // data는 startActivityForResult() 메소드로 호출된 액티비티에서 넘어오는 데이터가 저장된 Intent 객체이다.

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Toast.makeText(getApplicationContext(),""+requestCode,Toast.LENGTH_SHORT).show();
        // requestCode만 하면 에러가 나는 이유는 Toast는 문자열을 나타내기 때문에 ""을 추가해 문자로 만든다.

        // requestCode를 이용해서 어떤 액티비티에서 결과가 넘어왔나 판단해서 적절한 작업을 한다.
        switch (requestCode){
            case 20:
                // 어떤 액티비티에서 결과가 넘어왔나 판단했다면 resultCode를 이용해서 결과가 넘어온 상태를 파악하고
                // 적절한 작업을 한다.

                // startActivityForResult() 메소드로 호출된 액티비티에서 Intent 객체에 저장되어 넘어온 데이터를 받는다.
                String name = data.getStringExtra("name");
                int age = data.getIntExtra("age", 0);

                switch (resultCode){
                    case 0: // 이름, 나이 아무것도 넘어오지 않은 상태
                        Toast.makeText(getApplicationContext(),"넘어온 데이터가 없습니다.",Toast.LENGTH_SHORT).show();
                        break;
                    case 1: // 이름만 넘어온 상태
                        Toast.makeText(getApplicationContext(), name + "님 어서오세요.", Toast.LENGTH_SHORT).show();
                        break;
                    case 2: // 나이만 넘어온 상태
                        Toast.makeText(getApplicationContext(), age + "살 입니다.", Toast.LENGTH_SHORT).show();
                        break;
                    case 3: // 이름, 나이 둘 다 넘어온 상태
                        Toast.makeText(getApplicationContext(), name + "님 어서오세요. " + age + "살 입니다.", Toast.LENGTH_SHORT).show();
                        break;
                }
                break;
        }
    }
}
