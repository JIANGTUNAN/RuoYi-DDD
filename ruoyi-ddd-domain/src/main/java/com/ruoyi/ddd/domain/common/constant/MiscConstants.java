package com.ruoyi.ddd.domain.common.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.regex.Pattern;

/**
 * 通用杂项常量类
 * 存放系统级别的固定常量
 *
 * @author tooolan
 * @since 2026年2月19日
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class MiscConstants {

    /**
     * 管理员用户ID
     */
    public static final Long ADMIN_USER_ID = 1L;

    /**
     * 管理员用户名
     */
    public static final String ADMIN_USERNAME = "admin";

    /**
     * 昵称特殊字符校验正则：仅允许中文、英文、数字、下划线
     */
    public static final Pattern NICKNAME_PATTERN = Pattern.compile("^[\\u4e00-\\u9fa5a-zA-Z0-9_]+$");

}
