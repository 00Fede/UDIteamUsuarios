/**
 * 
 */
package com.uditeam.UsuariosCRUD.bl;

import java.util.List;

import com.uditeam.UsuariosCRUD.dto.Usuario;

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
	List<Usuario> listar();
	
	/**
	 * Obtiene la información del usuario después de autenticar el nombre de usuario
	 * y la contrasena ingresada
	 * @param username username del usuario
	 * @param password contrasena del usuario
	 * @return Usuario y toda su información obtenida
	 */
	Usuario getUsuarioByCredentials(String username, String password);
	
	/**
	 * Guarda un usuario u en el sistema
	 * @param u Usuario a ser guardado
	 * @return Usuario guardado
	 */
	Usuario saveUsuario(Usuario u);
	
	/**
	 *  Elimina usuario (borrado lógico) por id
	 * @param id id de usuario a eliminar
	 */
	void deleteUsuario(Integer id);
	
	/**
	 * Actualiza un usuario del sistema
	 * @param u usuario del sistema
	 * @return Usuario actualizado
	 */
	Usuario updateUsuario(Usuario u);
	
}
