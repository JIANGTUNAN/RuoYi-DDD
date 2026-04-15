package com.ruoyi.ddd.domain.user.exception;

import com.ruoyi.ddd.domain.user.constant.UserErrorCode;
import lombok.Getter;

/**
 * 用户领域异常
 * 封装用户子域的业务异常，携带错误码和面向用户的错误消息
 *
 * @author tooolan
 * @since 2026年4月12日
 */
@Getter
public class UserException extends RuntimeException {

    /**
     * 错误码
     */
    private final String code;

    /**
     * 基于错误码构造用户异常
     *
     * @param errorCode 用户错误码枚举
     */
    public UserException(UserErrorCode errorCode) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
    }

}
