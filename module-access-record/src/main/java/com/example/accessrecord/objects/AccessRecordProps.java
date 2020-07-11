package com.example.accessrecord.objects;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by AMe on 2020-07-12 00:37.
 */
@Data
@Component
@ConfigurationProperties(prefix = "access.record")
public class AccessRecordProps {

    private String noticeType = "before"; // 通知类型 before or around
    private String scope = "module-access-record"; // 数据所属域
}
