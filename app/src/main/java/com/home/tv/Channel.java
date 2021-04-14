package com.home.tv;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName Channel
 * @Description TODO
 * @Author Administrator
 * @Date 2021/4/9 15:59
 * @Version 1.0
 */
public class Channel {

    private int index;
    private String name;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Channel(int index, String name) {
        this.index = index;
        this.name = name;
    }

    public static List<Channel> initChannelList() {
        List<Channel> list = new ArrayList<>();
//        Channel video0 = new Channel(0, "中央一台");
//        Channel video1 = new Channel(1, "北京卫视");
//        Channel video2 = new Channel(2, "中央二台");
//        Channel video3 = new Channel(3, "天津无线");
//        Channel video4 = new Channel(4, "东方卫视");
//        Channel video5 = new Channel(5, "湖南卫视");
//        Channel video6 = new Channel(6, "陕西电视");
//        Channel video7 = new Channel(7, "中央8台");
//        Channel video8 = new Channel(8, "北京卫视");
//        Channel video9 = new Channel(9, "中央二台");
//        Channel video10 = new Channel(10, "CCTV7");
//        Channel video11 = new Channel(11, "东方卫视");
//        Channel video12 = new Channel(12, "湖南卫视");
//        Channel video13 = new Channel(13, "山东卫视");
//        Channel video14 = new Channel(14, "上海电视");
//        Channel video15 = new Channel(15, "大庆公共");
//        Channel video16 = new Channel(16, "电影节目");
//        list.add(video0);
//        list.add(video1);
//        list.add(video2);
//        list.add(video3);
//        list.add(video4);
//        list.add(video5);
//        list.add(video6);
//        list.add(video7);
//        list.add(video8);
//        list.add(video9);
//        list.add(video10);
//        list.add(video11);
//        list.add(video12);
//        list.add(video13);
//        list.add(video14);
//        list.add(video15);
//        list.add(video16);

        for(int i=0;i<2000;i++){
            Channel channel = new Channel(i, "电影节目");
            list.add(channel);
        }
        return list;
    }
}
