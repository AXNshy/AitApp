package com.github.VP.WebView;

import android.util.Log;
import android.view.View;

import com.github.B.BaseActivity;
import com.github.R;
import com.github.Util.UI.SwipeBackLayout;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import butterknife.BindView;

import static com.github.VP.Constants.HTML_URL;

/**
 * Created by axnshy on 2017/6/21.
 */

public class WebActivity extends BaseActivity<IWebView, WebViewPresenter> implements SwipeBackLayout.OnSwipeListener {

    private static final String TAG = "WebActivity";
    @BindView(R.id.webview)
    WebView webview;

    @BindView(R.id.layout_swipe_back)
    SwipeBackLayout swipelayout;
    String url;

    @Override
    public int layoutId() {
        return R.layout.webview;
    }

    @Override
    public WebViewPresenter createPresenter() {
        return new WebViewPresenter();
    }

    @Override
    public void initView(View view) {
        super.initView(view);

        swipelayout.addOnSwipeListener(this);

        WebViewClient client = new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView webView, String s) {
                webView.loadUrl(s);

                Log.d(TAG,"clicked url :"+s.toString());
                return true;
            }
        };

        WebChromeClient chromeClient = new WebChromeClient(){

        };
        webview.setWebViewClient(client);
        initWebViewSettings();

    }

    @Override
    public void initData() {
        super.initData();

        url = getIntent().getStringExtra(HTML_URL);

        webview.loadUrl(url);
    }

    private void initWebViewSettings() {
        WebSettings webSetting = webview.getSettings();
        webSetting.setJavaScriptEnabled(true);
        webSetting.setJavaScriptCanOpenWindowsAutomatically(true);
        webSetting.setAllowFileAccess(true);
        webSetting.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webSetting.setSupportZoom(true);
        webSetting.setBuiltInZoomControls(true);
        webSetting.setUseWideViewPort(true);
        webSetting.setSupportMultipleWindows(true);
        // webSetting.setLoadWithOverviewMode(true);
        webSetting.setAppCacheEnabled(true);
        // webSetting.setDatabaseEnabled(true);
        webSetting.setDomStorageEnabled(true);
        webSetting.setGeolocationEnabled(true);
        webSetting.setAppCacheMaxSize(Long.MAX_VALUE);
        // webSetting.setPageCacheCapacity(IX5WebSettings.DEFAULT_CACHE_CAPACITY);
        webSetting.setPluginState(WebSettings.PluginState.ON_DEMAND);
        // webSetting.setRenderPriority(WebSettings.RenderPriority.HIGH);
        webSetting.setCacheMode(WebSettings.LOAD_NO_CACHE);

        // this.getSettingsExtension().setPageCacheCapacity(IX5WebSettings.DEFAULT_CACHE_CAPACITY);//extension
        // settings 的设计
    }

    @Override
    public void onSwipe(int oritential) {
        if(webview.canGoBack()){
            webview.goBack();
        }
        else {
            onBackPressed();
        }
    }

    @Override
    public void onBackPressed() {
        if(webview.canGoBack()){
            webview.goBack();
            return;
        }
        super.onBackPressed();
    }
}
