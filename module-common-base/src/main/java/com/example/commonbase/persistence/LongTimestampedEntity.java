package com.example.commonbase.persistence;

import java.time.Instant;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
@EntityListeners({LongTimestampedEntityListener.class})
public abstract class LongTimestampedEntity {

    @Column(name = "utc_create")
    private Instant utcCreate; // 创建时间
    @Column(name = "utc_modified")
    private Instant utcModified; // 修改时间

    public Long getUtcCreateMilli() {
        return utcCreate != null ? utcCreate.toEpochMilli() : null;
    }

    public Long getUtcModifiedMilli() {
        return utcModified != null ? utcModified.toEpochMilli() : null;
    }
}
