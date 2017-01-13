package com.xiong.tbs_x5.activity;

import android.graphics.PixelFormat;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;
import com.xiong.tbs_x5.R;
import com.xiong.tbs_x5.utils.ToastUtil;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private EditText etWebsite;
    private TextView tvEnter;
    private ProgressBar progressBar;
    private com.tencent.smtt.sdk.WebView webView;
    private String url = "http://3g.qq.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFormat(PixelFormat.TRANSLUCENT);//（这个对宿主没什么影响，建议声明）
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        initView();
        loadUrl(url);
        openWebsite();
    }

    private void openWebsite() {
        etWebsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etWebsite.setText("");
            }
        });
    }

    private void initView() {
        etWebsite = (EditText) findViewById(R.id.etWebsite);
        tvEnter = (TextView) findViewById(R.id.tvEnter);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        webView = (com.tencent.smtt.sdk.WebView) findViewById(R.id.webView);
        tvEnter.setOnClickListener(this);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
    }

    private void loadUrl(String url) {
        etWebsite.setText(url);
        webView.loadUrl(url);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onReceivedError(WebView var1, int var2, String var3, String var4) {
                progressBar.setVisibility(View.GONE);
                ToastUtil.show("网页加载失败");
            }
        });
        //进度条
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    etWebsite.setText(webView.getOriginalUrl());
                    progressBar.setVisibility(View.GONE);
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
                progressBar.setProgress(newProgress);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (webView != null) webView.destroy();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (webView != null && webView.canGoBack()) {
                webView.goBack();
                return true;
            }
            return super.onKeyDown(keyCode, event);
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvEnter:
                String url = etWebsite.getText().toString();
                if (TextUtils.isEmpty(url)) {
                    ToastUtil.show("请输入网址");
                    return;
                }
                loadUrl(url);
                break;
        }
    }
}
