package com.github.M.Http;

import com.github.M.Entity.Repository;
import com.github.M.Entity.ResponseObject.BaseResponseSearch;
import com.github.M.Entity.User;

import java.io.IOException;
import java.util.List;

import io.reactivex.Flowable;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by axnshy on 2017/6/21.
 */

public class RetrofitClient {


    private final static String baseUrl = "https://api.github.com/";
    private HttpInterface mInterface;


    private RetrofitClient(){
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Interceptor.Chain chain) throws IOException {
                Request original = chain.request();

                // Request customization: add request headers
                Request.Builder requestBuilder = original.newBuilder()
                        .header("Accept", "application/vnd.github.mercy-preview+json"); // <-- this is the important line

                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        });

        OkHttpClient client = httpClient.build();

        Retrofit retrofit  = new Retrofit.Builder()
                .client(client)
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        mInterface = retrofit.create(HttpInterface.class);
    }

    public static RetrofitClient getInstance(){
        return Singleton.INSTANCE;
    }

    private static class Singleton{
        private static RetrofitClient INSTANCE = new RetrofitClient();
    }

    /*
    *   获取仓库
    * */
    public Flowable<List<Repository>> getRepositories(int since){
       return mInterface.getRepositories(since);
    }

    /*
    * 获取用户
    * */
    public Flowable<List<User>> getUsers(int since){
        return mInterface.getUsers(since);
    }


    /**
     * 搜索
     * @param qualifier 限定符
     * @param sort 根据 {@sort} 来排序
     * @param order 顺序还是倒叙
     * @return
     */

    public Flowable<BaseResponseSearch<Repository>> searchRepositories(String qualifier, String sort, String order){
        return mInterface.searchRepositories(qualifier,sort,order);
    }
}
