
package com.example.social.adapter;

import com.example.social.clients.TwitterClient;
import com.example.social.core.Result;
import com.example.social.core.SocialException;
import com.example.social.model.Content;
import com.example.social.model.Publication;
import com.example.social.model.Statistics;

import java.time.Instant;

public class TwitterAdapter implements SocialAdapter<String> {
    private volatile TwitterClient client; // composition, client is a third-party wrapper

    @Override
    public Result<Void> authenticate(String apiKey) {
        try {
            this.client = new TwitterClient(apiKey);
            return Result.ok(null);
        } catch (Exception e) {
            return Result.fail("Twitter auth failed: " + e.getMessage());
        }
    }

    @Override
    public Result<Publication> publish(Content content) throws SocialException {
        if (client == null) throw new SocialException("Not authenticated with Twitter");
        try {
            String remoteId = client.postTweet(content.getText());
            Publication p = new Publication(getPlatformName(), remoteId, Instant.now());
            return Result.ok(p);
        } catch (Exception e) {
            throw new SocialException("Twitter publish failed", e);
        }
    }

    @Override
    public Result<Statistics> fetchStatistics(String remotePublicationId) throws SocialException {
        if (client == null) throw new SocialException("Not authenticated with Twitter");
        try {
            int likes = client.getLikes(remotePublicationId);
            int shares = client.getShares(remotePublicationId);
            int comments = client.getComments(remotePublicationId);
            return Result.ok(new Statistics(likes, shares, comments));
        } catch (Exception e) {
            throw new SocialException("Twitter fetch statistics failed", e);
        }
    }

    @Override
    public String getPlatformName() { return "TWITTER"; }
}
