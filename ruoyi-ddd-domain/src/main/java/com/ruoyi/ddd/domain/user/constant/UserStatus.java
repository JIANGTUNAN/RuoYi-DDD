package com.ruoyi.ddd.domain.user.constant;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 用户状态枚举
 * 作为值对象使用，替代魔法值比较
 *
 * @author tooolan
 * @since 2026年4月15日
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum UserStatus {

    /**
     * 正常
     */
    NORMAL("0", "正常"),

    /**
     * 停用
     */
    DISABLED("1", "停用");

    /**
     * 状态值（对应数据库存储值）
     */
    private final String value;

    /**
     * 状态描述
     */
    private final String description;

}
