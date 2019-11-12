package java31.forum.dao;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
public class PostNotFoundThrow extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public PostNotFoundThrow (String id) {
		super("Post with id: " + id + " not faund");
	}

}
