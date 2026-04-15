package com.ruoyi.ddd.domain.user.exception;

import com.ruoyi.ddd.domain.common.exception.BaseException;
import com.ruoyi.ddd.domain.common.exception.ErrorCode;

/**
 * 用户领域异常
 * 封装用户子域的业务异常，携带错误码和面向用户的错误消息
 *
 * @author tooolan
 * @since 2026年4月12日
 */
public class UserException extends BaseException {

    /**
     * 基于错误码构造用户异常
     *
     * @param errorCode 错误码枚举
     */
    public UserException(ErrorCode errorCode) {
        super(errorCode);
    }

    /**
     * 基于错误码和原因异常构造用户异常
     *
     * @param errorCode 错误码枚举
     * @param cause     原始异常
     */
    public UserException(ErrorCode errorCode, Throwable cause) {
        super(errorCode, cause);
    }

}
