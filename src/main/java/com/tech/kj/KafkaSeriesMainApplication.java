package com.tech.kj;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class KafkaSeriesMainApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context= SpringApplication.run(KafkaSeriesMainApplication.class, args);
    }
}
