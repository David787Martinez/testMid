package com.chakray.testmid.security;

import com.chakray.testmid.utils.Constantes;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

/**
 *
 * @author luis-barrera
 */
public class JwtHelper {
    
    private static final String SECRET_KEY = Constantes.SECRET_KEY_TOKEN;
    private static final int MINUTES = Constantes.MINUTOS_TOKEN;
    
    public static String generateToken(String usuario) {

        var now = Instant.now();
        Map<String, Object> extraClaims = new HashMap<>();
        return Jwts.builder()
                .claims(extraClaims)
                .subject(usuario)
                .issuedAt(Date.from(now))
                .expiration(Date.from(now.plus(MINUTES, ChronoUnit.MINUTES)))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public static String extractUsername(String token) {
        if(getTokenBody(token) != null){
            return getTokenBody(token).getSubject();
        } 
        return "";
    }

    private static Claims getTokenBody(String token) {
        try {
            return Jwts
                    .parser()
                    .setSigningKey(SECRET_KEY)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch (SignatureException | ExpiredJwtException | MalformedJwtException e) { // Invalid signature or expired token
            return null;
        }
    }

    public static Boolean validateToken(String token, String user) {
        final String username = extractUsername(token);
        return username.equals(user) && !isTokenExpired(token);
    }

    public static boolean isTokenExpired(String token) {
        var now = Instant.now();
        Claims claims = getTokenBody(token);
        if(claims != null){
            return claims.getExpiration().before(Date.from(now));
        }else{
         return true;
        }
    }

    public static SecretKey generateKey(int n) throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(n);
        SecretKey originalKey = keyGenerator.generateKey();
        return originalKey;
    }

    public static String convertSecretKeyToString(SecretKey secretKey) throws NoSuchAlgorithmException {
        byte[] rawData = secretKey.getEncoded();
        String encodedKey = Base64.getEncoder().encodeToString(rawData);
        return encodedKey;
    }
}