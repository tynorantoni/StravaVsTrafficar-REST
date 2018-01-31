package pl.pawelSz.Controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.pawelSz.Service.MetarService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class MetarController {

	@Autowired
	public MetarService metarService;
	
	@RequestMapping("/krk")
	public String getWeather() {
		return metarService.airfieldCall();


	}
	
	
}
