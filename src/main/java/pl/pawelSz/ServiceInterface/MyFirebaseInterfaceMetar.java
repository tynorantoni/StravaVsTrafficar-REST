package pl.pawelSz.ServiceInterface;

import java.util.LinkedList;
import java.util.List;

public interface MyFirebaseInterfaceMetar {

	static public List<Object> listForMetar = new LinkedList<>();

	public void saveTheMetar(String metar);

	public void readTheMetar();
}
