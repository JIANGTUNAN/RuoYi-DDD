package com.ruoyi.ddd.app.common.context;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 安全上下文信息
 * 存储当前登录用户的基本信息，是纯粹的数据载体
 *
 * @author tooolan
 * @since 2026年4月9日
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SecurityContext {

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户昵称
     */
    private String nickname;

}
