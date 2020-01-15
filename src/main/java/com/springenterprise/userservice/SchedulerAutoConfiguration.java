package com.springenterprise.userservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.util.concurrent.Executors;

@Slf4j
@Configuration
public class SchedulerAutoConfiguration {
    private final Integer connectionPoolSize;

    public SchedulerAutoConfiguration(@Value("${spring.datasource.maximum-pool-size:10}") Integer connectionPoolSize) {
        this.connectionPoolSize = connectionPoolSize;
        log.info("Created");
    }

    @Bean
    public Scheduler jdbcScheduler() {
        log.info("Creates a jdbcScheduler with connectionPoolSize = " + connectionPoolSize);
        return Schedulers.fromExecutor(Executors.newFixedThreadPool(connectionPoolSize));
    }
}
