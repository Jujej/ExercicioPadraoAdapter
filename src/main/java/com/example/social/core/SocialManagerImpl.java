
package com.example.social.core;

import com.example.social.adapter.SocialAdapter;
import com.example.social.model.Content;
import com.example.social.model.Publication;
import com.example.social.model.Statistics;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Thread-safe manager that uses an adapter (composition).
 */
public class SocialManagerImpl<TAuth> implements SocialManager {
    private final SocialAdapter<TAuth> adapter;
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public SocialManagerImpl(SocialAdapter<TAuth> adapter) {
        this.adapter = adapter;
    }

    @Override
    public Result<Publication> publish(Content content) throws SocialException {
        lock.readLock().lock();
        try {
            return adapter.publish(content);
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public Result<Statistics> stats(String publicationId) throws SocialException {
        lock.readLock().lock();
        try {
            return adapter.fetchStatistics(publicationId);
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public String getPlatform() {
        return adapter.getPlatformName();
    }
}
