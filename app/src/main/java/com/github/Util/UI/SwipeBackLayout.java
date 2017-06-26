package com.github.Util.UI;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.LinearLayout;

/**
 * Created by axnshy on 2017/6/23.
 */

public class SwipeBackLayout extends LinearLayout {

    private float mScrollX;

    private float mScrollY;

    private OnSwipeListener mListener;

    public void addOnSwipeListener(OnSwipeListener listener) {
        this.mListener = listener;
    }


    public SwipeBackLayout(Context context) {
        this(context,null);
    }

    public SwipeBackLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SwipeBackLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public interface OnSwipeListener {
        void onSwipe(int oritential);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        int touchSlap = ViewConfiguration.get(getContext()).getScaledTouchSlop();

        boolean intercept = false;
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                intercept = false;

                break;
            case MotionEvent.ACTION_MOVE:
                if (Math.abs(mScrollX - ev.getX())>touchSlap&&mScrollX - ev.getX() < 0 && Math.abs(mScrollX - ev.getX()) > Math.abs(mScrollY - ev.getY())) {
                    intercept = true;
                }
                break;
            case MotionEvent.ACTION_UP:
                intercept = false;
        }
        mScrollX = ev.getX();
        mScrollY = ev.getY();
        return intercept;
    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:

                break;
            case MotionEvent.ACTION_MOVE:
                if (mScrollX - ev.getX() < 0 && Math.abs(mScrollX - ev.getX()) > Math.abs(mScrollY - ev.getY())) {

                }
                break;
            case MotionEvent.ACTION_UP:
                if (mListener != null) {
                    mListener.onSwipe(1);
                }
                break;
        }
        return true;

    }

}
