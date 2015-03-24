package com.example.pavilion.androidschool.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.pavilion.androidschool.R;
import com.example.pavilion.androidschool.model.VideoEntry;

import java.util.List;

public class VideoListAdapter extends BaseAdapter{

    private final List<VideoEntry> videoEntries;
    private Context context;
    private LayoutInflater inflater;

    public VideoListAdapter(Context context, List<VideoEntry> videoEntries) {
        this.context = context;
        this.videoEntries = videoEntries;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return videoEntries.size();
    }

    @Override
    public VideoEntry getItem(int position) {
        return videoEntries.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder viewHolder;

        if (view == null) {
            view = inflater.inflate(R.layout.video_list_item, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.tvitem = (TextView) view.findViewById(R.id.tvItem);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.tvitem.setText(videoEntries.get(position).Text);

        return view;
    }

    static class ViewHolder{
        public TextView tvitem;

    }
}
