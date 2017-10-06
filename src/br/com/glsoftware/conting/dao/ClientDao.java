package br.com.glsoftware.conting.dao;

import java.sql.Connection;

import br.com.glsoftware.conting.jdbc.ConnectionFactory;

public class ClientDao {
	
	private Connection connection;
	
	public ClientDao() throws ClassNotFoundException{
		this.connection = new ConnectionFactory().getConnection();
	}
}
