/**
 * 
 */
package br.com.glsoftware.conting.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.glsoftware.conting.entities.State;
import br.com.glsoftware.conting.exceptions.ElementAlreadyExistException;
import br.com.glsoftware.conting.exceptions.MissingElementException;
import br.com.glsoftware.conting.interfaces.IStateRepository;
import br.com.glsoftware.conting.jdbc.ConnectionFactory;

/**
 * @author Gilliard
 *
 */
public class StateDao implements IStateRepository {

	private Connection connection;
	
	public StateDao(){
		
		this.connection = new ConnectionFactory().getConnection();
	}
	
	@Override
	public void create(State state) throws ElementAlreadyExistException {
		
		String sql = "insert into states " +
	             "(abbrev,name)" +
	             " values (?,?)";
	 
	     try {
	         // prepared statement para inserção
	         PreparedStatement stmt = connection.prepareStatement(sql);
	 
	         // seta os valores
	         stmt.setString(1,state.getAbbrev());
	         stmt.setString(2,state.getName());
	         
	         // executa
	         stmt.execute();
	         stmt.close();
	         
	     } catch (SQLException e) {
	         throw new RuntimeException(e);
	     }
	}

	@Override
	public void update(State state) throws MissingElementException {
		
		String sql = "UPDATE states SET name = ?, abbrev = ? WHERE id = ?";
		try {
	         // prepared statement para inserção
	         PreparedStatement stmt = connection.prepareStatement(sql);
	 
	         // seta os valores
	         stmt.setString(1,state.getName());
	         stmt.setString(2,state.getAbbrev());
	         stmt.setLong(3, state.getId());
	         
	         // executa
	         stmt.execute();
	         stmt.close();
	         
	     } catch (SQLException e) {
	         throw new RuntimeException(e);
	     }
	}

	@Override
	public void delete(State state) throws MissingElementException {
		
		String sql = "DELETE FROM states WHERE id = ?";
		try {
	         // prepared statement para inserção
	         PreparedStatement stmt = connection.prepareStatement(sql);
	 
	         // seta os valores
	         stmt.setLong(1, state.getId());
	         
	         // executa
	         stmt.execute();
	         stmt.close();
	         
	     } catch (SQLException e) {
	         throw new RuntimeException(e);
	     }
	}

	@Override
	public State searchOne(String abbrev) {
		
		String sql = "SELECT * FROM states WHERE abbrev=?";
	    try {
	          
	    	PreparedStatement statement = connection.prepareStatement(sql);

	        statement.setString(1, abbrev);
	        
	        ResultSet rs = statement.executeQuery();
	        if(rs.next()){
	            return new State(rs.getInt("id"), 
	            		rs.getString("abbrev"), rs.getString("name"));
	        }
	        statement.close();
	        
	    } catch(SQLException sqle){
	        
	    	throw new RuntimeException(sqle);
	    }
	    
		return null;
	}

	@Override
	public List<State> search(String name) {
		
		List<State> states = new ArrayList<State>();
		String sql = "SELECT * FROM states WHERE name=?";
	    try {
	          
	    	PreparedStatement statement = connection.prepareStatement(sql);

	        statement.setString(1, name);
	        
	        ResultSet rs = statement.executeQuery();
	        while(rs.next()){
	            states.add(new State(rs.getInt("id"), 
	            		rs.getString("abbrev"), 
	            		rs.getString("name")));
	        }
	        statement.close();
	        
	    } catch(SQLException sqle){
	        
	    	throw new RuntimeException(sqle);
	    }
	    
		return states;
	}

}
