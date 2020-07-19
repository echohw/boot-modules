package com.example.commonbase.persistence;

import java.time.Instant;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import org.springframework.beans.factory.annotation.Configurable;

@Configurable
public class LongTimestampedEntityListener {

    @PrePersist
    public void touchForCreate(LongTimestampedEntity target) {
        Instant now = Instant.now();
        target.setUtcCreate(target.getUtcCreate() == null ? now : target.getUtcCreate());
        target.setUtcModified(target.getUtcModified() == null ? now : target.getUtcModified());
    }

    @PreUpdate
    public void touchForUpdate(LongTimestampedEntity target) {
        target.setUtcModified(Instant.now());
    }
}
