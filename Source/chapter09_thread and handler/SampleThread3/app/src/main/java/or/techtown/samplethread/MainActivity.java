package or.techtown.samplethread;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    int value = 0;
    TextView tv1;

    // API 기본 헨들러 객체 생성
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv1 = findViewById(R.id.tv1);
    }

    public void StartThread(View view) {
        BackgroundThread thread = new BackgroundThread();
        thread.start();
    }

    class BackgroundThread extends Thread{
        public void run(){
            for(int i = 0; i < 100; i++){
                try {
                    Thread.sleep(1000);
                }catch (Exception e){ }
                
                value += 1;
                Log.d("Thread", "value = " + value);

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        tv1.setText("value 값 : " + value);
                    }
                });
            }

        }
    }
}