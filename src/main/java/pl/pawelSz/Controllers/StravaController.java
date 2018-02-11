package pl.pawelSz.Controllers;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;

import pl.pawelSz.Entities.Strava;
import pl.pawelSz.Entities.StravaBike;
import pl.pawelSz.Entities.StravaRides;
import pl.pawelSz.Service.MyFirebaseService;
import pl.pawelSz.Service.StravaService;

@RestController
@RequestMapping("/api/strava")
@CrossOrigin(origins = "http://localhost:3000")
public class StravaController {

	@Autowired
	public StravaService stravaService;
	@Autowired
	public MyFirebaseService myfirebaseService;

	public Gson gson = new GsonBuilder().disableHtmlEscaping().create();

	@Scheduled(fixedDelay = 900000)
	@RequestMapping(value = "/user", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<String> getUser() {

		String json = stravaService.getResult("https://www.strava.com/api/v3/athlete");
		Strava strava = gson.fromJson(json, Strava.class);
		myfirebaseService.saveTheUser(strava);
		myfirebaseService.readTheUser();

		return new ResponseEntity<String>(gson.toJson(strava), HttpStatus.OK);
	}

	@RequestMapping(value = "/user/get", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<String> getUserFromDB() {

		String userFromDB = gson.toJson(MyFirebaseService.listForUser.get(MyFirebaseService.listForUser.size() - 1), Strava.class);

		return new ResponseEntity<String>(userFromDB, HttpStatus.OK);
	}

	@Scheduled(fixedDelay = 900000)
	@RequestMapping(value = "/bike", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<String> getBike() {

		String json = stravaService.getResult("https://www.strava.com/api/v3/gear/b4302241");
		StravaBike stravaBike = gson.fromJson(json, StravaBike.class);
		myfirebaseService.saveTheBike(stravaBike);
		myfirebaseService.readTheBike();

		return new ResponseEntity<String>(gson.toJson(stravaBike), HttpStatus.OK);
	}

	@RequestMapping(value = "/bike/get", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<String> getBikeFromDB() {

		String bikeFromDB = gson.toJson(MyFirebaseService.listForBike.get(MyFirebaseService.listForBike.size() - 1), StravaBike.class);
			
		return new ResponseEntity<String>(bikeFromDB, HttpStatus.OK);
	}

	@Scheduled(fixedDelay = 900000)
	@RequestMapping(value = "/activities", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<String> getActivities() {

		String json = stravaService.getResult("https://www.strava.com/api/v3/athlete/activities?per_page=200");
		Type listType = new TypeToken<List<StravaRides>>(){}.getType();
		List<StravaRides> stravaRides = gson.fromJson(json, listType);
		for(StravaRides ride: stravaRides){
			System.out.println(ride+"to jedna jazda z cntroll");
		myfirebaseService.saveTheRides(ride);
		break;
		}
		myfirebaseService.readTheRides();
		return new ResponseEntity<String>(gson.toJson(stravaRides), HttpStatus.OK);
	}

	@RequestMapping(value = "/activities/get", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<String> getActivitiesFromDB() {

		String ridesFromDB=gson.toJson(MyFirebaseService.listForRides.get(MyFirebaseService.listForRides.size() - 1),Object.class);
		return new ResponseEntity<String>(ridesFromDB, HttpStatus.OK);
	}
}
