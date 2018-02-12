package pl.pawelSz.ServiceInterface;

import java.util.LinkedList;
import java.util.List;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import pl.pawelSz.Entities.StravaRides;

public interface MyFirebaseInterfaceRides {

static public List<Object> listForRides = new LinkedList<>();
	
	public void saveTheRides(StravaRides rides);

	public void readTheRides();
}
