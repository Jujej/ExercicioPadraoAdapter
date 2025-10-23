
package com.example.social.strategy;

import com.example.social.model.Content;
import com.example.social.core.SocialManager;
import com.example.social.core.SocialException;
import com.example.social.core.Result;

public interface PublishStrategy {
    Result<?> publish(SocialManager manager, Content content) throws SocialException;
}
