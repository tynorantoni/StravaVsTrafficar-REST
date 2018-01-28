package pl.pawelSz.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MetarExceptions extends RuntimeException {

	public MetarExceptions(String msg) {
		super("could not get :( '" + msg + "'.");
	}
}