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
import pl.pawelSz.Entities.Strava;



@Service("myfirebaseService")
public class MyFirebaseService {

	
	static public List<Object> mapObj = new LinkedList<>();
	
	public void stage() throws IOException{
		FileInputStream serviceAccount = new FileInputStream(FirebaseAuthClass.FIREBASE_KEY);


		FirebaseOptions options = new FirebaseOptions.Builder()
		    .setCredential(FirebaseCredentials.fromCertificate(serviceAccount))
		    .setDatabaseUrl("https://bikemanagerapp-48d13.firebaseio.com/")
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
////				Map<String,Metar> metarMap = new HashMap<>();
////				metarMap.put(dataSnapshot.getKey(), dataSnapshot.getValue(Metar.class));
//			    Metar  metar = dataSnapshot.getValue(Metar.class);   
//			    System.out.println(metar.getMetar());
//				System.out.println(metar.getResult());
////				System.out.println(metarMap.size()+" aaaa");
				
			}
			
			@Override
			public void onChildAdded(DataSnapshot dataSnapshot, String arg1) {
				System.out.println(dataSnapshot.getValue());
				Object obj = dataSnapshot.getValue();
				System.out.println(obj);
				mapObj.add(obj);
			}
			
			@Override
			public void onCancelled(DatabaseError databaseError) {
				 System.out.println("The read failed: " + databaseError.getCode());
				
			}});
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
