package br.com.glsoftware.conting.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.glsoftware.conting.entities.Bank;
import br.com.glsoftware.conting.exceptions.ElementAlreadyExistException;
import br.com.glsoftware.conting.exceptions.MissingElementException;
import br.com.glsoftware.conting.interfaces.IBankRepository;
import br.com.glsoftware.conting.jdbc.ConnectionFactory;

public class BankDao implements IBankRepository {
	
	private Connection connection;
	
	public BankDao(){
		
		this.connection = new ConnectionFactory().getConnection();
	}
	
	@Override
	public void create(Bank bank) throws ElementAlreadyExistException {
		
		String sql = "insert into banks " +
	             "(name,code)" +
	             " values (?,?)";
	 
	     try {
	         // prepared statement para inserção
	         PreparedStatement stmt = connection.prepareStatement(sql);
	 
	         // seta os valores
	         stmt.setString(1,bank.getName());
	         stmt.setString(2,bank.getCode());
	         
	         // executa
	         stmt.execute();
	         stmt.close();
	         
	     } catch (SQLException e) {
	         throw new RuntimeException(e);
	     }
	}

	@Override
	public void update(Bank bank) throws MissingElementException {
		
		String sql = "UPDATE banks SET name = ?, code = ? WHERE id = ?";
		try {
	         // prepared statement para inserção
	         PreparedStatement stmt = connection.prepareStatement(sql);
	 
	         // seta os valores
	         stmt.setString(1,bank.getName());
	         stmt.setString(2,bank.getCode());
	         stmt.setLong(3, bank.getId());
	         
	         // executa
	         stmt.execute();
	         stmt.close();
	         
	     } catch (SQLException e) {
	         throw new RuntimeException(e);
	     }
	}

	@Override
	public void delete(Bank bank) throws MissingElementException {
		
		String sql = "DELETE FROM banks WHERE id = ?";
		try {
	         // prepared statement para inserção
	         PreparedStatement stmt = connection.prepareStatement(sql);
	 
	         // seta os valores
	         stmt.setLong(1, bank.getId());
	         
	         // executa
	         stmt.execute();
	         stmt.close();
	         
	     } catch (SQLException e) {
	         throw new RuntimeException(e);
	     }
	}

	@Override
	public Bank searchOne(String code) {
		
		String sql = "SELECT * FROM banks WHERE code=?";
	    try {
	          
	    	PreparedStatement statement = connection.prepareStatement(sql);

	        statement.setString(1, code);
	        
	        ResultSet rs = statement.executeQuery();
	        if(rs.next()){
	            return new Bank(rs.getInt("id"), 
	            		rs.getString("code"), rs.getString("name"));
	        }
	        
	    } catch(SQLException sqle){
	        
	    	throw new RuntimeException(sqle);
	    }
	    
		return null;
	}

}
