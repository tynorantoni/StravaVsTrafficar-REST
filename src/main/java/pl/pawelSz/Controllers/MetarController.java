package pl.pawelSz.Controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.pawelSz.Service.MetarService;
import pl.pawelSz.Service.StravaService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class MetarController {

	@Autowired
	public MetarService metarService;
	@Autowired
	public StravaService stravaService;
	
	@RequestMapping("/krk")
	public String getWeather() {
		return metarService.airfieldCall();


	}
	
	@RequestMapping("/strava")
	public String getStrava() {
		return stravaService.getResult("https://www.strava.com/api/v3/athlete");


	}
	
	
}
