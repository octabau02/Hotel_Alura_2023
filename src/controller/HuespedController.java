package controller;

import java.util.List;

import dao.HuespedDao;
import dao.ReservaDao;
import factory.ConnectionFactory;
import model.Huesped;

public class HuespedController {
	HuespedDao huespedDao;
	
	public HuespedController(){
		huespedDao = new HuespedDao(new ConnectionFactory().getConnection());
	}

	public void registrarHuesped(Huesped huesped) {
		huespedDao.registrarHuesped(huesped);
		
	}

	public List<Huesped> listar() {
		return huespedDao.listar();
	}

	public List<Huesped> listarPorCriterio(String criterio) {
		return huespedDao.listarPorCriterio(criterio);
	}

}
