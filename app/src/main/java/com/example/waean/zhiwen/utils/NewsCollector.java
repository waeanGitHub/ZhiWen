package com.example.waean.zhiwen.utils;


import com.example.waean.zhiwen.pojo.New;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by waean on 2017/04/20.
 */

public class NewsCollector {
    private static final String TAG = "NewsCollector";
    public static List<New> newList = new ArrayList<>();

    //收藏一条新闻
    public static void addNew(New aNew) {
        if (!findNewOnNewList(aNew)) {
            //不存在相同的收藏项
            newList.add(aNew);
            LogUtil.i(TAG, "addNew" + ":success");
        } else {
            //存在相同的收藏项
            LogUtil.i(TAG, "addNew" + ":failure");
        }
    }

    //取消收藏一条新闻
    public static void removeNew(New aNew) {
        if (!findNewOnNewList(aNew)) {
            //不存在相同的收藏项
            LogUtil.i(TAG, "removeNew" + ":success");
        } else {
            //存在相同的收藏项
            newList.remove(aNew);
            LogUtil.i(TAG, "removeNew" + ":success");
        }
    }

    //清空收藏
    public static void removeAllNew() {
        newList.clear();
        LogUtil.i(TAG, "removeAllNew" + ":success");
    }

    public static boolean findNewOnNewList(New aNew) {
        boolean isFind = false;
//        newList.contains(aNew);
        for (New aNew1 :
                newList) {
            if (aNew.getUniquekey().equals(aNew1.getUniquekey())) {
                isFind = true;
                return isFind;
            }
        }
        return isFind;
    }


}
