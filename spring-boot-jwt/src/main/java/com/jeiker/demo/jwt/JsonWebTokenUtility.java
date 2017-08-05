package com.jeiker.demo.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.List;

/**
 * @Author : xiao
 * @Date : 17/8/4 上午10:00
 */
public class JsonWebTokenUtility {

    private static final Logger logger = LoggerFactory.getLogger(JsonWebTokenUtility.class);

    private SignatureAlgorithm signatureAlgorithm;  // 签名算法
    private Key secretKey;                          // 密钥

    public JsonWebTokenUtility() {

        // 这里不是真正安全的实践
        // 为了简单,存储一个静态key在这里
        signatureAlgorithm = SignatureAlgorithm.HS512;
        String encodedKey = "adsfjasfui89qwer798qwerjkqwlj12934719!@#$!@#$1234!@#$!dsklfaj";
        secretKey = deserializeKey(encodedKey);
    }

    public String createJsonWebToken(AuthTokenDetails authTokenDetails) {
        String token = Jwts.builder().setSubject(authTokenDetails.getId().toString())
                .claim("username", authTokenDetails.getUsername())
                .claim("roleNames", authTokenDetails.getRoleNames())
                .setExpiration(authTokenDetails.getExpirationDate())
                .signWith(getSignatureAlgorithm(), getSecretKey())
                .compact();
        return token;
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

    public AuthTokenDetails parseAndValidate(String token) {
        AuthTokenDetails authTokenDetails = null;
        try {
            Claims claims = Jwts.parser().setSigningKey(getSecretKey())
                    .parseClaimsJws(token)
                    .getBody();
            String userId = claims.getSubject();
            String username = (String) claims.get("username");
            List<String> roleNames = (List) claims.get("roleNames");
            Date expirationDate = claims.getExpiration();

            authTokenDetails = new AuthTokenDetails();
            authTokenDetails.setId(Long.valueOf(userId));
            authTokenDetails.setUsername(username);
            authTokenDetails.setRoleNames(roleNames);
            authTokenDetails.setExpirationDate(expirationDate);
        } catch (JwtException ex) {
            logger.error(ex.getMessage(), ex);
        }
        return authTokenDetails;
    }

    private String serializeKey(Key key) {
        String encodedKey = Base64.getEncoder().encodeToString(key.getEncoded());
        return encodedKey;
    }
}
