package com.ruoyi.ddd.api.common.err;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;
import cn.dev33.satoken.exception.NotRoleException;
import com.ruoyi.ddd.api.common.response.ResultVo;
import com.ruoyi.ddd.domain.common.constant.CommonErrorCode;
import com.ruoyi.ddd.domain.common.exception.BaseException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.stream.Collectors;

/**
 * 全局异常处理器
 * 统一处理所有异常，HTTP 状态码始终返回 200，通过错误码区分业务状态
 *
 * @author tooolan
 * @since 2026年4月15日
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 权限校验异常（Sa-Token）
     */
    @ExceptionHandler(NotPermissionException.class)
    public ResultVo<Void> handleNotPermissionException(
            NotPermissionException e, HttpServletRequest request) {
        log.warn("权限校验失败: {} - {}", request.getRequestURI(), e.getMessage());
        return ResultVo.error(CommonErrorCode.ACCESS_DENIED);
    }

    /**
     * 角色校验异常（Sa-Token）
     */
    @ExceptionHandler(NotRoleException.class)
    public ResultVo<Void> handleNotRoleException(
            NotRoleException e, HttpServletRequest request) {
        log.warn("角色校验失败: {} - {}", request.getRequestURI(), e.getMessage());
        return ResultVo.error(CommonErrorCode.ACCESS_DENIED);
    }

    /**
     * 登录异常（Sa-Token）
     */
    @ExceptionHandler(NotLoginException.class)
    public ResultVo<Void> handleNotLoginException(
            NotLoginException e, HttpServletRequest request) {
        log.warn("登录校验失败: {} - {}", request.getRequestURI(), e.getMessage());
        return ResultVo.error(CommonErrorCode.ACCESS_DENIED.getCode(), "登录已过期，请重新登录");
    }

    /**
     * 请求方法不支持
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResultVo<Void> handleHttpRequestMethodNotSupportedException(
            HttpRequestMethodNotSupportedException e, HttpServletRequest request) {
        log.warn("请求方法不支持: {} - {}", request.getRequestURI(), e.getMethod());
        return ResultVo.error(CommonErrorCode.METHOD_NOT_ALLOWED.getCode(),
                String.format("不支持'%s'请求方法", e.getMethod()));
    }

    /**
     * 请求路径变量缺失
     */
    @ExceptionHandler(MissingPathVariableException.class)
    public ResultVo<Void> handleMissingPathVariableException(
            MissingPathVariableException e, HttpServletRequest request) {
        log.warn("路径变量缺失: {} - {}", request.getRequestURI(), e.getVariableName());
        return ResultVo.error(CommonErrorCode.MISSING_PATH_VARIABLE.getCode(),
                String.format("请求路径中缺少必需的变量[%s]", e.getVariableName()));
    }

    /**
     * 请求参数类型不匹配
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResultVo<Void> handleMethodArgumentTypeMismatchException(
            MethodArgumentTypeMismatchException e, HttpServletRequest request) {
        log.warn("参数类型不匹配: {} - 参数[{}]", request.getRequestURI(), e.getName());
        return ResultVo.error(CommonErrorCode.PARAM_TYPE_MISMATCH.getCode(),
                String.format("参数[%s]类型不匹配", e.getName()));
    }

    /**
     * 处理领域异常（所有业务异常的兜底）
     */
    @ExceptionHandler(BaseException.class)
    public ResultVo<Void> handleBaseException(BaseException e, HttpServletRequest request) {
        log.warn("业务异常: {} - {}", request.getRequestURI(), e.getMessage());
        return ResultVo.error(e.getErrorCode(), e.getMessage());
    }

    /**
     * 处理 @Valid 校验失败异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultVo<Void> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException e, HttpServletRequest request) {
        String errorMessage = e.getBindingResult().getFieldErrors().stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.joining(", "));
        log.warn("参数校验失败: {} - {}", request.getRequestURI(), errorMessage);
        return ResultVo.error(CommonErrorCode.PARAM_VALIDATION_FAILED.getCode(), errorMessage);
    }

    /**
     * 处理 @Validated 校验失败异常
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResultVo<Void> handleConstraintViolationException(
            ConstraintViolationException e, HttpServletRequest request) {
        String errorMessage = e.getConstraintViolations().stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining(", "));
        log.warn("参数约束违反: {} - {}", request.getRequestURI(), errorMessage);
        return ResultVo.error(CommonErrorCode.PARAM_CONSTRAINT_VIOLATION.getCode(), errorMessage);
    }

    /**
     * 处理表单绑定校验异常
     */
    @ExceptionHandler(BindException.class)
    public ResultVo<Void> handleBindException(BindException e, HttpServletRequest request) {
        String errorMessage = e.getBindingResult().getFieldErrors().stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.joining(", "));
        log.warn("表单绑定校验失败: {} - {}", request.getRequestURI(), errorMessage);
        return ResultVo.error(CommonErrorCode.PARAM_VALIDATION_FAILED.getCode(), errorMessage);
    }

    /**
     * 处理非法参数异常
     * 注意：不暴露原始异常消息，使用用户友好的提示
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResultVo<Void> handleIllegalArgumentException(
            IllegalArgumentException e, HttpServletRequest request) {
        log.warn("非法参数: {} - {}", request.getRequestURI(), e.getMessage());
        return ResultVo.error(CommonErrorCode.ILLEGAL_ARGUMENT);
    }

    /**
     * 处理非法状态异常
     * 注意：不暴露原始异常消息，使用用户友好的提示
     */
    @ExceptionHandler(IllegalStateException.class)
    public ResultVo<Void> handleIllegalStateException(
            IllegalStateException e, HttpServletRequest request) {
        log.warn("非法状态: {} - {}", request.getRequestURI(), e.getMessage());
        return ResultVo.error(CommonErrorCode.ILLEGAL_STATE);
    }

    /**
     * 处理所有未捕获的异常（兜底）
     * 注意：完全隐藏技术细节，返回用户友好的提示
     */
    @ExceptionHandler(Exception.class)
    public ResultVo<Void> handleException(Exception e, HttpServletRequest request) {
        log.error("系统异常: {} - {}", request.getRequestURI(), e.getMessage(), e);
        return ResultVo.error(CommonErrorCode.SYSTEM_ERROR);
    }

}
