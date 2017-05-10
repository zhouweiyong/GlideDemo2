package com.vst.glidedemo;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

/**
 * Created by zwy on 2017/5/8.
 * email:16681805@qq.com
 * <p>
 * Glide支持为图片加载设置优先级，优先级高的先加载，优先级低的后加载：
 * priority( )：Priority.HIGH   Priority.LOW
 */

public class DisLoadActivity extends Activity implements View.OnClickListener {

    private Button btn;
    private ImageView iv;

    private Handler handler = new Handler();
    private Button btn2;
    private Button btn3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disk_load);
        initView();
    }

    private void initView() {
        btn = (Button) findViewById(R.id.btn);
        iv = (ImageView) findViewById(R.id.iv);

        btn.setOnClickListener(this);
        btn2 = (Button) findViewById(R.id.btn2);
        btn2.setOnClickListener(this);
        btn3 = (Button) findViewById(R.id.btn3);
        btn3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn:
                Glide.with(this)
                        .load(UrlServer.IMAGE1)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .priority(Priority.HIGH)
                        .into(iv);
                Glide.with(DisLoadActivity.this)
                        .load(UrlServer.IMAGE2)
                        .priority(Priority.LOW)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(iv);

                break;
            case R.id.btn2:
                getBitmap();
                break;
            case R.id.btn3:
                setBitmapSize();
                break;
        }
    }

    /**
     * Glide通过Target的回调获取Bitmap，最常用的是SimpleTarget
     */
    private void getBitmap() {
        Glide.with(this)
                .load(UrlServer.IMAGE1)
                .asBitmap()
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        iv.setImageBitmap(resource);
                    }
                });
    }

    /**
     * 设置Bitmap的大小
     */
    private void setBitmapSize() {
        Glide.with(this.getApplicationContext())//使用Application比较安全
                .load(UrlServer.IMAGE1)
                .asBitmap()
                .into(new SimpleTarget<Bitmap>(50, 50) {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        iv.setImageBitmap(resource);
                    }
                });
    }
}
