package or.techtown.samplethread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    int value = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
            }

        }
    }
}