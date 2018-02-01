package pl.pawelSz.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.stereotype.Service;

import pl.pawelSz.Auth.StravaAuth;

@Service("stravaService")
public class StravaService {

	

	

	public String getResult(String URL){
        StringBuilder sb= new StringBuilder();

        try {
            URL url = new URL(URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("Authorization","Bearer "+StravaAuth.ACCESS_TOKEN);

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode() + " - " + conn.getResponseMessage());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;
            while ((output = br.readLine()) != null) {
                sb.append(output);
            }

            conn.disconnect();

        } catch (IOException e) {

            e.printStackTrace();
            return null;
        }
        System.out.println(sb.toString());
        return sb.toString();

    }
	
	
}
