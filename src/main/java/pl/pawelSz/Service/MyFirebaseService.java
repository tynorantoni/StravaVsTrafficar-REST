package pl.pawelSz.Service;

import java.io.FileInputStream;
import java.io.IOException;

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
import pl.pawelSz.Entities.Strava;



@Service("myfirebaseService")
public class MyFirebaseService {

	
	
	
	public void stage() throws IOException{
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
	
	
	public void saveTheMetar(String metar){
		final FirebaseDatabase database = FirebaseDatabase.getInstance();
		DatabaseReference ref = database.getReference("data/metar");
		System.out.println("trololololo lololo lololoooo");
		ref.push().setValueAsync(metar);
	}
	
	public void readTheMetar(){
		final FirebaseDatabase database = FirebaseDatabase.getInstance();
		DatabaseReference ref = database.getReference("data/metar");
		ref.addValueEventListener(new ValueEventListener() {
		    @Override
		    public void onDataChange(DataSnapshot dataSnapshot) {
		    	Metar[] metar = {dataSnapshot.getValue(Metar.class)};
		       System.out.println(metar[0]+"z db");
		    }

		    @Override
		    public void onCancelled(DatabaseError databaseError) {
		        System.out.println("The read failed: " + databaseError.getCode());
		    }
		});
	}
	
	public void saveTheUser(String user){
		final FirebaseDatabase database = FirebaseDatabase.getInstance();
		DatabaseReference ref = database.getReference("user");
		

		ref.setValueAsync(user);
	}
	
	public void readTheUser(){
		
		final FirebaseDatabase database = FirebaseDatabase.getInstance();
		DatabaseReference ref = database.getReference("user");
		
		 ref.addValueEventListener(new ValueEventListener() {
		    @Override
		    public void onDataChange(DataSnapshot dataSnapshot) {
		        Strava strava = dataSnapshot.getValue(Strava.class);
		      	System.out.println(strava);
		    }

		    @Override
		    public void onCancelled(DatabaseError databaseError) {
		        System.out.println("The read failed: " + databaseError.getCode());
		    }
		});
		
	}
	
	public void saveTheBike(String bike){
		final FirebaseDatabase database = FirebaseDatabase.getInstance();
		DatabaseReference ref = database.getReference("bike");
		

		ref.setValueAsync(bike);
	}
	
}
