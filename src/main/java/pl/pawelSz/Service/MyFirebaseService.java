package pl.pawelSz.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseCredentials;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import pl.pawelSz.Auth.FirebaseAuthClass;
import pl.pawelSz.Entities.Metar;


@Service("myfirebaseService")
public class MyFirebaseService {

	
	
	
	public void stg() throws IOException{
		FileInputStream serviceAccount = new FileInputStream(FirebaseAuthClass.FIREBASE_KEY);


		FirebaseOptions options = new FirebaseOptions.Builder()
		    .setCredential(FirebaseCredentials.fromCertificate(serviceAccount))
		    .setDatabaseUrl("https://bikemanagerapp.firebaseio.com/")
		    .build();
		FirebaseApp.initializeApp(options);
		
		DatabaseReference ref = FirebaseDatabase
			    .getInstance()
			    .getReference("restricted_access/secret_document");
			ref.addListenerForSingleValueEvent(new ValueEventListener() {
			    @Override
			    public void onDataChange(DataSnapshot dataSnapshot) {
			        Object document = dataSnapshot.getValue();
			        System.out.println(document);
			    }

				@Override
				public void onCancelled(DatabaseError arg0) {
				}
			});
		}
	
	
	public void saveTheMetar(String id, String test){
		final FirebaseDatabase database = FirebaseDatabase.getInstance();
		DatabaseReference ref = database.getReference("data/metar");
		
		Map<String, String> users = new HashMap<>();
		users.put(id, test);
		ref.setValueAsync(users);
	}
	
	public void readTheMetar(){
		final FirebaseDatabase database = FirebaseDatabase.getInstance();
		DatabaseReference ref = database.getReference("data/metar");
		ref.addValueEventListener(new ValueEventListener() {
		    @Override
		    public void onDataChange(DataSnapshot dataSnapshot) {
		       Metar metar = dataSnapshot.getValue(Metar.class);
		        System.out.println(metar);
		    }

		    @Override
		    public void onCancelled(DatabaseError databaseError) {
		        System.out.println("The read failed: " + databaseError.getCode());
		    }
		});
	}
	
}
