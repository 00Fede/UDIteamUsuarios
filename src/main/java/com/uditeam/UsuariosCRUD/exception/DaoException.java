/**
 * 
 */
package com.uditeam.UsuariosCRUD.exception;

import org.apache.log4j.Logger;

/**
 * Esta Clase manejara las excepciones del sistema
 * @author Federico Ocampo - CC 1039464102 - feedkiko@gmail.com
 */
public class DaoException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
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
