package com.home.tv;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.home.tv.widget.TvRecyclerView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    TvRecyclerView tvRecyclerCagegory;
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
    }

    private void init() {
        tvRecyclerCagegory = findViewById(R.id.tv_recycler_categorys);
        tvRecyclerChannel = findViewById(R.id.tv_recycler_channels);
        tvRecyclerCagegory.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        tvRecyclerChannel.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        categoryList = Category.initCategoryList();
        channelList = Channel.initChannelList();
        categoryAdapter = new CategoryAdapter(getApplicationContext(), categoryList);
        channelAdapter = new ChannelAdapter(getApplicationContext(), channelList);
        tvRecyclerCagegory.setAdapter(categoryAdapter);
        tvRecyclerChannel.setAdapter(channelAdapter);
        tvRecyclerCagegory.setItemSelected(3);
        tvRecyclerChannel.setItemSelected(6);
        tvRecyclerCagegory.setFocusDrawable(getResources().getDrawable(R.drawable.flowview01));
        tvRecyclerChannel.setFocusDrawable(getResources().getDrawable(R.drawable.flowview02));

        tvRecyclerCagegory.setOnItemStateListener(new TvRecyclerView.b() {
            @Override
            public void a(View var1, int var2) {

            }

            @Override
            public void a(boolean var1, View var2, int var3) {

            }
        });

        tvRecyclerCagegory.setOnScrollStateListener(new TvRecyclerView.e() {
            @Override
            public void a(View var1) {

            }

            @Override
            public void b(View var1) {

            }
        });

        tvRecyclerCagegory.setOverstepBorderListener(new TvRecyclerView.c() {

            @Override
            public boolean a(View var1, RecyclerView.ViewHolder var2, int var3) {
                Log.d(TAG, "var3:" + var3);
                if (var3 == 1 || var3 == 3) {
                    return true;
                } else {
                    return false;
                }
            }
        });


        tvRecyclerChannel.setOnItemStateListener(new TvRecyclerView.b() {
            @Override
            public void a(View var1, int var2) {

            }

            @Override
            public void a(boolean var1, View var2, int var3) {

            }
        });

        tvRecyclerChannel.setOnScrollStateListener(new TvRecyclerView.e() {
            @Override
            public void a(View var1) {

            }

            @Override
            public void b(View var1) {

            }
        });

        tvRecyclerChannel.setOverstepBorderListener(new TvRecyclerView.c() {

            @Override
            public boolean a(View var1, RecyclerView.ViewHolder var2, int var3) {
                Log.i(TAG, "var3:" + var3);
                if (var3 == 1 || var3 == 3) {
                    return true;
                } else {
                    return false;
                }
            }
        });
    }
}