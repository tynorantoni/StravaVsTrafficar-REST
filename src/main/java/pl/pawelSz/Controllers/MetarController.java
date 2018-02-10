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
import pl.pawelSz.Entities.MetarDescriptor;
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

	public Gson gson = new Gson();

	@Scheduled(fixedDelay = 1740000)
	@RequestMapping(value = "/metar", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<String> getMetarFromAPI() {

		String json = metarService.airfieldCall();
		Metar metar = gson.fromJson(json, Metar.class);
		myfirebaseService.saveTheMetar(gson.toJson(metar.metar));
		myfirebaseService.readTheMetar();
		return new ResponseEntity<String>(gson.toJson(metar.metar), HttpStatus.OK);

	}

	@RequestMapping(value = "/metar/decoded", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<String> getDecodedMetar() {

		String metarFromDB = MyFirebaseService.listForMetar.get(MyFirebaseService.listForMetar.size() - 1).toString();
		metarFromDB = metarFromDB.substring(2, metarFromDB.length() - 2);
		MetarDescriptor metarDecoded = metarService.metarOnDecode(metarFromDB);

		return new ResponseEntity<String>(gson.toJson(metarDecoded), HttpStatus.OK);

	}

}