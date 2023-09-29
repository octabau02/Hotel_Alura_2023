package model;

import java.util.Date;

public class Huesped {
	private Integer id;
	private String nombre;
	private String apellido;
	private Date fechaNacimiento;
	private String nacionalidad;
	private String telefono;
	private Integer idReserva;
	
	public Huesped(String nombre, String apellido, Date fechaNacimiento,
			String nacionalidad, String telefono, Integer idReserva) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNacimiento = fechaNacimiento;
		this.nacionalidad = nacionalidad;
		this.telefono = telefono;
		this.idReserva = idReserva;
	}
	
	public Huesped(int id, String nombre, String apellido, java.sql.Date fechaNacimiento,
			String nacionalidad, String telefono, int idReserva) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNacimiento = fechaNacimiento;
		this.nacionalidad = nacionalidad;
		this.telefono = telefono;
		this.idReserva = idReserva;
	}

	public void setId(int id) {
		this.id = id;
		
	}
	public Integer getId() {
		return this.id;
	}
	
	public String getNombre() {
		return nombre;
	}


	public String getApellido() {
		return apellido;
	}


	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}


	public String getNacionalidad() {
		return nacionalidad;
	}


	public String getTelefono() {
		return telefono;
	}


	public Integer getIdReserva() {
		return idReserva;
	}


	@Override
	public String toString() {
		return nombre + this.fechaNacimiento+ this.nacionalidad;
	}


	
}
