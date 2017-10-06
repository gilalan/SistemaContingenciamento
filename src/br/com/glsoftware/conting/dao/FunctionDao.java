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

import br.com.glsoftware.conting.entities.Function;
import br.com.glsoftware.conting.exceptions.ElementAlreadyExistException;
import br.com.glsoftware.conting.exceptions.MissingElementException;
import br.com.glsoftware.conting.interfaces.IFunctionRepository;
import br.com.glsoftware.conting.jdbc.ConnectionFactory;

/**
 * @author Gilliard
 *
 */
public class FunctionDao implements IFunctionRepository {

	private Connection connection;
	
	public FunctionDao(){
		
		this.connection = new ConnectionFactory().getConnection();
	}
	
	@Override
	public void create(Function function) throws ElementAlreadyExistException {
		
		String sql = "insert into functions " +
	             "(cbo,name,female_name,description)" +
	             " values (?,?,?,?)";
	 
	     try {
	         // prepared statement para inserção
	         PreparedStatement stmt = connection.prepareStatement(sql);
	 
	         // seta os valores
	         stmt.setString(1,function.getCbo());
	         stmt.setString(2,function.getName());
	         stmt.setString(3,function.getFemaleName());
	         stmt.setString(4,function.getDescription());
	         
	         // executa
	         stmt.execute();
	         stmt.close();
	         
	     } catch (SQLException e) {
	         throw new RuntimeException(e);
	     }
	}

	@Override
	public void update(Function function) throws MissingElementException {
		
		String sql = "UPDATE functions SET cbo = ?, "
				+ "name = ?, female_name = ?, description = ? WHERE id = ?";
		try {
	         // prepared statement para inserção
	         PreparedStatement stmt = connection.prepareStatement(sql);
	 
	         // seta os valores
	         stmt.setString(1, function.getCbo());
	         stmt.setString(2,function.getName());
	         stmt.setString(3,function.getFemaleName());
	         stmt.setString(4, function.getDescription());
	         stmt.setLong(5, function.getId());
	         
	         // executa
	         stmt.execute();
	         stmt.close();
	         
	     } catch (SQLException e) {
	         throw new RuntimeException(e);
	     }
	}

	@Override
	public void delete(Function function) throws MissingElementException {
		
		String sql = "DELETE FROM functions WHERE id = ?";
		try {
	         // prepared functionment para inserção
	         PreparedStatement stmt = connection.prepareStatement(sql);
	 
	         // seta os valores
	         stmt.setLong(1, function.getId());
	         
	         // executa
	         stmt.execute();
	         stmt.close();
	         
	     } catch (SQLException e) {
	         throw new RuntimeException(e);
	     }
	}

	@Override
	public Function searchOne(String cbo) {
		
		String sql = "SELECT * FROM functions WHERE cbo=?";
	    try {
	          
	    	PreparedStatement functionment = connection.prepareStatement(sql);

	        functionment.setString(1, cbo);
	        
	        ResultSet rs = functionment.executeQuery();
	        if(rs.next()){
	            return new Function(rs.getInt("id"), 
	            		rs.getString("name"), 
	            		rs.getString("female_name"), 
	            		rs.getString("description"),
	            		rs.getString("cbo")); 
	        }
	        functionment.close();
	        
	    } catch(SQLException sqle){
	        
	    	throw new RuntimeException(sqle);
	    }
	    
		return null;
	}

	@Override
	public List<Function> search(String name) {
		
		List<Function> functions = new ArrayList<Function>();
		String sql = "SELECT * FROM functions WHERE name=?";
	    try {
	          
	    	PreparedStatement functionment = connection.prepareStatement(sql);

	        functionment.setString(1, name);
	        
	        ResultSet rs = functionment.executeQuery();
	        while(rs.next()){
	            functions.add(new Function(rs.getInt("id"), 
	            		rs.getString("cbo"), 
	            		rs.getString("name"),
	            		rs.getString("female_name"),
	            		rs.getString("description")));
	        }
	        functionment.close();
	        
	    } catch(SQLException sqle){
	        
	    	throw new RuntimeException(sqle);
	    }
	    
		return functions;
	}

}
