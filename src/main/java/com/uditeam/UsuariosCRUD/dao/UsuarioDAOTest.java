package com.uditeam.UsuariosCRUD.dao;

import static org.junit.Assert.*;

import java.util.List;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.uditeam.UsuariosCRUD.dto.Usuario;

@RunWith(SpringJUnit4ClassRunner.class)
// Anotacion para pruebas en Spring Boot https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-testing.html
@SpringBootTest
public class UsuarioDAOTest {
	
	
	@Autowired
	UsuarioDAO usuarioDAO;
	
	/**
	 * @param usuario
	 * @param contrasena
	 * @return
	 * @see com.uditeam.UsuariosCRUD.dao.UsuarioDAO#findByUsuarioAndContrasena(java.lang.String, java.lang.String)
	 */
	@Test
	public void testFindByUsuarioAndContrasena() {
		try{
			Usuario u = usuarioDAO.findByUsuarioAndContrasena("user", "password");
			assertTrue(u!=null);
		}catch (Exception e){
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	
	@Test
	@Transactional
	public void testUpdateUsuario(){
		Usuario nuevoUser = usuarioDAO.findOne(1);
		//borrado logico
		nuevoUser.setEstado("activo");
		try{
			assertTrue("Usuario Actualizado",usuarioDAO.save(nuevoUser)!=null);
		}catch(Exception e){
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	
	

	@Test
	public void testSaveS() {
		Usuario u = new Usuario();
		
		u.setNombre("Juan");
		u.setApellido("Alfredo");
		u.setUsuario("jualf");
		u.setContrasena("pass");
		u.setEstado("activo");
		u.setEmail("email@email.com");
		
		u.setId(2);
		try{
			usuarioDAO.save(u);
			assertTrue(usuarioDAO.exists(1));
		}catch(Exception e){
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void testFindOne() {
		try{
			assertTrue("Completado", usuarioDAO.findOne(1)!=null);
		}catch(Exception e){
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void testFindAll() {
		try{
		assertTrue(usuarioDAO.findAll().iterator().next()!=null);
		}catch(Exception e){
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

}
