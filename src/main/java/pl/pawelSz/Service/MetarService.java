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

@Service("metarService")
public class MetarService {

	RestTemplate restTemplate;
	@Autowired
	MetarDecoder decoder;

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
	
    public String metarOnDecode(String metar){
    	StringBuilder sb = new StringBuilder();
    	MetarDescriptor metarObject = decoder.handler(metar);
    	
    	sb.append(decoder.temperature(metarObject.getTemperature()));
    	sb.append(decoder.dewPiontTemperature(metarObject.getTemperature()));
    	sb.append(decoder.pressure(metarObject.getPressure()));
    	sb.append(decoder.visibility(metarObject.getVisibility()));
    	sb.append(decoder.wind(metarObject.getWind()));
    	
    	return sb.toString();
    }
    
}
