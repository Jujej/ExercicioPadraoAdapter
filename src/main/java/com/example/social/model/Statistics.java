
package com.example.social.model;

public class Statistics {
    private final int likes;
    private final int shares;
    private final int comments;

    public Statistics(int likes, int shares, int comments) {
        this.likes = likes;
        this.shares = shares;
        this.comments = comments;
    }

    public int getLikes() { return likes; }
    public int getShares() { return shares; }
    public int getComments() { return comments; }

    @Override public String toString() {
        return "Statistics{likes=" + likes + ", shares=" + shares + ", comments=" + comments + "}";
    }
}
