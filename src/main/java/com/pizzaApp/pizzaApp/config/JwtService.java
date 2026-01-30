package com.pizzaApp.pizzaApp.config;

import com.pizzaApp.pizzaApp.config.properties.JwtSecurityConfigProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class JwtService {

    private final JwtSecurityConfigProperties jwtSecurityConfigProperties;

    public String extractUsername(String token){
        return extractClaim(token, Claims::getSubject);
    }
    // Этот метод выдергивает email через токен. Метод вызывается при запросах на защищенный endpoint


    /* --------------------------------------------------------------------------------*/


    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }
    // Метод берет токен и с этого токена получает нужное поле с помощью "Function<Claims, T>" и возвращает ее


    /* --------------------------------------------------------------------------------*/

    private Claims getAllClaimsFromToken(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey()) // ключ, которым подписан токен
                .build()
                .parseClaimsJws(token)         // парсит токен и проверяет подпись
                .getBody();                    // возвращает Claims
    }
    // Берет токен, проверяет его подпись, расшифровывает и возвращает все его поля


    /* --------------------------------------------------------------------------------*/

    private SecretKey getSignInKey(){
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecurityConfigProperties.getSecretKey());
        return Keys.hmacShaKeyFor(keyBytes);
    }
    // Этот метод возвращает секретный ключ, который подписывает токен при создании и проверяет его
}
