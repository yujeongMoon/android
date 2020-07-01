package or.techtown.doitmission_14;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    ShopListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        GridLayoutManager manager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(manager);

        adapter = new ShopListAdapter();
        adapter.addItem(new ShopList(R.drawable.clothes1, "아이템1", "1000원", "이 아이템은 1000원이다"));
        adapter.addItem(new ShopList(R.drawable.clothes2, "아이템2", "2000원", "이 아이템은 2000원이다"));
        adapter.addItem(new ShopList(R.drawable.clothes3, "아이템3", "3000원", "이 아이템은 3000원이다"));
        adapter.addItem(new ShopList(R.drawable.clothes4, "아이템4", "4000원", "이 아이템은 4000원이다"));
        adapter.addItem(new ShopList(R.drawable.clothes5, "아이템5", "5000원", "이 아이템은 5000원이다"));
        adapter.addItem(new ShopList(R.drawable.clothes6, "아이템6", "6000원", "이 아이템은 6000원이다"));
        adapter.addItem(new ShopList(R.drawable.clothes7, "아이템7", "7000원", "이 아이템은 7000원이다"));
        adapter.addItem(new ShopList(R.drawable.clothes8, "아이템8", "8000원", "이 아이템은 8000원이다"));

        recyclerView.setAdapter(adapter);
    }
}