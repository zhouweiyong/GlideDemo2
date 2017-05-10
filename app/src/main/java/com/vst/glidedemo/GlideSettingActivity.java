package com.vst.glidedemo;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.bumptech.glide.Glide;

import java.io.File;

/**
 * Created by zwy on 2017/5/8.
 * email:16681805@qq.com
 */

public class GlideSettingActivity extends Activity implements View.OnClickListener {

    private static final String TAG = "zwy";
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide_settting);
        initView();
    }

    private void initView() {
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                fun1();
                break;
            case R.id.btn2:
                clearCache();
                break;
            case R.id.btn3:
                break;
            case R.id.btn4:

                break;
        }
    }

    //获取缓存目录
    private void fun1() {
        File photoCacheDir = Glide.getPhotoCacheDir(this);
        Log.i("zwy", photoCacheDir.getAbsolutePath());

    }

    //清空缓存
    private void clearCache() {
        Glide.get(this).clearMemory();
        File cacheDir = Glide.getPhotoCacheDir(this);
        if (cacheDir.isDirectory()) {
            Log.w(TAG, "Number of files to clear from cache: " + cacheDir.listFiles().length);
            for (File child : cacheDir.listFiles()) {
                if (!child.delete()) {
                    Log.w(TAG, "cannot delete: " + child);
                }
            }
        }
    }


}
