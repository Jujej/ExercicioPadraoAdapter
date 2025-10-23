
package com.example.social.clients;
public class LinkedInClient {
    private final String token;
    public LinkedInClient(String token) { this.token = token; }
    public String publishShare(String text) { return "li_" + Math.abs(text.hashCode()); }
}
