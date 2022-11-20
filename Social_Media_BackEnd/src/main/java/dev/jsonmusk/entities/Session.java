package dev.jsonmusk.entities;

public class Session {

    private int sessionId;

    private int userId;
    private String token;

    public Session(){
    }

    public Session(int sessionId, String token) {
        this.sessionId = sessionId;
        this.token = token;
    }

    public Session(int sessionId, String token, int userId) {
        this.sessionId = sessionId;
        this.token = token;
        this.userId = userId;
    }

    public Session(int sessionId, int userId, String token) {
        this.sessionId = sessionId;
        this.userId = userId;
        this.token = token;
    }

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getUserId() {        return userId;    }

    public void setUserId(int userId) {        this.userId = userId;    }

}