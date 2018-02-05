package pl.pawelSz.Entities;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class StravaBike {

	@SerializedName("id") 
	private String bikeId;
	@SerializedName("name") 
	private String bikeName;
	@SerializedName("distance") 
	private long bikeTotalDistance;
	
}
