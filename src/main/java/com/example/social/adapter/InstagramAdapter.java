
package com.example.social.adapter;

import com.example.social.clients.InstagramClient;
import com.example.social.core.Result;
import com.example.social.core.SocialException;
import com.example.social.model.Content;
import com.example.social.model.Publication;
import com.example.social.model.Statistics;

import java.time.Instant;

public class InstagramAdapter implements SocialAdapter<String> {
    private InstagramClient client;

    @Override
    public Result<Void> authenticate(String token) {
        try {
            this.client = new InstagramClient(token);
            return Result.ok(null);
        } catch (Exception e) {
            return Result.fail("Instagram auth failed: " + e.getMessage());
        }
    }

    @Override
    public Result<Publication> publish(Content content) throws SocialException {
        if (client == null) throw new SocialException("Not authenticated with Instagram");
        try {
            String remoteId = client.createPost(content.getText());
            return Result.ok(new Publication(getPlatformName(), remoteId, Instant.now()));
        } catch (Exception e) {
            throw new SocialException("Instagram publish failed", e);
        }
    }

    @Override
    public Result<Statistics> fetchStatistics(String remotePublicationId) throws SocialException {
        // Instagram client exposes different methods; we simulate mapping
        if (client == null) throw new SocialException("Not authenticated with Instagram");
        try {
            int likes = client.reactions(remotePublicationId);
            return Result.ok(new Statistics(likes, 0, 0));
        } catch (Exception e) {
            throw new SocialException("Instagram stats failed", e);
        }
    }

    @Override
    public String getPlatformName() { return "INSTAGRAM"; }
}
