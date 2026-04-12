package com.ruoyi.ddd.app.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * RSA 密钥配置属性
 * 从 application.yml 读取 ruoyi.security.rsa 配置
 *
 * @author tooolan
 * @since 2026年4月9日
 */
@Data
@Component
@ConfigurationProperties(prefix = "ruoyi.security.rsa")
public class RsaProperties {

    /**
     * RSA 私钥（Base64 编码）
     */
    private String privateKey;

    /**
     * RSA 公钥（Base64 编码）
     */
    private String publicKey;

}
