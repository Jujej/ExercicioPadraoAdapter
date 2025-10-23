
package com.example.social.core;

import com.example.social.model.Content;
import com.example.social.model.Publication;
import com.example.social.model.Statistics;

public interface SocialManager {
    Result<Publication> publish(Content content) throws SocialException;
    Result<Statistics> stats(String publicationId) throws SocialException;
    String getPlatform();
}
