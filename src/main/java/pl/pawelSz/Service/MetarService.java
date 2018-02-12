package pl.pawelSz.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import pl.pawelSz.Auth.MetarAuth;
import pl.pawelSz.Entities.MetarDescriptor;
import pl.pawelSz.MetarTranslator.MetarDecoder;
import pl.pawelSz.ServiceInterface.MetarInterface;

@Service("metarService")
public class MetarService implements MetarInterface {

	@Autowired
	MetarDecoder decoder;
	RestTemplate restTemplate;

	public MetarService() {
		restTemplate = new RestTemplate();

	}

	public String airfieldCall() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set(MetarAuth.METAR_KEY, MetarAuth.METAR_VALUE);

		HttpEntity<String> entity = new HttpEntity<String>(headers);
		String result = restTemplate.postForObject("https://api.checkwx.com/metar/epkk", entity, String.class);
		System.out.println(result);
		return result;

	}

	public MetarDescriptor metarOnDecode(String metar) {

		MetarDescriptor metarObject = decoder.handler(metar);

		metarObject.setTemperature(decoder.temperature(metarObject.getTemperature()) + " "
				+ decoder.dewPiontTemperature(metarObject.getTemperature()));
		metarObject.setPressure(decoder.pressure(metarObject.getPressure()));
		metarObject.setVisibility(decoder.visibility(metarObject.getVisibility()));
		metarObject.setWind(decoder.wind(metarObject.getWind()));

		return metarObject;
	}

}
