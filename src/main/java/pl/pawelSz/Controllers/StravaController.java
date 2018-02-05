package pl.pawelSz.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import pl.pawelSz.Entities.Strava;
import pl.pawelSz.Entities.StravaBike;
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
	
	
	
	
	@RequestMapping("/")
	public void getUser() {
		Gson gson = new Gson();
		String json = stravaService.getResult("https://www.strava.com/api/v3/athlete");
		Strava strava = gson.fromJson(json, Strava.class);
		myfirebaseService.saveTheUser(strava.toString());
		
	}
	
//	@RequestMapping("/u")
//	public void getUser1() {
//		
//		 myfirebaseService.readTheUser();
//	}
	@RequestMapping("/bike")
	public void getBike() {
		
		Gson gson = new Gson();
		String json = stravaService.getResult("https://www.strava.com/api/v3/gear/b4302241");
		StravaBike stravaBike = gson.fromJson(json, StravaBike.class);
		myfirebaseService.saveTheBike(stravaBike.toString());
	}
	
//	@RequestMapping("/activities")
//	public void getActivities() {
	
}

