package com.ruoyi.ddd.api.common.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * 认证相关常量
 *
 * @author tooolan
 * @since 2026年4月9日
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthConstant {

    // ==================== 会话 Key ====================

    /**
     * 会话中存储的用户名 Key
     */
    public static final String SESSION_KEY_USERNAME = "username";

    /**
     * 会话中存储的用户昵称 Key
     */
    public static final String SESSION_KEY_NICKNAME = "nickname";

    // ==================== Mock 登录 ====================

    /**
     * Mock token 前缀，完整格式：MOCK_TOKEN_PREFIX + userId
     * 例如：test-1 表示以 userId=1 的用户身份登录
     */
    public static final String MOCK_TOKEN_PREFIX = "test-";

    /**
     * Mock 用户名前缀
     */
    public static final String MOCK_USERNAME_PREFIX = "mock-user-";

    /**
     * Mock 用户昵称前缀
     */
    public static final String MOCK_NICKNAME_PREFIX = "Mock用户";

}
