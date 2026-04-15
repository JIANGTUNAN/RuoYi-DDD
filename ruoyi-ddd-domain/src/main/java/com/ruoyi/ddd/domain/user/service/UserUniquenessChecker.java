package com.ruoyi.ddd.domain.user.service;

import com.ruoyi.ddd.domain.common.annotation.DomainService;
import com.ruoyi.ddd.domain.user.constant.UserErrorCode;
import com.ruoyi.ddd.domain.user.exception.UserException;
import com.ruoyi.ddd.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;

/**
 * 用户唯一性校验器
 * 封装用户名、手机号等唯一性校验的业务规则
 * 跨聚合校验需要数据库查询，因此抽取为领域服务
 *
 * @author tooolan
 * @since 2026年4月12日
 */
@DomainService
@RequiredArgsConstructor
public class UserUniquenessChecker {

    private final UserRepository userRepository;

    /**
     * 校验用户名唯一性
     * 如果用户名已被占用，抛出领域异常
     *
     * @param userName 用户账号
     */
    public void checkUserNameUnique(String userName) {
        if (userRepository.existsByUserName(userName)) {
            throw new UserException(UserErrorCode.USER_NAME_EXISTS);
        }
    }

    /**
     * 校验手机号唯一性
     * 排除指定用户ID（用于修改时排除自身）
     * 如果手机号已被其他用户使用，抛出领域异常
     *
     * @param phonenumber   手机号
     * @param excludeUserId 排除的用户ID（可为空）
     */
    public void checkPhonenumberUnique(String phonenumber, Long excludeUserId) {
        if (userRepository.existsByPhonenumber(phonenumber, excludeUserId)) {
            throw new UserException(UserErrorCode.PHONE_NUMBER_EXISTS);
        }
    }

}
