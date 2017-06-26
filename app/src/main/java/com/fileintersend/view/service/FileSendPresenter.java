package com.fileintersend.view.service;

import java.lang.ref.WeakReference;

/**
 * Created by axnshy on 2017/6/25.
 */

public class FileSendPresenter<S>{

    private WeakReference<S> mService;

    public void attachService(S service){
        this.mService = new WeakReference<>(service);
    }

    public S getService(){
        return mService.get();
    }

    public void detachService(){
        if(mService !=null){
            mService.clear();
            mService = null;
        }
    }
}
