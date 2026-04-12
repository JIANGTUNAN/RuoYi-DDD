package com.ruoyi.ddd.api.common.web;

import cn.dev33.satoken.fun.SaParamFunction;
import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.BooleanUtil;
import cn.hutool.core.util.NumberUtil;
import com.ruoyi.ddd.api.common.config.SecurityProperties;
import com.ruoyi.ddd.api.common.constant.MockTokenConstant;
import com.ruoyi.ddd.app.common.context.ContextHolder;
import com.ruoyi.ddd.app.common.context.HttpContext;
import com.ruoyi.ddd.app.common.context.SecurityContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 统一用户上下文拦截器
 * 继承 SaInterceptor，在 Sa-Token 认证完成后自动初始化上下文
 * <p>
 * 执行顺序：
 * 1. Mock 身份处理（在 Sa-Token 认证之前）
 * 2. Sa-Token 路由拦截 + checkLogin
 * 3. 上下文初始化
 *
 * @author tooolan
 * @since 2026年4月9日
 */
@Slf4j
public class UserContextInterceptor extends SaInterceptor {

    private final SecurityProperties authProperties;

    /**
     * 构造函数
     *
     * @param handler        Sa-Token 认证处理器（通常传入 SaRouter 匹配规则）
     * @param authProperties 安全配置属性
     */
    public UserContextInterceptor(SaParamFunction<Object> handler, SecurityProperties authProperties) {
        super(handler);
        this.authProperties = authProperties;
    }

    /**
     * 请求前置处理
     */
    @Override
    public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
                             @NonNull Object handler) throws Exception {
        // 1. Mock 身份处理（在 Sa-Token 认证之前）
        handleMockLogin();

        // 2. 执行 Sa-Token 路由拦截 + checkLogin
        boolean result = super.preHandle(request, response, handler);

        // 3. 认证通过后，初始化上下文
        initializeContext(request);

        return result;
    }

    /**
     * 请求完成后清理资源
     */
    @Override
    public void afterCompletion(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
                                @NonNull Object handler, Exception ex) throws Exception {
        try {
            ContextHolder.clearContext();
        } finally {
            super.afterCompletion(request, response, handler, ex);
        }
    }

    /**
     * Mock 身份处理
     * 开发环境下可通过 test-{userId} 格式的 token 模拟任意用户身份
     */
    private void handleMockLogin() {
        if (BooleanUtil.isFalse(authProperties.isMockEnabled())) {
            return;
        }

        String tokenValue = StpUtil.getTokenValue();
        if (tokenValue == null || !tokenValue.startsWith(MockTokenConstant.MOCK_PREFIX)) {
            return;
        }

        String userIdStr = tokenValue.substring(MockTokenConstant.MOCK_PREFIX.length());
        if (BooleanUtil.isFalse(NumberUtil.isInteger(userIdStr))) {
            log.warn("【Mock 登录】无效的 Mock Token: {}", tokenValue);
            return;
        }

        long userId = Long.parseLong(userIdStr);
        StpUtil.login(userId);
        StpUtil.getSession().set("username", "mock-user-" + userId);
        StpUtil.getSession().set("nickname", "Mock用户" + userId);

        log.warn("【Mock 登录】userId: {}", userId);
    }

    /**
     * 初始化上下文
     * 创建 HttpContext 快照，已登录时初始化安全上下文
     */
    private void initializeContext(HttpServletRequest request) {
        try {
            // 1. 初始化 HTTP 上下文（每个请求都有）
            String token = StpUtil.isLogin() ? StpUtil.getTokenValue() : null;
            ContextHolder.setHttpContext(HttpContext.snapshot(request, token));

            // 2. 如果已登录，初始化安全上下文
            if (StpUtil.isLogin()) {
                SecurityContext securityContext = new SecurityContext(
                        StpUtil.getLoginIdAsInt(),
                        StpUtil.getSession().getString("username"),
                        StpUtil.getSession().getString("nickname")
                );
                ContextHolder.setContext(securityContext);
            }
        } catch (Exception e) {
            log.warn("初始化上下文失败: {}", e.getMessage());
        }
    }

}
