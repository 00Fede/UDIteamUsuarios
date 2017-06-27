package com.uditeam.UsuariosCRUD.bl.imp;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.uditeam.UsuariosCRUD.bl.UsuarioBl;
import com.uditeam.UsuariosCRUD.dao.UsuarioDAO;
import com.uditeam.UsuariosCRUD.dto.Usuario;
import com.uditeam.UsuariosCRUD.exception.DaoException;
/**
 * Pruebas unitarias a la logica del negocio
 * @author Administrator
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UsuarioBlImpTest {
	
	@Autowired
	UsuarioBl userBl;
	@Autowired
	UsuarioDAO userDao;

	@Test
	public void testGetUsuarioByCredentials() {
		String user = "nana";
		String pass = "dfsklfjs";
		try{
			Usuario u = userBl.getUsuarioByCredentials(user, pass);
			//el mensaje es cuando no se valida la condicion
			assertTrue("No se encontro usuario",u!=null);
		}catch(DaoException e){
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void testSaveUsuario() {
		//el id es incremental, y el estado se crea automatico
		Usuario u = new Usuario("jua a","dearco","jualf","123","mail@mail.com","3234");
		try{
			assertTrue("Usuario guardado correctamente",userBl.saveUsuario(u)!=null);
		}catch(DaoException e){
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void testDeleteUsuario() {
		int id = 1;
		try{
			userBl.deleteUsuario(id);
			assertTrue("Usuario eliminado correctamente", userDao.findOne(id)!=null);
		}catch(DaoException e){
			e.printStackTrace();
			fail(e.getMessage());
		}
		
	}

	@Test
	public void testUpdateUsuario() {
		Usuario u = userDao.findOne(2);
		u.setTelefono("31243242");
		try{
			assertTrue("Usuario actualizado correctamente",userBl.updateUsuario(u)!=null);
		}catch(DaoException e){
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

}
