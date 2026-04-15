package com.ruoyi.ddd.api.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ruoyi.ddd.domain.common.constant.CommonErrorCode;
import com.ruoyi.ddd.domain.common.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 统一响应包装类
 * 封装所有 API 接口的返回格式，包含响应码、消息和数据
 *
 * @param <T> 数据类型
 * @author tooolan
 * @since 2026年4月15日
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultVo<T> {

    /**
     * 响应码
     * {@link CommonErrorCode#SUCCESS}表示成功，其他为业务错误码
     */
    private String code;

    /**
     * 响应消息
     */
    private String message;

    /**
     * 响应数据
     */
    private T data;

    /**
     * 创建成功响应（无数据）
     *
     * @param <T> 数据类型
     * @return 成功响应对象
     */
    public static <T> ResultVo<T> success() {
        return new ResultVo<>(CommonErrorCode.SUCCESS.getCode(), "操作成功", null);
    }

    /**
     * 创建成功响应（带数据）
     *
     * @param data 响应数据
     * @param <T>  数据类型
     * @return 成功响应对象
     */
    public static <T> ResultVo<T> success(T data) {
        return new ResultVo<>(CommonErrorCode.SUCCESS.getCode(), "操作成功", data);
    }

    /**
     * 创建成功响应（自定义消息和数据）
     *
     * @param message 响应消息
     * @param data    响应数据
     * @param <T>     数据类型
     * @return 成功响应对象
     */
    public static <T> ResultVo<T> success(String message, T data) {
        return new ResultVo<>(CommonErrorCode.SUCCESS.getCode(), message, data);
    }

    /**
     * 创建错误响应（使用错误码枚举）
     *
     * @param errorCode 错误码枚举
     * @param <T>       数据类型
     * @return 错误响应对象
     */
    public static <T> ResultVo<T> error(ErrorCode errorCode) {
        return new ResultVo<>(errorCode.getCode(), errorCode.getMessage(), null);
    }

    /**
     * 创建错误响应（自定义错误码和消息）
     *
     * @param code    业务错误码
     * @param message 错误消息
     * @param <T>     数据类型
     * @return 错误响应对象
     */
    public static <T> ResultVo<T> error(String code, String message) {
        return new ResultVo<>(code, message, null);
    }

    /**
     * 创建警告响应（仅消息）
     *
     * @param message 警告消息
     * @param <T>     数据类型
     * @return 警告响应对象
     */
    public static <T> ResultVo<T> warn(String message) {
        return new ResultVo<>(CommonErrorCode.WARN.getCode(), message, null);
    }

    /**
     * 创建警告响应（带数据）
     *
     * @param message 警告消息
     * @param data    响应数据
     * @param <T>     数据类型
     * @return 警告响应对象
     */
    public static <T> ResultVo<T> warn(String message, T data) {
        return new ResultVo<>(CommonErrorCode.WARN.getCode(), message, data);
    }

    /**
     * 判断是否为成功响应
     *
     * @return true 表示成功
     */
    public boolean isSuccess() {
        return CommonErrorCode.SUCCESS.getCode().equals(this.code);
    }

}
