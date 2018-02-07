package pl.pawelSz.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseCredentials;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import pl.pawelSz.Auth.FirebaseAuthClass;

@Service("myfirebaseService")
public class MyFirebaseService {

	static public List<Object> listForMetar = new LinkedList<>();
	static public List<Object> listForUser = new LinkedList<>();
	static public List<Object> listForBike = new LinkedList<>();
	static public List<Object> listForRides = new LinkedList<>();

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
				System.out.println(document);
			}

			@Override
			public void onCancelled(DatabaseError arg0) {
			}
		});
	}

	public void saveTheMetar(String metar) {
		final FirebaseDatabase database = FirebaseDatabase.getInstance();
		DatabaseReference ref = database.getReference("data/metar");
		ref.push().setValueAsync(metar);
	}

	public void readTheMetar() {
		final FirebaseDatabase database = FirebaseDatabase.getInstance();
		DatabaseReference ref = database.getReference("data/metar");

		ref.addChildEventListener(new ChildEventListener() {

			@Override
			public void onChildRemoved(DataSnapshot arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onChildMoved(DataSnapshot arg0, String arg1) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onChildChanged(DataSnapshot dataSnapshot, String arg1) {
				System.out.println(dataSnapshot.getValue() + " on child changed");
				Object obj = dataSnapshot.getValue();

				listForMetar.add(obj);

			}

			@Override
			public void onChildAdded(DataSnapshot dataSnapshot, String arg1) {
				System.out.println(dataSnapshot.getValue() + " on child added");
				Object obj = dataSnapshot.getValue();

				listForMetar.add(obj);
			}

			@Override
			public void onCancelled(DatabaseError databaseError) {
				Object errorObj = databaseError.getCode();
				listForMetar.add(errorObj);
			}
		});
	}

	public void saveTheUser(String user) {
		final FirebaseDatabase database = FirebaseDatabase.getInstance();
		DatabaseReference ref = database.getReference("user");

		ref.setValueAsync(user);
	}

	public void readTheUser() {
		final FirebaseDatabase database = FirebaseDatabase.getInstance();
		DatabaseReference ref = database.getReference("user");

		ref.addChildEventListener(new ChildEventListener() {

			@Override
			public void onChildRemoved(DataSnapshot snapshot) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onChildMoved(DataSnapshot snapshot, String previousChildName) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onChildChanged(DataSnapshot dataSnapshot, String previousChildName) {
				Object obj = dataSnapshot.getValue();

				listForUser.add(obj);

			}

			@Override
			public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {
				Object obj = dataSnapshot.getValue();

				listForUser.add(obj);

			}

			@Override
			public void onCancelled(DatabaseError databaseError) {
				Object objError = databaseError.getCode();

				listForUser.add(objError);

			}
		});
	}

	public void saveTheBike(String bike) {
		final FirebaseDatabase database = FirebaseDatabase.getInstance();
		DatabaseReference ref = database.getReference("bike");

		ref.setValueAsync(bike);
	}

	public void readTheBike() {
		final FirebaseDatabase database = FirebaseDatabase.getInstance();
		DatabaseReference ref = database.getReference("bike");

		ref.addChildEventListener(new ChildEventListener() {

			@Override
			public void onChildRemoved(DataSnapshot snapshot) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onChildMoved(DataSnapshot snapshot, String previousChildName) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onChildChanged(DataSnapshot dataSnapshot, String previousChildName) {
				Object obj = dataSnapshot.getValue();

				listForBike.add(obj);

			}

			@Override
			public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {
				Object obj = dataSnapshot.getValue();

				listForBike.add(obj);

			}

			@Override
			public void onCancelled(DatabaseError databaseError) {
				Object objError = databaseError.getCode();

				listForBike.add(objError);

			}
		});
	}

	public void saveTheRides(String rides) {
		final FirebaseDatabase database = FirebaseDatabase.getInstance();
		DatabaseReference ref = database.getReference("data/rides");

		ref.setValueAsync(rides);

	}

	public void readTheRides() {
		final FirebaseDatabase database = FirebaseDatabase.getInstance();
		DatabaseReference ref = database.getReference("data/rides");

		ref.addChildEventListener(new ChildEventListener() {

			@Override
			public void onChildRemoved(DataSnapshot snapshot) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onChildMoved(DataSnapshot snapshot, String previousChildName) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onChildChanged(DataSnapshot dataSnapshot, String previousChildName) {
				Object obj = dataSnapshot.getValue();

				listForRides.add(obj);

			}

			@Override
			public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {
				Object obj = dataSnapshot.getValue();

				listForRides.add(obj);

			}

			@Override
			public void onCancelled(DatabaseError databaseError) {
				Object objError = databaseError.getCode();

				listForRides.add(objError);

			}
		});
	}

}
