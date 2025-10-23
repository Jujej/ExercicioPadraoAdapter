
package com.example.social.factory;

import com.example.social.adapter.*;
import com.example.social.core.SocialManager;
import com.example.social.core.SocialManagerImpl;
import com.example.social.core.Result;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Factory method + simple registry. Thread-safe.
 */
public class SocialMediaFactory {
    private static final ConcurrentHashMap<String, SocialManager> registry = new ConcurrentHashMap<>();

    public static SocialManager createFromConfig() throws Exception {
        Properties p = new Properties();
        try (InputStream in = new FileInputStream("config.properties")) {
            p.load(in);
        }

        String platform = p.getProperty("platform", "TWITTER").toUpperCase().trim();
        return registry.computeIfAbsent(platform, SocialMediaFactory::createManagerFor);
    }

    private static SocialManager createManagerFor(String platform) {
        try {
            switch (platform) {
                case "TWITTER": {
                    TwitterAdapter adapter = new TwitterAdapter();
                    adapter.authenticate(System.getenv().getOrDefault("TWITTER_API_KEY", "demo-key"));
                    return new SocialManagerImpl<>(adapter);
                }
                case "INSTAGRAM": {
                    InstagramAdapter adapter = new InstagramAdapter();
                    adapter.authenticate(System.getenv().getOrDefault("INSTAGRAM_TOKEN", "demo-token"));
                    return new SocialManagerImpl<>(adapter);
                }
                case "LINKEDIN": {
                    LinkedInAdapter adapter = new LinkedInAdapter();
                    adapter.authenticate(System.getenv().getOrDefault("LINKEDIN_TOKEN", "demo-token"));
                    return new SocialManagerImpl<>(adapter);
                }
                case "TIKTOK": {
                    TikTokAdapter adapter = new TikTokAdapter();
                    adapter.authenticate(System.getenv().getOrDefault("TIKTOK_TOKEN", "demo-token"));
                    return new SocialManagerImpl<>(adapter);
                }
                default:
                    throw new IllegalArgumentException("Unsupported platform: " + platform);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
