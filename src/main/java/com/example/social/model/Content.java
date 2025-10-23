
package com.example.social.model;

import java.time.Instant;
import java.util.UUID;

public class Content {
    private final String id;
    private final String text;
    private final Instant created;

    public Content(String text) {
        this.id = UUID.randomUUID().toString();
        this.text = text;
        this.created = Instant.now();
    }

    public String getId() { return id; }
    public String getText() { return text; }
    public Instant getCreated() { return created; }

    @Override public String toString() {
        return "Content{id=" + id + ", text='" + text + "'}";
    }
}
