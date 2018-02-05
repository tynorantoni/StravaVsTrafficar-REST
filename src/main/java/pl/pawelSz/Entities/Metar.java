package pl.pawelSz.Entities;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class Metar {

	@SerializedName("data") 
	public String[] metar;

	
	
}
