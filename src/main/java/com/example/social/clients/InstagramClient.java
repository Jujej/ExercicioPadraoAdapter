
package com.example.social.clients;

// Simulated external client
public class InstagramClient {
    private final String token;
    public InstagramClient(String token) { this.token = token; }
    public String createPost(String text) { return "ig_" + Math.abs(text.hashCode()); }
    public int reactions(String id) { return Math.abs(id.hashCode() % 2000); }
}
