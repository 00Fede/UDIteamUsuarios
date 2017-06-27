package com.uditeam.UsuariosCRUD.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uditeam.UsuariosCRUD.bl.UsuarioBl;
import com.uditeam.UsuariosCRUD.dto.Usuario;
import com.uditeam.UsuariosCRUD.exception.DaoException;

/**
 * Esta clase expone los servicios que seran consumidos a traves de REST
 * @author Administrator
 *
 */
@RestController
public class ServiciosUsuario {
	@Autowired
	UsuarioBl userBl;
	
	@RequestMapping("/usuarios")
	public List<Usuario> getAllUsuarios(){
		try {
			return userBl.listar();
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
}
