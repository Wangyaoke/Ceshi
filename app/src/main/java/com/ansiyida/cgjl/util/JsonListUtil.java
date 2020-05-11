package com.ansiyida.cgjl.util;

import com.ansiyida.cgjl.tab.ChannelItem;

import java.util.ArrayList;

/**
 * Created by chenyaoxiang on 2017/12/6.
 */
public class JsonListUtil {
    public static ArrayList<ChannelItem> getChoiceItemList() {
        ArrayList<ChannelItem> defaultOtherChannels = new ArrayList<>();
        defaultOtherChannels.add(new ChannelItem(1, "推荐", 1, 1));
        defaultOtherChannels.add(new ChannelItem(2, "热点", 2, 1));
        defaultOtherChannels.add(new ChannelItem(3, "图片", 3, 1));
        defaultOtherChannels.add(new ChannelItem(4, "视频", 4, 1));
        defaultOtherChannels.add(new ChannelItem(5, "科技", 5, 1));
        defaultOtherChannels.add(new ChannelItem(6, "体育", 6, 1));
        defaultOtherChannels.add(new ChannelItem(7, "军事", 7, 1));
        return defaultOtherChannels;

    }

    public static ArrayList<ChannelItem> getDefaultItemList() {
        ArrayList<ChannelItem> defaultOtherChannels = new ArrayList<>();
        defaultOtherChannels.add(new ChannelItem(8, "财经", 1, 0));
        defaultOtherChannels.add(new ChannelItem(9, "汽车", 2, 0));
        defaultOtherChannels.add(new ChannelItem(10, "房产", 3, 0));
        defaultOtherChannels.add(new ChannelItem(11, "社会", 4, 0));
        defaultOtherChannels.add(new ChannelItem(12, "情感", 5, 0));
        defaultOtherChannels.add(new ChannelItem(13, "女人", 6, 0));
        defaultOtherChannels.add(new ChannelItem(14, "旅游", 7, 0));
        defaultOtherChannels.add(new ChannelItem(15, "健康", 8, 0));
        defaultOtherChannels.add(new ChannelItem(16, "美女", 9, 0));
        defaultOtherChannels.add(new ChannelItem(17, "游戏", 10, 0));
        defaultOtherChannels.add(new ChannelItem(18, "数码", 11, 0));

        return defaultOtherChannels;

    }

    public static ArrayList<ChannelItem> getMineChoiceItemList() {
        ArrayList<ChannelItem> defaultOtherChannels = new ArrayList<>();
        defaultOtherChannels.add(new ChannelItem(1, "推荐", 1, 1));
        defaultOtherChannels.add(new ChannelItem(2, "热点", 2, 1));
        defaultOtherChannels.add(new ChannelItem(3, "娱乐", 3, 1));
        defaultOtherChannels.add(new ChannelItem(4, "时尚", 4, 1));
        defaultOtherChannels.add(new ChannelItem(5, "科技", 5, 1));
        defaultOtherChannels.add(new ChannelItem(6, "体育", 6, 1));
        defaultOtherChannels.add(new ChannelItem(7, "军事", 7, 1));
        return defaultOtherChannels;

    }

    public static ArrayList<ChannelItem> getMineDefaultItemList() {
        ArrayList<ChannelItem> defaultOtherChannels = new ArrayList<>();
        defaultOtherChannels.add(new ChannelItem(8, "财经", 1, 0));
        defaultOtherChannels.add(new ChannelItem(9, "汽车", 2, 0));
        defaultOtherChannels.add(new ChannelItem(10, "房产", 3, 0));
        defaultOtherChannels.add(new ChannelItem(11, "社会", 4, 0));
        defaultOtherChannels.add(new ChannelItem(12, "情感", 5, 0));
        defaultOtherChannels.add(new ChannelItem(13, "女人", 6, 0));
        defaultOtherChannels.add(new ChannelItem(14, "旅游", 7, 0));
        defaultOtherChannels.add(new ChannelItem(15, "健康", 8, 0));
        defaultOtherChannels.add(new ChannelItem(16, "美女", 9, 0));
        defaultOtherChannels.add(new ChannelItem(17, "游戏", 10, 0));
        defaultOtherChannels.add(new ChannelItem(18, "数码", 11, 0));

        return defaultOtherChannels;

    }


}
