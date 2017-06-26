package com.github.VP.A.About;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.TextView;

import com.github.B.BaseActivity;
import com.github.R;
import com.github.Util.UI.SwipeBackLayout;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by axnshy on 2017/6/24.
 */

public class AboutActivity extends BaseActivity<IAbout, AboutPresenter> implements IAbout ,SwipeBackLayout.OnSwipeListener{
    @BindView(R.id.tv_mail)
    TextView mail;

    @BindView(R.id.swipelayout)
    SwipeBackLayout swipeLayout;

    @OnClick({R.id.tv_mail})
    void onClick(View view){
        switch (view.getId()){
            case R.id.tv_mail:
                String e_mail = mail.getText().toString();
                Uri uri = Uri.parse("mailto:"+e_mail);
                Intent intent = new Intent(Intent.ACTION_SENDTO,uri);
                startActivity(intent);
        }
    }

    @Override
    public int layoutId() {
        return R.layout.activity_about;
    }

    @Override
    public AboutPresenter createPresenter() {
        return new AboutPresenter();
    }

    @Override
    public void initView(View view) {
        swipeLayout.addOnSwipeListener(this);
    }

    @Override
    public void onSwipe(int oritential) {
        onBackPressed();
        finish();
    }
}
