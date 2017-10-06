/**
 * 
 */
package br.com.glsoftware.conting.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.glsoftware.conting.entities.BankInfo;
import br.com.glsoftware.conting.exceptions.ElementAlreadyExistException;
import br.com.glsoftware.conting.exceptions.MissingElementException;
import br.com.glsoftware.conting.interfaces.IBankInfoRepository;
import br.com.glsoftware.conting.jdbc.ConnectionFactory;

/**
 * @author Gilliard
 * informações bancárias do Funcionário, está em outra tabela
 *
 */
public class BankInfoDao implements IBankInfoRepository {

	private Connection connection;
	
	public BankInfoDao(){
		
		this.connection = new ConnectionFactory().getConnection();
	}
	
	@Override
	public void create(BankInfo bankInfo) throws ElementAlreadyExistException {
		
		String sql = "insert into bank_info " +
	             "(bank_id,employee_id,agency,account,vd)" +
	             " values (?,?,?,?,?)";
	 
	     try {
	         // prepared statement para inserção
	         PreparedStatement stmt = connection.prepareStatement(sql);
	 
	         // seta os valores
	         stmt.setLong(1,bankInfo.getBank().getId());
	         stmt.setLong(2,bankInfo.getEmployee().getId());
	         stmt.setString(3,bankInfo.getAgency());
	         stmt.setString(4,bankInfo.getAccount());
	         stmt.setString(5,String.valueOf(bankInfo.getVd()));
	         
	         // executa
	         stmt.execute();
	         stmt.close();
	         
	     } catch (SQLException e) {
	         throw new RuntimeException(e);
	     }
	}

	@Override
	public void update(BankInfo bankInfo) throws MissingElementException {
		
		String sql = "UPDATE bank_info SET bank_id = ?, "
				+ "employee_id = ?, agency = ?, account = ?, "
				+ "vd = ? WHERE id = ?";
		try {
	         // prepared statement para inserção
	         PreparedStatement stmt = connection.prepareStatement(sql);
	 
	         stmt.setLong(1,bankInfo.getBank().getId());
	         stmt.setLong(2,bankInfo.getEmployee().getId());
	         stmt.setString(3,bankInfo.getAgency());
	         stmt.setString(4,bankInfo.getAccount());
	         stmt.setString(5,String.valueOf(bankInfo.getVd()));
	         stmt.setLong(6, bankInfo.getId());
	         
	         // executa
	         stmt.execute();
	         stmt.close();
	         
	     } catch (SQLException e) {
	         throw new RuntimeException(e);
	     }
	}

	@Override
	public void delete(BankInfo bankInfo) throws MissingElementException {
		
		String sql = "DELETE FROM bank_info WHERE id = ?";
		try {
	         // prepared bankInfoment para inserção
	         PreparedStatement stmt = connection.prepareStatement(sql);
	 
	         // seta os valores
	         stmt.setLong(1, bankInfo.getId());
	         
	         // executa
	         stmt.execute();
	         stmt.close();
	         
	     } catch (SQLException e) {
	         throw new RuntimeException(e);
	     }
	}

	@Override
	public BankInfo searchOne(long id) {
		
		//String sql = "SELECT * FROM bank_info WHERE id=?";
		String SQL_JOIN = "SELECT bi.id as bi_id, emp.id as emp_id, bk.id as bk_id "
				+ "FROM (bank_info bi INNER JOIN "
				+ "employees emp ON (bi.employee_id = emp.id)) "
				+ "INNER JOIN banks bk ON (bi.bank_id = bk.id) "
				+ "WHERE bi.id=?";
	    try {
	          
	    	PreparedStatement stmt = connection.prepareStatement(SQL_JOIN);

	    	stmt.setLong(1, id);
	        
	        ResultSet rs = stmt.executeQuery();
	        if(rs.next()){
//	        	Bank bank = new Bank(rs.getLong("emp.id"), 
//	        			rs.getString("emp.code"), rs.getString("emp.name"));
	        	System.out.println("bankInfo Id: " + rs.getLong("bi_id"));
	        	System.out.println("Emp id: " + rs.getLong("emp_id"));
	        	System.out.println("Bank id: " + rs.getLong("bk_id"));
	        	//System.out.println("Emp Name: " + rs.getString("emp.name"));
//	            return new BankInfo(rs.getLong("id"), 
//	            		rs.getString("name"), 
//	            		rs.getString("female_name"), 
//	            		rs.getString("description"),
//	            		rs.getString("cbo")); 
	        }
	        stmt.close();
	        
	    } catch(SQLException sqle){
	        
	    	throw new RuntimeException(sqle);
	    }
	    
		return null;
	}

//	@Override
//	public List<BankInfo> search(String name) {
		
//		List<BankInfo> bankInfos = new ArrayList<BankInfo>();
//		String sql = "SELECT * FROM bankInfos WHERE name=?";
//	    try {
//	          
//	    	PreparedStatement bankInfoment = connection.prepareStatement(sql);
//
//	        bankInfoment.setString(1, name);
//	        
//	        ResultSet rs = bankInfoment.executeQuery();
//	        while(rs.next()){
//	            bankInfos.add(new BankInfo(rs.getInt("id"), 
//	            		rs.getString("cbo"), 
//	            		rs.getString("name"),
//	            		rs.getString("female_name"),
//	            		rs.getString("description")));
//	        }
//	        
//	    } catch(SQLException sqle){
//	        
//	    	throw new RuntimeException(sqle);
//	    }
//	    
//		return bankInfos;
//		return null;
//	}
}
