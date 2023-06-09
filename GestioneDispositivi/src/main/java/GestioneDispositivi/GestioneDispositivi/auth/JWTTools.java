package GestioneDispositivi.GestioneDispositivi.auth;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import GestioneDispositivi.GestioneDispositivi.entities.User;
import GestioneDispositivi.GestioneDispositivi.exceptions.UnauthorizedException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.Keys;

@Component
public class JWTTools {

	//trucco per usare i value dall'env 
	private static String secret;
	private static int expiration;
	
	@Value("${spring.application.jwt.secret}")
	public void setSecret(String secretKey) {
		secret = secretKey;
	}

	@Value("${spring.application.jwt.expirationindays}")
	public void setExpiration(String expirationInDays) {
		expiration = Integer.parseInt(expirationInDays) * 24 * 60 * 60 * 1000;
	}

	static public String createToken(User u) {
		String token = Jwts.builder().setSubject(u.getUsername()).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + expiration))
				.signWith(Keys.hmacShaKeyFor(secret.getBytes())).compact();
		return token;
		
	}

	static public void isTokenValid(String token) {
		try {
			Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(secret.getBytes())).build().parse(token);

		} catch (MalformedJwtException e) {
			throw new UnauthorizedException("Invalid Token");
		} catch (ExpiredJwtException e) {
			throw new UnauthorizedException("Expired Token");
		} catch (Exception e) {
			throw new UnauthorizedException("There is a problem with your Token, try login again");
		}
	}
	
	
	//utilizzo lo username come subject dell'utente
	static public String extractSubject(String token) { 
		return Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(secret.getBytes())).build().parseClaimsJws(token)
				.getBody().getSubject();
	}
}
