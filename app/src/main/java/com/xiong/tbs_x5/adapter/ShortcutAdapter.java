package com.xiong.tbs_x5.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xiong.tbs_x5.R;
import com.xiong.tbs_x5.activity.WebActivity;
import com.xiong.tbs_x5.model.Shortcut;

import java.util.List;

/**
 * Created by xiongwenwei@aliyun.com
 * CreateTime: 2017/1/19
 * Note:
 */
public class ShortcutAdapter extends BaseAdapter {

    private Context context;
    private List<Shortcut> shortcuts;

    public ShortcutAdapter(Context context, List<Shortcut> shortcuts) {
        this.context = context;
        this.shortcuts = shortcuts;
    }

    @Override
    public int getCount() {
        return shortcuts.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (null == convertView) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_shortcut, null);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.llShortcut = (LinearLayout) convertView.findViewById(R.id.llShortcut);
        holder.ivIcon = (ImageView) convertView.findViewById(R.id.ivIcon);
        holder.tvName = (TextView) convertView.findViewById(R.id.tvName);

        final Shortcut shortcut = shortcuts.get(position);
        holder.ivIcon.setImageResource(shortcut.getResId());
        holder.tvName.setText(shortcut.getName());
        holder.llShortcut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WebActivity.gotoActivity(shortcut.getUrl());
            }
        });

        return convertView;
    }

    class ViewHolder {
        LinearLayout llShortcut;
        ImageView ivIcon;
        TextView tvName;
    }
}
