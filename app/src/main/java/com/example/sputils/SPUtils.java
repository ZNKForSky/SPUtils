package com.example.sputils;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;


/**
 * author : Harris Luffy
 * e-mail : 744423651@qq.com
 * date   : 2019/9/3-15:11
 * desc   :SharesPreferens的工具类
 * version: 1.0
 */
public class SPUtils {

    private SPUtils() {
    }

    private static class SPUtilsBuilder {
        private static final SPUtils SP_UTILS = new SPUtils();
    }

    public static SPUtils getInstance() {
        return SPUtilsBuilder.SP_UTILS;
    }

    /**
     * 保存用户信息：包括用户名和密码
     *
     * @param context  用来获取SharedPreferences实例的上下文参数
     * @param userName 用户名
     * @param psw      用户密码
     */
    public void saveUserInfo(Context context, String userName, String psw) {
        /*
         * SharedPreferences将用户的数据存储到该包下的shared_prefs/userInfo.xml文件中，
         * 并且设置该文件的读取方式为私有，即只有该软件自身可以访问该文件
         */
        SharedPreferences userInfo = context.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = userInfo.edit();//获取SP的编辑器
        editor.putString("username", userName);
        editor.putString("password", psw);
        ////当然sharepreference会对一些特殊的字符进行转义，使得读取的时候更加准确，这里我们输入一些特殊的字符来实验效果
        editor.putString("specialtext", "hajsdh><?//");
        editor.putBoolean("or", true);
        editor.putInt("int", 47);
        //切记最后要使用commit或者apply方法将数据写入文件
        editor.apply();
    }

    public SharedPreferences getUserInfo(@NonNull Context context) {
        SharedPreferences userInfo = context.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        return userInfo;
    }

    public void clearUserInfo(@NonNull Context context) {
        SharedPreferences userInfo = context.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        userInfo.edit().clear().apply();
    }

}
