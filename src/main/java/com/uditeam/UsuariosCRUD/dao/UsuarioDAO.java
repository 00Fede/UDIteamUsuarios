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
	 * Busca usuario por username y contrasena
	 * @param usuario
	 * @param contrasena
	 * @return
	 */
	Usuario findByUsuarioAndContrasena(String usuario, String contrasena) throws DaoException;
	
	/**
	 * Encuentra un usuario por nombre de usuario
	 * @param usuario Nombre de usuario
	 * @return
	 */
	Usuario findByUsuario(String usuario) throws DaoException;
}
