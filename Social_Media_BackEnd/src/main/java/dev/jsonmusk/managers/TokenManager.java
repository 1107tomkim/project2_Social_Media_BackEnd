package dev.jsonmusk.managers;

public interface TokenManager {
    String issueTokenToUserId (int userId);

    boolean authorize (String token, int userId);

}
