/**
 * 
 */
package com.uditeam.UsuariosCRUD.bl.imp;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uditeam.UsuariosCRUD.bl.UsuarioBl;
import com.uditeam.UsuariosCRUD.dao.UsuarioDAO;
import com.uditeam.UsuariosCRUD.dto.Usuario;
import com.uditeam.UsuariosCRUD.encode.Cifrar;
import com.uditeam.UsuariosCRUD.exception.DaoException;
import com.uditeam.UsuariosCRUD.validations.Validaciones;

/**
 * Esta clase realiza la implementacion de la capa de negocio
 * @author Federico Ocampo - CC. 1039464102 - feedkiko@gmail.com
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
	public List<Usuario> listar() throws DaoException {
		
		List<Usuario> usuarios = new ArrayList<>();
		
		Iterator<Usuario> i = userDao.findAll().iterator();
		
		while(i.hasNext()){
			Usuario u = i.next();
			if(!u.getEstado().equals("inactivo")) usuarios.add(u);
		}
		
		return usuarios;
	}

	/* (non-Javadoc)
	 * @see com.uditeam.UsuariosCRUD.bl.UsuarioBl#getUsuarioByCredentials(java.lang.String, java.lang.String)
	 */
	@Override
	public Usuario getUsuarioByCredentials(String username, String password) throws DaoException{
		if(username == null || "".equals(username.trim())) throw new DaoException("Debe ingresar nombre de username");
		if(password == null || "".equals(password.trim())) throw new DaoException("Debe ingresar contrasena");
		Cifrar c = new Cifrar();
		//cifra contrasena antes de verificar identidad
		password = c.encrypt(password); 
		Usuario u = userDao.findByUsernameAndContrasena(username, password);
		return u;
	}

	/* (non-Javadoc)
	 * @see com.uditeam.UsuariosCRUD.bl.UsuarioBl#saveUsuario(com.uditeam.UsuariosCRUD.dto.Usuario)
	 */
	@Override
	public void saveUsuario(Usuario u) throws DaoException{
		String nombre = u.getNombre();
		if(nombre == null || "".equals(nombre.trim())) throw new DaoException("Debe ingresar nombre");
		String apellido = u.getApellido();
		if(apellido== null || "".equals(apellido.trim())) throw new DaoException("Debe ingresar apellido");
		String username = u.getUsername();
		if(username == null || "".equals(username.trim())) throw new DaoException("Debe ingresar nombre de username");
		String password = u.getContrasena();
		if(password == null || "".equals(password.trim())) throw new DaoException("Debe ingresar contrasena");
		String telefono = u.getTelefono();
		if(telefono == null || "".equals(telefono.trim())) throw new DaoException("Debe ingresar telefono");
		if(!Validaciones.isNumber(telefono)) throw new DaoException("El telefono no es valido");
		String correo = u.getEmail();
		if(!Validaciones.isEmail(correo)) throw new DaoException("El correo electronico no es valido");
		if(existeUsername(username)) throw new DaoException("El nombre de username ya existe");
		
		u.setEstado("activo");
		
		Cifrar c = new Cifrar();
		u.setContrasena(c.encrypt(password));
		
		userDao.save(u);
	}
	
	/**
	 * Verifica si ya existe el nombre de username en el sistema
	 * @param username a ser validado
	 * @return true si el username ya existe, false de lo contrario
	 */
	private boolean existeUsername(String username) {
		Iterator<Usuario> usuarios = userDao.findAll().iterator();
		
		username = username.toLowerCase();
		while(usuarios.hasNext()){
			Usuario u = usuarios.next();
			//si ya existe un username con ese username, sin importar el case, retorna true
			if(u.getUsername().toLowerCase().equals(username)) return true;
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see com.uditeam.UsuariosCRUD.bl.UsuarioBl#deleteUsuario(java.lang.Integer)
	 */
	@Override
	public void deleteUsuario(Integer id) throws DaoException{
		
		Usuario delUser = userDao.findOne(id);
		
		if(delUser == null) throw new DaoException("No existe el username a eliminar");
		String estado = delUser.getEstado();
		if(estado.equals("inactivo")) throw new DaoException("El username ya fue borrado");
		
		delUser.setEstado("inactivo");
		
		userDao.save(delUser);
	}
	
	/* (non-Javadoc)
	 * @see com.uditeam.UsuariosCRUD.bl.UsuarioBl#updateUsuario(int, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public Usuario updateUsuario(int id, String name, String apellido, String username, String pass,
			String telefono, String correo, String estado) throws DaoException {
		
		Usuario newUser = userDao.findOne(id);
		
		if(estado!=null) newUser.setEstado(estado);
		if(name!=null) newUser.setNombre(name);
		if(apellido!=null) newUser.setApellido(apellido);
		//se valida que usuario ingresado sea !=null y usuario recibido sea diferente a usuario antiguo
		if(username!=null && !username.equals(newUser.getUsername())){
			if(existeUsername(username)) throw new DaoException("Nombre de username " + username + " ya existe");
			newUser.setUsername(username);
		}
		if(pass!=null){
			Cifrar c = new Cifrar();
			newUser.setContrasena(c.encrypt(pass));
		}
		if(correo!=null){
			if(!Validaciones.isEmail(correo)) throw new DaoException("El correo electrónico no tiene un formato valido");
			newUser.setEmail(correo);
		}
		if(telefono!=null){
			if(!Validaciones.isNumber(telefono)) throw new DaoException("El telefono solo puede contener numeros");
			newUser.setTelefono(telefono);
		}
		
		//Despues de hacer todas las validacion, se procede a actualizar el registro
				
		return userDao.save(newUser);
	}

}
