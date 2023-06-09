package GestioneDispositivi.GestioneDispositivi.entities;

import java.util.UUID;

import GestioneDispositivi.GestioneDispositivi.enums.State;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Data
public abstract class Device {
	
	@Id
	@GeneratedValue
	private UUID id;
	@Enumerated(EnumType.STRING)
	private State state;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_username")
	private User user;
	
}
