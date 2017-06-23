package com.uditeam.UsuariosCRUD.dao;



import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import com.uditeam.UsuariosCRUD.dto.Usuario;
/**
 * Esta interfaz contiene los metodos necesarios para manipular la base de datos
 * @author Administrator
 *
 */
@RepositoryRestResource
public interface UsuarioDAO extends CrudRepository<Usuario, Integer> {
	
}
