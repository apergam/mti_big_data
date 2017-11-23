package com.mti.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MariaDBUtils {

	private static final Logger logger = LogManager.getLogger("com.mti.db");


	public static void main(String[] args) {
		MariaDBUtils maria = new MariaDBUtils();

		ArrayList<Cuenta> cuentas = maria.getCuentas();

		for (Cuenta cuenta : cuentas) {
			//logger.debug("Cuenta: " + cuenta.toString());
			System.out.println("Cuenta: " + cuenta.toString());
			cuenta.setMaxId(931931799739142150L);
			System.out.println("Cuenta: " + cuenta.toString());
			maria.updateMaxId(cuenta);
		}
		
		Cuenta cuentaZMG = new Cuenta("Trafico_ZMG", "Tráfico Zona Metropolitana de Guadalajara");
		System.out.println("cuentaZMG: " + cuentaZMG);
		
		System.out.println("Cambiando el max id a 100");
		cuentaZMG.setMaxId(100);
		maria.updateMaxId(cuentaZMG);
		System.out.println("cuentaZMG: " + cuentaZMG);

	}

	/**
	 * Updates the max_id field on the db for cuenta parameter
	 * @param cuenta
	 * @return
	 */
	public int updateMaxId(Cuenta cuenta) {

		int resultadoUpdate = -1;
		
		try {
			MariaDBConnection connection = new MariaDBConnection();
			resultadoUpdate = connection.updateCuenta(cuenta);
		} catch (ClassNotFoundException e) {
			logger.error("Error getting class for DB " + e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			logger.error("Error updating cuenta [" + cuenta + "] " + e.getMessage());
			e.printStackTrace();
		}
		
		return resultadoUpdate;

	}

	/**
	 * Get all the records from cuentas table
	 * @return
	 */
	public ArrayList<Cuenta> getCuentas() {

		ArrayList <Cuenta> cuentas =new ArrayList<Cuenta>();
		ResultSet resultSet = null; 
		try {

			MariaDBConnection connection = new MariaDBConnection();

			resultSet = connection.query("SELECT * FROM CUENTAS");

			while(resultSet.next()) {

				Cuenta cuentaTemporal = new Cuenta();
				cuentaTemporal.setIdCuenta(resultSet.getString("id_cuenta"));
				cuentaTemporal.setNombre(resultSet.getString("nombre"));
				cuentaTemporal.setLatitud(resultSet.getString("latitud"));
				cuentaTemporal.setLongitud(resultSet.getString("longitud"));
				cuentaTemporal.setUrl(resultSet.getString("url"));
				cuentaTemporal.setMaxId(resultSet.getLong("max_id"));

				cuentas.add(cuentaTemporal);
			}

		} catch (SQLException e) {
			logger.error("Error getting cuentas from DB " + e.getMessage());
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			logger.error("Error getting class for DB " + e.getMessage());
			e.printStackTrace();
		}

		return cuentas;
	}
	
	

	public MariaDBUtils() {
		super();
	}



}
