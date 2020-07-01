package or.techtown.sampleservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText et1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1 = findViewById(R.id.et1);

        Intent passedIntent = getIntent();
        processIntent(passedIntent);
    }

    public void goService(View view) {
        String name = et1.getText().toString();

        Intent intent = new Intent(getApplicationContext(), MyService.class);
        intent.putExtra("command", "show");
        intent.putExtra("name", name);

        startService(intent);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        processIntent(intent);

        super.onNewIntent(intent);
    }

    private void processIntent(Intent intent) {
        if(intent != null){
            String command = intent.getStringExtra("command");
            String name = intent.getStringExtra("name");

            Toast.makeText(getApplicationContext(),"command : " + command +
                    ", name : " + name,Toast.LENGTH_SHORT).show();
        }
    }
}