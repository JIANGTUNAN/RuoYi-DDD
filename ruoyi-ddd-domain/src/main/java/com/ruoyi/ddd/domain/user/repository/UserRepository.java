package com.ruoyi.ddd.domain.user.repository;

import com.ruoyi.ddd.domain.user.entity.User;

import java.util.Optional;

/**
 * 用户 仓储接口
 * 定义用户持久化操作契约，由基础设施层实现
 * 仅包含写操作和按ID/唯一键查询，分页查询走 CQRS 在应用层定义
 *
 * @author tooolan
 * @since 2026年4月12日
 */
public interface UserRepository {

    /**
     * 保存用户（新增）
     * 保存成功后应将数据库生成的 ID 回填到 user 对象中
     *
     * @param user 用户领域实体
     * @return 是否保存成功
     */
    boolean save(User user);

    /**
     * 根据用户ID更新用户信息
     * 仅更新非 null 字段，部分更新策略
     *
     * @param user 用户领域实体
     * @return 是否更新成功
     */
    boolean updateById(User user);

    /**
     * 根据用户ID查询用户
     *
     * @param userId 用户ID
     * @return 用户实体，不存在时返回空
     */
    Optional<User> findById(Long userId);

    /**
     * 根据用户账号查询用户
     *
     * @param userName 用户账号
     * @return 用户实体，不存在时返回空
     */
    Optional<User> findByUserName(String userName);

    /**
     * 校验用户名是否已存在
     *
     * @param userName 用户账号
     * @return true 已存在，false 不存在
     */
    boolean existsByUserName(String userName);

    /**
     * 校验手机号是否已被其他用户使用
     * 排除指定用户ID（用于修改时排除自身）
     *
     * @param phonenumber   手机号
     * @param excludeUserId 排除的用户ID（可为空，表示不排除）
     * @return true 已被使用，false 未被使用
     */
    boolean existsByPhonenumber(String phonenumber, Long excludeUserId);

}
