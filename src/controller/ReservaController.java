package controller;

import java.math.BigDecimal;
import java.sql.Connection;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

import com.toedter.calendar.JDateChooser;

import dao.ReservaDao;
import factory.ConnectionFactory;
import model.Reserva;

public class ReservaController {
	ReservaDao reservaDao;
	
	public ReservaController() {
		reservaDao = new ReservaDao(new ConnectionFactory().getConnection());
	}
	
	public void hacerReservacion(Reserva reserva) {

		reservaDao.hacerReservacion(reserva);
	}
	
	public String calculateValue(JDateChooser txtFechaEntrada, JDateChooser txtFechaSalida) {
		Integer totalDays = 0;
		
		Date fe = txtFechaEntrada.getDate();
		Date fs = txtFechaSalida.getDate();
		if(fe!= null && fs != null && fe.before(fs)) {
			long timeDifference = fs.getTime() - fe.getTime();
			totalDays = (int) (timeDifference/ (1000 * 60 * 60 * 24));
			return String.valueOf(totalDays * 150);
		}else {
			return String.valueOf("Eliga una fecha valida");
		}	
	}
	
	public List<Reserva> listar(){
		return reservaDao.listar();
	}

	public List<Reserva> listarPorCriterio(String criterio) {
		return reservaDao.listarPorCriterio(criterio);
	}
	

}
