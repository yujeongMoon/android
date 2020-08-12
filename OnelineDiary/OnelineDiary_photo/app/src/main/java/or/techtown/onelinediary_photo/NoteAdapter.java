package or.techtown.onelinediary_photo;

import android.animation.LayoutTransition;
import android.net.Uri;
import android.text.style.AlignmentSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder>
                         implements OnNoteItemClickListener{

    ArrayList<Note> items = new ArrayList<Note>();

    OnNoteItemClickListener listener;

    int layoutType = 0;

    @NonNull
    @Override
    // 뷰 객체가 만들어질 때
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // 인플레이션을 통해 뷰 객체 만들기
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.note_item, parent, false);

        return new ViewHolder(itemView, this, layoutType);
    }

    @Override
    // 뷰 객체가 재사용될 때(데이터만 바꿔줌)
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Note item = items.get(position);
    }

    @Override
    // 어댑터가 관리하는 아이템 개수
    public int getItemCount() {
        return items.size();
    }

    public void addItem(Note item){
        items.add(item);
    }

    public void setItems(ArrayList<Note> items){
        this.items = items;
    }

    public Note getItem(int position){
        return items.get(position);
    }

    public void setOnItemClickListener(OnNoteItemClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onItemClick(ViewHolder holder, View view, int position) {
        if(listener != null){
            listener.onItemClick(holder, view, position);
        }
    }

    public void switchLayout(int position){
        layoutType = position;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        /* 사진 또는 내용 레이아웃 */
        LinearLayout layout1;
        LinearLayout layout2;

        /* 기분 아이콘 */
        ImageView moodImageView;
        ImageView moodImageView2;

        /* 사진 포함 여부 이미지 및 사진 레이아웃 중심 사진 */
        ImageView pictureExistsImageView;
        ImageView pictureImageView;

        /* 날씨 이미지 */
        ImageView weatherImageView;
        ImageView weatherImageView2;

        /* 내용 */
        TextView contentsTextView;
        TextView contentsTextView2;

        /* 위치 */
        TextView locationTextView;
        TextView locationTextView2;

        /* 작성 일자 */
        TextView dateTextView;
        TextView dateTextView2;

        public ViewHolder(@NonNull View itemView, final OnNoteItemClickListener listener, int layoutType) {
            super(itemView);

            /* 사진 또는 내용 레이아웃 */
            layout1 = itemView.findViewById(R.id.layout1);
            layout2 = itemView.findViewById(R.id.layout2);

            /* 기분 아이콘 */
            moodImageView = itemView.findViewById(R.id.moodImageView);
            moodImageView2 = itemView.findViewById(R.id.moodImageView2);

            /* 사진 포함 여부 이미지 및 사진 레이아웃 중심 사진 */
            pictureExistsImageView = itemView.findViewById(R.id.pictureExistsImageView);
            pictureImageView = itemView.findViewById(R.id.pictureImageView);

            /* 날씨 이미지 */
            weatherImageView = itemView.findViewById(R.id.weatherImageView);
            weatherImageView2 = itemView.findViewById(R.id.weatherImageView2);

            /* 내용 */
            contentsTextView = itemView.findViewById(R.id.contentsTextView);
            contentsTextView2 = itemView.findViewById(R.id.contentsTextView2);

            /* 위치 */
            locationTextView = itemView.findViewById(R.id.locationTextView);
            locationTextView2 = itemView.findViewById(R.id.locationTextView2);

            /* 작성 일자 */
            dateTextView = itemView.findViewById(R.id.dateTextView);
            dateTextView2 = itemView.findViewById(R.id.dateTextView2);

            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();

                    if(listener != null){
                        listener.onItemClick(ViewHolder.this, v, position);
                    }
                }
            });
            setLayoutType(layoutType);
        }

        public void setItem(Note item){

            // 기분 이모티콘 설정
            String mood = item.getMood();
            int moodIndex = Integer.parseInt(mood);
            setMoodImage(moodIndex);

            // 사진 보여주기
            String picturePath = item.getPicture();
            if(picturePath != null && !picturePath.equals("")) {
                pictureExistsImageView.setVisibility(View.VISIBLE);
                pictureImageView.setVisibility(View.VISIBLE);
                pictureImageView.setImageURI(Uri.parse("file://" + picturePath));
            } else{
                pictureExistsImageView.setVisibility(View.GONE);
                pictureImageView.setVisibility(View.GONE);
                pictureImageView.setImageResource(R.drawable.noimagefound);
            }

            // 날씨 이모티콘 설정
            String weather = item.getWeather();
            int weatherIndex = Integer.parseInt(weather);
            setWeatherImage(weatherIndex);

            contentsTextView.setText(item.getContents());
            contentsTextView2.setText(item.getContents());

            locationTextView.setText(item.getAddress());
            locationTextView2.setText(item.getAddress());

            dateTextView.setText(item.getCreateDateStr());
            dateTextView2.setText(item.getCreateDateStr());
        }

        // 기분 이모티콘 바꾸기
        public void setMoodImage(int moodImage){
            switch (moodImage){
                case 0:
                    moodImageView.setImageResource(R.drawable.smile1_48);
                    moodImageView2.setImageResource(R.drawable.smile1_48);
                    break;

                case 1:
                    moodImageView.setImageResource(R.drawable.smile2_48);
                    moodImageView2.setImageResource(R.drawable.smile2_48);
                    break;

                case 2:
                    moodImageView.setImageResource(R.drawable.smile3_48);
                    moodImageView2.setImageResource(R.drawable.smile3_48);
                    break;

                case 3:
                    moodImageView.setImageResource(R.drawable.smile4_48);
                    moodImageView2.setImageResource(R.drawable.smile4_48);
                    break;

                case 4:
                    moodImageView.setImageResource(R.drawable.smile5_48);
                    moodImageView2.setImageResource(R.drawable.smile5_48);
                    break;
            }
        }

       // 날씨 이모티콘 바꾸기
       public void setWeatherImage(int weatherIndex){
            switch (weatherIndex) {
                case 0:
                    weatherImageView.setImageResource(R.drawable.weather_icon_1);
                    weatherImageView2.setImageResource(R.drawable.weather_icon_1);
                    break;

                case 1:
                    weatherImageView.setImageResource(R.drawable.weather_icon_2);
                    weatherImageView2.setImageResource(R.drawable.weather_icon_2);
                    break;

                case 2:
                    weatherImageView.setImageResource(R.drawable.weather_icon_3);
                    weatherImageView2.setImageResource(R.drawable.weather_icon_3);
                    break;

                case 4:
                    weatherImageView.setImageResource(R.drawable.weather_icon_4);
                    weatherImageView2.setImageResource(R.drawable.weather_icon_4);
                    break;

                case 5:
                    weatherImageView.setImageResource(R.drawable.weather_icon_5);
                    weatherImageView2.setImageResource(R.drawable.weather_icon_5);
                    break;
            }
       }

       // 내용 또는 사진 레이아웃 변경하기
       public void setLayoutType(int layoutType){
            if(layoutType == 0){
                layout1.setVisibility(View.VISIBLE);
                layout2.setVisibility(View.GONE);
            } else if(layoutType == 1){
                layout1.setVisibility(View.GONE);
                layout2.setVisibility(View.VISIBLE);
            }
       }
    }
}
