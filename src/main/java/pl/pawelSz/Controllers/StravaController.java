package pl.pawelSz.Controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.pawelSz.Service.StravaService;

@RestController
@RequestMapping("/api/strava")
@CrossOrigin(origins = "http://localhost:4200")
public class StravaController {

	
	@Autowired
	public StravaService stravaService;
	
	
	@RequestMapping("/")
	public String getUser() {
		return stravaService.getResult("https://www.strava.com/api/v3/athlete");
	}
	@RequestMapping("/bike")
	public String getBike() {
		return stravaService.getResult("https://www.strava.com/api/v3/gear/b4302241");
	}
	
}

