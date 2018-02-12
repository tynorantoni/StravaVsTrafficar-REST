package pl.pawelSz.ServiceInterface;

import pl.pawelSz.Entities.MetarDescriptor;

public interface MetarInterface {
	
	public String airfieldCall();

	public MetarDescriptor metarOnDecode(String metar);
	

}
