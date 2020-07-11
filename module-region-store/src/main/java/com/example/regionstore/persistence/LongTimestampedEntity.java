package com.example.regionstore.persistence;

import java.time.Instant;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@EntityListeners({LongTimestampedEntityListener.class})
public abstract class LongTimestampedEntity {

    @Column(name = "utc_create", nullable = false)
    private Instant utcCreate;
    @Column(name = "utc_modified", nullable = false)
    private Instant utcModified;

    public void setUtcCreate(Instant utcCreate) {
        this.utcCreate = utcCreate;
    }

    public void setUtcModified(Instant utcModified) {
        this.utcModified = utcModified;
    }

    public Instant getUtcCreate() {
        return utcCreate;
    }

    public Instant getUtcModified() {
        return utcModified;
    }

    public Long getUtcCreateMilli() {
        if (utcCreate != null) {
            return utcCreate.toEpochMilli();
        }
        return null;
    }

    public Long getUtcModifiedMilli() {
        if (utcModified != null) {
            return utcModified.toEpochMilli();
        }
        return null;
    }
}
