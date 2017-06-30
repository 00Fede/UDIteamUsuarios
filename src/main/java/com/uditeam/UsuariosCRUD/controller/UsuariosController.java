package com.uditeam.UsuariosCRUD.controller;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.uditeam.UsuariosCRUD.bl.UsuarioBl;
import com.uditeam.UsuariosCRUD.dao.UsuarioDAO;
import com.uditeam.UsuariosCRUD.dto.Usuario;
import com.uditeam.UsuariosCRUD.exception.DaoException;

/**
 * Esta clase respondera a las peticiones web realizadas desde el frontend
 * @author Federico Ocampo - C.C. 1039464102 - feedkiko@gmail.com
 */
@RestController
public class UsuariosController {
	
	@Autowired
	UsuarioBl userBl;
	
	@Autowired
	UsuarioDAO userDao;


	/** 
	 * Mapeo para listar todos los usuarios, muestra la pagina principal
	 * template a usar es list.html
	 * @param user Usuario que se rellenara en la vista
	 * @return muestra list.html con todos los usuarios en modelo usuarios
	 */
	@GetMapping({"/","/usuarios"})
	public ModelAndView list(@ModelAttribute Usuario user){
		List<Usuario> usuarios = new ArrayList<>(); 
		try {
			usuarios = userBl.listar();
		} catch (DaoException e) {
			e.printStackTrace();
		}
		
		ModelAndView mav = new ModelAndView("/list");
		//si no se ingresa usuario, cree un usuario vacio, necesario para form de buscar.
		if(user==null) {
			user = new Usuario();
		};
		
		mav.addObject("usuarios",usuarios);
		mav.addObject("usuario",user);
		return mav;
	}
	
	/**
	 * Devuelve en la vista el formulario (form.html) para crear usuario.
	 * @param usuario - obj usuario vacio donde se inyectara info del usuario
	 * @return
	 */
	@GetMapping("/usuarios/add")
	public ModelAndView createForm(@ModelAttribute Usuario usuario) {
		return new ModelAndView("/form");
	}
	
	/**
	 * Mapeo para guardar un usuario bajo ruta /usuarios
	 * Por ser el unico post, elboton submit de la vista dirige a este link
	 * @param nusuario usuario que va a ser guardado
	 * @param result Resultado del proceso de binding entre la vista y el modelo usuario
	 * @param redirect para la redireccion
	 * @return redireccion a usuarios/id con la informacion del nuevo usuario
	 * @throws RemoteException Lanza una excepción al cliente con el mensaje de DaoException
	 */
	@RequestMapping(value="/usuarios", params={"save"})
	public ModelAndView save(@Valid Usuario nusuario,BindingResult result,RedirectAttributes redirect) throws RemoteException{
		if (result.hasErrors()) { //si se generan errores va a plantilla form
			return new ModelAndView("form", "formErrors", result.getAllErrors()); 
		}
		
		try { //guarda el usuario
			userBl.saveUsuario(nusuario);
		} catch (DaoException e) {
			throw new RemoteException(e.getMessage(), e);
		}
		redirect.addFlashAttribute("globalMessage", "Usuario agregado exitosamente"); //atributo a satisfactorio
		return new ModelAndView("redirect:/"); //redirige a usuarios/{id} para mostrar nuevo usuario
	}
	
	@RequestMapping(value="/usuarios/creds")
	public ModelAndView getUserInfo(@ModelAttribute Usuario usuario){		
		final String username = usuario.getUsername();
		final String contrasena = usuario.getContrasena();
		
		System.out.println("usuario y contraseña recibidos" + username + " " + contrasena);
		
		try {
			Usuario user = userBl.getUsuarioByCredentials(username, contrasena);
			if(user!=null){
				//userResult(user);
				System.out.println("Encontro usuario " + user.getNombre());
				List<Usuario> usuarios = new ArrayList<>();
				usuarios.add(user);
				ModelAndView mav = new ModelAndView("/list");
				mav.addObject("usuarios",usuarios);
				//necesario para mostrar boton de atras.
				mav.addObject("esconsulta",true);
				
				return mav;
			}else{
				return new ModelAndView("No se encontro usuario");
			}
		} catch (DaoException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * Mapea a /usuarios/udpate/{id} con pathvar id para mostar el update form, se le entrega
	 * el objeto usuario (modelo) correspondiente a id 
	 * @return id id de usuario que se va actualizar, con este id se obtiene la informacion del usuario
	 */
	@GetMapping("update/{id}")
	public ModelAndView update(@PathVariable Integer id){
		ModelAndView mav = new ModelAndView("updateForm");
		Usuario u = userDao.findOne(id);
		mav.addObject("usuario",u);
		return mav;
	}
	
	/**
	 * Mapeo para actualizar usuario, toma usuario modelo de vista y actualiza, retorna en /usuarios
	 * @param u - recibido de vista con nueva informacion
	 * @param redirect contiene atributos para la redireccion a la vista
	 * @return Vista y Modelo para /
	 * @throws RemoteException
	 */
	@RequestMapping(value = "/usuarios", params={"update"})
	public ModelAndView saveUpdate(@ModelAttribute Usuario u, RedirectAttributes redirect) throws RemoteException{
		
		System.out.println("Entro a update/id POST method");
		
		try {
			userBl.updateUsuario(u.getId(),u.getNombre(), u.getApellido(), u.getUsername(), u.getContrasena(), u.getTelefono(), u.getEmail(),u.getEstado());
		} catch (DaoException e) {
			throw new RemoteException(e.getMessage(),e);
		}
		redirect.addFlashAttribute("globalMessage", "Usuario actualizado exitosamente");
		return new ModelAndView("redirect:/"); //redirige a usuarios/{id} para mostrar nuevo usuario;
		
	}
	
	/**
	 * Funcionalidad bajo /usuarios?userId=id para eliminar usuario
	 * @param req de este parametro se obtiene información del request
	 * @param redirect contiene atributos para la redireccion a la vista
	 * @return Modelo y Vista para /
	 * @throws RemoteException
	 */
	@RequestMapping(value="/usuarios", params={"userId"})
	public ModelAndView deleteUser(final HttpServletRequest req, RedirectAttributes redirect) throws RemoteException{
		final int id = Integer.valueOf(req.getParameter("userId"));
		
		System.out.println("Method delete id usuario para eliminar " + id);
		
		try {
			userBl.deleteUsuario(id);
		} catch (DaoException e) {
			throw new RemoteException(e.getMessage(), e);
		}
		redirect.addFlashAttribute("globalMessage", "El usuario se ha eliminado correctamente");
		return new ModelAndView("redirect:/");		
	}

	
}

//@GetMapping("{id}")
//public ModelAndView view(@PathVariable("id") Usuario usuario) {
//	return new ModelAndView("view", "usuario", usuario); //se va a plantilla view con la info del usuario
//}