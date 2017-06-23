/**
 * 
 */
package com.uditeam.UsuariosCRUD.bl.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uditeam.UsuariosCRUD.bl.UsuarioBl;
import com.uditeam.UsuariosCRUD.dao.UsuarioDAO;
import com.uditeam.UsuariosCRUD.dto.Usuario;

/**
 * Implementacion de la capa de negocio
 * @author Administrator
 *
 */
@Service	
public class UsuarioBlImp implements UsuarioBl {
	
	@Autowired
	private UsuarioDAO userDao;
	
	/* (non-Javadoc)
	 * @see com.uditeam.UsuariosCRUD.bl.UsuarioBl#listar()
	 */
	@Override
	public List<Usuario> listar() {
		
		List<Usuario> usuarios = new ArrayList<>();
		
		userDao.findAll()
		.forEach(usuarios::add);
		
		return usuarios;
	}

	/* (non-Javadoc)
	 * @see com.uditeam.UsuariosCRUD.bl.UsuarioBl#getUsuarioByCredentials(java.lang.String, java.lang.String)
	 */
	@Override
	public Usuario getUsuarioByCredentials(String username, String password) {
		// TODO Auto-generated method stub
		Usuario u = userDao.findByUsuarioAndContrasena(username, password);
		return u;
	}

	/* (non-Javadoc)
	 * @see com.uditeam.UsuariosCRUD.bl.UsuarioBl#saveUsuario(com.uditeam.UsuariosCRUD.dto.Usuario)
	 */
	@Override
	public Usuario saveUsuario(Usuario u) {
		// TODO Auto-generated method stub
		return userDao.save(u);
	}

	/* (non-Javadoc)
	 * @see com.uditeam.UsuariosCRUD.bl.UsuarioBl#deleteUsuario(java.lang.Integer)
	 */
	@Override
	public void deleteUsuario(Integer id) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.uditeam.UsuariosCRUD.bl.UsuarioBl#updateUsuario(com.uditeam.UsuariosCRUD.dto.Usuario)
	 */
	@Override
	public Usuario updateUsuario(Usuario u) {
		// TODO Auto-generated method stub
		return null;
	}

}
