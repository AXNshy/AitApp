package com.github.VP.F.User;

import com.github.B.BasePresenter;
import com.github.M.Entity.User;
import com.github.M.Http.RetrofitClient;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by axnshy on 2017/6/21.
 */

public class UserFragmentPresenter extends BasePresenter<IUser> {
    public static final int CLEAR = 1;
    public static final int UNCLEAR = 0;
    private static int sinces = 0;

    public void getUsers(final int clear) {
        RetrofitClient.getInstance().getUsers(sinces)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<User>>() {
                    @Override
                    public void accept(List<User> users) throws Exception {
                        sinces  += users.size();
                        getView().update(users,clear);
                    }
                });
    }



}
