package com.ruoyi.ddd.infra.db.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.ruoyi.ddd.domain.common.context.ContextHolder;
import com.ruoyi.ddd.infra.db.common.entity.BaseEntity;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * MyBatis-Plus 自动填充处理器
 * 自动处理 createBy/createTime/updateBy/updateTime 等审计字段
 *
 * @author tooolan
 * @since 2026年4月12日
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    /**
     * 插入时自动填充
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        LocalDateTime now = LocalDateTime.now();
        String currentUser = getCurrentUser();

        this.strictInsertFill(metaObject, BaseEntity.Fields.createBy, String.class, currentUser);
        this.strictInsertFill(metaObject, BaseEntity.Fields.createTime, LocalDateTime.class, now);
        this.strictInsertFill(metaObject, BaseEntity.Fields.updateBy, String.class, currentUser);
        this.strictInsertFill(metaObject, BaseEntity.Fields.updateTime, LocalDateTime.class, now);
    }

    /**
     * 更新时自动填充
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        String currentUser = getCurrentUser();
        this.strictUpdateFill(metaObject, BaseEntity.Fields.updateBy, String.class, currentUser);
        this.strictUpdateFill(metaObject, BaseEntity.Fields.updateTime, LocalDateTime.class, LocalDateTime.now());
    }

    /**
     * 获取当前用户标识
     * 通过上下文获取已登录用户ID，未登录时返回 "system"
     */
    private String getCurrentUser() {
        try {
            if (ContextHolder.isLoggedIn()) {
                return String.valueOf(ContextHolder.getUserId());
            }
        } catch (Exception ignored) {
            // 未登录场景（如系统初始化）
        }
        return "system";
    }

}
