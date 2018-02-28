package com.example.isma3el.re_codedapp.Models;

//FeedCard

/**
 * Created by Recodedharran on 9.2.2018.
 */

public class FeedCard  {

    public static int STATUS = 0;
    public static int PROGRESS = 1;

    private User user;
    private String id;
    private String image;
    private String text;
    private int heartCounter = 0;
    private int happyCounter = 0;
    private int winkCounter = 0;
    private int nerdCounter = 0;
    private int inLoveCounter = 0;
    private int thumbsUpCounter = 0;
    private int postType;
    private String classRoom;

    public FeedCard(){

    }

    public FeedCard(User user, String image, String text, int postType, String classRoom) {
        this.user = user;
        this.image = image;
        this.text = text;
        this.postType = postType;
        this.classRoom = classRoom;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getHeartCounter() {
        return heartCounter;
    }

    public void setHeartCounter(int heartCounter) {
        this.heartCounter = heartCounter;
    }

    public int getHappyCounter() {
        return happyCounter;
    }

    public void setHappyCounter(int happyCounter) {
        this.happyCounter = happyCounter;
    }

    public int getWinkCounter() {
        return winkCounter;
    }

    public void setWinkCounter(int winkCounter) {
        this.winkCounter = winkCounter;
    }

    public int getNerdCounter() {
        return nerdCounter;
    }

    public void setNerdCounter(int nerdCounter) {
        this.nerdCounter = nerdCounter;
    }

    public int getInLoveCounter() {
        return inLoveCounter;
    }

    public void setInLoveCounter(int inLoveCounter) {
        this.inLoveCounter = inLoveCounter;
    }

    public int getThumbsUpCounter() {
        return thumbsUpCounter;
    }

    public void setThumbsUpCounter(int thumbsUpCounter) {
        this.thumbsUpCounter = thumbsUpCounter;
    }

    public int getPostType() {
        return postType;
    }

    public void setPostType(int postType) {
        this.postType = postType;
    }

    public String getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(String classRoom) {
        this.classRoom = classRoom;
    }
}