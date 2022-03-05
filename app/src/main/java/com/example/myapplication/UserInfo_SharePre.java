package com.example.myapplication;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;
import java.util.Map;

public class UserInfo_SharePre {
    public static boolean saveUserInfo(String username, String password, Context context){
        //获取SharedPreferences对象，同时指定文件名称和文件对象
        SharedPreferences sp=context.getSharedPreferences("MyData",Context.MODE_PRIVATE);
        //获取SharedPreferences编辑器对象
        SharedPreferences.Editor editor=sp.edit();
        //通过编辑器进行数据的存储
        editor.putString("username",username);
        editor.putString("password",password);
        editor.commit();
        return true;
    }
    //读取用户数据的方法
    public static Map<String,String> getUserInfo(Context context){
        //获取SharedPreferences对象，同时指定文件名称和文件对象
        SharedPreferences sp=context.getSharedPreferences("MyData",Context.MODE_PRIVATE);
        Map<String,String> userMap=new HashMap<>();
        userMap.put("username",sp.getString("username",""));
        userMap.put("password",sp.getString("username",""));
        return userMap;
    }
}
