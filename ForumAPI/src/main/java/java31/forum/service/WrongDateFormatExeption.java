package java31.forum.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Wrong date format")
public class WrongDateFormatExeption extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
