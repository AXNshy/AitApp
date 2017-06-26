package com.github.VP.F.User;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.github.B.BaseFragment;
import com.github.M.Entity.User;
import com.github.R;
import com.github.VP.WebView.WebActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.github.VP.Constants.HTML_URL;
import static com.github.VP.Constants.MY_PERMISSIONS_REQUEST_READ_PHONE_STATE;

/**
 * Created by axnshy on 2017/6/21.
 */

public class UserFragment extends BaseFragment<IUser,UserFragmentPresenter> implements IUser, UserAdapter.OnItemClickLister {

    private static final String TAG = "UserFragment";
    @BindView(R.id.recycler)
    RecyclerView recyclerView;

    UserAdapter adapter;

    List<User> users;

    @Override
    public UserFragmentPresenter createPresenter() {
        return new UserFragmentPresenter();
    }

    public static UserFragment newInstance(){
        return new UserFragment();
    }

    @Override
    public int layoutId() {
        return R.layout.fragment_repo;
    }

    @Override
    public void initView(View view) {
        users = new ArrayList<>();
        adapter = new UserAdapter(getContext(),users);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        DividerItemDecoration decoration = new DividerItemDecoration(obtainContext(),DividerItemDecoration.VERTICAL);
//        decoration.setDrawable(new ColorDrawable(Color.WHITE));
        recyclerView.addItemDecoration(decoration);

        adapter.addOnItemClickListener(this);

//        SimpleDivider dividerItemDecoration = new SimpleDivider(getContext(), DividerItemDecoration.VERTICAL);
//        recyclerview.addItemDecoration(dividerItemDecoration);
    }

    @Override
    public void initData() {
        mPresenter.getUsers(UserFragmentPresenter.UNCLEAR);

    }

    @Override
    public void update(List<User> users,int clear) {
        Log.d(TAG, "update start");

        if (adapter != null) {

            if (clear == UserFragmentPresenter.CLEAR) {
                this.users.clear();
            }

            if (users != null && users.size() > 0)
                this.users.addAll(users);

            adapter.notifyDataSetChanged();
        }
        Log.d(TAG, "update end");
    }

    @Override
    public void onClick(View view, int position) {
        if (ContextCompat.checkSelfPermission(getActivity().getApplicationContext(),
                Manifest.permission.INTERNET)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.INTERNET},
                    MY_PERMISSIONS_REQUEST_READ_PHONE_STATE);
        } else {

            String url = users.get(position).getHtmlUrl();
            Intent intent = new Intent();
            intent.setClass(getContext(), WebActivity.class);
            intent.putExtra(HTML_URL, url);
            getActivity().startActivity(intent);
        }
    }
}
