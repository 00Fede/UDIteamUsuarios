package com.uditeam.UsuariosCRUD.dao;





import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.uditeam.UsuariosCRUD.dto.Usuario;
import com.uditeam.UsuariosCRUD.exception.DaoException;
/**
 * Esta interfaz contiene los metodos necesarios para manipular la base de datos
 * @author Federico Ocampo - CC 1039464102 - feedkiko@gmail.com
 *
 */
@Repository
public interface UsuarioDAO extends CrudRepository<Usuario, Integer> {
	/**
	 * Busca username por username y contrasena
	 * @param username nombre de usuario, debe existir en el sistema
	 * @param contrasena contraseña del usuario
	 * @return
	 * @throws Excepcion si hay algun error con la conexion a bd
	 */
	Usuario findByUsernameAndContrasena(String username, String contrasena) throws DaoException;
	
	/**
	 * Encuentra un username por nombre de username
	 * @param username Nombre de username
	 * @return
	 * @throws Excepcion si hay algun error con la conexion a bd
	 */
	Usuario findByUsername(String usuario) throws DaoException;
}
