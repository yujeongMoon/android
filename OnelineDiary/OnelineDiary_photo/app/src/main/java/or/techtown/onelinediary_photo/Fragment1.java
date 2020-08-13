package or.techtown.onelinediary_photo;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import lib.kingja.switchbutton.SwitchMultiButton;

public class Fragment1 extends Fragment {

    RecyclerView recyclerView;
    NoteAdapter adapter;

    Context context;

    // 하단 탭 관련 인터페이스
    OnTabItemSelectedListener listener;

    @Override
    // 프래그먼트가 액티비티 위에 올라갈 때 호출됨.
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        this.context = context;

        if(context instanceof OnTabItemSelectedListener){
            listener = (OnTabItemSelectedListener) context;
        }
    }

    @Override
    // 프래그먼트가 액티비티에서 내려올 때 호출됨.
    public void onDetach() {
        super.onDetach();

        if(context != null){
            context = null;
            listener = null;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_1, container, false);

        initUI(rootView);

        return rootView;
    }

    // 인플레이션 후에 XML 레이아웃 안에 들어 잇는 위젯이나 레이아웃을 찾아
    // 변수에 할당하는 코드를 넣기 위한 메소드
    private void initUI(ViewGroup rootView){
        Button todayWriteButton = rootView.findViewById(R.id.todayWriteButton);
        todayWriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener != null){
                    // 작성 페이지 선택
                    listener.onTabSelected(1);
                }
            }
        });

        SwitchMultiButton switchButton = rootView.findViewById(R.id.switchButton);
        switchButton.setOnSwitchListener(new SwitchMultiButton.OnSwitchListener() {
            @Override
            public void onSwitch(int position, String tabText) {
                Toast.makeText(getContext(), tabText, Toast.LENGTH_LONG).show();

                adapter.switchLayout(position);

                adapter.notifyDataSetChanged();
            }
        });

        recyclerView = rootView.findViewById(R.id.recyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new NoteAdapter();

        adapter.addItem(new Note(0, "0", "강남구 삼성동", "","",
                "오늘 너무 행복해!", "3", "capture1.jpg", "8월 10일"));
        adapter.addItem(new Note(1, "1", "강남구 삼성동", "","",
                "친구와 재미있게 놀았어", "3", "capture1.jpg", "8월 11일"));
        adapter.addItem(new Note(2, "0", "강남구 역삼동", "","",
                "집에 왔는데 너무 피곤해", "0", "capture1.jpg", "8월 12일"));

        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new OnNoteItemClickListener() {
            @Override
            public void onItemClick(NoteAdapter.ViewHolder holder, View view, int position) {
               Note item = adapter.getItem(position);
               Toast.makeText(getContext(), "아이템 선택됨 (" + item.getContents() + ")", Toast.LENGTH_LONG).show();
            }
        });
    }
}