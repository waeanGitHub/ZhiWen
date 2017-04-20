package com.example.waean.zhiwen.utils;

import com.example.waean.zhiwen.pojo.New;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by waean on 2017/04/06.
 */

public class JsonUtil {
    public static List<New> parseJSONWithJSONObjiect(String jsonData) {
        List<New> newList = null;
        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            JSONObject result = jsonObject.getJSONObject("result");
            JSONArray data = result.getJSONArray("data");
            Gson gson = new Gson();
            newList = gson.fromJson(data.toString(), new TypeToken<List<New>>() {
            }.getType());
           /* for (New aNew : newList) {
                LogUtil.d("JsonUtil", aNew.getTitle());
            }*/

        } catch (JSONException e) {
            e.printStackTrace();
            LogUtil.e("JsonUtil", "Json数据解析失败");
        }
        return newList;
    }
}
