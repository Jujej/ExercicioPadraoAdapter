
package com.example.social.strategy;

import com.example.social.core.SocialManager;
import com.example.social.core.Result;
import com.example.social.core.SocialException;
import com.example.social.model.Content;

public class ImmediatePublishStrategy implements PublishStrategy {
    @Override
    public Result<?> publish(SocialManager manager, Content content) throws SocialException {
        return manager.publish(content);
    }
}
