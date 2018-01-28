package pl.pawelSz.Entities;

import javax.persistence.Entity;

@Entity
public class Metar {

	public String metar;

	public Metar(String metar) {
		
		this.metar = metar;
	}

	public String getMetar() {
		return metar;
	}

	public void setMetar(String metar) {
		this.metar = metar;
	}

	@Override
	public String toString() {
		return "Metar [metar=" + metar + "]";
	}
	
	
	
}
