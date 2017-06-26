package com.github.B;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.B.I.IBaseView;

import butterknife.ButterKnife;


/**
 * Created by Administrator on 2016/11/27.
 */

public abstract class BaseFragment<V extends IBaseView,P extends BasePresenter<V>>
        extends Fragment implements IBaseView{

    protected P mPresenter;
    int layoutId;


    @Override
    public int layoutId() {
        return 0;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initView(View view) {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public abstract P createPresenter();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(layoutId(), container, false);
        mPresenter = createPresenter();
        mPresenter.attachView((V) this);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
        mPresenter=null;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        beforeInitView();
        initView(view);
        initData();
    }

    @Override
    public void beforeInitView() {

    }

    @Override
    public Context obtainContext() {
        return getContext();
    }

    @Override
    public Context obtainApplicationContext() {
        return getContext().getApplicationContext();
    }
}
