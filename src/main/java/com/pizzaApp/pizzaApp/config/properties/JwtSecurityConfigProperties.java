package com.pizzaApp.pizzaApp.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "application.security.jwt")
public class JwtSecurityConfigProperties {

    private String secretKey;
    private RefreshToken refreshToken;
    private AccessToken accessToken;

    @Getter
    @Setter
    public static class RefreshToken{
        private Long expiration;
    }

    @Getter
    @Setter
    public static class AccessToken{
        private Long expiration;
    }
}
