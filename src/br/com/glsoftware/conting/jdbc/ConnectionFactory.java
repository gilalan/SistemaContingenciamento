package br.com.glsoftware.conting.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.management.RuntimeErrorException;

public class ConnectionFactory {
	
	public Connection getConnection(){
	    String url = "jdbc:postgresql://localhost:5432/contingencimento";
		try {	    	 
	    	 Class.forName("org.postgresql.Driver");
	         return DriverManager.getConnection(url, "postgres", "postgilli86*");
	     } catch (SQLException e) {
	         throw new RuntimeException(e);
	     } catch (ClassNotFoundException cnfe) {	    	
			throw new RuntimeException(cnfe);			
	     }
	 }
}
