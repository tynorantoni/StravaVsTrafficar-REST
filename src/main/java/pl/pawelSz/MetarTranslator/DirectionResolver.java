package pl.pawelSz.MetarTranslator;

import java.util.HashMap;
import java.util.Map;

public class DirectionResolver {

	 public static String direction(int degree) {
	        int key = Math.abs(degree > 22 ? degree / 22 : 0);

	        Map<Integer, String> directionMap = new HashMap<>();

	        directionMap.put(0, "North");
	        directionMap.put(1, "North North East");
	        directionMap.put(2, "North East");
	        directionMap.put(3, "East North East");
	        directionMap.put(4, "East");
	        directionMap.put(5, "East South East");
	        directionMap.put(6, "South East");
	        directionMap.put(7, "South South East");
	        directionMap.put(8, "South");
	        directionMap.put(9, "South South West");
	        directionMap.put(10, "South West");
	        directionMap.put(11, "West South West");
	        directionMap.put(12, "West");
	        directionMap.put(13, "West North West");
	        directionMap.put(14, "North West");
	        directionMap.put(15, "North North West");
	        directionMap.put(16, "North North West");

	        return directionMap.entrySet().stream().filter(map -> map.getKey() == key).map(Map.Entry::getValue).findFirst().get();
	    }

	    //TODO podzielić na trzy metody zwracające mapy i ify przenieść do MetarDecodera?
	    public static String weather(String message) {

	        Map<String, String> weatherAcronyms = new HashMap<>();
	        weatherAcronyms.put("DZ", "Drizzle");
	        weatherAcronyms.put("RA", "Rain");
	        weatherAcronyms.put("SN", "Snow");
	        weatherAcronyms.put("GR", "Hail");
	        weatherAcronyms.put("SQ", "Squalls");
	        weatherAcronyms.put("GS", "Small Hail");
	        weatherAcronyms.put("SG", "Snow Grains");
	        weatherAcronyms.put("IC", "Diamont Dust");
	        weatherAcronyms.put("PE", "Ice Pellets");
	        weatherAcronyms.put("BR", "Mist");
	        weatherAcronyms.put("FG", "Fog");
	        weatherAcronyms.put("FU", "Smoke");
	        weatherAcronyms.put("HZ", "Haze");
	        weatherAcronyms.put("VA", "Volcanic Ash");
	        weatherAcronyms.put("DU", "Widespread dust");
	        weatherAcronyms.put("SA", "Sand");
	        weatherAcronyms.put("SS", "Sand Storm");
	        weatherAcronyms.put("DS", "DustStorm");
	        weatherAcronyms.put("FC", "Funnel Cloud");

	        Map<String, String> weatherAcronymsAdditionals = new HashMap<>();
	        weatherAcronymsAdditionals.put("MI", "Shallow");
	        weatherAcronymsAdditionals.put("BC", "Patches");
	        weatherAcronymsAdditionals.put("DR", "Drifting");
	        weatherAcronymsAdditionals.put("BL", "Blowing");
	        weatherAcronymsAdditionals.put("SH", "Showers");
	        weatherAcronymsAdditionals.put("TS", "Thunderstorms");
	        weatherAcronymsAdditionals.put("FZ", "Supercooled (freezing)");
	        weatherAcronymsAdditionals.put("CB", "Cumulonimbus");
	        weatherAcronymsAdditionals.put("TC", "ToweringCumulonimbus");

	        Map<String, String> weatherAcronymsPrefixes = new HashMap<>();
	        weatherAcronymsPrefixes.put("+", "Heavy");
	        weatherAcronymsPrefixes.put("-", "Slight");
	        if(message.length()==2){
	            return weatherAcronyms.entrySet().stream().filter(map->map.getKey().equals(message.substring(0, 2))).map(Map.Entry::getValue).findFirst().get();
	        }
	        if(message.length()==3){
	            return weatherAcronymsPrefixes.entrySet().stream().filter(map->map.getKey().equals(message.substring(0, 1))).map(Map.Entry::getValue).findFirst().get()+
	                    " "+weatherAcronyms.entrySet().stream().filter(map->map.getKey().equals(message.substring(1, 3))).map(Map.Entry::getValue).findFirst().get();
	        }
	        return weatherAcronymsPrefixes.entrySet().stream().filter(map->map.getKey().equals(message.substring(0, 1))).map(Map.Entry::getValue).findFirst().get()+
	                " "+weatherAcronymsAdditionals.entrySet().stream().filter(map->map.getKey().equals(message.substring(1, 3))).map(Map.Entry::getValue).findFirst().get()+
	                    " "+weatherAcronyms.entrySet().stream().filter(map->map.getKey().equals(message.substring(3, 5))).map(Map.Entry::getValue).findFirst().get();
	    }
	
	
	
}
