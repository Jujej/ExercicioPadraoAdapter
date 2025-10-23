
package com.example.social;

import com.example.social.core.SocialManager;
import com.example.social.core.Result;
import com.example.social.factory.SocialMediaFactory;
import com.example.social.model.Content;
import com.example.social.model.Publication;
import com.example.social.model.Statistics;
import com.example.social.strategy.ImmediatePublishStrategy;
import com.example.social.strategy.PublishStrategy;

public class Main {
    public static void main(String[] args) {
        try {
            SocialManager manager = SocialMediaFactory.createFromConfig();
            System.out.println("Using platform: " + manager.getPlatform());

            Content c = new Content("Hello from Unified Social Manager!");

            PublishStrategy strategy = new ImmediatePublishStrategy();

            Result<?> res = strategy.publish(manager, c);
            if (res.isSuccess()) {
                Publication pub = (Publication) res.getData().orElse(null);
                System.out.println("Published: " + pub);
                Result<Statistics> stats = manager.stats(pub.getRemoteId());
                if (stats.isSuccess()) {
                    System.out.println("Stats: " + stats.getData().get());
                } else {
                    System.out.println("Stats error: " + stats.getErrorMessage().orElse("unknown"));
                }
            } else {
                System.out.println("Publish failed: " + res.getErrorMessage().orElse("unknown"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
