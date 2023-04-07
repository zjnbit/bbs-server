package com.zjnbit.framework.tools.util;

import cn.hutool.crypto.digest.BCrypt;
import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import cn.hutool.crypto.symmetric.SymmetricCrypto;

import java.nio.charset.StandardCharsets;

public class PasswordUtils {


    /**
     * 检查明文密码与密文密码是否一致
     * plainText:明文密码，由客户端提供(前端提供的为明文进行md5加密后的，小写)
     * cipherText:密文密码，由数据库查询
     * aesKey:AES加密密钥，由系统配置获取
     *
     * @author 非羽Army
     **/
    public static Boolean checkPassword(String plainText, String cipherText, String aesKey) {
        return BCrypt.checkpw(
                (new Digester(DigestAlgorithm.SHA512)).digestHex(plainText.toLowerCase(), StandardCharsets.UTF_8)
                , (new SymmetricCrypto(SymmetricAlgorithm.AES, aesKey.getBytes(StandardCharsets.UTF_8))).decryptStr(cipherText)
        );
    }

    /**
     * 生成密码
     * plainText:明文密码，由客户端提供(前端提供的为明文进行md5加密后的，小写)
     * aesKey:AES加密密钥，由系统配置获取
     *
     * @author 非羽Army
     **/
    public static String generatePassword(String plainText, String aesKey) {
        return (new SymmetricCrypto(SymmetricAlgorithm.AES, aesKey.getBytes(StandardCharsets.UTF_8))).encryptHex(
                BCrypt.hashpw(
                        (new Digester(DigestAlgorithm.SHA512)).digestHex(
                                plainText.toLowerCase(), StandardCharsets.UTF_8
                        )
                )
        );
    }
}
