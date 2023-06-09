package GestioneDispositivi.GestioneDispositivi.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("SMARTPHONE")
public class Smartphone extends Device{
	
}
