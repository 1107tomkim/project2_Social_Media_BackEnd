package dev.jsonmusk.entities;

import java.sql.Timestamp;
import java.util.Date;

public class Comment {
    private int comment_id;
    private int userId;
    private String text;
    private Timestamp date;
    private int parentId;

    private int postId;

    private int liked = 0;

    private int disliked = 0;

    public Comment() {
    }

    public Comment(int comment_id, int userId, String text, Timestamp date, int parentId, int postId, int liked, int disliked) {
        this.comment_id = comment_id;
        this.userId = userId;
        this.text = text;
        this.date = date;
        this.parentId = parentId;
        this.postId = postId;
        this.liked = liked;
        this.disliked = disliked;
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

    @Override
    public String toString() {
        return "Comment{" +
                "comment_id=" + comment_id +
                ", userId=" + userId +
                ", text='" + text + '\'' +
                ", date=" + date +
                ", parentId=" + parentId +
                ", postId=" + postId +
                ", liked=" + liked +
                ", disliked=" + disliked +
                '}';
    }

    public int getComment_id() {
        return comment_id;
    }

    public void setComment_id(int comment_id) {
        this.comment_id = comment_id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }
}
