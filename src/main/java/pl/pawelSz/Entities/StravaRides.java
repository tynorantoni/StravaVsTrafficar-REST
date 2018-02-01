package pl.pawelSz.Entities;

import lombok.Data;

@Data
public class StravaRides {

	
	private int rideCount;
	private long totalDistance;
	private long totalMovingTime;
	private long totalElapsedTime;
	private long totalElevationGain;
	
	private long rideId;
	private long distance;
	private long movingTime;
	private long elapsedTime;
	private long elevationGain;
	private double averageSpeed;
	private double maxSpeed;
	private double averageWatts;
	
	private int achievementCount;
	private int kudosCount;
	
}
