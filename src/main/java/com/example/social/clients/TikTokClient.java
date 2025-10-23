
package com.example.social.clients;
public class TikTokClient {
    private final String token;
    public TikTokClient(String token) { this.token = token; }
    public String uploadVideo(String description) { return "tt_" + Math.abs(description.hashCode()); }
}
