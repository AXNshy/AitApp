package com.fileintersend.model.socket;

import android.util.Log;

import com.fileintersend.model.http.SocketConfigeration;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by axnshy on 2017/6/25.
 */

public class HttpClient {

    private static final String TAG = "HttpClient";

    Socket socket;

    SocketConfigeration config;


    public HttpClient(final SocketConfigeration config) {
        this.config = config;
    }

    public void close() {

        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        socket = null;
    }

    public void startAync(final String path) {


        Log.d(TAG, "开始运行Socket");
        Flowable.just(path)
                .subscribeOn(Schedulers.io())
                .map(new Function<String, Object>() {
                    @Override
                    public Object apply(@NonNull String s) throws Exception {
                        doProcAsyc(s);
                        return "end";
                    }
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {

                    }
                });

    }

    private void doProcAsyc(String path) {
        try {
            Log.d(TAG, "doProcAsync start");
            socket = new Socket();

            InetSocketAddress address = new InetSocketAddress(config.getAddress(), config.getPort());

            socket.connect(address);

            InputStream is = null;
            OutputStream os = null;

            is = new BufferedInputStream(new FileInputStream(path));
            os = socket.getOutputStream();


            byte[] buffer = new byte[6 * 1024];
            int Readed = 0;

            while ((Readed = is.read(buffer)) != -1) {
                os.write(buffer);
            }

            byte[] re = new byte[1024];
            socket.getInputStream().read(re);

            while (socket.getInputStream().read(re) > -1)
                if (new String(re, "utf-8") == "Hello") {
                    socket.close();
                }
            os.close();
            is.close();


//            socket.close();
//            socket = null;
        } catch (IOException e) {
            e.printStackTrace();

            Log.d(TAG, "doProcAsync exception  " + e.toString());
        }

        Log.d(TAG, "doProcAsync start");
    }
}
