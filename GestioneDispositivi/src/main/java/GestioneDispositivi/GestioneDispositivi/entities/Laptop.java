package GestioneDispositivi.GestioneDispositivi.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("LAPTOP")
public class Laptop extends Device{
	
}
