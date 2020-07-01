package or.techtown.doitmission_14;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ShopListAdapter extends RecyclerView.Adapter<ShopListAdapter.ViewHolder>{
    ArrayList<ShopList> items = new ArrayList<ShopList>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.shopping_item,parent,false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ShopList item = items.get(position);
        holder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(ShopList item){
        items.add(item);
    }

    public void setItems(ArrayList<ShopList> items){
        this.items = items;
    }

    public ShopList getItem(int position){
        return items.get(position);
    }

    public void setItem(int position, ShopList item){
        items.add(position, item);
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView tv_itemName, tv_price, tv_itemInfo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.image);
            tv_itemName =itemView.findViewById(R.id.tv_itemName);
            tv_price = itemView.findViewById(R.id.tv_price);
            tv_itemInfo =itemView.findViewById(R.id.tv_itemInfo);
        }

        public void setItem(ShopList item){
            image.setImageResource(item.getResId());
            tv_itemName.setText(item.getItemName());
            tv_price.setText(item.getPrice());
            tv_itemInfo.setText(item.getItemInfo());
        }
    }
}
