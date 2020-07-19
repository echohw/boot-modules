package com.example.commonbase.persistence;

import java.time.Instant;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
@EntityListeners({LongUtcCreateEntityListener.class})
public abstract class LongUtcCreateEntity {

    @Column(name = "utc_create")
    private Instant utcCreate; // 创建时间

}
