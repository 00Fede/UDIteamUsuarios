/**
 * 
 */
package com.uditeam.UsuariosCRUD.bl.imp.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.uditeam.UsuariosCRUD.bl.UsuarioBl;
import com.uditeam.UsuariosCRUD.dto.Usuario;

/**
 * @author Administrator
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UsuarioBlImpTest {
	
	@Autowired
	UsuarioBl userBl;

	/**
	 * Test method for {@link com.uditeam.UsuariosCRUD.bl.imp.UsuarioBlImp#listar()}.
	 */
	@Test
	public void testListar() {
		try{
			Iterable<Usuario> usuarios = userBl.listar();
			assert(usuarios!=null);
		}catch(Exception e){
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void testGetUsuarioByCredentials() {
		try{
		Usuario u = userBl.getUsuarioByCredentials("user", "password");
		assertTrue(u!=null);
		}catch(Exception e){
			e.printStackTrace();
			fail(e.getMessage());
		}
		
	}
	
	
}
