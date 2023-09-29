package model;

import java.math.BigDecimal;
import java.util.Date;


public class Reserva {
	Integer id;
	
	Date fechaEntrada;
	
	Date FechaSalida;
	
	BigDecimal valor;
	
	String formapago;

	public Reserva(Date fechaEntrada, Date fechaSalida, Double valor , String formapago) {
		super();
		this.fechaEntrada = fechaEntrada;
		this.FechaSalida = fechaSalida;
		this.valor = BigDecimal.valueOf(valor);
		this.formapago = formapago;
	}
	public Reserva(int id, java.sql.Date fechaEntrada, java.sql.Date fechaSalida,
				BigDecimal valor, String formaPago) {
		this.id = id;
		this.fechaEntrada = fechaEntrada;
		this.FechaSalida = fechaSalida;
		this.valor = valor;
		this.formapago = formaPago;
	}
	public Integer getId() {
		return this.id;
	}
	
	public Date getFechaEntrada() {
		return fechaEntrada;
	}

	public Date getFechaSalida() {
		return FechaSalida;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public String getFormapago() {
		return formapago;
	}

	public void setId(Integer id) {
		this.id = id;
		
	}
	
	public void setValor(BigDecimal valor) {
		this.valor = valor;
		
	}
	
	@Override
	public String toString() {
		return (String)("Id: "+(String.valueOf(this.id) + " Entrada: "+this.fechaEntrada +
				" Salida: "+this.FechaSalida + " Pago: "+this.formapago + " Precio: "+this.valor));
	}

	
	
	

}
