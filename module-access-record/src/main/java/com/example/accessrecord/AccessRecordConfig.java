package com.example.accessrecord;

import com.example.accessrecord.aspect.AccessRecordAroundAspect;
import com.example.accessrecord.aspect.AccessRecordBeforeAspect;
import com.example.accessrecord.objects.DefaultDesensitizeHandler;
import com.example.accessrecord.objects.DefaultVisitorInfoSupplier;
import com.example.accessrecord.objects.DesensitizeHandler;
import com.example.accessrecord.objects.VisitorInfoSupplier;
import com.example.commonbase.EnableCommonBase;
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
@EnableCommonBase
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

    @Bean
    @ConditionalOnMissingBean
    public VisitorInfoSupplier defaultVisitorInfoSupplier() {
        return new DefaultVisitorInfoSupplier();
    }

    @Bean
    @ConditionalOnMissingBean
    public DesensitizeHandler defaultDesensitizeHandler() {
        return new DefaultDesensitizeHandler();
    }

}
