package com.mti.db;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MariaDBConnection {

	private Connection connection;
	private Statement statement;
	private PreparedStatement preparedStatement;
	private static MariaDBConnection dBConnection;
	private static final Logger logger = LogManager.getLogger("com.mti.db");
	private static Properties dbProperties = new Properties();

	public MariaDBConnection() throws ClassNotFoundException, SQLException {
		getDBParameters();
		
		Class.forName("org.mariadb.jdbc.Driver");
		
		StringBuffer connectionParams = new StringBuffer("");
		connectionParams
		.append("jdbc:mariadb://localhost:3306/")
		.append(dbProperties.getProperty("database"))
		.append("?user=").append(dbProperties.getProperty("dbuser"))
		.append("&password=").append(dbProperties.getProperty("dbpassword"));
		
		connection = DriverManager.getConnection(connectionParams.toString());
	}

	public static Connection getDBConnection() throws ClassNotFoundException, SQLException {
	    if (dBConnection == null) {
	    	
	        dBConnection =   new MariaDBConnection();
	        logger.debug("---> New Database Connection Created <---");
	    }
	    return dBConnection.getConnection();
	}


	
	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	
	 /**
    *
    * @param query String The query to be executed
    * @return a ResultSet object containing the results or null if not available
    * @throws SQLException
    */
   public ResultSet query(String query) throws SQLException{
       statement = connection.createStatement();
       ResultSet res = statement.executeQuery(query);
       return res;
   }
   
   /**
    * @desc Method to insert data to a table
    * @param insertQuery String The Insert query
    * @return boolean
    * @throws SQLException
    */
   public int insert(String insertQuery) throws SQLException {
       statement = connection.createStatement();
       int result = statement.executeUpdate(insertQuery);
       return result;
   }
   
   public int update(String updateQuery) throws SQLException {
       return insert(updateQuery);
   }
   
   public ResultSet getCuentas(String enabledFlag) throws SQLException{
	   preparedStatement = connection.prepareStatement("SELECT * FROM CUENTAS WHERE ENABLED = ?");
	   preparedStatement.setString(1, enabledFlag);
       ResultSet res = preparedStatement.executeQuery();
       return res;
   }
   
   public int updateCuenta(Cuenta cuenta) throws SQLException {
	   
	   //preparedStatement = connection.prepareStatement("UPDATE CUENTAS SET MAX_ID = ?, LAST_MODIFIED = NOW() WHERE ID_CUENTA = ?");
	   preparedStatement = connection.prepareStatement("UPDATE CUENTAS SET MAX_ID = ? WHERE ID_CUENTA = ?");
	   preparedStatement.setString(1, cuenta.getMaxId());
	   preparedStatement.setString(2, cuenta.getIdCuenta());
	   
	   return preparedStatement.executeUpdate();
	   
   }

	public static void getDBParameters() {
		InputStream input = null;
		try {
			input = MariaDBConnection.class.getClassLoader().getResourceAsStream("config.properties");

			dbProperties.load(input);
			
		} catch (FileNotFoundException e) {
			logger.error("File config.properties was not found. " + e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("Error while loading config.properties file. " + e.getMessage());
			e.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					logger.error("Error while closing config.properties file. " + e.getMessage());
					e.printStackTrace();
				}
			}
		}
	}
}
