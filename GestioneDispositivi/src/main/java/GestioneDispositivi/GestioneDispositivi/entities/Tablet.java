package GestioneDispositivi.GestioneDispositivi.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("TABLET")
public class Tablet extends Device{
	
}