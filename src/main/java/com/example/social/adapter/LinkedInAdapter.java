
package com.example.social.adapter;

import com.example.social.clients.LinkedInClient;
import com.example.social.core.Result;
import com.example.social.core.SocialException;
import com.example.social.model.Content;
import com.example.social.model.Publication;

import java.time.Instant;

public class LinkedInAdapter implements SocialAdapter<String> {
    private LinkedInClient client;

    @Override
    public Result<Void> authenticate(String token) {
        try {
            this.client = new LinkedInClient(token);
            return Result.ok(null);
        } catch (Exception e) {
            return Result.fail("LinkedIn auth failed: " + e.getMessage());
        }
    }

    @Override
    public Result<Publication> publish(Content content) throws SocialException {
        if (client == null) throw new SocialException("Not authenticated with LinkedIn");
        try {
            String remoteId = client.publishShare(content.getText());
            return Result.ok(new Publication(getPlatformName(), remoteId, Instant.now()));
        } catch (Exception e) {
            throw new SocialException("LinkedIn publish failed", e);
        }
    }

    @Override
    public Result<com.example.social.model.Statistics> fetchStatistics(String remotePublicationId) throws SocialException {
        // Not implemented in this simulated client
        throw new SocialException("LinkedIn stats not supported in simulation");
    }

    @Override
    public String getPlatformName() { return "LINKEDIN"; }
}
