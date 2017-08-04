package com.jeiker.demo.jwt;

import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;

/**
 * @Author : xiao
 * @Date : 17/8/4 上午10:00
 */
public class JsonWebTokenUtility {

    private SignatureAlgorithm signatureAlgorithm;  // 签名算法
    private Key secretKey;                          // 密钥

    public JsonWebTokenUtility() {

        // 这里不是真正安全的实践
        // 为了简单,存储一个静态key在这里
        signatureAlgorithm = SignatureAlgorithm.HS512;
        String encodedKey = "adsfjasfui89qwer798qwerjkqwlj12934719!@#$!@#$1234!@#$!dsklfaj";
        secretKey = deserializeKey(encodedKey);
    }

    private Key deserializeKey(String encodeKey) {
        byte[] decodeKey = Base64.getDecoder().decode(encodeKey);
        Key key = new SecretKeySpec(decodeKey, getSignatureAlgorithm().getJcaName());
        return key;
    }

    public SignatureAlgorithm getSignatureAlgorithm() {
        return signatureAlgorithm;
    }

    public Key getSecretKey() {
        return secretKey;
    }
}
