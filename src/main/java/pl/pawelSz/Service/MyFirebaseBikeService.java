package pl.pawelSz.Service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import pl.pawelSz.Entities.StravaBike;
import pl.pawelSz.ServiceInterface.MyFirebaseInterfaceBike;

@Service("myfirebaseServiceBike")
public class MyFirebaseBikeService implements MyFirebaseInterfaceBike {

	static public List<StravaBike> listForBike = new LinkedList<>();
	
	
	public void saveTheBike(StravaBike bike) {
		final FirebaseDatabase database = FirebaseDatabase.getInstance();
		DatabaseReference ref = database.getReference("bike");

		ref.setValueAsync(bike);
	}

	public void readTheBike() {

		final FirebaseDatabase database = FirebaseDatabase.getInstance();
		DatabaseReference ref = database.getReference("bike");

		// Attach a listener to read the data at our posts reference
		ref.addValueEventListener(new ValueEventListener() {
			@Override
			public void onDataChange(DataSnapshot dataSnapshot) {
				StravaBike obj = dataSnapshot.getValue(StravaBike.class);
				System.out.println(dataSnapshot.getValue() + " on child changed");
				listForBike.add(obj);
			}

			@Override
			public void onCancelled(DatabaseError databaseError) {
				System.out.println("The read failed: " + databaseError.getCode());
			}
		});

	}
}
