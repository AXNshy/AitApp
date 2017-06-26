package com.fileintersend.view.service;

import android.app.Service;
import android.content.Intent;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.fileintersend.model.http.SocketConfigeration;
import com.fileintersend.model.socket.HttpClient;
import com.fileintersend.model.socket.HttpServer;
import com.fileintersend.util.FileUtils;

import java.io.File;

/**
 * Created by axnshy on 2017/6/24.
 */

public class FileInterSendService extends Service implements ISendService {

    private static final String TAG = "FileInterSendService";


    FileSendPresenter mPresenter;

    HttpServer mServer;

    HttpClient mClient;

    MyBinder mBinder = new MyBinder();

    public class MyBinder extends Binder {

        public FileInterSendService getService() {
            return FileInterSendService.this;
        }
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        /*mPresenter = new FileSendPresenter();
        mPresenter.attachService(this);*/

        SocketConfigeration sc = new SocketConfigeration.Builder()
                .setMaxParallels(4)
                .setPort(8088)
                .setAddress("192.168.1.105")
                .build();

//        mServer = new HttpServer(sc);
//        mServer.startAsync();
        mClient = new HttpClient(sc);
        Log.d(TAG, "current Thread name :" + Thread.currentThread().getName());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.detachService();
        mPresenter = null;
        closeClientSocket();
        closeServerSocket();
    }

    @Override
    public void send(String path) {
        mClient.startAync(path);

    }

    public void sendContentUri(String uri){


        Log.d(TAG,"contentpath : "+uri);

        String path = FileUtils.getPath(getApplicationContext(),Uri.parse(uri));
        Log.d(TAG,"path : "+path);
        send(path);
    }





    @Override
    public File accept() {
        return null;
    }

    @Override
    public void writeToDisk(File file, String dictionary) {

    }

    @Override
    public void closeClientSocket() {
        mClient.close();
    }

    @Override
    public void closeServerSocket() {
        mServer.stopAsync();
    }
}
