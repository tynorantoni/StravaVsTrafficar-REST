package pl.pawelSz.ServiceInterface;

import java.util.LinkedList;
import java.util.List;

import pl.pawelSz.Entities.StravaBike;

public interface MyFirebaseInterfaceBike {

	static public List<StravaBike> listForBike = new LinkedList<>();

	public void saveTheBike(StravaBike bike);

	public void readTheBike();
}
