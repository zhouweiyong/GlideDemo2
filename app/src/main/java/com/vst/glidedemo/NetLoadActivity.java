package com.vst.glidedemo;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.FutureTarget;

import java.io.File;
import java.util.concurrent.ExecutionException;

/**
 * Created by zwy on 2017/5/8.
 * email:16681805@qq.com
 * <p>
 * <p>
 * 缓存策略
 * skipMemoryCache( true )：设置图片不加入到内存缓存
 * DiskCacheStrategy.NONE :不缓存图片
 * DiskCacheStrategy.SOURCE :缓存图片源文件
 * DiskCacheStrategy.RESULT:缓存修改过的图片
 * DiskCacheStrategy.ALL:缓存所有的图片，默认
 */

public class NetLoadActivity extends Activity implements View.OnClickListener {

    private Button btn;
    private ImageView iv;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    private Button btn5;
    private File file;
    private Bitmap bitmap;
    private Button btn6;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_net_load);
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
        btn4 = (Button) findViewById(R.id.btn4);
        btn4.setOnClickListener(this);
        btn5 = (Button) findViewById(R.id.btn5);
        btn5.setOnClickListener(this);
        btn6 = (Button) findViewById(R.id.btn6);
        btn6.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn:
                load();
                break;
            case R.id.btn2:
                thumbnail();
                break;
            case R.id.btn3:
                thumbnail2();
                break;
            case R.id.btn4:
                downloadOnly();
                break;
            case R.id.btn5:
                getBitmap();
                break;
            case R.id.btn6:
                gif();
                break;
        }
    }

    private void load() {
        Glide
                .with(this)
                .load(UrlServer.IMAGE1)
                .placeholder(R.mipmap.ic_launcher)//设置占位图
                .error(R.mipmap.img04)//设置错误图片
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .override(10, 10)//限制图片大小
                .crossFade(3000)//设置淡入淡出效果，默认300ms，可以传参
                //.centerCrop()//等比例缩放图片，直到图片的狂高都大于等于ImageView的宽度，然后截取中间的显示。
                .fitCenter()//等比例缩放图片，宽或者是高等于ImageView的宽或者是高
                .into(iv);
    }

    private void thumbnail() {
        Glide.with(this.getApplicationContext())
                .load(UrlServer.IMAGE_BIG3)
                .thumbnail(0.1f)
                .into(iv);
    }

    /**
     * 先加载缩略图
     */
    private void thumbnail2() {
        DrawableTypeRequest<String> thumbnail = Glide.with(this.getApplicationContext())
                .load(UrlServer.IMAGE1);
        Glide.with(this.getApplicationContext())
                .load(UrlServer.IMAGE_BIG4)
                .thumbnail(thumbnail)
                .into(iv);

    }

    private Handler handler = new Handler();

    /**
     * 下载图片
     */
    private void downloadOnly() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                FutureTarget<File> fileFutureTarget = Glide.with(NetLoadActivity.this)
                        .load(UrlServer.IMAGE_BIG5)
                        .downloadOnly(500, 500);
                file = null;
                try {
                    file = fileFutureTarget.get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {

                        iv.setImageBitmap(BitmapFactory.decodeFile(file.getAbsolutePath()));
                    }
                });
            }
        }).start();
    }

    private void getBitmap() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    bitmap = Glide.with(NetLoadActivity.this)
                            .load(UrlServer.IMAGE_BIG5)
                            .asBitmap()
                            .centerCrop()
                            .into(500, 500)
                            .get();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            iv.setImageBitmap(bitmap);
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void gif() {
        Glide.with(this)
                .load(UrlServer.GIF)
                .asGif()
                .into(iv);
    }
}
