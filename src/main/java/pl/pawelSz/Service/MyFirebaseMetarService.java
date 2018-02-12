package pl.pawelSz.Service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import pl.pawelSz.ServiceInterface.MyFirebaseInterfaceMetar;

@Service("myfirebaseServiceMetar")
public class MyFirebaseMetarService implements MyFirebaseInterfaceMetar {

	static public List<Object> listForMetar = new LinkedList<>();

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
				// System.out.println(dataSnapshot.getValue() + " on child
				// changed");
				Object obj = dataSnapshot.getValue();

				listForMetar.add(obj);

			}

			@Override
			public void onChildAdded(DataSnapshot dataSnapshot, String arg1) {
				// System.out.println(dataSnapshot.getValue() + " on child
				// added");
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
}
