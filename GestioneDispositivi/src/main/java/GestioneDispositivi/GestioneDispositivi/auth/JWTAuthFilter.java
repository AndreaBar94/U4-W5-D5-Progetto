package GestioneDispositivi.GestioneDispositivi.auth;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import GestioneDispositivi.GestioneDispositivi.entities.User;
import GestioneDispositivi.GestioneDispositivi.exceptions.NotFoundException;
import GestioneDispositivi.GestioneDispositivi.exceptions.UnauthorizedException;
import GestioneDispositivi.GestioneDispositivi.services.UsersService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JWTAuthFilter extends OncePerRequestFilter{
	
	@Autowired
	private UsersService usersService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		//questo metodo verrà lanciato ad ogni request
		//si inizia estraendo il token dall'header della richiesta
		String authHeader = request.getHeader("Authorization");
		
		//controllo validità del token
		if(authHeader == null || !authHeader.startsWith("Bearer ")) throw new UnauthorizedException("Remeber to add your token to the request!");
		
		//faccio il substring per saltare la stringa "Bearer " (7 caratteri) e ottengo il token pulito
		String accessToken = authHeader.substring(7);
		
		JWTTools.isTokenValid(accessToken);
		//se va tutto bene lavoro sul token
		String email = JWTTools.extractSubject(accessToken);
		
		try {
			
			User user = usersService.findByEmail(email);
			
			//una volta trovato l'utente lo aggiungo al SecurityContextHolder
			UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
			authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			SecurityContextHolder.getContext().setAuthentication(authToken);
			
			// accedo al prossimo blocco della filterChain
			filterChain.doFilter(request, response);
			
		}catch (NotFoundException e){
			//se non trova l'utente lancia un not found
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//questo serve ad evitare che il filtro venga eseguito per ogni richiesta
	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) {
		return new AntPathMatcher().match("/auth/**", request.getServletPath());
	}
	
}
