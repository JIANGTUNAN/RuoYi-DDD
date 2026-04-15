package com.ruoyi.ddd.domain.user.constant;

import com.ruoyi.ddd.domain.common.exception.ErrorCode;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 用户错误码常量
 * <p>
 * 编码格式：{来源(1)}{模块(3)}{HTTP状态码(3)}{序号(3)}
 * - 来源：1=本地
 * - 模块：002=用户
 * - HTTP状态码：仅作参考
 * - 序号：模块内全局递增
 *
 * @author tooolan
 * @since 2026年4月12日
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum UserErrorCode implements ErrorCode {

    USER_NAME_EXISTS("1-002-400-001", "用户名已存在"),
    PHONE_NUMBER_EXISTS("1-002-400-002", "手机号已存在"),
    NOT_FOUND("1-002-404-001", "用户不存在"),
    NICKNAME_EMPTY("1-002-400-003", "用户昵称不能为空"),
    NICKNAME_INVALID("1-002-400-004", "用户昵称不能包含特殊字符"),
    USER_NAME_EMPTY("1-002-400-005", "用户账号不能为空"),
    PASSWORD_EMPTY("1-002-400-006", "密码不能为空"),
    CANNOT_DISABLE_ADMIN("1-002-403-001", "不允许停用超级管理员"),
    ALREADY_DISABLED("1-002-400-007", "用户已是停用状态"),
    SAVE_FAILED("1-002-500-001", "用户保存失败，请稍后重试");

    /**
     * 错误码
     */
    private final String code;

    /**
     * 错误消息（面向用户，不含技术细节）
     */
    private final String message;

}
