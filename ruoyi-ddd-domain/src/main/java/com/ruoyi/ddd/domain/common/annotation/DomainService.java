package com.ruoyi.ddd.domain.common.annotation;

import java.lang.annotation.*;

/**
 * 领域服务注解
 * 标注在领域服务类上，表示该类是一个领域服务
 * 领域服务不依赖 Spring，由基础设施层通过类路径扫描注册为 Spring Bean
 *
 * @author tooolan
 * @since 2026年4月12日
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface DomainService {

    /**
     * 领域服务描述
     */
    String value() default "";

}
