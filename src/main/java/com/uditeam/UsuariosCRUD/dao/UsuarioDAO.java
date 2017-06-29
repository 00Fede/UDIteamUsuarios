package com.uditeam.UsuariosCRUD.dao;





import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.uditeam.UsuariosCRUD.dto.Usuario;
import com.uditeam.UsuariosCRUD.exception.DaoException;
/**
 * Esta interfaz contiene los metodos necesarios para manipular la base de datos
 * @author Administrator
 *
 */
@Repository
public interface UsuarioDAO extends CrudRepository<Usuario, Integer> {
	/**
	 * Busca username por username y contrasena
	 * @param username
	 * @param contrasena
	 * @return
	 */
	Usuario findByUsernameAndContrasena(String username, String contrasena) throws DaoException;
	
	/**
	 * Encuentra un username por nombre de username
	 * @param username Nombre de username
	 * @return
	 */
	Usuario findByUsername(String usuario) throws DaoException;
}
