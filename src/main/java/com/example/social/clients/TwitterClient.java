
package com.example.social.clients;

// Simulated external client - DO NOT MODIFY (treat as 3rd party lib)
public class TwitterClient {
    private final String apiKey;

    public TwitterClient(String apiKey) {
        this.apiKey = apiKey;
    }

    public String postTweet(String message) {
        // Simulated remote id
        return "tw_" + Math.abs(message.hashCode());
    }

    public int getLikes(String id) { return Math.abs(id.hashCode() % 1000); }
    public int getShares(String id) { return Math.abs(id.hashCode() % 500); }
    public int getComments(String id) { return Math.abs(id.hashCode() % 200); }
}
