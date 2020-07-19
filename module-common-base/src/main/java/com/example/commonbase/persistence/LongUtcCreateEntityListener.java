package com.example.commonbase.persistence;

import java.time.Instant;
import javax.persistence.PrePersist;
import org.springframework.beans.factory.annotation.Configurable;

@Configurable
public class LongUtcCreateEntityListener {

    @PrePersist
    public void touchForCreate(LongUtcCreateEntity target) {
        target.setUtcCreate(target.getUtcCreate() == null ? Instant.now() : target.getUtcCreate());
    }
}
