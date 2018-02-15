package com.example.isma3el.re_codedapp.Models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by isma3el on 2/7/2018.
 */

public class User {
    //@SerializedName( "token" ) public String token;
    @SerializedName( "_id" ) public String userId;
    @SerializedName( "fullName" ) public String fullName;
    @SerializedName( "image" ) public String image;
    @SerializedName( "email" ) public String email;
    @SerializedName( "password" ) public String password;
    @SerializedName( "phoneNumber" ) public String phoneNumber;
    @SerializedName( "bootCamp" ) public String bootCamp;
    @SerializedName( "nationality" ) public String nationality;
}
