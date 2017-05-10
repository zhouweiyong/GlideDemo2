package com.vst.glidedemo;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.vst.glidedemo.transform.CropCircleTransformation;
import com.vst.glidedemo.transform.GrayscaleTransformation;
import com.vst.glidedemo.transform.RoundedCornersTransformation;

/**
 * Created by zwy on 2017/5/9.
 * email:16681805@qq.com
 */

public class TransformActivity extends Activity implements View.OnClickListener {

    private Button btn1;
    private Button btn2;
    private Button btn3;
    private ImageView iv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transform);
        initView();
    }

    private void initView() {
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3 = (Button) findViewById(R.id.btn3);
        btn3.setOnClickListener(this);
        iv = (ImageView) findViewById(R.id.iv);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                Glide.with(this.getApplicationContext())
                        .load(UrlServer.IMAGE1)
                        .placeholder(R.mipmap.ic_launcher)
                        .bitmapTransform(new CropCircleTransformation(this))
                        .into(iv);
                break;
            case R.id.btn2:
                Glide.with(this)
                        .load(UrlServer.IMAGE1)
                        .placeholder(R.mipmap.ic_launcher)
                        .bitmapTransform(new RoundedCornersTransformation(this,10,0))
                        .into(iv);
                break;
            case R.id.btn3:
                Glide.with(this)
                        .load(UrlServer.IMAGE1)
                        .placeholder(R.mipmap.ic_launcher)
                        .bitmapTransform(new GrayscaleTransformation(this))
                        .into(iv);
                break;
        }
    }
}
