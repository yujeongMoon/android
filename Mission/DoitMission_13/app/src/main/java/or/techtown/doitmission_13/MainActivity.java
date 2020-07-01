package or.techtown.doitmission_13;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView tv_person;

    EditText et_name, et_birthday, et_phone;

    Button btn_addInfo;

    RecyclerView recyclerView;

    PersonAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_person = findViewById(R.id.tv_person);

        et_name = findViewById(R.id.et_name);
        et_birthday = findViewById(R.id.et_birthday);
        et_phone = findViewById(R.id.et_phone);
        tv_person.setText("2명");

        recyclerView = findViewById(R.id.recyclerView);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);

        adapter = new PersonAdapter();
        adapter.addItem(new Person("이름1", "1995-05-05", "010-4567-1985"));
        adapter.addItem(new Person("이름2", "1995-05-06", "010-4457-1985"));

        recyclerView.setAdapter(adapter);

        btn_addInfo = findViewById(R.id.btn_addInfo);
        btn_addInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = et_name.getText().toString();
                String birthday = et_birthday.getText().toString();
                String phone = et_phone.getText().toString();

                adapter.addItem(new Person(name, birthday, phone));
                adapter.notifyDataSetChanged();

                tv_person.setText(adapter.getItemCount() + "명");
            }
        });
    }
}