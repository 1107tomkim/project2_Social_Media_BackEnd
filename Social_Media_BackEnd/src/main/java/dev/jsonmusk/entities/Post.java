package dev.jsonmusk.entities;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;

public class Post {

    // id, userid, postText, postPhoto, date

    private String postText;

    private String username;
    private int postId;
    private int userId;

    private int liked;
    private int disliked;
    private int liker;

    private int[] liked_by;

    public Post(String postText, String username, int postId, int userId, int liked, int disliked, int liker, int[] liked_by, int[] disliked_by, Timestamp date, byte[] postPhoto) {
        this.postText = postText;
        this.username = username;
        this.postId = postId;
        this.userId = userId;
        this.liked = liked;
        this.disliked = disliked;
        this.liker = liker;
        this.liked_by = liked_by;
        this.disliked_by = disliked_by;
        this.date = date;
        this.postPhoto = postPhoto;
    }

    private int[] disliked_by;

    private Timestamp date;
    private byte[] postPhoto;

    public Post() {
    }


    public Post(String postText, byte[] postPhoto) {
        this.postText = postText;
        this.postPhoto = postPhoto;
    }
    public Post(String username, int userId, int postId, String postText, Timestamp date, byte[] postPhoto) {
        this.postText = postText;
        this.username = username;
        this.postId = postId;
        this.userId = userId;
        this.date = null;
        this.postPhoto = null;
    }

    public Post(String postText, String username){
        this.postText = postText;
        this.username = username;
    }


    public int getLiker() {
        return liker;
    }

    public void setLiker(int liker) {
        this.liker = liker;
    }

    public int[] getLiked_by() {
        return liked_by;
    }

    public void setLiked_by(int[] liked_by) {
        this.liked_by = liked_by;
    }

    public int[] getDisliked_by() {
        return disliked_by;
    }

    public void setDisliked_by(int[] disliked_by) {
        this.disliked_by = disliked_by;
    }

    @Override
    public String toString() {
        return "Post{" +
                "postText='" + postText + '\'' +
                ", username='" + username + '\'' +
                ", postId=" + postId +
                ", userId=" + userId +
                ", liked=" + liked +
                ", disliked=" + disliked +
                ", liker=" + liker +
                ", liked_by=" + Arrays.toString(liked_by) +
                ", disliked_by=" + Arrays.toString(disliked_by) +
                ", date=" + date +
                ", postPhoto=" + Arrays.toString(postPhoto) +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
