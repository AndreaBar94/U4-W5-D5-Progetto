package GestioneDispositivi.GestioneDispositivi.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegistrationPayload {
	
	@NotNull(message="Username is mandatory!")
	@Size(min = 3, max = 30)
	String username;
	
	@NotNull(message="Name is mandatory!")
	@Size(min = 3, max = 30)
	String name;
	
	@NotNull(message="Surname is mandatory!")
	@Size(min = 3, max = 30)
	String surname;
	
	@NotNull
	@Email(message="Insert a valid email address")
	String email;
	
	@NotNull(message = "Password is mandatory!")
	String password;
}
