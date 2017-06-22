package com.uditeam.UsuariosCRUD.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.uditeam.UsuariosCRUD.dto.Usuario;

public class UsuarioDAOTest {
	
	
	private UsuarioDAO usuarioDAO;

	@Test
	public void testSaveS() {
		Usuario u = new Usuario();
		
		u.setNombre("Federico");
		u.setApellido("Ocampo");
		u.setUsuario("usuario");
		u.setContrasena("pass");
		u.setEstado("activo");
		u.setEmail("email@email.com");
		
		u.setId(1);
		
		usuarioDAO.save(u);
		
		assertTrue(usuarioDAO.exists(1));
		
		fail("Not working");
	}

	@Test
	public void testFindOne() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindAll() {
		assertTrue(usuarioDAO.findAll().iterator().next()!=null);
	}

	@Test
	public void testDeleteID() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteAll() {
		fail("Not yet implemented");
	}

}
