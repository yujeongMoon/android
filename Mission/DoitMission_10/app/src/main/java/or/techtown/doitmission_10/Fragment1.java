package or.techtown.doitmission_10;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class Fragment1 extends Fragment {

    Fragment_image1 image1;
    Fragment_image2 image2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup rootview = (ViewGroup) inflater.inflate(R.layout.fragment_1, container, false);

        ViewPager pager = rootview.findViewById(R.id.pager);
        pager.setOffscreenPageLimit(3);

        MypagerAdapter adapter = new MypagerAdapter(getFragmentManager());

        image1 = new Fragment_image1();
        adapter.addItem(image1);
        image2 = new Fragment_image2();
        adapter.addItem(image2);

        pager.setAdapter(adapter);

        return rootview;
    }

    public class MypagerAdapter extends FragmentStatePagerAdapter {
        ArrayList<Fragment> items = new ArrayList<Fragment>();

        public MypagerAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        public void addItem(Fragment item){
            items.add(item);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return items.get(position);
        }

        @Override
        public int getCount() {
            return items.size();
        }
    }
}