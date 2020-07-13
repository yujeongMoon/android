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
    MainHandler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv1 = findViewById(R.id.tv1);

        // 핸들러 객체 생성
        handler = new MainHandler();
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

                Message message = handler.obtainMessage();
                Bundle bundle = new Bundle();
                bundle.putInt("value", value);
                message.setData(bundle);

                handler.sendMessage(message);
            }

        }
    }

    class MainHandler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);

            Bundle bundle = msg.getData();
            int value = bundle.getInt("value");
            tv1.setText("value 값 : " + value);
        }
    }
}