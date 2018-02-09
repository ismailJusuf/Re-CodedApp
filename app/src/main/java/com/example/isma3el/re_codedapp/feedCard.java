package com.example.isma3el.re_codedapp;

/**
 * Created by Recodedharran on 9.2.2018.
 */

public class feedCard {
    private int imagePostId;
    //private int imagePersonalIconId;
    private String personalName;
    private String addPostText;
    //private int imageHeartIconId;
    private String numberHeartText;
    //private int imageHappyIconId;
    private String numberHappyText;
    //private int imageWinkIconId;
    private String numberWinkText;
    //private int imageNerdIconId;
    private String numberNerdText;
    // private int imageInloveIconId;
    private String numberInloveText;
    //private int imageThumbsIconId;
    private String numberThumbsText;

    public feedCard(int imagePostId, String personalName, String addPostText, String numberHeartText, String numberHappyText, String numberWinkText, String numberNerdText, String numberInloveText, String numberThumbsText) {
        this.imagePostId = imagePostId;
        this.personalName = personalName;
        this.addPostText = addPostText;
        this.numberHeartText = numberHeartText;
        this.numberHappyText = numberHappyText;
        this.numberWinkText = numberWinkText;
        this.numberNerdText = numberNerdText;
        this.numberInloveText = numberInloveText;
        this.numberThumbsText = numberThumbsText;
    }

    public int getImagePostId() {
        return imagePostId;
    }

    public void setImagePostId(int imagePostId) {
        this.imagePostId = imagePostId;
    }

    public String getPersonalName() {
        return personalName;
    }

    public void setPersonalName(String personalName) {
        this.personalName = personalName;
    }

    public String getAddPostText() {
        return addPostText;
    }

    public void setAddPostText(String addPostText) {
        this.addPostText = addPostText;
    }

    public String getNumberHeartText() {
        return numberHeartText;
    }

    public void setNumberHeartText(String numberHeartText) {
        this.numberHeartText = numberHeartText;
    }

    public String getNumberHappyText() {
        return numberHappyText;
    }

    public void setNumberHappyText(String numberHappyText) {
        this.numberHappyText = numberHappyText;
    }

    public String getNumberWinkText() {
        return numberWinkText;
    }

    public void setNumberWinkText(String numberWinkText) {
        this.numberWinkText = numberWinkText;
    }

    public String getNumberNerdText() {
        return numberNerdText;
    }

    public void setNumberNerdText(String numberNerdText) {
        this.numberNerdText = numberNerdText;
    }

    public String getNumberInloveText() {
        return numberInloveText;
    }

    public void setNumberInloveText(String numberInloveText) {
        this.numberInloveText = numberInloveText;
    }

    public String getNumberThumbsText() {
        return numberThumbsText;
    }

    public void setNumberThumbsText(String numberThumbsText) {
        this.numberThumbsText = numberThumbsText;
    }
}
