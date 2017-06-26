package com.github.VP.A.Home;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;
import android.view.View;

import com.fileintersend.view.activity.FileInterSend.FileInterSendActivity;
import com.github.B.BaseActivity;
import com.github.R;
import com.github.VP.A.About.AboutActivity;
import com.github.VP.F.Main.MainFragment;

import butterknife.BindView;


public class MainActivity extends BaseActivity<IHome,HomePresenter> implements IHome{

    @BindView(R.id.navigation)
    NavigationView navigation;


    @Override
    public int layoutId() {
        return R.layout.activity_main;
    }

    @Override
    public HomePresenter createPresenter() {
        return new HomePresenter();
    }


    @Override
    public void initView(View view) {
        MainFragment main = MainFragment.newInstance();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.frame_main,main);
        ft.commit();
        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.about:
                        Intent intent = new Intent();
                        intent.setClass(MainActivity.this, AboutActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.exit:
                        System.exit(0);
                        break;
                    case R.id.file_fastsend:
                        startActivity(new Intent(MainActivity.this, FileInterSendActivity.class));
                        break;
                }
                return true;
            }
        });
    }

    @Override
    public void initData() {

    }



    @Override
    public void beforeInitView() {
    }
}
