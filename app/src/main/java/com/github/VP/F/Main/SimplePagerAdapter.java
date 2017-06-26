package com.github.VP.F.Main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by axnshy on 2017/6/21.
 */

public class SimplePagerAdapter<T extends Fragment> extends FragmentPagerAdapter{

    List<T> data;

    public SimplePagerAdapter(FragmentManager fm,List<T> data) {
        super(fm);
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Fragment getItem(int i) {
        return data.get(i);
    }

    @Override
    public CharSequence getPageTitle(int position) {

        return super.getPageTitle(position);
    }
}
