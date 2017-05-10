package com.vst.glidedemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {


    private ListView listView;
    private List<String> list = Arrays.asList("普通加载", "缓存加载", "glide小设置", "Transform");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, list));
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                startActivity(new Intent(MainActivity.this, NetLoadActivity.class));
                break;
            case 1:
                startActivity(new Intent(MainActivity.this, DisLoadActivity.class));
                break;
            case 2:
                startActivity(new Intent(MainActivity.this, GlideSettingActivity.class));
                break;
            case 3:
                startActivity(new Intent(MainActivity.this, TransformActivity.class));
                break;
        }
    }
}
