package com.ruoyi.ddd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * RuoYi DDD 领域驱动设计 启动类
 * 负责初始化 Spring Boot 应用上下文并启动服务
 *
 * @author tooolan
 * @since 2026年4月7日
 */
@SpringBootApplication
public class RuoYiDddApplication {

    /**
     * 应用程序入口方法
     * 启动 Spring Boot 应用，初始化应用上下文和 Web 服务器
     *
     * @param args 命令行参数，可用于传递运行时配置
     */
    public static void main(String[] args) {
        SpringApplication.run(RuoYiDddApplication.class, args);
    }

}
