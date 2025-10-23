
package com.example.social.model;

import java.time.Instant;

public class Publication {
    private final String platform;
    private final String remoteId;
    private final Instant publishedAt;

    public Publication(String platform, String remoteId, Instant publishedAt) {
        this.platform = platform;
        this.remoteId = remoteId;
        this.publishedAt = publishedAt;
    }

    public String getPlatform() { return platform; }
    public String getRemoteId() { return remoteId; }
    public Instant getPublishedAt() { return publishedAt; }

    @Override public String toString() {
        return "Publication{platform=" + platform + ", remoteId=" + remoteId + ", publishedAt=" + publishedAt + "}";
    }
}
