package com.example.shiqian.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.shiqian.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by chenzd on 18-5-4.
 */

public class RecyclerViewAdapter extends Adapter<RecyclerViewAdapter.MyHolder> {

    private Context context;
    private ArrayList<HashMap<String,Object>> list;

    public RecyclerViewAdapter(Context context, ArrayList<HashMap<String,Object>> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        if (viewType == 0) {
            itemView = LayoutInflater.from(context).inflate(R.layout.item_recycler_view_activity_imageview, null);
        } else {
            itemView = LayoutInflater.from(context).inflate(R.layout.item_recycler_view_activity, null);
        }
        return new MyHolder(itemView, viewType);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        if (position != 0) {
            holder.tv.setText(list.get(position).get("title") + "");
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position == 0 ? position : 1;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        private TextView tv;

        public MyHolder(View itemView, int type) {
            super(itemView);
            if (type != 0)
                tv = itemView.findViewById(R.id.tv_item_recyclerview_title);
        }
    }


}
