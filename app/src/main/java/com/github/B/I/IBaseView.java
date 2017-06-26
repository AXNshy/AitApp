package com.github.B.I;

import android.content.Context;
import android.view.View;

/**
 * Created by Administrator on 2016/11/27.
 */

public interface IBaseView {
    int layoutId();
    void initView(View view);
    void initData();
    void beforeInitView();
    Context obtainContext();
    Context obtainApplicationContext();
}
