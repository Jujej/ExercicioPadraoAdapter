
package com.example.social.adapter;

import com.example.social.model.Content;
import com.example.social.model.Publication;
import com.example.social.model.Statistics;
import com.example.social.core.Result;
import com.example.social.core.SocialException;

public interface SocialAdapter<TAuth> {
    // Authenticate using platform-specific credentials/objects.
    Result<Void> authenticate(TAuth auth);

    // Publish content, returning Publication info.
    Result<Publication> publish(Content content) throws SocialException;

    // Fetch statistics for a publication.
    Result<Statistics> fetchStatistics(String remotePublicationId) throws SocialException;

    String getPlatformName();
}
