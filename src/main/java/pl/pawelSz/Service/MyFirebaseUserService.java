package pl.pawelSz.Service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import pl.pawelSz.Entities.Strava;
import pl.pawelSz.ServiceInterface.MyFirebaseInterfaceUser;

@Service("myfirebaseServiceUser")
public class MyFirebaseUserService implements MyFirebaseInterfaceUser {

	
	static public List<Strava> listForUser = new LinkedList<>();
	
	public void saveTheUser(Strava user) {
		final FirebaseDatabase database = FirebaseDatabase.getInstance();
		DatabaseReference ref = database.getReference("user");

		ref.setValueAsync(user);
	}

	public void readTheUser() {

		final FirebaseDatabase database = FirebaseDatabase.getInstance();
		DatabaseReference ref = database.getReference("user");

		ref.addValueEventListener(new ValueEventListener() {
			@Override
			public void onDataChange(DataSnapshot dataSnapshot) {
				Strava obj = dataSnapshot.getValue(Strava.class);
				System.out.println(dataSnapshot.getValue() + " on child changed");
				listForUser.add(obj);
			}

			@Override
			public void onCancelled(DatabaseError databaseError) {
				System.out.println("The read failed: " + databaseError.getCode());
			}
		});

	}
	
}
