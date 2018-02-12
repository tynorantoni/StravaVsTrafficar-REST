package pl.pawelSz.ServiceInterface;

import java.util.LinkedList;
import java.util.List;

import pl.pawelSz.Entities.Strava;

public interface MyFirebaseInterfaceUser {

static public List<Strava> listForUser = new LinkedList<>();
	
	public void saveTheUser(Strava user);

	public void readTheUser();
}
