package com.example.commonclassify.persistence;

import java.time.Instant;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import org.springframework.beans.factory.annotation.Configurable;

@Configurable
public class LongTimestampedEntityListener {

    @PrePersist
    public void touchForCreate(LongTimestampedEntity target) {
        Instant now = Instant.now();
        if (target.getUtcCreate() == null) {
            target.setUtcCreate(now);
        }
        if (target.getUtcModified() == null) {
            target.setUtcModified(now);
        }
    }

    @PreUpdate
    public void touchForUpdate(LongTimestampedEntity target) {
        target.setUtcModified(Instant.now());
    }
}
