package com.FinalProject.FinalProjectBackend.Service;


        import io.jsonwebtoken.Claims;
        import io.jsonwebtoken.Jwts;
        import io.jsonwebtoken.security.Keys;
        import org.springframework.security.core.userdetails.UserDetails;
        import org.springframework.stereotype.Component;
        import org.springframework.stereotype.Service;

        import javax.crypto.SecretKey;
        import java.nio.charset.StandardCharsets;
        import java.util.Base64;
        import java.util.Date;
        import java.util.HashMap;
        import java.util.function.Function;

@Component
@Service
public class JwtUtil {

    private SecretKey key;
    private static final long EXPIRATION_TIME = 86400000; // 24 hours

    public JwtUtil() {
        String secretString = "ASMNDUAY3OENUAGD9AHWODAHJ908WYDHUOABWD97AHOWUHD89AYW9DHAOWHD98AWDOABWD7IAWDBAKW8DAO8WDHLAWNDOA8AOW8D8OAWHDOAWHOD";
        byte[] keyBytes = Base64.getDecoder().decode(secretString.getBytes(StandardCharsets.UTF_8));
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(UserDetails userDetails) {
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key)
                .compact();
    }

    public String generateRefreshToken(HashMap<String, Object> claims, UserDetails userDetails) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key)
                .compact();
    }

    public String extractUsername(String token) {
        return extractClaims(token, Claims::getSubject);
    }

    private <T> T extractClaims(String token, Function<Claims, T> claimsResolver) {
        return claimsResolver.apply(Jwts.parser().setSigningKey(key).build().parseClaimsJws(token).getBody());
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public boolean isTokenExpired(String token) {
        return extractClaims(token, Claims::getExpiration).before(new Date());
    }
}