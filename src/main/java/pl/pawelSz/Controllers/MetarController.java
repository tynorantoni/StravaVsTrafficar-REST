package pl.pawelSz.Controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import pl.pawelSz.Entities.Metar;
import pl.pawelSz.Service.MetarService;
import pl.pawelSz.Service.MyFirebaseService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000/")
public class MetarController {

	@Autowired
	public MetarService metarService;
	@Autowired
	public MyFirebaseService myfirebaseService;
	
	
	@Scheduled(fixedDelay=1740000)
	@RequestMapping("/krk")
	public String getWeather() {
		Gson gson = new Gson();
		String json = metarService.airfieldCall();
		Metar metar = gson.fromJson(json, Metar.class);
		myfirebaseService.saveTheMetar(gson.toJson(metar.metar));
		return "success?";


	}
	
	@RequestMapping("/krk2")
	public String getWeather2() {
		myfirebaseService.readTheMetar();
//		System.out.println(myfirebaseService.readTheMetar());
		return metarService.airfieldCall();


	}
	
	
}
