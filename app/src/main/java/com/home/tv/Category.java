package com.home.tv;

import java.util.ArrayList;
import java.util.List;

public class Category {
    private int index;
    private String name;

    public Category(int index, String name, int score) {
        this.index = index;
        this.name = name;
    }

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


    public Category(int index, String name) {
        this.index = index;
        this.name = name;
    }

    public static List<Category> initCategoryList() {
        List<Category> list = new ArrayList<>();
        Category category0 = new Category(0, "收藏");
        Category category1 = new Category(1, "爱看榜");
        Category category2 = new Category(2, "央视");
        Category category3 = new Category(3, "卫视");
        Category category4 = new Category(4, "无线");
        Category category5 = new Category(5, "广告");
        Category category6 = new Category(6, "电影");
        Category category7 = new Category(7, "动画");
        Category category8 = new Category(8, "冒险");
        Category category9 = new Category(9, "娱乐综艺");
        Category category10 = new Category(10, "世界景色");
        Category category11 = new Category(11, "精选节目");
        list.add(category0);
        list.add(category1);
        list.add(category2);
        list.add(category3);
        list.add(category4);
        list.add(category5);
        list.add(category6);
        list.add(category7);
        list.add(category8);
        list.add(category9);
        list.add(category10);
        list.add(category11);
        return list;
    }
}
