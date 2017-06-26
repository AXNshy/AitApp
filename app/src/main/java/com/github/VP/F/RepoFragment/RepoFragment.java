package com.github.VP.F.RepoFragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.github.B.BaseFragment;
import com.github.M.Entity.Repository;
import com.github.R;
import com.github.Util.Event.BusEventSearch;
import com.github.Util.Event.OttoBus;
import com.github.Util.UI.RecyclerViewUtils;
import com.github.Util.UI.SimpleDivider;
import com.github.VP.A.Home.MainActivity;
import com.github.VP.WebView.WebActivity;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.github.VP.Constants.HTML_URL;
import static com.github.VP.Constants.MY_PERMISSIONS_REQUEST_READ_PHONE_STATE;

/**
 * Created by axnshy on 2017/6/21.
 */

public class RepoFragment extends BaseFragment<IRepo, RepoFragmentPresenter> implements IRepo, RepositoryAdapter.OnItemClickListener {

    private static final String TAG = "RepoFragment";
    @BindView(R.id.recycler)
    RecyclerView recyclerview;

    @BindView(R.id.swipe_refresh_laytou)
    SwipeRefreshLayout swipeRefreshLayout;

    RepositoryAdapter adapter;

    List<Repository> datas;

    boolean mRefresh = false;
    boolean mLoad = false;


    @Override
    public RepoFragmentPresenter createPresenter() {
        return new RepoFragmentPresenter();
    }

    public static RepoFragment newInstance() {
        return new RepoFragment();
    }

    @Override
    public int layoutId() {
        return R.layout.fragment_repo;
    }

    @Override
    public void initView(View view) {
        OttoBus.getInstance().register(this);

        datas = new ArrayList<>();
        adapter = new RepositoryAdapter(getContext(), datas);
        recyclerview.setAdapter(adapter);
        recyclerview.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        adapter.addOnItemClickListener(this);

        SimpleDivider dividerItemDecoration = new SimpleDivider(getContext(), DividerItemDecoration.VERTICAL);
        recyclerview.addItemDecoration(dividerItemDecoration);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.clearAndGetRepositories();
            }
        });

        recyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_DRAGGING &&
                        //滚动到底部了
                        !RecyclerViewUtils.canScrollVertically(recyclerView, RecyclerViewUtils.SCROLL_DOWN)) {

                    /* 显示加载item 开始进行加载操作
                    */
                    if (getActivity() instanceof MainActivity) {
                        setLoading(true);
                        mPresenter.getRepositories();
                        Log.d(TAG, "在 MainActivity中   直接调用getRepositories");
                    }

                }

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

            }
        });
    }

    @Override
    public void initData() {
        if (!swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(true);
        }
        mPresenter.getRepositories();
    }

    @Override
    public void update(List<Repository> repos, int clear) {
        Log.d(TAG, "update start");
        if (swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }
        if (mLoad) {
            setLoading(false);
        }
        if (adapter != null) {

            if (clear == RepoFragmentPresenter.CLEAR) {
                this.datas.clear();
            }

            if (repos != null && repos.size() > 0)
                this.datas.addAll(repos);


            adapter.notifyDataSetChanged();
        }
        Log.d(TAG, "update end");


    }

    @Override
    public void clearData() {
        if (datas != null && datas.size() > 0) {
            datas.clear();
        }
    }

    @Override
    public void onItemClick(View v, int position) {

        if (ContextCompat.checkSelfPermission(getActivity().getApplicationContext(),
                Manifest.permission.INTERNET)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.INTERNET},
                    MY_PERMISSIONS_REQUEST_READ_PHONE_STATE);
        } else {

            String url = datas.get(position).getHtmlUrl();
            Intent intent = new Intent();
            intent.setClass(getContext(), WebActivity.class);
            intent.putExtra(HTML_URL, url);
            getActivity().startActivity(intent);
        }
    }

    @Override
    public void onItemLongClick(View v, int position) {

    }

    @Subscribe
    public void anwserEvent(BusEventSearch event) {
        Log.d(TAG, "anwserEvent  start");
        String qualifier = event.getQualifier();
        String sort = event.getSort();
        String order = event.getOrder();
        if (!swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(true);
        }
        mPresenter.clearSince();
        datas.clear();
        mPresenter.searchRepositories(qualifier, sort, order);

        Log.d(TAG, "anwserEvent  end");
    }

    @Override
    public void setRefreshing(boolean refresh) {
        Log.d(TAG, "setRefreshing :" + refresh + "   start");
        if ((mRefresh && !refresh) || !mRefresh && refresh) {
            mRefresh = refresh;
        }
        swipeRefreshLayout.setRefreshing(mRefresh);
        Log.d(TAG, "setRefreshing :" + refresh + "   end");
    }

    @Override
    public void setLoading(boolean load) {
        Log.d(TAG, "setLoading :" + load + "   start");
        if ((mLoad && !load)) {
            mLoad = load;
            adapter.setFooterVisibility(View.GONE);
        } else if (!mRefresh && load) {
            mLoad = load;
            adapter.setFooterVisibility(View.VISIBLE);
        }
        Log.d(TAG, "setLoading :" + load + "   end");
    }
}
