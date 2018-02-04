package pl.pawelSz.StravaVsTrafficarApp;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import pl.pawelSz.Service.MyFirebaseService;

@SpringBootApplication(scanBasePackages={"pl.pawelSz"})
public class StravaVsTrafficarApplication {

	public static void main(String[] args) {
		SpringApplication.run(StravaVsTrafficarApplication.class, args);	
		try {
			new MyFirebaseService().stg();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
