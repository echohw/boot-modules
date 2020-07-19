package com.example.commonbase;

import com.example.devutils.utils.id.IdUtils;
import com.example.devutils.utils.id.SnowFlake;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan
@Configuration
public class CommonBaseConfig {

    @Bean
    public IdUtils idUtils() {
        return new IdUtils(new SnowFlake());
    }

}
