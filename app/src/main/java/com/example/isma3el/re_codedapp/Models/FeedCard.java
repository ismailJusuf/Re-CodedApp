package com.example.isma3el.re_codedapp.Models;

/**
 * Created by Recodedharran on 9.2.2018.
 */

public class FeedCard {
    private int image;
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

    public FeedCard(int image, String userName, String text, String heartCounter, String happyCounter, String winkCounter, String nerdCounter, String inLoveCounter, String thumbsUpCounter) {
        this.image = image;
        this.userName = userName;
        this.text = text;
        this.heartCounter = heartCounter;
        this.happyCounter = happyCounter;
        this.winkCounter = winkCounter;
        this.nerdCounter = nerdCounter;
        this.inLoveCounter = inLoveCounter;
        this.thumbsUpCounter = thumbsUpCounter;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
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
}
