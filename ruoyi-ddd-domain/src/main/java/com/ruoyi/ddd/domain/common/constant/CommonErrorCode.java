package com.ruoyi.ddd.domain.common.constant;

import com.ruoyi.ddd.domain.common.exception.ErrorCode;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 通用错误码枚举
 * 包含系统级别的通用错误码
 * 模块编码：001
 *
 * @author tooolan
 * @since 2026年2月17日
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum CommonErrorCode implements ErrorCode {

    SUCCESS("0", "操作成功"),
    WARN("1-001-601-001", "警告"),

    PARAM_VALIDATION_FAILED("1-001-422-001", "参数校验失败"),
    PARAM_CONSTRAINT_VIOLATION("1-001-422-002", "参数约束违反"),
    ILLEGAL_ARGUMENT("1-001-400-001", "参数错误，请检查输入"),
    ILLEGAL_STATE("1-001-500-001", "系统繁忙，请稍后再试"),
    SYSTEM_ERROR("1-001-500-002", "系统繁忙，请稍后再试"),
    ACCESS_DENIED("1-001-403-001", "没有权限，请联系管理员授权"),
    METHOD_NOT_ALLOWED("1-001-405-001", "请求方法不支持"),
    PARAM_TYPE_MISMATCH("1-001-400-002", "请求参数类型不匹配"),
    MISSING_PATH_VARIABLE("1-001-400-003", "请求路径变量缺失"),
    ;

    private final String code;
    private final String message;

}
