package com.home.tv;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.home.tv.widget.TvRecyclerView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    TvRecyclerView tvRecyclerCategory;
    TvRecyclerView tvRecyclerChannel;
    private CategoryAdapter categoryAdapter;
    private ChannelAdapter channelAdapter;
    private List<Category> categoryList;
    private List<Channel> channelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            RequestPermission requestPermission = new RequestPermission();
            requestPermission.RequestPermission(this);
        }
    }

    private void init() {
        tvRecyclerCategory = findViewById(R.id.tv_recycler_categorys);
        tvRecyclerChannel = findViewById(R.id.tv_recycler_channels);
        tvRecyclerCategory.TAG = "tvRecyclerCategory";
        tvRecyclerChannel.TAG = "tvRecyclerChannel";
        tvRecyclerCategory.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        tvRecyclerChannel.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        categoryList = Category.initCategoryList();
        channelList = Channel.initChannelList();
        categoryAdapter = new CategoryAdapter(getApplicationContext(), categoryList);
        channelAdapter = new ChannelAdapter(getApplicationContext(), channelList);
        tvRecyclerCategory.setAdapter(categoryAdapter);
        tvRecyclerChannel.setAdapter(channelAdapter);
        tvRecyclerCategory.setItemSelected(5);
        tvRecyclerChannel.setItemSelected(10);
        tvRecyclerCategory.setFocusDrawable(getResources().getDrawable(R.drawable.flowview02));
        tvRecyclerChannel.setFocusDrawable(getResources().getDrawable(R.drawable.flowview01));

        tvRecyclerCategory.setOnItemStateListener(new TvRecyclerView.OnItemStateListener() {
            @Override
            public void onClickItemView(View itemView, int position) {
                Log.d(TAG, "click position:" + position);
            }

            @Override
            public void onItemFocus(boolean hasFocus, View itemView, int position) {
                Log.d(TAG, "onItemFocus position:" + position + ",hasFocus:" + hasFocus);
            }
        });

        tvRecyclerCategory.setOnScrollStateListener(new TvRecyclerView.OnScrollStateListener() {
            @Override
            public void scrollToPrev(View view) {
                Log.d(TAG, "scrollToPrev");
            }

            @Override
            public void scrollToNext(View var1) {
                Log.d(TAG, "scrollToNext");
            }
        });

        tvRecyclerCategory.setOverstepBorderListener(new TvRecyclerView.OverstepBorderListener() {

            @Override
            public boolean notAllowOverStepBorder(View view, RecyclerView.ViewHolder viewHolder, int direction) {
                Log.d(TAG, "direction:" + direction);
                if (direction == 1 || direction == 3) {
                    return true;
                } else {
                    return false;
                }
            }
        });


        tvRecyclerChannel.setOnItemStateListener(new TvRecyclerView.OnItemStateListener() {
            @Override
            public void onClickItemView(View itemView, int position) {
                Log.i(TAG, "click position:" + position);
            }

            @Override
            public void onItemFocus(boolean hasFocus, View itemView, int position) {
                Log.i(TAG, "onItemFocus position:" + position + ",hasFocus:" + hasFocus);
            }
        });

        tvRecyclerChannel.setOnScrollStateListener(new TvRecyclerView.OnScrollStateListener() {
            @Override
            public void scrollToPrev(View view) {
                Log.i(TAG, "scrollToPrev");
            }

            @Override
            public void scrollToNext(View var1) {
                Log.i(TAG, "scrollToNext");
            }
        });

        tvRecyclerChannel.setOverstepBorderListener(new TvRecyclerView.OverstepBorderListener() {

            @Override
            public boolean notAllowOverStepBorder(View var1, RecyclerView.ViewHolder var2, int direction) {
                Log.i(TAG, "direction:" + direction);
                if (direction == TvRecyclerView.DIRECTION_UP || direction == TvRecyclerView.DIRECTION_DOWN) {
                    return true;  //1,3 上下焦点无法离开TvRecyclerView
                } else {
                    return false;   ////0,2 左右焦点允许离开TvRecyclerView
                }
            }
        });
    }
}