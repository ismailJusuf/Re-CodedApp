package com.example.isma3el.re_codedapp.Models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by isma3el on 2/7/2018.
 */

public class User {
    @SerializedName( "_id" ) public String userId;
    @SerializedName( "userName" ) public String userName;
    @SerializedName( "bootCamp" ) public String bootCamp;
    @SerializedName( "token" ) public String token;
    @SerializedName( "image" ) public String image;
}
