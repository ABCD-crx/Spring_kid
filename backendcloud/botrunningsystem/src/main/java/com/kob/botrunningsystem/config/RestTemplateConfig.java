package com.kob.botrunningsystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Author: chen
 * Time: 2024/2/5 14:54
 */

// 向微服务发送请求时用到 restTemplate + ribbon

    // resTemplate 用于两个spingboot 进程间通信
@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}

// 用到某个东西定义他的 Configuration + @Bean 返回实例
