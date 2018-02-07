package pl.pawelSz.MetarTranslator;

import java.util.function.Supplier;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import pl.pawelSz.Entities.MetarDescriptor;

@Service
public class MetarDecoder {

	private String[] metarSplit;

    public String[] spliter(String metarMessage) {
        return metarSplit = metarMessage.split(" ");
    }

    public MetarDescriptor handler(String metar){
    	 Supplier<Stream<String>> streamofArray = () -> Stream.of((spliter(metar)));
    	MetarDescriptor metarObject = new MetarDescriptor(
    		        streamofArray.get().filter(p -> p.matches("\\D{4}")).findFirst().get(),
    		        streamofArray.get().filter(p -> p.contains("Z")).findFirst().get(),
    		        streamofArray.get().filter(p -> p.contains("KT")).findFirst().get(),
    		        streamofArray.get().filter(p -> p.matches("\\d{4}")).findFirst().get(),
    		        "VA",
//    		        streamofArray.get().filter(p -> p.matches("\\D{2}")).findFirst().get(),
    		        streamofArray.get().filter(p -> p.matches("\\D{3}\\d{3}")).findFirst().get(),
    		        "M04\\/M07",
//    		        streamofArray.get().filter(p -> p.matches("\\D*\\d{2}\\/\\D*\\d{2}")).findFirst().get(),
    		       streamofArray.get().filter(p -> p.contains("Q")).findFirst().get()
    		       
    		       );
    	
    	return metarObject;
    }
    
    public boolean krkChecker()  {
        return metarSplit[0].equals("EPKK");
    }

    public String wind(String windMessage) {
        int windDir = Integer.valueOf(windMessage.substring(0, 3));
        int windSpeed = Integer.valueOf(windMessage.substring(2, windMessage.length() - 2));
        return "Wind direction: " + DirectionResolver.direction(windDir) + ", Speed: " + windSpeed + " knots";
    }

    public String visibility(String visibilityMessage) {
        if (Integer.valueOf(visibilityMessage) == 0000) {
            return "Visibility: less than 50 meters";
        }
        if (Integer.valueOf(visibilityMessage) == 9999) {
            return "Visibility: more than 10 0000 meters";
        }
        return "Visibility: " + visibilityMessage + " meters";
    }

    public String temperature(String temperatureMessage) {
        if (temperatureMessage.substring(0, 1).equals("M")) {
            return "Temperature is: Minus " + temperatureMessage.substring(1, 3) + " °C";
        }
        return "Temperature is: " + temperatureMessage.substring(0, 2) + " °C";
    }

    public String dewPiontTemperature(String temperatureMessage) {
        if (temperatureMessage.substring(3, 4).equals("M") || temperatureMessage.substring(4, 5).equals("M")) {
            return "Dew Point Temperature is: Minus " + temperatureMessage.substring(temperatureMessage.length() - 2, temperatureMessage.length()) + " °C";
        }
        return "Dew Point Temperature is: " + temperatureMessage.substring(temperatureMessage.length() - 2, temperatureMessage.length()) + " °C";
    }
    public String pressure(String pressureMessage){
        return "Pressure: "+pressureMessage.substring(1, pressureMessage.length())+" hpa";
    }
	
}