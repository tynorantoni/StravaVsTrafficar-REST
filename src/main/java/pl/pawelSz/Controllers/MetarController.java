package pl.pawelSz.Controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.pawelSz.Service.MyFirebaseService;
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
	public MyFirebaseService myfirebaseService;
	
	
	@RequestMapping("/krk")
	public String getWeather() {
		myfirebaseService.saveTheMetar("1", metarService.airfieldCall());
		return metarService.airfieldCall();


	}
	@RequestMapping("/krk2")
	public String getWeather2() {
		myfirebaseService.readTheMetar();
//		System.out.println(myfirebaseService.readTheMetar());
		return metarService.airfieldCall();


	}
	
	
}
