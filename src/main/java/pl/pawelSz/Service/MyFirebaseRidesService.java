package pl.pawelSz.Service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import pl.pawelSz.Entities.StravaRides;
import pl.pawelSz.ServiceInterface.MyFirebaseInterfaceRides;

@Service("myfirebaseServiceRides")
public class MyFirebaseRidesService implements MyFirebaseInterfaceRides {

	static public List<Object> listForRides = new LinkedList<>();
	
	public void saveTheRides(StravaRides rides) {
		final FirebaseDatabase database = FirebaseDatabase.getInstance();
		DatabaseReference ref = database.getReference("activities");
		
		ref.setValueAsync(rides);
		
	}

	public void readTheRides() {

		final FirebaseDatabase database = FirebaseDatabase.getInstance();
		DatabaseReference ref = database.getReference("activities");

		ref.addValueEventListener(new ValueEventListener() {
			@Override
			public void onDataChange(DataSnapshot dataSnapshot) {
				Object obj = dataSnapshot.getValue();
				
				listForRides.add(obj);

			}

			@Override
			public void onCancelled(DatabaseError databaseError) {
				System.out.println("The read failed: " + databaseError.getCode());
			}
		});

	}

}
