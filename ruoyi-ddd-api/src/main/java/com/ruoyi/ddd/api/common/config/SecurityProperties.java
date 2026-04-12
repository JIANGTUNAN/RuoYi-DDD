package com.ruoyi.ddd.api.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 安全配置属性
 * 从 application.yml 读取 ruoyi.security 配置
 *
 * @author tooolan
 * @since 2026年4月9日
 */
@Data
@Component
@ConfigurationProperties(prefix = "ruoyi.security")
public class SecurityProperties {

    /**
     * 是否开启 Mock 身份功能
     * 开启后，token 传入 test-{userId} 格式可自动以该用户身份登录
     * 仅用于开发环境，生产环境必须关闭
     */
    private boolean mockEnabled = false;

    /**
     * 公开路径白名单
     * 匹配这些路径的请求不需要登录校验
     * 支持 Ant 风格路径模式（如 /api/**）
     */
    private List<String> publicPaths = new ArrayList<>();

    /**
     * 允许跨域的前端地址列表
     * 支持通配符（如 http://192.168.*.*）
     */
    private List<String> corsOrigins = new ArrayList<>();

}
