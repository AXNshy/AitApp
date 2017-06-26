package com.github.B;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;

import com.github.B.I.IBaseView;

import butterknife.ButterKnife;


/**
 * Created by Administrator on 2016/11/27.
 */

public abstract class BaseActivity<V extends IBaseView, P extends BasePresenter<V>> extends AppCompatActivity
        implements IBaseView{

    protected P mPresenter;
    private int layoutId;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = createPresenter();
        mPresenter.attachView((V) this);
        setContentView(layoutId());
        ButterKnife.bind(this);

        if(Build.VERSION.SDK_INT>=19){
            getWindow().setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        beforeInitView();
        initView(null);
        initData();
    }

    @Override
    public void initView(View view) {

    }

    @Override
    public void beforeInitView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public int layoutId() {
        return 0;
    }

    public abstract P createPresenter();


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
        mPresenter=null;
    }

    protected void intentTo(Class<? extends Activity> clazz){
        Intent intent = new Intent();
        intent.setClass(this,clazz);
        startActivity(intent);
    }

    @Override
    public Context obtainContext() {
        return this;
    }

    @Override
    public Context obtainApplicationContext() {
        return getApplicationContext();
    }

}
