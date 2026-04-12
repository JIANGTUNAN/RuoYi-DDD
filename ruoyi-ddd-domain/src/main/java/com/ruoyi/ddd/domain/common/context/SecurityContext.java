package com.ruoyi.ddd.domain.common.context;

/**
 * 安全上下文信息
 * 存储当前登录用户的基本信息，是纯粹的数据载体
 *
 * @param userId   用户ID
 * @param username 用户名
 * @param nickname 用户昵称
 * @author tooolan
 * @since 2026年4月9日
 */
public record SecurityContext(Integer userId, String username, String nickname) {

}
