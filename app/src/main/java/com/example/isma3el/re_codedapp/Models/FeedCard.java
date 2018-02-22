package com.example.isma3el.re_codedapp.Models;

//FeedCard
/**
 * Created by Recodedharran on 9.2.2018.
 */

public class FeedCard {
    private String image;
    //private int imagePersonalIconId;
    private String userName;
    private String text;
    //private int imageHeartIconId;
    private String heartCounter;
    //private int imageHappyIconId;
    private String happyCounter;
    //private int imageWinkIconId;
    private String winkCounter;
    //private int imageNerdIconId;
    private String nerdCounter;
    // private int imageInloveIconId;
    private String inLoveCounter;
    //private int imageThumbsIconId;
    private String thumbsUpCounter;
    private String postType;

    public FeedCard(String image, String userName, String text, String heartCounter, String happyCounter, String winkCounter, String nerdCounter, String inLoveCounter, String thumbsUpCounter, String postType) {
        this.image = image;
        this.userName = userName;
        this.text = text;
        this.heartCounter = heartCounter;
        this.happyCounter = happyCounter;
        this.winkCounter = winkCounter;
        this.nerdCounter = nerdCounter;
        this.inLoveCounter = inLoveCounter;
        this.thumbsUpCounter = thumbsUpCounter;
        this.postType = postType;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getHeartCounter() {
        return heartCounter;
    }

    public void setHeartCounter(String heartCounter) {
        this.heartCounter = heartCounter;
    }

    public String getHappyCounter() {
        return happyCounter;
    }

    public void setHappyCounter(String happyCounter) {
        this.happyCounter = happyCounter;
    }

    public String getWinkCounter() {
        return winkCounter;
    }

    public void setWinkCounter(String winkCounter) {
        this.winkCounter = winkCounter;
    }

    public String getNerdCounter() {
        return nerdCounter;
    }

    public void setNerdCounter(String nerdCounter) {
        this.nerdCounter = nerdCounter;
    }

    public String getInLoveCounter() {
        return inLoveCounter;
    }

    public void setInLoveCounter(String inLoveCounter) {
        this.inLoveCounter = inLoveCounter;
    }

    public String getThumbsUpCounter() {
        return thumbsUpCounter;
    }

    public void setThumbsUpCounter(String thumbsUpCounter) {
        this.thumbsUpCounter = thumbsUpCounter;
    }

    public String getPostType() {
        return postType;
    }

    public void setPostType(String postType) {
        this.postType = postType;
    }
}