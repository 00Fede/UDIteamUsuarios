/**
 * 
 */
package com.uditeam.UsuariosCRUD.bl;

import java.util.List;

import com.uditeam.UsuariosCRUD.dto.Usuario;
import com.uditeam.UsuariosCRUD.exception.DaoException;

/**
 * Esta interfaz contiene los metodos necesarios para
 * utilizar el dao en el sistema
 * @author Administrator
 */
public interface UsuarioBl {
	
	/**
	 * Lista todos los usuarios en el sistema
	 * @return Lista de usuarios en el sistema
	 */
	List<Usuario> listar() throws DaoException;
	
	/**
	 * Obtiene la información del usuario después de autenticar el nombre de usuario
	 * y la contrasena ingresada
	 * @param username username del usuario
	 * @param password contrasena del usuario
	 * @return Usuario y toda su información obtenida
	 */
	Usuario getUsuarioByCredentials(String username, String password) throws DaoException;
	
	/**
	 * Guarda un usuario u en el sistema
	 * @param u Usuario a ser guardado
	 * @return Usuario guardado
	 */
	Usuario saveUsuario(Usuario u) throws DaoException;
	
	/**
	 *  Elimina usuario (borrado lógico) por id
	 * @param id id de usuario a eliminar
	 */
	void deleteUsuario(Integer id) throws DaoException;
	
	/**
	 * Actualiza un usuario del sistema
	 * @param u usuario del sistema
	 * @return Usuario actualizado
	 */
	Usuario updateUsuario(Usuario u) throws DaoException;
	
}
