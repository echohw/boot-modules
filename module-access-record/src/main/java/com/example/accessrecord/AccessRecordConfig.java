package com.example.accessrecord;

import com.example.accessrecord.aspect.AccessRecordAroundAspect;
import com.example.accessrecord.aspect.AccessRecordBeforeAspect;
import lombok.Data;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by AMe on 2020-07-10 02:30.
 */
@Data
@ComponentScan
@EnableJpaRepositories({"com.example.accessrecord.persistence.repos"})
@EntityScan({"com.example.accessrecord.persistence.entity"})
@Configuration
public class AccessRecordConfig {

    @Bean
    @ConditionalOnProperty(prefix = "access.record", name = "notice-type", havingValue = "around")
    public AccessRecordAroundAspect accessRecordAroundAspect() {
        return new AccessRecordAroundAspect();
    }

    @Bean
    @ConditionalOnMissingBean(AccessRecordAroundAspect.class)
    public AccessRecordBeforeAspect accessRecordBeforeAspect() {
        return new AccessRecordBeforeAspect();
    }

}
