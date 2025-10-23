
package com.example.social.strategy;

import com.example.social.core.SocialManager;
import com.example.social.core.Result;
import com.example.social.core.SocialException;
import com.example.social.model.Content;

import java.util.concurrent.*;

/**
 * A simple scheduled strategy that posts after a delay asynchronously.
 */
public class ScheduledPublishStrategy implements PublishStrategy {
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    private final long delaySeconds;

    public ScheduledPublishStrategy(long delaySeconds) {
        this.delaySeconds = delaySeconds;
    }

    @Override
    public Result<?> publish(SocialManager manager, Content content) throws SocialException {
        CompletableFuture<Result<?>> future = new CompletableFuture<>();
        scheduler.schedule(() -> {
            try {
                Result<?> r = manager.publish(content);
                future.complete(r);
            } catch (SocialException e) {
                future.completeExceptionally(e);
            }
        }, delaySeconds, TimeUnit.SECONDS);

        try {
            // wait synchronously here for demo; in real app you'd return immediately.
            return future.get();
        } catch (ExecutionException | InterruptedException e) {
            throw new SocialException("Scheduled publish failed", e);
        }
    }
}
