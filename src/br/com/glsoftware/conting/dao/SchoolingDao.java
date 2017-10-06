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

import br.com.glsoftware.conting.entities.Schooling;
import br.com.glsoftware.conting.exceptions.ElementAlreadyExistException;
import br.com.glsoftware.conting.exceptions.MissingElementException;
import br.com.glsoftware.conting.interfaces.ISchoolingRepository;
import br.com.glsoftware.conting.jdbc.ConnectionFactory;

/**
 * @author Gilliard
 * representa a escolaridade
 *
 */
public class SchoolingDao implements ISchoolingRepository {

	private Connection connection;
	
	public SchoolingDao(){
		
		this.connection = new ConnectionFactory().getConnection();
	}
	
	@Override
	public void create(Schooling schooling) throws ElementAlreadyExistException {
		
		String sql = "insert into schooling " +
	             "(name)" +
	             " values (?)";
	 
	     try {
	         // prepared statement para inserção
	         PreparedStatement stmt = connection.prepareStatement(sql);
	 
	         // seta os valores
	         
	         stmt.setString(1,schooling.getName());	         
	         
	         // executa
	         stmt.execute();
	         stmt.close();
	         
	     } catch (SQLException e) {
	         throw new RuntimeException(e);
	     }
	}

	@Override
	public void update(Schooling schooling) throws MissingElementException {
		
		String sql = "UPDATE schooling SET name = ? "
				+ "WHERE id = ?";
		try {
	         // prepared statement para inserção
	         PreparedStatement stmt = connection.prepareStatement(sql);
	 
	         // seta os valores
	         
	         stmt.setString(1,schooling.getName());
	         stmt.setLong(2, schooling.getId());
	         
	         // executa
	         stmt.execute();
	         stmt.close();
	         
	     } catch (SQLException e) {
	         throw new RuntimeException(e);
	     }
	}

	@Override
	public void delete(Schooling schooling) throws MissingElementException {
		
		String sql = "DELETE FROM schooling WHERE id = ?";
		try {
	         // prepared schoolingment para inserção
	         PreparedStatement stmt = connection.prepareStatement(sql);
	 
	         // seta os valores
	         stmt.setLong(1, schooling.getId());
	         
	         // executa
	         stmt.execute();
	         stmt.close();
	         
	     } catch (SQLException e) {
	         throw new RuntimeException(e);
	     }
	}

	@Override
	public Schooling searchOne(long id) {
		
		String sql = "SELECT * FROM schooling WHERE id=?";
	    try {
	          
	    	PreparedStatement schoolingment = connection.prepareStatement(sql);

	        schoolingment.setLong(1, id);
	        
	        ResultSet rs = schoolingment.executeQuery();
	        if(rs.next()){
	            return new Schooling(rs.getInt("id"), 	            		
	            		rs.getString("name"));
	        }
	        schoolingment.close();
	        
	    } catch(SQLException sqle){
	        
	    	throw new RuntimeException(sqle);
	    }
	    
		return null;
	}

	@Override
	public List<Schooling> search(String name) {
		
		List<Schooling> schoolings = new ArrayList<Schooling>();
		String sql = "SELECT * FROM schooling WHERE name=?";
	    try {
	          
	    	PreparedStatement schoolingment = connection.prepareStatement(sql);

	        schoolingment.setString(1, name);
	        
	        ResultSet rs = schoolingment.executeQuery();
	        while(rs.next()){
	            schoolings.add(new Schooling(rs.getInt("id"), 	            		 
	            		rs.getString("name")));
	        }
	        schoolingment.close();
	        
	    } catch(SQLException sqle){
	        
	    	throw new RuntimeException(sqle);
	    }
	    
		return schoolings;
	}

}
