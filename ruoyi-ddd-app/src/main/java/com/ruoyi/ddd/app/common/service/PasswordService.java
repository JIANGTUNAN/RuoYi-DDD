package com.ruoyi.ddd.app.common.service;

import cn.hutool.core.codec.Base64;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import cn.hutool.crypto.digest.BCrypt;
import com.ruoyi.ddd.app.common.config.RsaProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 密码加密器应用服务
 * 处理密码的 RSA 解密、BCrypt 加密和验证
 *
 * @author tooolan
 * @since 2026年4月9日
 */
@Service
@RequiredArgsConstructor
public class PasswordService {

    private final RsaProperties rsaProperties;

    /**
     * RSA 私钥解密前端传来的密码
     *
     * @param encryptedPassword RSA 公钥加密后的密码（Base64 编码）
     * @return 解密后的原始密码
     */
    public String decryptPassword(String encryptedPassword) {
        RSA rsa = new RSA(Base64.decode(rsaProperties.getPrivateKey()), null);
        return rsa.decryptStr(encryptedPassword, KeyType.PrivateKey);
    }

    /**
     * BCrypt 加密密码
     *
     * @param rawPassword 原始密码
     * @return BCrypt 加密后的密码哈希
     */
    public String encodePassword(String rawPassword) {
        return BCrypt.hashpw(rawPassword);
    }

    /**
     * 校验密码
     *
     * @param rawPassword     原始密码
     * @param encodedPassword BCrypt 加密后的密码哈希
     * @return true 密码匹配，false 密码不匹配
     */
    public boolean verifyPassword(String rawPassword, String encodedPassword) {
        return BCrypt.checkpw(rawPassword, encodedPassword);
    }

}
