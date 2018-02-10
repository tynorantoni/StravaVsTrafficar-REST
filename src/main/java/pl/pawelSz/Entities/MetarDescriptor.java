package pl.pawelSz.Entities;

import lombok.Data;

@Data
public class MetarDescriptor {

	private String airfield;
    private String timestamp;
    private String wind;
    private String windChanges;
    private String visibility;
    private String weather;
    private String clouds;
    private String temperature;
    private String pressure;
    private String message;
    
	public MetarDescriptor(String airfield, String timestamp, String wind, String visibility, String weather,
			String clouds, String temperature, String pressure, String message) {
		
		this.airfield = airfield;
		this.timestamp = timestamp;
		this.wind = wind;
		this.visibility = visibility;
		this.weather = weather;
		this.clouds = clouds;
		this.temperature = temperature;
		this.pressure = pressure;
		this.message = message;
	}
	public MetarDescriptor(String airfield, String timestamp, String wind, String visibility, String weather,
			String clouds, String temperature, String pressure) {
	
		this.airfield = airfield;
		this.timestamp = timestamp;
		this.wind = wind;
		this.visibility = visibility;
		this.weather = weather;
		this.clouds = clouds;
		this.temperature = temperature;
		this.pressure = pressure;
	}
    
   
    
    
	
}
