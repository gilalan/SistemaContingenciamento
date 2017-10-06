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

import br.com.glsoftware.conting.entities.Workplace;
import br.com.glsoftware.conting.exceptions.ElementAlreadyExistException;
import br.com.glsoftware.conting.exceptions.MissingElementException;
import br.com.glsoftware.conting.interfaces.IWorkplaceRepository;
import br.com.glsoftware.conting.jdbc.ConnectionFactory;

/**
 * @author Gilliard
 * representa o departamento
 */
public class WorkplaceDao implements IWorkplaceRepository {

	private Connection connection;
	
	public WorkplaceDao(){
		
		this.connection = new ConnectionFactory().getConnection();
	}
	
	@Override
	public void create(Workplace workplace) throws ElementAlreadyExistException {
		
		String sql = "insert into workplaces " +
	             "(code,name)" +
	             " values (?,?)";
	 
	     try {
	         // prepared statement para inserção
	         PreparedStatement stmt = connection.prepareStatement(sql);
	 
	         // seta os valores
	         stmt.setString(1,workplace.getCode());
	         stmt.setString(2,workplace.getName());	         
	         
	         // executa
	         stmt.execute();
	         stmt.close();
	         
	     } catch (SQLException e) {
	         throw new RuntimeException(e);
	     }
	}

	@Override
	public void update(Workplace workplace) throws MissingElementException {
		
		String sql = "UPDATE workplaces SET code = ?, "
				+ "name = ? WHERE id = ?";
		try {
	         // prepared statement para inserção
	         PreparedStatement stmt = connection.prepareStatement(sql);
	 
	         // seta os valores
	         stmt.setString(1, workplace.getCode());
	         stmt.setString(2,workplace.getName());
	         stmt.setLong(3, workplace.getId());
	         
	         // executa
	         stmt.execute();
	         stmt.close();
	         
	     } catch (SQLException e) {
	         throw new RuntimeException(e);
	     }
	}

	@Override
	public void delete(Workplace workplace) throws MissingElementException {
		
		String sql = "DELETE FROM workplaces WHERE id = ?";
		try {
	         // prepared workplacement para inserção
	         PreparedStatement stmt = connection.prepareStatement(sql);
	 
	         // seta os valores
	         stmt.setLong(1, workplace.getId());
	         
	         // executa
	         stmt.execute();
	         stmt.close();
	         
	     } catch (SQLException e) {
	         throw new RuntimeException(e);
	     }
	}

	@Override
	public Workplace searchOne(String code) {
		
		String sql = "SELECT * FROM workplaces WHERE code=?";
	    try {
	          
	    	PreparedStatement workplacement = connection.prepareStatement(sql);

	        workplacement.setString(1, code);
	        
	        ResultSet rs = workplacement.executeQuery();
	        if(rs.next()){
	            return new Workplace(rs.getInt("id"), 
	            		rs.getString("code"), 
	            		rs.getString("name"));
	        }
	        
	        workplacement.close();
	        
	    } catch(SQLException sqle){
	        
	    	throw new RuntimeException(sqle);
	    }
	    
		return null;
	}

	@Override
	public List<Workplace> search(String name) {
		
		List<Workplace> workplaces = new ArrayList<Workplace>();
		String sql = "SELECT * FROM workplaces WHERE name=?";
	    try {
	          
	    	PreparedStatement workplacement = connection.prepareStatement(sql);

	        workplacement.setString(1, name);
	        
	        ResultSet rs = workplacement.executeQuery();
	        while(rs.next()){
	            workplaces.add(new Workplace(rs.getInt("id"), 
	            		rs.getString("code"), 
	            		rs.getString("name")));
	        }
	        
	        workplacement.close();
	        
	    } catch(SQLException sqle){
	        
	    	throw new RuntimeException(sqle);
	    }
	    
		return workplaces;
	}

}
