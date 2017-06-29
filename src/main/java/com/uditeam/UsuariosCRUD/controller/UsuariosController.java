/**
 * 
 */
package com.uditeam.UsuariosCRUD.controller;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.uditeam.UsuariosCRUD.bl.UsuarioBl;
import com.uditeam.UsuariosCRUD.dao.UsuarioDAO;
import com.uditeam.UsuariosCRUD.dto.Usuario;
import com.uditeam.UsuariosCRUD.exception.DaoException;

/**
 * Esta clase respondera a las peticiones web realizadas desde el frontend
 * @author Administrator
 *
 */
// RestController junta Controller y ResponseBody Anotaciones
@RestController
@RequestMapping("/usuarios/")
public class UsuariosController {
	
	Usuario u = new Usuario();
	
	@Autowired
	UsuarioBl userBl;
	
	@Autowired
	UsuarioDAO userDao;


	/** Mapeo para lista, bajo la ruta usuarios/
	 * 
	 * @return hacia la ruta /list con todos los usuarios en modelo usuarios
	 */
	@GetMapping
	public ModelAndView list(){
		List<Usuario> usuarios = null;
		try {
			usuarios = userBl.listar();
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return new ModelAndView("/list", "usuarios", usuarios);
	}

	@GetMapping("prueba")
	public ModelAndView welcome(Map<String, Object> model) {
		ModelAndView mav = new ModelAndView("main");
		return mav;
	}
	
	@GetMapping("/add")
	public ModelAndView createForm(@ModelAttribute Usuario nusuario) {
		return new ModelAndView("form");
	}
	
	/**
	 * Mapeo para guardar un usuario bajo ruta /usuarios
	 * Por ser el unico post, elboton submit de la vista dirige a este link
	 * @param usuario usuario que va a ser guardado
	 * @param result Resultado del proceso de binding entre la vista y el modelo usuario
	 * @param redirect para la redireccion
	 * @return redireccion a usuarios/id con la informacion del nuevo usuario
	 */
	@RequestMapping(value="new", params={"save"})
	public ModelAndView save(@Valid Usuario nusuario,BindingResult result,RedirectAttributes redirect){
		if (result.hasErrors()) { //si se generan errores va a plantilla form
			return new ModelAndView("form", "formErrors", result.getAllErrors()); 
		}
		
		try { //guarda el usuario
			userBl.saveUsuario(nusuario);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		redirect.addFlashAttribute("globalMessage", "Successfully created a new message"); //atributo a satisfactorio
		return new ModelAndView("redirect:/{nusuario.id}", "nusuario.id", nusuario.getId()); //redirige a usuarios/{id} para mostrar nuevo usuario
	}
	
	/**
	 * Mapea en plantilla view modelo usuario
	 * @param usuario
	 * @return plantilla view con el modelo usuario
	 */
	@GetMapping("{id}")
	public ModelAndView view(@PathVariable("id") Usuario usuario) {
		return new ModelAndView("view", "usuario", usuario); //se va a plantilla view con la info del usuario
	}
	
	@GetMapping("creds/{user}/{pass}")
	public ModelAndView getUserInfo(@PathVariable("user") String username, @PathVariable("pass") String pass){
		Usuario user;
		try {
			user = userBl.getUsuarioByCredentials(username, pass);
			System.out.println("Encontro usuario " + username);
			if(user!=null){
				ModelAndView mav = new ModelAndView("main");
				mav.addObject(user);
				return mav;
			}
		} catch (DaoException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * Mapea a /usuarios/udpate/{id} con pathvar id para mostar el update form, se le entrega
	 * el objeto usuario (modelo) correspondiente a id 
	 */
	@GetMapping("update/{id}")
	public ModelAndView update(@PathVariable Integer id){
		ModelAndView mav = new ModelAndView("updateForm");
		Usuario u = userDao.findOne(id);
		mav.addObject("usuario",u);
		return mav;
	}
	
	/**
	 * Mapeo para actualizar usuario, toma usuario modelo de vista y actualiza, retorna en /usuarios/update/ con el
	 * usuario correspondiente al id utilizado
	 * @param id
	 * @param u - recibido de vista con nueva informacion
	 * @return
	 * @throws RemoteException
	 */
	@PostMapping("update/{id}")
	public String saveUpdate(@PathVariable Integer id,@ModelAttribute Usuario u) throws RemoteException{
		
		System.out.println("Entro a update/id POST method");
		
		try {
			userBl.updateUsuario(id,u.getNombre(), u.getApellido(), u.getUsername(), u.getContrasena(), u.getTelefono(), u.getEmail(),u.getEstado());
		} catch (DaoException e) {
			throw new RemoteException(e.getMessage(),e);
		}
		
		return "redirect:update/"+id;
		
	}
	
	@RequestMapping(value="/usuarios", params={"userId"})
	public String deleteUser(final Usuario usuario, final BindingResult result, final HttpServletRequest req) throws RemoteException{
		final int id = Integer.valueOf(req.getParameter("userId"));
		
		try {
			userBl.deleteUsuario(id);
		} catch (DaoException e) {
			throw new RemoteException(e.getMessage(), e);
		}
		
		return "sin terminar";		
	}
	
//	
//	/**
//	 * Servicio para listar todos los usuarios del sistema
//	 * Se consume bajo localhost/usuarios
//	 * @return
//	 * @throws RemoteException
//	 */
//	@RequestMapping(method=RequestMethod.GET,produces="application/json")
//	public List<Usuario> listarTodos() throws RemoteException{
//		try{
//			return userBl.listar();
//		}catch(DaoException e){
//			throw new RemoteException(e.getMessage(), e);
//		}
//	}
//	
	/**
	 * Este metodo recibe una peticion post para guardar un usuario user
	 * @param user Usuario a ser guardado
	 * @return Usuario guardado
	 * @throws RemoteException 
	 */
//	@RequestMapping(value = "/add", method=RequestMethod.POST, produces =MediaType.TEXT_HTML)
//	public String addUser(@RequestBody Usuario user) throws RemoteException{
//		
//		try{
//			userBl.saveUsuario(user);
//			if(user!=null){
//				return "Usuario " + user.getNombre() +" guardado exitosamente.";
//			}else{
//				return "El usuario no pudo ser registrado";
//			}
//		}catch (DaoException e) {
//			throw new RemoteException(e.getMessage(),e);
//		}
//		
//	}
	/**
	 * Este metodo recibe una peticion GET para obtener un usuario dado el nombre de 
	 * usuario y contrasena
	 * @param username - criterio de busqueda
	 * @param pass - criterio de busqueda
	 * @return Usuario encontrado o null si no lo encuentra
	 * @throws RemoteException
	 */
//	@RequestMapping(value="/creds/{username}/{password}", method=RequestMethod.GET, produces="application/json")
//	public Usuario getUsuarioByCreds(
//			@PathVariable String username,
//			@PathVariable String password) 
//					throws RemoteException{
//		try {
//			Usuario u = userBl.getUsuarioByCredentials(username, password);
//			if(u!=null){
//				return u;
//			}else{
//				throw new RemoteException("No se encontro el usuario");
//			}
//		} catch (DaoException e) {
//			throw new RemoteException(e.getMessage(),e);
//		}
//	}
	
	/**
	 * Expone el servicio de actualizar un usuario.
	 * @param user - Usuario a ser actualizado
	 * @return Mensaje de confirmacion
	 * @throws RemoteException
	 */
//	@RequestMapping(value="/update/{username}", method= RequestMethod.PUT, produces=MediaType.TEXT_HTML)
//	public String updateUser(@RequestParam(value="name", required=false) String name,
//			@RequestParam(value="apell", required=false) String apellido,
//			@PathVariable String username,
//			@RequestParam(value="newusername", required=false) String newUsername,
//			@RequestParam(value="pass", required=false) String pass,
//			@RequestParam(value="telefono", required=false) String telefono,
//			@RequestParam(value="correo", required=false) String correo,
//			@RequestParam(value="estado", required=false) String estado) throws RemoteException{
//		try {
//			Usuario u = userBl.updateUsuario(username,name,apellido,newUsername,pass,telefono,correo,estado);
//			if(u!=null){
//				return "Usuario " + u.getUsuario() + " actualizado correctamente";
//			}else{
//				return "Ocurrio un error actualizando usuario";
//			}
//		} catch (DaoException e) {
//			throw new RemoteException(e.getMessage(),e);
//		}
//	}
	
	/**
	 * Expone servicio para eliminar (logico) usuario por id
	 * @param id - id de usuario a eliminar
	 * @return Mensaje de confirmacion
	 * @throws RemoteException
	 */
//	@RequestMapping(value="/delete/{id}", method=RequestMethod.DELETE, produces=MediaType.TEXT_HTML)
//	public String deleteUser(@PathVariable int id) throws RemoteException{
//		try {
//			userBl.deleteUsuario(id); //borrado lógico
//			return "Usuario eliminado correctamente";
//		} catch (DaoException e) {
//			throw new RemoteException(e.getMessage(), e);
//		}
//	}
	
}
