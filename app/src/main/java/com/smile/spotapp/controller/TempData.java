package com.smile.spotapp.controller;

import android.content.Context;
import android.content.SharedPreferences;

public class TempData {

    private static SharedPreferences sf;
    private static SharedPreferences.Editor ed;
    Context mcontext;

    public TempData(Context mcontext) {
        this.mcontext = mcontext;
        sf = mcontext.getSharedPreferences("spot",Context.MODE_PRIVATE);
        ed = sf.edit();
        ed.apply();
    }

    public  void addlogdetails(String log){
        ed.putString("log",log).apply();
    }

    public  String getlog(){
        return sf.getString("log",null);
    }

    public void adduid(String uid){
        ed.putString("uid",uid).apply();
    }

    public String getuid(){
        return sf.getString("uid",null);
    }

    public void logout(){
        ed.clear().apply();
    }

}
