/**
 * 
 */
package com.uditeam.UsuariosCRUD.exception;

import org.apache.log4j.Logger;

/**
 * Esta Clase manejara las excepciones del sistema
 * @author Administrator
 *
 */
public class DaoException extends Exception {
	
	Logger log = Logger.getLogger(this.getClass());

	public DaoException(String message, Throwable cause) {
		super(message, cause);
		log.error(message);
	}
	
	public DaoException(String message) {
		super(message);
		log.error(message);
	}

	public DaoException(Throwable cause) {
		super(cause);
		log.error(cause.getMessage());
	}
}
