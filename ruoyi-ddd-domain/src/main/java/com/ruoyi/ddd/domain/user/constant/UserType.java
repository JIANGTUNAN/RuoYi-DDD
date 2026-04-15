package com.ruoyi.ddd.domain.user.constant;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 用户类型枚举
 *
 * @author tooolan
 * @since 2026年4月15日
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum UserType {

    /**
     * 系统用户
     */
    SYSTEM("00", "系统用户");

    /**
     * 类型值（对应数据库存储值）
     */
    private final String value;

    /**
     * 类型描述
     */
    private final String description;

}
