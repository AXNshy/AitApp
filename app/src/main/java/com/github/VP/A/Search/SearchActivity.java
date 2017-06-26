package com.github.VP.A.Search;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.github.B.BaseActivity;
import com.github.M.Http.Constants;
import com.github.R;
import com.github.Util.Event.BusEventSearch;
import com.github.Util.Event.OttoBus;
import com.github.Util.UI.SwipeBackLayout;
import com.github.VP.F.RepoFragment.RepoFragment;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by axnshy on 2017/6/23.
 */

public class SearchActivity extends BaseActivity<ISearch, SearchPresenter> implements ISearch, SwipeBackLayout.OnSwipeListener {

    private static final String TAG = "SearchActivity";
    @BindView(R.id.llyt_search)
    LinearLayout searchLayout;
    @BindView(R.id.llyt_toolbar)
    LinearLayout toolbar;
    @BindView(R.id.et_key_input)
    EditText inputEt;
    @BindView(R.id.frame_result)
    FrameLayout searchResultLayout;
    @BindView(R.id.iv_search_forward)
    ImageView forwardIv;
    @BindView(R.id.swipelayout)
    SwipeBackLayout swipeLayout;


    @OnClick({R.id.iv_search_forward})
    void onClick(View v){
        switch (v.getId()){
            case R.id.iv_search_forward:
                if(!"".equals(inputEt.getText().toString())){
                    Log.d(TAG,"onClick  Bus post");
                    OttoBus.getInstance().post(new BusEventSearch(inputEt.getText().toString(), Constants.SORT_STAR,Constants.ORDER_DESC));
                }

                break;
        }
    }

    @Override
    public SearchPresenter createPresenter() {
        return new SearchPresenter();
    }

    @Override
    public int layoutId() {
        return R.layout.activity_search;
    }

    @Override
    public void initView(View view) {
        inputEt.setVisibility(View.VISIBLE);
        forwardIv.setVisibility(View.VISIBLE);
        swipeLayout.addOnSwipeListener(this);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();

        RepoFragment fg = RepoFragment.newInstance();
        transaction.replace(R.id.frame_result,fg);
        transaction.commit();

        /*inputEt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus&&!"".equals(inputEt.getText().toString())){

                    OttoBus.getInstance().post(new BusEventSearch(inputEt.getText().toString(), Constants.SORT_STARS,Constants.ORDER_DESC));

                }
            }
        });*/
    }

    @Override
    public void onSwipe(int oritential) {
        onBackPressed();
        finish();
    }
}
