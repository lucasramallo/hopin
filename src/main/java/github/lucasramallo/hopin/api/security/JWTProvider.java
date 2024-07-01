package github.lucasramallo.hopin.api.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import github.lucasramallo.hopin.core.domain.customer.Customer;
import github.lucasramallo.hopin.core.globalExceptions.InvalidTokenException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;

@Component
public class JWTProvider {
    @Value("${JWT.secret.key}")
    private String secretKey;

    public String getIdentifierFromToken(String token) {
        token = token.replace("Bearer ", "");
        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        try {
            return JWT.require(algorithm)
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException e) {
            throw new InvalidTokenException();
        }
    }

    public String generateToken(Customer customer) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        return JWT.create().withIssuer("HopIn")
                .withExpiresAt(Instant.now().plus(Duration.ofHours(2)))
                .withSubject(customer.getId().toString())
                .sign(algorithm);
    }

}
