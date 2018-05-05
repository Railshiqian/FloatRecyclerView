package com.example.shiqian.activity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;

import com.example.shiqian.R;
import com.example.shiqian.adapter.RecyclerViewAdapter;
import com.example.shiqian.widget.FloatingItemDecoration;
import com.example.shiqian.widget.FloatingItemDecoration1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by chenzd on 18-5-4.
 */

public class RecyclerViewActivity extends Activity {

    private RecyclerView rcl;

    private ArrayList<HashMap<String, Object>> datas;
    private Map<Integer, String> keys = new HashMap<>();//存放所有key的位置和内容
    private RecyclerViewAdapter adapter;
    private Context mContext = RecyclerViewActivity.this;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity_recycler_view);

        initData();
        initView();

    }

    private void initData() {
        datas = new ArrayList<>();

        HashMap<String, Object> map = new HashMap<>();

        map.put("type", "iv");
        datas.add(map);

        map = new HashMap<>();
        map.put("type", "tv");
        map.put("title", "atome");
        map.put("head", "A");
        datas.add(map);

        map = new HashMap<>();
        map.put("type", "tv");
        map.put("title", "atome1");
        map.put("head", "A");
        datas.add(map);

        map = new HashMap<>();
        map.put("type", "tv");
        map.put("title", "atome2");
        map.put("head", "A");
        datas.add(map);

        map = new HashMap<>();
        map.put("type", "tv");
        map.put("title", "atome3");
        map.put("head", "A");
        datas.add(map);

        map = new HashMap<>();
        map.put("type", "tv");
        map.put("title", "atome4");
        map.put("head", "A");
        datas.add(map);

        map = new HashMap<>();
        map.put("type", "tv");
        map.put("title", "bee");
        map.put("head", "B");
        datas.add(map);

        map = new HashMap<>();
        map.put("type", "tv");
        map.put("title", "bee1");
        map.put("head", "B");
        datas.add(map);

        map = new HashMap<>();
        map.put("type", "tv");
        map.put("title", "bee2");
        map.put("head", "B");
        datas.add(map);

        map = new HashMap<>();
        map.put("type", "tv");
        map.put("title", "carry");
        map.put("head", "C");
        datas.add(map);

        map = new HashMap<>();
        map.put("type", "tv");
        map.put("title", "carry1");
        map.put("head", "C");
        datas.add(map);
        map = new HashMap<>();
        map.put("type", "tv");
        map.put("title", "carry2");
        map.put("head", "C");
        datas.add(map);

        map = new HashMap<>();
        map.put("type", "tv");
        map.put("title", "carry3");
        map.put("head", "C");
        datas.add(map);

        map = new HashMap<>();
        map.put("type", "tv");
        map.put("title", "carry4");
        map.put("head", "C");
        datas.add(map);

        map = new HashMap<>();
        map.put("type", "tv");
        map.put("title", "carry5");
        map.put("head", "C");
        datas.add(map);

        map = new HashMap<>();
        map.put("type", "tv");
        map.put("title", "drive");
        map.put("head", "D");
        datas.add(map);

        map = new HashMap<>();
        map.put("type", "tv");
        map.put("title", "drive1");
        map.put("head", "D");
        datas.add(map);

        map = new HashMap<>();
        map.put("type", "tv");
        map.put("title", "drive2");
        map.put("head", "D");
        datas.add(map);

        map = new HashMap<>();
        map.put("type", "tv");
        map.put("title", "drive3");
        map.put("head", "D");
        datas.add(map);

        map = new HashMap<>();
        map.put("type", "tv");
        map.put("title", "drive4");
        map.put("head", "D");
        datas.add(map);

        map = new HashMap<>();
        map.put("type", "tv");
        map.put("title", "drive5");
        map.put("head", "D");
        datas.add(map);

        map = new HashMap<>();
        map.put("type", "tv");
        map.put("title", "drive6");
        map.put("head", "D");
        datas.add(map);

        map = new HashMap<>();
        map.put("type", "tv");
        map.put("title", "Erive6");
        map.put("head", "E");
        datas.add(map);

        map = new HashMap<>();
        map.put("type", "tv");
        map.put("title", "Frive6");
        map.put("head", "F");
        datas.add(map);

        map = new HashMap<>();
        map.put("type", "tv");
        map.put("title", "Grive6");
        map.put("head", "G");
        datas.add(map);

        map = new HashMap<>();
        map.put("type", "tv");
        map.put("title", "Hrive6");
        map.put("head", "H");
        datas.add(map);

        map = new HashMap<>();
        map.put("type", "tv");
        map.put("title", "Irive6");
        map.put("head", "I");
        datas.add(map);

        map = new HashMap<>();
        map.put("type", "tv");
        map.put("title", "Jrive6");
        map.put("head", "J");
        datas.add(map);

        map = new HashMap<>();
        map.put("type", "tv");
        map.put("title", "Krive6");
        map.put("head", "K");
        datas.add(map);

        map = new HashMap<>();
        map.put("type", "tv");
        map.put("title", "Lrive6");
        map.put("head", "L");
        datas.add(map);

        map = new HashMap<>();
        map.put("type", "tv");
        map.put("title", "Mrive6");
        map.put("head", "M");
        datas.add(map);

        map = new HashMap<>();
        map.put("type", "tv");
        map.put("title", "Nrive6");
        map.put("head", "N");
        datas.add(map);

        map = new HashMap<>();
        map.put("type", "tv");
        map.put("title", "Orive6");
        map.put("head", "O");
        datas.add(map);

        map = new HashMap<>();
        map.put("type", "tv");
        map.put("title", "Prive6");
        map.put("head", "P");
        datas.add(map);

        map = new HashMap<>();
        map.put("type", "tv");
        map.put("title", "Qrive6");
        map.put("head", "Q");
        datas.add(map);


        dealKeys();


    }

    private void dealKeys() {

        keys.clear();

        String temp = "";

        for (int i = 1; i < datas.size(); i++) {
            if (!temp.equals(datas.get(i).get("head"))) {
                temp = datas.get(i).get("head") + "";
                keys.put(i, temp);
            }
        }
    }

    private void initView() {

        rcl = findViewById(R.id.recycler_view);

        GridLayoutManager manager = new GridLayoutManager(this, 1);
        rcl.setLayoutManager(manager);

//        keys.put(0, "欧美大片");
//        keys.put(2, "国产剧透");
//        keys.put(4, "印度神剧");

        final FloatingItemDecoration1 floatingItemDecoration = new FloatingItemDecoration1(this, Color.GREEN, 1, 1);
        floatingItemDecoration.setKeys(keys);
        floatingItemDecoration.setmTitleHeight((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20, getResources().getDisplayMetrics()));
        rcl.addItemDecoration(floatingItemDecoration);

        adapter = new RecyclerViewAdapter(mContext, datas);
        rcl.setHasFixedSize(true);
        rcl.setAdapter(adapter);

    }
}
