package com.github.VP.F.RepoFragment;

import android.util.Log;

import com.github.B.BasePresenter;
import com.github.M.Entity.Repository;
import com.github.M.Entity.ResponseObject.BaseResponseSearch;
import com.github.M.Http.RetrofitClient;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by axnshy on 2017/6/21.
 */

public class RepoFragmentPresenter extends BasePresenter<IRepo> {

    private static final String TAG = "RepoFragmentPresenter";
    private int sinces = 0;
    public static final  int CLEAR = 1;
    public static final  int UNCLEAR = 0;

    public void getRepositories() {
        Log.d(TAG,"getRepositories start");
        Log.d(TAG,"getRepositories since :"+sinces);
        RetrofitClient.getInstance().getRepositories(sinces)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Repository>>() {
                    @Override
                    public void accept(List<Repository> repositories) throws Exception {
                        sinces  += repositories.size();
                        getView().update(repositories,UNCLEAR);
                    }
                });
        Log.d(TAG,"getRepositories end");
    }

    public void searchRepositories(String qualifier, String sort, String order) {
        Log.d(TAG,"searchRepositories start");
        RetrofitClient.getInstance().searchRepositories(qualifier,sort,order)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BaseResponseSearch<Repository>>() {
                    @Override
                    public void accept(@NonNull BaseResponseSearch<Repository> responseSearch) throws Exception {
                        sinces += responseSearch.getItems().size();
                        getView().update(responseSearch.getItems(),UNCLEAR);
                    }
                });

        Log.d(TAG,"searchRepositories end");
    }

    public void clearSince(){
        sinces = 0;
    }



    public void clearAndGetRepositories() {
        Log.d(TAG,"clearAndGetRepositories start");
        sinces = 0;
        Log.d(TAG,"clearAndGetRepositories since :"+sinces);
        RetrofitClient.getInstance().getRepositories(sinces)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Repository>>() {
                    @Override
                    public void accept(List<Repository> repositories) throws Exception {
                        sinces  += repositories.size();
                        getView().update(repositories,CLEAR);
                    }
                });
        Log.d(TAG,"clearAndGetRepositories end");
    }
}
