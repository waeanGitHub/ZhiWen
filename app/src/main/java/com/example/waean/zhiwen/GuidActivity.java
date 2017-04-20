package com.example.waean.zhiwen;

import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;

public class GuidActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guid);
        //延迟两秒进入MainActivity
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                enterHomeActivty();
            }
        }, 2000);
    }

    private void enterHomeActivty() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
