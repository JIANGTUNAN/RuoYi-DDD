package com.ruoyi.ddd.domain.user.entity;

import cn.hutool.core.util.StrUtil;
import com.ruoyi.ddd.domain.user.constant.UserErrorCode;
import com.ruoyi.ddd.domain.user.exception.UserException;
import lombok.Getter;

import java.util.regex.Pattern;

/**
 * 用户 聚合根实体
 * 充血模型：通过业务语义方法变更状态，禁止使用 @Setter
 * 内部封装所有字段校验规则，保护自身不变量
 *
 * @author tooolan
 * @since 2026年4月12日
 */
@Getter
public class User {

    /**
     * 昵称特殊字符校验正则：仅允许中文、英文、数字、下划线
     */
    private static final Pattern NICKNAME_PATTERN = Pattern.compile("^[\\u4e00-\\u9fa5a-zA-Z0-9_]+$");

    /**
     * 超级管理员用户ID
     */
    private static final Long ADMIN_USER_ID = 1L;

    /**
     * 用户ID
     */
    private Long id;

    /**
     * 部门ID（跨聚合引用，仅持有ID）
     */
    private Long deptId;

    /**
     * 用户账号
     */
    private String userName;

    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 用户类型（00系统用户）
     */
    private String userType;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 手机号码
     */
    private String phonenumber;

    /**
     * 用户性别（0男 1女 2未知）
     */
    private String sex;

    /**
     * 头像地址
     */
    private String avatar;

    /**
     * 密码（BCrypt 加密）
     */
    private String password;

    /**
     * 账号状态（0正常 1停用）
     */
    private String status;

    /**
     * 备注
     */
    private String remark;

    // ==================== 业务语义方法 ====================

    /**
     * 创建用户实例（注册用）
     * 工厂方法：创建一个尚未持久化的用户实体
     * 注意：密码应在应用层加密后通过 resetPassword() 设置
     *
     * @param userName 用户账号
     * @param nickName 用户昵称
     * @param deptId   部门ID（可为空）
     * @return 用户实体
     */
    public static User create(String userName, String nickName, Long deptId) {
        if (StrUtil.isBlank(userName)) {
            throw new UserException(UserErrorCode.USER_NAME_EMPTY);
        }
        User user = new User();
        user.userName = userName;
        user.nickName = nickName;
        user.deptId = deptId;
        user.init();
        return user;
    }

    /**
     * 初始化用户状态
     * 注册完成后调用，设置默认状态为正常
     */
    public void init() {
        this.status = "0";
        this.userType = "00";
    }

    /**
     * 修改用户基本信息
     * 仅允许修改昵称和手机号，内部封装字段校验规则
     *
     * @param nickName    新昵称（不能为空，不能含特殊字符）
     * @param phonenumber 新手机号（可为空）
     */
    public void updateProfile(String nickName, String phonenumber) {
        validateNickName(nickName);
        this.nickName = nickName;
        this.phonenumber = phonenumber;
    }

    /**
     * 停用用户
     * 管理员保护：超级管理员不允许被停用
     * 幂等性：已是停用状态不重复操作
     */
    public void disable() {
        if (isAdmin()) {
            throw new UserException(UserErrorCode.CANNOT_DISABLE_ADMIN);
        }
        if ("1".equals(this.status)) {
            throw new UserException(UserErrorCode.ALREADY_DISABLED);
        }
        this.status = "1";
    }

    /**
     * 重置密码
     * 由应用层完成密码加密后，将加密后的密码传入
     *
     * @param encodedPassword BCrypt 加密后的密码
     */
    public void resetPassword(String encodedPassword) {
        if (StrUtil.isBlank(encodedPassword)) {
            throw new UserException(UserErrorCode.PASSWORD_EMPTY);
        }
        this.password = encodedPassword;
    }

    // ==================== 内部校验方法 ====================

    /**
     * 回填自增主键ID
     * 仅在仓储层保存后调用，将数据库生成的ID回填到领域实体
     *
     * @param id 数据库生成的用户ID
     */
    public void fillId(Long id) {
        this.id = id;
    }

    /**
     * 校验昵称合法性
     * 昵称不能为空，且不能包含特殊字符
     */
    private void validateNickName(String nickName) {
        if (StrUtil.isBlank(nickName)) {
            throw new UserException(UserErrorCode.NICKNAME_EMPTY);
        }
        if (!NICKNAME_PATTERN.matcher(nickName).matches()) {
            throw new UserException(UserErrorCode.NICKNAME_INVALID);
        }
    }

    // ==================== 静态工厂方法 ====================

    /**
     * 判断是否为超级管理员
     */
    private boolean isAdmin() {
        return ADMIN_USER_ID.equals(this.id);
    }

}
