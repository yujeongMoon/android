package or.techtown.sampleservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("MyService", "onCreate() 호출됨");
    }


    @Override
    public void onDestroy() {
        Log.e("MyService", "onDestroy() 호출됨");

        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("MyService", "onStartCommand() 호출됨");

        if(intent == null){
            return Service.START_STICKY;
        } else {
            processCommand(intent);
        }

        return super.onStartCommand(intent, flags, startId);
    }

    private void processCommand(Intent intent){
        String command = intent.getStringExtra("command");
        String name = intent.getStringExtra("name");

        Log.e("MyService", "command : " + command + ", name : " + name);

        for(int i = 0; i < 5; i++){
            try{
                Thread.sleep(1000);
            } catch (Exception e){ };

            Log.e("MyService", "Waiting " + i + "seconds.");
        }

        Intent showIntent = new Intent(getApplicationContext(), MainActivity.class);

        showIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                            Intent.FLAG_ACTIVITY_SINGLE_TOP|
                            Intent.FLAG_ACTIVITY_CLEAR_TOP);
        showIntent.putExtra("command", "command");
        showIntent.putExtra("name", name + " from service.");

        startActivity(showIntent);
    }
}
