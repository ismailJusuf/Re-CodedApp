package com.example.isma3el.re_codedapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.isma3el.re_codedapp.Models.User;
import com.google.gson.Gson;

/**
 * Created by isma3el on 2/7/2018.
 */

public class BaseActivity extends AppCompatActivity {

    SharedPreferences preferences;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        preferences = PreferenceManager.getDefaultSharedPreferences( this );
    }

    public void saveUser(User user) {
        preferences.edit().putString( "user", new Gson().toJson( user ) );
    }

    public User getUser() {
        return new Gson().fromJson( preferences.getString( "user", new Gson().toJson( new User() ) ), User.class );
    }
}
