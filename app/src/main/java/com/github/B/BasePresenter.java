package com.github.B;

import com.github.B.I.IBasePresenter;
import com.github.B.I.IBaseView;

import java.lang.ref.WeakReference;


/**
 * Created by Administrator on 2016/11/27.
 */

public abstract class BasePresenter<V extends IBaseView> implements IBasePresenter {
    WeakReference<V> view;

    public V getView() {
        return view.get();
    }

    public void attachView(V v){
        if(view==null){
            view = new WeakReference<>(v);
        }
    }

    public void detachView(){
        if(view!=null){
            view.clear();
            view=null;
        }
    }
}
