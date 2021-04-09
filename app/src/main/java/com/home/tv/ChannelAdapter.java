package com.home.tv;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * @ClassName VideoAdapter
 * @Description TODO
 * @Author Administrator
 * @Date 2021/4/9 16:05
 * @Version 1.0
 */
public class ChannelAdapter extends RecyclerView.Adapter<ChannelAdapter.VH> {
    private Context context;
    private List<Channel> channelList;

    public ChannelAdapter(Context context, List<Channel> channelList) {
        this.context = context;
        this.channelList = channelList;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VH(LayoutInflater.from(context).inflate(R.layout.item_channel, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        Channel channel = channelList.get(position);
        holder.tvName.setText(channel.getName());
    }

    @Override
    public int getItemCount() {
        return channelList.size();
    }

    static class VH extends RecyclerView.ViewHolder {
        private TextView tvName;

        public VH(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.txt_name);
        }
    }
}