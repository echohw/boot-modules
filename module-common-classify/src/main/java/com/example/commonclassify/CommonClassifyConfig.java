package com.example.commonclassify;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by AMe on 2020-07-08 18:58.
 */
@ComponentScan
@EnableJpaRepositories({"com.example.commonclassify.persistence.repos"})
@EntityScan({"com.example.commonclassify.persistence.entity"})
@Configuration
public class CommonClassifyConfig {

}
