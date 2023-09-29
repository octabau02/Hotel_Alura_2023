package dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

import model.Huesped;
import model.Reserva;

public class ReservaDao {
	Connection con;
	
	public ReservaDao(Connection con) {
		this.con = con;
	}
	
	public void hacerReservacion(Reserva reserva) {
		
		PreparedStatement statement;
		try {
			statement = con.prepareStatement("INSERT INTO RESERVAS "
					+ "(FechaEntrada, FechaSalida, Valor, FormaPago) "
					+ "VALUES (?, ?, ?, ? )", Statement.RETURN_GENERATED_KEYS);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		Long fechaEntrada = reserva.getFechaEntrada().getTime();
		Long fechaSalida = reserva.getFechaSalida().getTime();
		BigDecimal valor = reserva.getValor();
		String FormaPago = reserva.getFormapago();

		java.sql.Date fechaEntradaSql = new java.sql.Date(fechaEntrada);
		java.sql.Date fechaSalidaSql = new java.sql.Date(fechaSalida);
		
		
		try(statement) {
			statement.setDate(1, fechaEntradaSql);
			statement.setDate(2, fechaSalidaSql);
			statement.setBigDecimal(3, valor);
			statement.setString(4, FormaPago);
			
			statement.execute();
			
			final ResultSet resultSet = statement.getGeneratedKeys();
			try(statement){
				while(resultSet.next()) {
					reserva.setId(resultSet.getInt(1));
					System.out.println(reserva.toString());
				}
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Reserva> listar() {
		List<Reserva> reservas = new ArrayList<>();
		
		try {
			final PreparedStatement statement = con.prepareStatement("SELECT IDreserva, FECHAENTRADA, FECHASALIDA,"
					+ " VALOR, FORMAPAGO FROM RESERVAS");
			try(statement){
				
				statement.execute();
				ResultSet resultSet = statement.getResultSet();
				
				while(resultSet.next()) {
					Reserva fila = new Reserva(resultSet.getInt("IDreserva"), resultSet.getDate("FECHAENTRADA"),
							resultSet.getDate("FECHASALIDA"), resultSet.getBigDecimal("VALOR"), resultSet.getString("FORMAPAGO"));
					reservas.add(fila);
				}
				return reservas;
				
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		
	}

	public List<Reserva> listarPorCriterio(String criterio) {
		List<Reserva> reservas = new ArrayList<>();

		try {
			PreparedStatement statement = con.prepareStatement("SELECT IDreserva, FECHAENTRADA, FECHASALIDA,"
					+ " VALOR, FORMAPAGO FROM RESERVAS WHERE IDreserva = ?");
			statement.setString(1, criterio);

			try(statement){
				statement.execute();
				ResultSet resultSet = statement.getResultSet();
				
				while(resultSet.next()) {
					Reserva fila = new Reserva(resultSet.getInt("IDreserva"), resultSet.getDate("FECHAENTRADA"),
							resultSet.getDate("FECHASALIDA"), resultSet.getBigDecimal("VALOR"), resultSet.getString("FORMAPAGO"));
					reservas.add(fila);

				}
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return reservas;
	}

}
