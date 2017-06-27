/**
 * 
 */
package com.uditeam.UsuariosCRUD.controller;

import java.rmi.RemoteException;
import java.util.List;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uditeam.UsuariosCRUD.bl.UsuarioBl;
import com.uditeam.UsuariosCRUD.dto.Usuario;
import com.uditeam.UsuariosCRUD.exception.DaoException;


/**
 * Esta clase respondera a las peticiones web realizadas desde el frontend
 * @author Administrator
 *
 */
// RestController junta Controller y ResponseBody Anotaciones
@RestController
@RequestMapping("usuarios")
public class UsuariosController {
	
	Usuario u = new Usuario();
	
	@Autowired
	UsuarioBl userBl;
	
	/**
	 * Servicio para listar todos los usuarios del sistema
	 * Se consume bajo localhost/usuarios
	 * @return
	 * @throws RemoteException
	 */
	@RequestMapping(method=RequestMethod.GET,produces="application/json")
	public List<Usuario> listarTodos() throws RemoteException{
		try{
			return userBl.listar();
		}catch(DaoException e){
			throw new RemoteException(e.getMessage(), e);
		}
	}
	
	/**
	 * Este metodo recibe una peticion post para guardar un usuario user
	 * @param user Usuario a ser guardado
	 * @return Usuario guardado
	 * @throws RemoteException 
	 */
	@RequestMapping(value = "/add", method=RequestMethod.POST, produces =MediaType.TEXT_HTML)
	public String addUser(@RequestBody Usuario user) throws RemoteException{
		
		try{
			userBl.saveUsuario(user);
			if(user!=null){
				return "Usuario " + user.getNombre() +" guardado exitosamente.";
			}else{
				return "El usuario no pudo ser registrado";
			}
		}catch (DaoException e) {
			throw new RemoteException(e.getMessage(),e);
		}
		
	}
	/**
	 * Este metodo recibe una peticion GET para obtener un usuario dado el nombre de 
	 * usuario y contrasena
	 * @param username - criterio de busqueda
	 * @param pass - criterio de busqueda
	 * @return Usuario encontrado o null si no lo encuentra
	 * @throws RemoteException
	 */
	@RequestMapping(value="/creds/{username}/{password}", method=RequestMethod.GET, produces="application/json")
	public Usuario getUsuarioByCreds(
			@PathVariable String username,
			@PathVariable String password) 
					throws RemoteException{
		try {
			Usuario u = userBl.getUsuarioByCredentials(username, password);
			if(u!=null){
				return u;
			}else{
				throw new RemoteException("No se encontro el usuario");
			}
		} catch (DaoException e) {
			throw new RemoteException(e.getMessage(),e);
		}
	}
	
	/**
	 * Expone el servicio de actualizar un usuario.
	 * @param user - Usuario a ser actualizado
	 * @return Mensaje de confirmacion
	 * @throws RemoteException
	 */
	@RequestMapping(value="/update/{username}", method= RequestMethod.PUT, produces=MediaType.TEXT_HTML)
	public String updateUser(@RequestParam(value="name", required=false) String name,
			@RequestParam(value="apell", required=false) String apellido,
			@PathVariable String username,
			@RequestParam(value="newusername", required=false) String newUsername,
			@RequestParam(value="pass", required=false) String pass,
			@RequestParam(value="telefono", required=false) String telefono,
			@RequestParam(value="correo", required=false) String correo,
			@RequestParam(value="estado", required=false) String estado) throws RemoteException{
		try {
			Usuario u = userBl.updateUsuario(username,name,apellido,newUsername,pass,telefono,correo,estado);
			if(u!=null){
				return "Usuario " + u.getUsuario() + " actualizado correctamente";
			}else{
				return "Ocurrio un error actualizando usuario";
			}
		} catch (DaoException e) {
			throw new RemoteException(e.getMessage(),e);
		}
	}
	
	/**
	 * Expone servicio para eliminar (logico) usuario por id
	 * @param id - id de usuario a eliminar
	 * @return Mensaje de confirmacion
	 * @throws RemoteException
	 */
	@RequestMapping(value="/delete/{id}", method=RequestMethod.DELETE, produces=MediaType.TEXT_HTML)
	public String deleteUser(@PathVariable int id) throws RemoteException{
		try {
			userBl.deleteUsuario(id); //borrado lógico
			return "Usuario eliminado correctamente";
		} catch (DaoException e) {
			throw new RemoteException(e.getMessage(), e);
		}
	}
	
}
