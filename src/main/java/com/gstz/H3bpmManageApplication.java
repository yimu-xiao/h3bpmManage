package com.gstz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author yimu
 * @version 1.0
 * @description:
 * @date 2023/6/12 15:42
 */
@EnableScheduling
@SpringBootApplication
public class H3bpmManageApplication {
    public static void main(String[] args) {
        SpringApplication.run(H3bpmManageApplication.class, args);
    }
}
