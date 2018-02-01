package pl.pawelSz.Controllers;


import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.pawelSz.Auth.FirebaseAuthClass;
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
	@Autowired
	public FirebaseAuthClass fac;
	
	@RequestMapping("/krk")
	public String getWeather() {
		try {
			fac.stg();
			fac.jesusSaves(metarService.airfieldCall());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return metarService.airfieldCall();


	}
	
	
}
