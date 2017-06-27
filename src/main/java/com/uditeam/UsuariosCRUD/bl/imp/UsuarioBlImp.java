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
 * Implementacion de la capa de negocio
 * @author Administrator
 *
 */
@Service	
public class UsuarioBlImp implements UsuarioBl {
	
	@Autowired
	private UsuarioDAO userDao;
	
	/** 
	 * (No solicitado) Lista todos los usuarios que se encuentran activos.
	 */
	@Override
	public List<Usuario> listar() throws DaoException {
		
		List<Usuario> usuarios = new ArrayList<>();
		
		Iterator<Usuario> i = userDao.findAll().iterator();
		
		while(i.hasNext()){
			Usuario u = i.next();
			if(u.getEstado()!="inactivo") usuarios.add(u);
		}
		
		return usuarios;
	}

	/**
	 * Obtiene un usuario por sus credenciales
	 * @param username nombre de usuario de usuario
	 * @param password contrasena de usuario
	 * @return Usuario encontrado con esas credenciales, null si no lo encuentra
	 */
	@Override
	public Usuario getUsuarioByCredentials(String username, String password) throws DaoException{
		if(username == null || "".equals(username.trim())) throw new DaoException("Debe ingresar nombre de usuario");
		if(password == null || "".equals(password.trim())) throw new DaoException("Debe ingresar contrasena");
		Cifrar c = new Cifrar();
		//cifra contrasena antes de verificar identidad
		password = c.encrypt(password); 
		Usuario u = userDao.findByUsuarioAndContrasena(username, password);
		return u;
	}

	/**
	 * Guarda un usuario u en el sistema
	 * @param u Informacion del nuevo usuario a guardar
	 * @return Usuario guardado en el sistema
	 */
	@Override
	public void saveUsuario(Usuario u) throws DaoException{
		String nombre = u.getNombre();
		if(nombre == null || "".equals(nombre.trim())) throw new DaoException("Debe ingresar nombre");
		String apellido = u.getApellido();
		if(apellido== null || "".equals(apellido.trim())) throw new DaoException("Debe ingresar apellido");
		String username = u.getUsuario();
		if(username == null || "".equals(username.trim())) throw new DaoException("Debe ingresar nombre de usuario");
		String password = u.getContrasena();
		if(password == null || "".equals(password.trim())) throw new DaoException("Debe ingresar contrasena");
		String telefono = u.getTelefono();
		if(telefono == null || "".equals(telefono.trim())) throw new DaoException("Debe ingresar telefono");
		if(!Validaciones.isNumber(telefono)) throw new DaoException("El telefono no es valido");
		String correo = u.getEmail();
		if(!Validaciones.isEmail(correo)) throw new DaoException("El correo electronico no es valido");
		if(existeUsername(username)) throw new DaoException("El nombre de usuario ya existe");
		
		u.setEstado("activo");
		
		Cifrar c = new Cifrar();
		u.setContrasena(c.encrypt(password));
		
		userDao.save(u);
	}
	
	/**
	 * Verifica si ya existe el nombre de usuario en el sistema
	 * @param username a ser validado
	 * @return true si el username ya existe, false de lo contrario
	 */
	private boolean existeUsername(String username) {
		Iterator<Usuario> usuarios = userDao.findAll().iterator();
		
		username = username.toLowerCase();
		while(usuarios.hasNext()){
			Usuario u = usuarios.next();
			//si ya existe un usuario con ese username, sin importar el case, retorna true
			if(u.getUsuario().toLowerCase().equals(username)) return true;
		}
		return false;
	}

	/**
	 * Elimina un usuario por id
	 * @param id del usuario a eliminar
	 */
	@Override
	public void deleteUsuario(Integer id) throws DaoException{
		
		Usuario delUser = userDao.findOne(id);
		
		if(delUser == null) throw new DaoException("No existe el usuario a eliminar");
		String estado = delUser.getEstado();
		if(estado.equals("inactivo")) throw new DaoException("El usuario ya fue borrado");
		
		delUser.setEstado("inactivo");
		
		userDao.save(delUser);
	}

	/** 
	 * Actualiza un usuario u
	 * @param u usuario con la nueva informacion
	 * @return usuario modificado
	 */

	@Override
	public Usuario updateUsuario(String username, String name, String apellido, String newUsername, String pass,
			String telefono, String correo, String estado) throws DaoException {
		if(!existeUsername(username)) throw new DaoException("No se encuentra Usuario con username " + username);
		
		Usuario newUser = userDao.findByUsuario(username);
		
		if(estado!=null) newUser.setEstado(estado);
		if(name!=null) newUser.setNombre(name);
		if(apellido!=null) newUser.setApellido(apellido);
		if(newUsername!=null){
			if(existeUsername(newUsername)) throw new DaoException("Nombre de usuario " + newUsername + " ya existe");
			newUser.setUsuario(newUsername);
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
