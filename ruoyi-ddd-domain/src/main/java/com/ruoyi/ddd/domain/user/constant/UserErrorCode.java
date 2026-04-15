package com.ruoyi.ddd.domain.user.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 用户错误码常量
 * 定义用户子域的业务错误码，格式：用户模块(1) + 错误类型(XX) + 序号(XX)
 *
 * @author tooolan
 * @since 2026年4月12日
 */
@Getter
@AllArgsConstructor
public enum UserErrorCode {

    USER_NAME_EXISTS("10101", "用户名已存在"),
    PHONE_NUMBER_EXISTS("10102", "手机号已存在"),
    NOT_FOUND("10103", "用户不存在"),
    NICKNAME_EMPTY("10104", "用户昵称不能为空"),
    NICKNAME_INVALID("10105", "用户昵称不能包含特殊字符"),
    USER_NAME_EMPTY("10106", "用户账号不能为空"),
    PASSWORD_EMPTY("10107", "密码不能为空"),
    CANNOT_DISABLE_ADMIN("10108", "不允许停用超级管理员"),
    ALREADY_DISABLED("10109", "用户已是停用状态"),
    SAVE_FAILED("10110", "用户保存失败，请稍后重试");

    /**
     * 错误码
     */
    private final String code;

    /**
     * 错误消息（面向用户，不含技术细节）
     */
    private final String message;

}
