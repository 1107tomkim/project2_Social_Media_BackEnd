package dev.jsonmusk.entities;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;

public class Post {

    // id, userid, postText, postPhoto, date

    private String postText;


    private int postId;
    private int userId;

    private int liked;
    private int disliked;

    private Timestamp date;
    private byte[] postPhoto;

    public Post() {
        this.postText = "";
        this.postId = 0;
        this.userId = 0;
        this.date = null;
        this.postPhoto = null;
    }
    public Post(int userId, int postId, String postText, Timestamp date, byte[] postPhoto) {
        this.postText = postText;
        this.postId = postId;
        this.userId = userId;
        this.date = null;
        this.postPhoto = null;
    }

    @Override
    public String toString() {
        return "Post{" +
                "postText='" + postText + '\'' +
                ", postId=" + postId +
                ", userId=" + userId +
                ", liked=" + liked +
                ", disliked=" + disliked +
                ", date=" + date +
                ", postPhoto=" + Arrays.toString(postPhoto) +
                '}';
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public Timestamp getDate() {
        return date;
    }


    public int getLiked() {
        return liked;
    }

    public void setLiked(int liked) {
        this.liked = liked;
    }

    public int getDisliked() {
        return disliked;
    }

    public void setDisliked(int disliked) {
        this.disliked = disliked;
    }

    public String getPostText() {
        return postText;
    }

    public void setPostText(String postText) {
        this.postText = postText;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }



    public byte[] getPostPhoto() {
        return postPhoto;
    }

    public void setPostPhoto(byte[] postPhoto) {
        this.postPhoto = postPhoto;
    }

}
