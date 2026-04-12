package com.ruoyi.ddd.infra.db.user.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.ruoyi.ddd.infra.db.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 系统用户信息 实体类
 *
 * @author tooolan
 * @since 2026年4月12日
 */
@Data
@TableName(value = "sys_user")
@EqualsAndHashCode(callSuper = true)
public class SysUserEntity extends BaseEntity {

    /**
     * 用户ID
     */
    @TableId(value = "user_id", type = IdType.AUTO)
    private Long userId;

    /**
     * 部门ID
     */
    @TableField(value = "dept_id")
    private Long deptId;

    /**
     * 用户账号
     */
    @TableField(value = "user_name")
    private String userName;

    /**
     * 用户昵称
     */
    @TableField(value = "nick_name")
    private String nickName;

    /**
     * 用户类型（00系统用户）
     */
    @TableField(value = "user_type")
    private String userType;

    /**
     * 用户邮箱
     */
    @TableField(value = "email")
    private String email;

    /**
     * 手机号码
     */
    @TableField(value = "phonenumber")
    private String phonenumber;

    /**
     * 用户性别（0男 1女 2未知）
     */
    @TableField(value = "sex")
    private String sex;

    /**
     * 头像地址
     */
    @TableField(value = "avatar")
    private String avatar;

    /**
     * 密码
     */
    @TableField(value = "password")
    private String password;

    /**
     * 账号状态（0正常 1停用）
     */
    @TableField(value = "status")
    private String status;

    /**
     * 删除标志（0代表存在 2代表删除）
     */
    @TableLogic(delval = "2", value = "0")
    @TableField(value = "del_flag")
    private String delFlag;

    /**
     * 最后登录IP
     */
    @TableField(value = "login_ip")
    private String loginIp;

    /**
     * 最后登录时间
     */
    @TableField(value = "login_date")
    private LocalDateTime loginDate;

    /**
     * 密码最后更新时间
     */
    @TableField(value = "pwd_update_date")
    private LocalDateTime pwdUpdateDate;

}
