package com.itstaredu.utils;

import com.google.gson.Gson;
/**
 * GsonUtil类
 */
public class GsonUtil {
    // GsonUtil类
    private static Gson gson = new Gson();
    // Gson将Pojo转换成Json
    public static String in(Object pojo){
        return gson.toJson(pojo);
    }
    // 将Json 转换成对象
    public static Object out(String json,Class clazz){
        return gson.fromJson(json,clazz);
    }
}
