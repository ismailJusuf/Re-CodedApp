package com.example.isma3el.re_codedapp;

//BaseActivity

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.isma3el.re_codedapp.Models.User;
import com.google.gson.Gson;

import io.realm.Realm;

/**
 * Created by isma3el on 2/7/2018.
 */

public class BaseActivity extends AppCompatActivity {

    SharedPreferences preferences;
    public static BaseActivity instance;
    public static BaseActivity getInstance()
    {
        return instance;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance=this;
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
    }

    public User getUser() {

        return new Gson().fromJson(preferences.getString("savedUser", ""), User.class);

    }

    public void saveUser(User user) {

        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("savedUser", new Gson().toJson(user));
        editor.commit();
    }


   public  boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager)  getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        } else {
            return false;
        }

    }
}