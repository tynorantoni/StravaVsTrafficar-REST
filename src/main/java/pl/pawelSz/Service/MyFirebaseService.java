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
import pl.pawelSz.ServiceInterface.MyFirebaseInterface;

@Service("myfirebaseService")
public class MyFirebaseService implements MyFirebaseInterface {

	
	public void stageFirebase() throws IOException {
		FileInputStream serviceAccount = new FileInputStream(FirebaseAuthClass.FIREBASE_KEY);
		FirebaseOptions options = new FirebaseOptions.Builder()
				.setCredential(FirebaseCredentials.fromCertificate(serviceAccount))
				.setDatabaseUrl("https://bikemanagerapp-48d13.firebaseio.com/").build();
		FirebaseApp.initializeApp(options);

		DatabaseReference ref = FirebaseDatabase.getInstance().getReference("restricted_access/secret_document");
		ref.addListenerForSingleValueEvent(new ValueEventListener() {
			@Override
			public void onDataChange(DataSnapshot dataSnapshot) {
				Object document = dataSnapshot.getValue();
			}

			@Override
			public void onCancelled(DatabaseError arg0) {
			}
		});
	}
}
