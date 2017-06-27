/**
 * 
 */
package com.uditeam.UsuariosCRUD.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Esta clase representa una entidad Usuario de la base de datos
 * @author Administrator
 *
 */
@Entity
@Table(name = "tblusuarios")
public class Usuario {
	
	public Usuario(){
		
	}
	
	
	public Usuario(int id, String nombre, String apellido, String usuario, String contrasena, String email,
			String estado, String telefono) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.usuario = usuario;
		this.contrasena = contrasena;
		this.email = email;
		this.estado = estado;
		this.telefono = telefono;
	}
	
	


	/**
	 * @param nombre
	 * @param apellido
	 * @param usuario
	 * @param contrasena
	 * @param email
	 * @param telefono
	 */
	public Usuario(String nombre, String apellido, String usuario, String contrasena, String email, String telefono) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.usuario = usuario;
		this.contrasena = contrasena;
		this.email = email;
		this.telefono = telefono;
	}


	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * @return the apellido
	 */
	public String getApellido() {
		return apellido;
	}
	/**
	 * @param apellido the apellido to set
	 */
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	/**
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}
	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	/**
	 * @return the contrasena
	 */
	public String getContrasena() {
		return contrasena;
	}
	/**
	 * @param contrasena the contrasena to set
	 */
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}
	/**
	 * @param estado the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}
	/**
	 * @return the telefono
	 */
	public String getTelefono() {
		return telefono;
	}


	/**
	 * @param telefono the telefono to set
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;
	@NotNull
	String nombre;
	@NotNull
	String apellido;
	@NotNull
	String usuario;
	@NotNull
	String contrasena;
	@NotNull
	String email;
	@NotNull
	String estado;
	String telefono;
}
