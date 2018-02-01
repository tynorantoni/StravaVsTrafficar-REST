package pl.pawelSz.Auth;

import java.io.FileInputStream;
import java.io.IOException;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseCredentials;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FirebaseAuthClass {

	public static final String FIREBASE_KEY = "//assets//bikemanagerapp-firebase-adminsdk-ydzdb-511a0b6db3.json";
	
	public void stg() throws IOException{
	FileInputStream serviceAccount = new FileInputStream(FIREBASE_KEY);


	FirebaseOptions options = new FirebaseOptions.Builder()
	    .setCredential(FirebaseCredentials.fromCertificate(serviceAccount))
	    .setDatabaseUrl("https://databaseName.firebaseio.com")
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
				System.out.println("dupa");
				
			}
		});
	}
	
}
