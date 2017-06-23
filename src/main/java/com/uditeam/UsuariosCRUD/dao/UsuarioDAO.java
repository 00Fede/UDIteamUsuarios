package com.uditeam.UsuariosCRUD.dao;





import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.uditeam.UsuariosCRUD.dto.Usuario;
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
	Usuario findByUsuarioAndContrasena(String usuario, String contrasena);
}
