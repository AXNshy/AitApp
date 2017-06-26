package com.fileintersend.model.socket;

import android.util.Log;

import com.fileintersend.model.http.SocketConfigeration;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by axnshy on 2017/6/25.
 */

public class HttpServer {

    private static final String TAG = "HttpServer";
    SocketConfigeration mConfig;

    ServerSocket serverSocket;

    boolean enable;

    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();

    ExecutorService mExecutor = Executors.newFixedThreadPool(CPU_COUNT);

    public HttpServer(SocketConfigeration mConfig) {
        this.mConfig = mConfig;
    }

    public void startAsync(){
        Log.d(TAG,"开始运行Socket");
        enable = true;
        new Thread(new Runnable() {
            @Override
            public void run() {
                doProcAsyc();
            }
        }).start();
    }

    public void doProcAsyc() {
        InetSocketAddress address = new InetSocketAddress(mConfig.getPort());
        try {
            serverSocket = new ServerSocket();
            serverSocket.bind(address);
            while (enable) {
                final Socket remotePeer = serverSocket.accept();
                mExecutor.submit(new Runnable() {
                    @Override
                    public void run() {
                        processTask(remotePeer);
                    }
                });
                Log.d(TAG,"remotePeer :"+remotePeer.getLocalAddress().toString());

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void processTask(Socket remotePeer) {
        try {


            remotePeer.getOutputStream().write("from http server \n 祝贺你连接成功".getBytes());


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stopAsync(){
        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        serverSocket = null;
    }


}
