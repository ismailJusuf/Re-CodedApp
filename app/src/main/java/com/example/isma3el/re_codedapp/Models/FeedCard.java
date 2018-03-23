package com.example.isma3el.re_codedapp.Models;

//FeedCard

/**
 * Created by Recodedharran on 9.2.2018.
 */

public class FeedCard {

    public static int STATUS = 0;
    public static int PROGRESS = 1;
    public static int TASK = 2;

    private User user;
    private String id;
    private String image;
    private String text;
    int emojiInLoveCounter = 0, emojiHappyCounter = 0, emojiHeartCounter = 0,
            emojiThumbsOnCounter = 0, emojiThumbsDownCounter = 0, emojiWinkCounter = 0;

    private int postType;
    private String classRoom;

    public FeedCard() {

    }

    public FeedCard(User user, String image, String text, int postType, String classRoom) {
        this.user = user;
        this.image = image;
        this.text = text;
        this.postType = postType;
        this.classRoom = classRoom;
    }



    public static int getSTATUS() {
        return STATUS;
    }

    public static void setSTATUS(int STATUS) {
        FeedCard.STATUS = STATUS;
    }

    public static int getPROGRESS() {
        return PROGRESS;
    }

    public static void setPROGRESS(int PROGRESS) {
        FeedCard.PROGRESS = PROGRESS;
    }

    public static int getTASK() {
        return TASK;
    }

    public static void setTASK(int TASK) {
        FeedCard.TASK = TASK;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public int getEmojiInLoveCounter() {
        return emojiInLoveCounter;
    }

    public void setEmojiInLoveCounter(int emojiInLoveCounter) {
        this.emojiInLoveCounter = emojiInLoveCounter;
    }

    public int getEmojiHappyCounter() {
        return emojiHappyCounter;
    }

    public void setEmojiHappyCounter(int emojiHappyCounter) {
        this.emojiHappyCounter = emojiHappyCounter;
    }

    public int getEmojiHeartCounter() {
        return emojiHeartCounter;
    }

    public void setEmojiHeartCounter(int emojiHeartCounter) {
        this.emojiHeartCounter = emojiHeartCounter;
    }

    public int getEmojiThumbsOnCounter() {
        return emojiThumbsOnCounter;
    }

    public void setEmojiThumbsOnCounter(int emojiThumbsOnCounter) {
        this.emojiThumbsOnCounter = emojiThumbsOnCounter;
    }

    public int getEmojiThumbsDownCounter() {
        return emojiThumbsDownCounter;
    }

    public void setEmojiThumbsDownCounter(int emojiThumbsDownCounter) {
        this.emojiThumbsDownCounter = emojiThumbsDownCounter;
    }

    public int getEmojiWinkCounter() {
        return emojiWinkCounter;
    }

    public void setEmojiWinkCounter(int emojiWinkCounter) {
        this.emojiWinkCounter = emojiWinkCounter;
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

    public FeedCard(User user, String id, String image, String text, int emojiInLoveCounter, int emojiHappyCounter, int emojiHeartCounter, int emojiThumbsOnCounter, int emojiThumbsDownCounter, int emojiWinkCounter, int postType, String classRoom) {
        this.user = user;
        this.id = id;
        this.image = image;
        this.text = text;
        this.emojiInLoveCounter = emojiInLoveCounter;
        this.emojiHappyCounter = emojiHappyCounter;
        this.emojiHeartCounter = emojiHeartCounter;
        this.emojiThumbsOnCounter = emojiThumbsOnCounter;
        this.emojiThumbsDownCounter = emojiThumbsDownCounter;
        this.emojiWinkCounter = emojiWinkCounter;
        this.postType = postType;
        this.classRoom = classRoom;
    }
}