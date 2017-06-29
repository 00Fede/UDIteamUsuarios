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
	 * Obtiene la información del username después de autenticar el nombre de username
	 * y la contrasena ingresada
	 * @param username username del username
	 * @param password contrasena del username
	 * @return Usuario y toda su información obtenida
	 */
	Usuario getUsuarioByCredentials(String username, String password) throws DaoException;
	
	/**
	 * Guarda un username u en el sistema
	 * @param u Usuario a ser guardado
	 * @return Usuario guardado
	 */
	void saveUsuario(Usuario u) throws DaoException;
	
	/**
	 *  Elimina username (borrado lógico) por id
	 * @param id id de username a eliminar
	 */
	void deleteUsuario(Integer id) throws DaoException;
	

	/**
	 * Actualiza un username del sistema
	 * @param id - id unico del usuario
	 * @param name
	 * @param apellido
	 * @param username
	 * @param pass
	 * @param telefono
	 * @param correo
	 * @param estado
	 * @return
	 */
	Usuario updateUsuario(int id, String name, String apellido, String username, String pass,
			String telefono, String correo, String estado) throws DaoException;
	
}
