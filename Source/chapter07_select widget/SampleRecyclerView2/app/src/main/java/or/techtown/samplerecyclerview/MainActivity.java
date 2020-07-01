package or.techtown.samplerecyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);

        GridLayoutManager manager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(manager);

        final PersonAdapter adapter = new PersonAdapter();
        adapter.addItem(new Person("이름1", "010-1"));
        adapter.addItem(new Person("이름2", "010-2"));
        adapter.addItem(new Person("이름3", "010-3"));
        adapter.addItem(new Person("이름4", "010-4"));
        adapter.addItem(new Person("이름5", "010-5"));
        adapter.addItem(new Person("이름6", "010-6"));
        adapter.addItem(new Person("이름7", "010-7"));
        adapter.addItem(new Person("이름8", "010-8"));
        adapter.addItem(new Person("이름9", "010-9"));
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new OnPersonItemClickListener() {
            @Override
            public void onItemClick(PersonAdapter.ViewHolder holder, View view, int position) {
                Person item = adapter.getItem(position);
                Toast.makeText(getApplicationContext(), "아이템 선택됨 : " + item.getName(),Toast.LENGTH_LONG).show();
            }
        });
    }
}