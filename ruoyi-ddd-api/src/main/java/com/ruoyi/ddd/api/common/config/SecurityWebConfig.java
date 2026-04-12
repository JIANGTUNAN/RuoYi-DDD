package com.ruoyi.ddd.api.common.config;

import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.collection.CollUtil;
import com.ruoyi.ddd.api.common.web.UserContextInterceptor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * Web 安全配置
 * 注册 UserContextInterceptor（Sa-Token 拦截 + 上下文初始化）和 CORS 跨域配置
 *
 * @author tooolan
 * @since 2026年4月9日
 */
@Configuration
@RequiredArgsConstructor
public class SecurityWebConfig implements WebMvcConfigurer {

    private final SecurityProperties securityProperties;

    /**
     * 注册拦截器
     * UserContextInterceptor 继承 SaInterceptor，同时处理：
     * 1. Mock 身份处理（test-{userId} 格式 token）
     * 2. Sa-Token 路由拦截和登录认证
     * 3. 用户上下文的初始化和清理
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new UserContextInterceptor(handle -> {
            // 使用 SaRouter 匹配所有路径，排除白名单，然后校验登录
            SaRouter
                    .match("/**")
                    .notMatch(securityProperties.getPublicPaths())
                    .check(r -> StpUtil.checkLogin());
        }, securityProperties)).addPathPatterns("/**");
    }

    /**
     * 配置 CORS 跨域
     * 只有在 cors-origins 非空时才配置，避免无谓配置
     */
    @Override
    public void addCorsMappings(@NonNull CorsRegistry registry) {
        List<String> origins = securityProperties.getCorsOrigins();
        if (CollUtil.isEmpty(origins)) {
            return;
        }

        String[] originArray = origins.toArray(new String[0]);
        registry.addMapping("/**")
                .allowedOriginPatterns(originArray)
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true)
                .maxAge(3600);
    }

}
