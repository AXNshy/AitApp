package com.github.M.Http;

import com.github.M.Entity.Repository;
import com.github.M.Entity.ResponseObject.BaseResponseSearch;
import com.github.M.Entity.User;

import java.util.List;

import io.reactivex.Flowable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by axnshy on 2017/6/21.
 */

public interface HttpInterface {


    @GET
    Flowable<ResponseBody> downloadFile(String url);

    @GET(Constants.REPOSITORIES)
    Flowable<List<Repository>> getRepositories(@Query("since") int since);

    @GET(Constants.REPOSITORIES)
    Flowable<ResponseBody> getRepositoriesc(@Query("since") int since);


    @GET(Constants.USERS)
    Flowable<List<User>> getUsers(@Query("since") int since);

    @GET(Constants.SEARCH_REPOSITORY)
    Flowable<BaseResponseSearch<Repository>> searchRepositories(@Query("q") String qualifiers, @Query("sort") String sortby, @Query("order") String orderby);
}
