
package com.example.social.adapter;

import com.example.social.clients.TikTokClient;
import com.example.social.core.Result;
import com.example.social.core.SocialException;
import com.example.social.model.Content;
import com.example.social.model.Publication;

import java.time.Instant;

public class TikTokAdapter implements SocialAdapter<String> {
    private TikTokClient client;

    @Override
    public Result<Void> authenticate(String token) {
        try {
            this.client = new TikTokClient(token);
            return Result.ok(null);
        } catch (Exception e) {
            return Result.fail("TikTok auth failed: " + e.getMessage());
        }
    }

    @Override
    public Result<Publication> publish(Content content) throws SocialException {
        if (client == null) throw new SocialException("Not authenticated with TikTok");
        try {
            String remoteId = client.uploadVideo(content.getText());
            return Result.ok(new Publication(getPlatformName(), remoteId, Instant.now()));
        } catch (Exception e) {
            throw new SocialException("TikTok publish failed", e);
        }
    }

    @Override
    public Result<com.example.social.model.Statistics> fetchStatistics(String remotePublicationId) throws SocialException {
        throw new SocialException("TikTok stats not supported in simulation");
    }

    @Override
    public String getPlatformName() { return "TIKTOK"; }
}
