package com.example.waean.zhiwen;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.waean.zhiwen.pojo.New;
import com.example.waean.zhiwen.utils.LogUtil;
import com.example.waean.zhiwen.utils.NewsCollector;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewActivity extends BaseActivity {
    private static final String TAG = "NewActivity";

    @Bind(R.id.new_image_view)
    ImageView mNewImageView;
    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.collapsing_toolbar)
    CollapsingToolbarLayout mCollapsingToolbar;
    @Bind(R.id.new_webview)
    WebView mNewWebview;
    @Bind(R.id.new_floatingacionbutton)
    FloatingActionButton mNewFloatingacionbutton;
    private New mNewData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        //获取Intent
        mNewData = (New) getIntent().getSerializableExtra("new_data");
        mCollapsingToolbar.setTitle(mNewData.getAuthor_name());

//        mNewWebview.setInitialScale(100);
        mNewWebview.getSettings().setJavaScriptEnabled(true);
        mNewWebview.loadUrl(mNewData.getUrl());
        mNewWebview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return super.shouldOverrideUrlLoading(view, url);
            }
        });
        Log.i("info", "onCreate: " + mNewData.getUrl());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.new_floatingacionbutton)
    public void onClick() {
        boolean isFind = NewsCollector.findNewOnNewList(mNewData);
        LogUtil.i(TAG, isFind + "-----");
        if (isFind) {
//            NewsCollector.removeNew(mNewData);
            Toast.makeText(this, "已收藏", Toast.LENGTH_SHORT).show();
        } else {
            NewsCollector.addNew(mNewData);
            Toast.makeText(this, "收藏成功", Toast.LENGTH_SHORT).show();
        }
        LogUtil.i(TAG, NewsCollector.newList.size() + "===");
    }
}
