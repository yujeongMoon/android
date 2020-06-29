package or.techtown.samplerecyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);

        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);

        PersonAdapter adapter = new PersonAdapter();
        adapter.addItem(new Person("이름1", "010-1234-5678"));
        adapter.addItem(new Person("이름2", "010-5678-1234"));
        adapter.addItem(new Person("이름3", "010-7895-5544"));
        recyclerView.setAdapter(adapter);
    }
}