package pl.pawelSz.StravaVsTrafficarApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import pl.pawelSz.Service.MetarService;

@SpringBootApplication(scanBasePackages={"pl.pawelSz"})
public class StravaVsTrafficarApplication {

	public static void main(String[] args) {
		SpringApplication.run(StravaVsTrafficarApplication.class, args);		
	}
}
