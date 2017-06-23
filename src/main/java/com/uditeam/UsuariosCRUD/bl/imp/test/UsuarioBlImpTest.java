/**
 * 
 */
package com.uditeam.UsuariosCRUD.bl.imp.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.uditeam.UsuariosCRUD.bl.UsuarioBl;
import com.uditeam.UsuariosCRUD.dto.Usuario;

/**
 * @author Administrator
 *
 */
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

}
