package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Huesped;

public class HuespedDao {
	
	Connection con;
	
	public HuespedDao(Connection con) {
		this.con = con;
	}

	public void registrarHuesped(Huesped huesped) {
		PreparedStatement statement;
		
		try {
			statement = con.prepareStatement("INSERT INTO HUESPEDES (NOMBRE, APELLIDO, FECHANACIMIENTO"
					+ ", NACIONALIDAD, TELEFONO, IDRESERVA)"
					+ " VALUES (?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		long fechaN = huesped.getFechaNacimiento().getTime();
		Date fechaSql = new Date(fechaN);
		try {
			statement.setString(1, huesped.getNombre());
			statement.setString(2, huesped.getApellido());
			statement.setDate(3, fechaSql);
			statement.setString(4, huesped.getNacionalidad());
			statement.setString(5, huesped.getTelefono());
			statement.setInt(6, huesped.getIdReserva());
			
			statement.execute();
			
			ResultSet resultSet =  statement.getGeneratedKeys();
			try(statement){
				while(resultSet.next()) {
					huesped.setId(resultSet.getInt(1));
					System.out.println(huesped.toString());
				}
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

	public List<Huesped> listar() {
		List<Huesped> huespedes = new ArrayList<>();
		
		
		try {
			final PreparedStatement statement = con.prepareStatement("SELECT IDhuesped, NOMBRE, APELLIDO,"
					+ " FECHANACIMIENTO, TELEFONO, NACIONALIDAD, IDRESERVA FROM HUESPEDES");
		
			try(statement){
				statement.execute();
				ResultSet resultSet = statement.getResultSet();
				
				while(resultSet.next()) {
					Huesped fila = new Huesped(resultSet.getInt("IDhuesped"), resultSet.getString("NOMBRE"),
							resultSet.getString("APELLIDO"), resultSet.getDate("FECHANACIMIENTO"),
							resultSet.getString("NACIONALIDAD"), resultSet.getString("TELEFONO"),
							resultSet.getInt("IDRESERVA"));
					huespedes.add(fila);
				}
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		return huespedes;
	}

	public List<Huesped> listarPorCriterio(String criterio) {
		List<Huesped> huespedes = new ArrayList<>();
		
		try {
			PreparedStatement statement = con.prepareStatement("SELECT IDhuesped, NOMBRE, APELLIDO,"
					+ " FECHANACIMIENTO, TELEFONO, NACIONALIDAD, IDRESERVA FROM HUESPEDES"
					+ " WHERE APELLIDO = ? OR IDRESERVA = ?");
			statement.setString(1, criterio);
			statement.setString(2, criterio);
			try(statement){
				statement.execute();
				ResultSet resultSet = statement.getResultSet();
				
				while(resultSet.next()) {
					Huesped fila = new Huesped(resultSet.getInt("IDhuesped"), resultSet.getString("NOMBRE"),
							resultSet.getString("APELLIDO"), resultSet.getDate("FECHANACIMIENTO"),
							resultSet.getString("NACIONALIDAD"), resultSet.getString("TELEFONO"),
							resultSet.getInt("IDRESERVA"));
					huespedes.add(fila);

				}
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return huespedes;
	}
	
	

}
