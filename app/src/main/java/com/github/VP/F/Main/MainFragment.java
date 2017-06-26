package com.github.VP.F.Main;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.github.B.BaseFragment;
import com.github.R;
import com.github.VP.A.Search.SearchActivity;
import com.github.VP.F.RepoFragment.RepoFragment;
import com.github.VP.F.User.UserFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by axnshy on 2017/6/21.
 */

public class MainFragment extends BaseFragment<IMain, MainPresenter> implements IMain {

    /*@BindView(R.id.iv_return)
    ImageView returnIv;*/

    @BindView(R.id.tablayout)
    TabLayout tabLayout;

    @BindView(R.id.viewpager)
    ViewPager viewPager;

    SimplePagerAdapter<Fragment> ReAdapter;

    RecyclerView recyclerView;

    @BindView(R.id.llyt_toolbar)
    LinearLayout toolbar;

    @BindView(R.id.llyt_search)
    LinearLayout searchLayout;

    List<Fragment> fragments;

    @OnClick({R.id.llyt_search})
    void onClick(View v){
        switch (v.getId()){
            case R.id.llyt_search:
//                Toast.makeText(getContext(), "click to search", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.setClass(getContext(), SearchActivity.class);
                startActivity(intent);
                break;
        }
    }

    private static CharSequence[] tabName = {
            "Repository",
            "User",
    };

    @Override
    public MainPresenter createPresenter() {
        return new MainPresenter();
    }

    @Override
    public int layoutId() {
        return R.layout.fragment_main;
    }

    @Override
    public void initView(View view) {
//        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

//        returnIv.setVisibility(View.GONE);

        fragments = new ArrayList<>();
        fragments.add(RepoFragment.newInstance());
        fragments.add(UserFragment.newInstance());

        ReAdapter = new SimplePagerAdapter<>(getChildFragmentManager(),fragments);
        viewPager.setAdapter(ReAdapter);
        tabLayout.setupWithViewPager(viewPager, true);
        for (int i=0;i<tabName.length;i++) {
            tabLayout.getTabAt(i).setText(tabName[i]);
        }

    }

    @Override
    public void addTab(CharSequence name) {


        tabLayout.addTab(tabLayout.newTab().setText(name));
    }

    public static MainFragment newInstance() {
        return new MainFragment();
    }
}
