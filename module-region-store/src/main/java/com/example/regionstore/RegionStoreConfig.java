package com.example.regionstore;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by AMe on 2020-07-10 02:30.
 */
@ComponentScan
@EnableJpaRepositories({"com.example.regionstore.persistence.repos"})
@EntityScan({"com.example.regionstore.persistence.entity"})
@Configuration
public class RegionStoreConfig {

}
