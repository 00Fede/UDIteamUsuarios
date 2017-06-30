/**
 * 
 */
package com.uditeam.UsuariosCRUD.bl;

import java.util.List;

import com.uditeam.UsuariosCRUD.dto.Usuario;
import com.uditeam.UsuariosCRUD.exception.DaoException;

/**
 * Esta interfaz contiene los metodos necesarios para
 * utilizar el dao del sistema
 * @author Federico Ocampo - cc 1039464102 - feedkiko@gmail.com
 */
public interface UsuarioBl {
	
	/**
	 * Lista todos los usuarios en el sistema
	 * @return Lista de usuarios en el sistema
	 * @throws Excepcion si hay algun error con la conexion a bd
	 */
	List<Usuario> listar() throws DaoException;
	
	/**
	 * Obtiene la información del username después de autenticar el nombre de username
	 * y la contrasena ingresada
	 * @param username username del username
	 * @param password contrasena del username
	 * @return Usuario y toda su información obtenida
	 * @throws Excepcion si hay algun error con la conexion a bd
	 */
	Usuario getUsuarioByCredentials(String username, String password) throws DaoException;
	
	/**
	 * Guarda un username u en el sistema
	 * @param u Usuario a ser guardado
	 * @return Usuario guardado
	 * @throws Excepcion si hay algun error con la conexion a bd
	 */
	void saveUsuario(Usuario u) throws DaoException;
	
	/**
	 *  Elimina username (borrado lógico) por id
	 * @param id id de username a eliminar
	 * @throws Excepcion si hay algun error con la conexion a bd
	 */
	void deleteUsuario(Integer id) throws DaoException;
	

	/**
	 * Actualiza un username del sistema
	 * @param id - id unico del usuario
	 * @param name - nombre de usuario
	 * @param apellido apellido de usuario
	 * @param username username de usuario
	 * @param pass contraseña de usuario
	 * @param telefono telefono de usuario
	 * @param correo correo electronico de usuario
	 * @param estado Estado de usuario en el sistema
	 * @return El usuario actualizado
	 * @throws Excepcion si hay algun error con la conexion a bd
	 */
	Usuario updateUsuario(int id, String name, String apellido, String username, String pass,
			String telefono, String correo, String estado) throws DaoException;
	
}
