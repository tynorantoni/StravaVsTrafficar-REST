package pl.pawelSz.Entities;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class Metar {

	@SerializedName("data") 
	public String[] metar;
	
	private String result;
	
	public Metar(String result) {
		this.result=result;
	}

	public Metar(){}
	
//	public Metar(String[] metar){
//		this.metar = metar;
//	}
}
