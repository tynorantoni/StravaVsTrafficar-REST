package pl.pawelSz.Entities;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class StravaRides {
	
	
//	https://www.strava.com/api/v3/athletes/227615/stats
//		"all_ride_totals": {
//	    "count": 134,
//	    "distance": 4927252,
//	    "moving_time": 659982,
//	    "elapsed_time": 892644,
//	    "elevation_gain": 49940
//	  },
//	@SerializedName("count") 
//	private int rideCount;
//	@SerializedName("distance") 
//	private long totalDistance;
//	@SerializedName("moving_time") 
//	private long totalMovingTime;
//	@SerializedName("elapsed_time") 
//	private long totalElapsedTime;
//	@SerializedName("elecation_gain") 
//	private long totalElevationGain;
	
	@SerializedName("id") 
	private int rideId;
	@SerializedName("distance") 
	private float distance;
	@SerializedName("moving_time") 
	private int movingTime;
	@SerializedName("elapsed_time") 
	private int elapsedTime;
	@SerializedName("total_elevation_gain") 
	private float elevationGain;
	@SerializedName("average_speed") 
	private float averageSpeed;
	@SerializedName("max_speed") 
	private float maxSpeed;
	@SerializedName("average_watts") 
	private float averageWatts;
	
	@SerializedName("achievement_count") 
	private int achievementCount;
	@SerializedName("pr_count") 
	private int personalRecords;
	@SerializedName("kudos_count") 
	private int kudosCount;
	
	
	public StravaRides(int rideId, float distance, int movingTime, int elapsedTime, float elevationGain,
			float averageSpeed, float maxSpeed, float averageWatts, int achievementCount, int personalRecords,
			int kudosCount) {
		super();
		this.rideId = rideId;
		this.distance = distance;
		this.movingTime = movingTime;
		this.elapsedTime = elapsedTime;
		this.elevationGain = elevationGain;
		this.averageSpeed = averageSpeed;
		this.maxSpeed = maxSpeed;
		this.averageWatts = averageWatts;
		this.achievementCount = achievementCount;
		this.personalRecords = personalRecords;
		this.kudosCount = kudosCount;
	}
	
	
	
}
