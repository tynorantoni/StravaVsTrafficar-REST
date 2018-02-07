package pl.pawelSz.Controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	
	public Metar mt;
	
	@Scheduled(fixedDelay=1740000)
	@RequestMapping(value = "/krk", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<String> getWeather() {
		Gson gson = new Gson();
		String json = metarService.airfieldCall();
		Metar metar = gson.fromJson(json, Metar.class);
		System.out.println(metarService.metarOnDecode("EPKK 062030Z 04005KT 7000 BKN044 M04\\/M07 Q1018"));
	Metar res = new Metar();
	res.setResult(metarService.metarOnDecode("EPKK 062030Z 04005KT 7000 BKN044 M04\\/M07 Q1018"));
		myfirebaseService.saveTheMetar(gson.toJson(metar.metar));
		return new ResponseEntity<String>(gson.toJson(res.getResult()), HttpStatus.OK);


	}
	
	@RequestMapping("/krk2")
	public String getWeather2() {
		myfirebaseService.readTheMetar();

		return "well";
	}
		
		
		@RequestMapping("/krk3")
		public String getWeather3() {
		

			return MyFirebaseService.mapObj.get(1).toString();

	}
	
	
}
