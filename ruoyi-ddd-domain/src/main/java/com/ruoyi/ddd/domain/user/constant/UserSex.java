package com.ruoyi.ddd.domain.user.constant;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 用户性别枚举
 *
 * @author tooolan
 * @since 2026年4月15日
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum UserSex {

    /**
     * 男
     */
    MALE("0", "男"),

    /**
     * 女
     */
    FEMALE("1", "女"),

    /**
     * 未知
     */
    UNKNOWN("2", "未知");

    /**
     * 性别值（对应数据库存储值）
     */
    private final String value;

    /**
     * 性别描述
     */
    private final String description;

}
