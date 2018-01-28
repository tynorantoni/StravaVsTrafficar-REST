package pl.pawelSz.Service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import pl.pawelSz.Entities.Metar;

@Service("metarService")
public class MetarService {

	RestTemplate restTemplate;

    public MetarService() {
        restTemplate = new RestTemplate();
    }

    public String airfieldCall() {
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);
    	headers.set("x-api-key", "6595b9ffa19255257a0159a6be");

    	HttpEntity<String> entity = new HttpEntity<String>(headers);
    	String result = restTemplate.postForObject("https://api.checkwx.com/metar/epkk", entity, String.class);
    	System.out.println(result);
    	return result;
    			
    }
	
}
