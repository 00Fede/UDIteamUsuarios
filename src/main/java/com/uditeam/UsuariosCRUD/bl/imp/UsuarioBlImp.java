/**
 * 
 */
package com.uditeam.UsuariosCRUD.bl.imp;

import com.uditeam.UsuariosCRUD.bl.UsuarioBl;
import com.uditeam.UsuariosCRUD.dao.UsuarioDAO;
import com.uditeam.UsuariosCRUD.dto.Usuario;

/**
 * Implementacion de la capa de negocio
 * @author Administrator
 *
 */
public class UsuarioBlImp implements UsuarioBl {

	UsuarioDAO userDao;
	
	/* (non-Javadoc)
	 * @see com.uditeam.UsuariosCRUD.bl.UsuarioBl#listar()
	 */
	@Override
	public Iterable<Usuario> listar() {
		Iterable<Usuario> usuarios = userDao.findAll();
		return usuarios;
	}

	/* (non-Javadoc)
	 * @see com.uditeam.UsuariosCRUD.bl.UsuarioBl#getUsuarioByCredentials(java.lang.String, java.lang.String)
	 */
	@Override
	public Usuario getUsuarioByCredentials(String username, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.uditeam.UsuariosCRUD.bl.UsuarioBl#saveUsuario(com.uditeam.UsuariosCRUD.dto.Usuario)
	 */
	@Override
	public Usuario saveUsuario(Usuario u) {
		// TODO Auto-generated method stub
		return null;
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
