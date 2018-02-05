package pl.pawelSz.Entities;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class Strava {

	@SerializedName("id") 
	private long userId;
	@SerializedName("firstname") 
	private String firstname;
	@SerializedName("created_at") 
	private String createdAt;
	@SerializedName("updated_at") 
	private String updatedAt;
	@SerializedName("email") 
	private String email;
	

	

	
}

