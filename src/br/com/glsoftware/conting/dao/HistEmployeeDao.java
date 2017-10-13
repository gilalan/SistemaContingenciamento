package br.com.glsoftware.conting.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.glsoftware.conting.entities.Employee;
import br.com.glsoftware.conting.entities.HistEmployee;
import br.com.glsoftware.conting.exceptions.ElementAlreadyExistException;
import br.com.glsoftware.conting.exceptions.MissingElementException;
import br.com.glsoftware.conting.interfaces.IHistEmployeeRepository;
import br.com.glsoftware.conting.jdbc.ConnectionFactory;

public class HistEmployeeDao implements IHistEmployeeRepository{
	
	private Connection connection;
	
	public HistEmployeeDao(){
		this.connection = new ConnectionFactory().getConnection();
	}

	@Override
	public void create(HistEmployee histEmployee) throws ElementAlreadyExistException {
		
		String sql = "insert into historico_funcionario " +
	             "(id_funcionario,mes,ano,salario,codigo_depto) " +	             
	             " values (?,?,?,?,?)";
	 
	     try {
	         // prepared statement para inserção
	         //PreparedStatement stmt = connection.prepareStatement(sql);
	         PreparedStatement stmt = connection.prepareStatement(sql,
                     Statement.RETURN_GENERATED_KEYS);
	 
	         // seta os valores
	         stmt.setLong(1,histEmployee.getEmployee().getId());
	         stmt.setString(2,histEmployee.getBaseMonth());
	         stmt.setString(3,histEmployee.getBaseYear());
	         stmt.setBigDecimal(4,histEmployee.getBaseSalary());
	         stmt.setString(5, histEmployee.getDptoCode());
	 
	         // executa
	         stmt.execute();
	         
	         ResultSet rs = stmt.getGeneratedKeys();
	         if (rs.next()) {
	        	 
	        	 long id = rs.getLong(1);
	        	 System.out.println("Id gerado: " + id);
	        	 //employee.setId(id);
	        	 
	         } else {
	        	 
	        	 throw new SQLException("Falha na criação de usuário, nenhuma linha afetada");
	         }
	        
	         stmt.close();
	         
	     } catch (SQLException e) {
	         throw new RuntimeException(e);
	     }
	}
	
	public void createBatch(List<HistEmployee> histEmployees) throws ElementAlreadyExistException{
		
		String sql_select = "SELECT id FROM funcionario WHERE id_soll=?";
		
		String sql = "insert into historico_funcionario " +
	             "(id_funcionario,mes,ano,salario,codigo_depto) "
	             + "values (?,?,?,?,?)";
		
//		String sql = "insert into historico_funcionario " +
//	             "(id_funcionario,mes,ano,salario,codigo_depto) "
//	             + "values (SELECT id FROM funcionario WHERE id_soll=?,?,?,?,?)";
		
		
//		String sql = "INSERT INTO historico_funcionario "
//				+ "(id_funcionario,mes,ano,salario,codigo_depto) " 
//				SELECT '1stVal', '2ndVal', '1stString.' + cast(id as varchar(50)) + '.2ndString' 
//				FROM table2 
//				WHERE id = 10"; 
		
	     try {
	         // prepared statement para inserção
	    	 PreparedStatement stmtSelect = connection.prepareStatement(sql_select);
	         PreparedStatement stmt = connection.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
	         
	         long idSoll;
	         ResultSet rsSelect;
	         //long idEmployee;
	         for (HistEmployee hemp: histEmployees) {
	        	
	        	idSoll = hemp.getEmployee().getIdSoll();
	        	stmtSelect.setLong(1, idSoll);
	        	rsSelect = stmtSelect.executeQuery();
	        	//idEmployee = 
	        	//stmt.setLong(1, hemp.getEmployee().getId());
	        	if (rsSelect.next()){
	        		stmt.setLong(1, rsSelect.getLong("id"));	        		
	        	} else {
	        		System.out.println("Funcionário não encontrado");
	        	}
	 			stmt.setString(2, hemp.getBaseMonth());
	 			stmt.setString(3, hemp.getBaseYear());
	 			stmt.setBigDecimal(4, hemp.getBaseSalary());
	 			stmt.setString(5, hemp.getDptoCode());
	 			stmt.addBatch();
	 		}
	 		stmt.executeBatch();
	 		ResultSet rs = stmt.getGeneratedKeys();
	 		while (rs.next()) {
	        	 	 			
	        	long id = rs.getLong(1);
	        	System.out.println("Id gerado: " + id);
	 		} 
	 		stmtSelect.close();
	 		stmt.close();
	         
	     } catch (SQLException e) {
	         throw new RuntimeException(e);
	     }
	}

	@Override
	public void update(HistEmployee histEmployee) throws MissingElementException {
		
		String UPDATE_SQL = "UPDATE historico_funcionario SET id_funcionario = ?,"
				+ " mes = ?, ano = ?, salario = ?, codigo_depto = ?" + 
	             " WHERE id = ?";
	 
	     try {
	         // prepared statement para inserção
	         PreparedStatement stmt = connection.prepareStatement(UPDATE_SQL);
	 
	         // seta os valores
	         stmt.setLong(1,histEmployee.getEmployee().getId());
	         stmt.setString(2,histEmployee.getBaseMonth());
	         stmt.setString(3,histEmployee.getBaseYear());
	         stmt.setBigDecimal(4,histEmployee.getBaseSalary());
	         stmt.setString(5,histEmployee.getDptoCode());
	 
	         // executa
	         stmt.execute();
	         stmt.close();
	         
	     } catch (SQLException e) {
	         throw new RuntimeException(e);
	     }
	}

	@Override
	public void delete(HistEmployee histEmployee) throws MissingElementException {
		
		String sql = "DELETE FROM historico_funcionario WHERE id = ?";
		try {
	         // prepared bankInfoment para inserção
	         PreparedStatement stmt = connection.prepareStatement(sql);
	 
	         // seta os valores
	         stmt.setLong(1, histEmployee.getId());
	         
	         // executa
	         stmt.execute();
	         stmt.close();
	         
	     } catch (SQLException e) {
	         throw new RuntimeException(e);
	     }
	}

	@Override
	public HistEmployee searchOne(long id) throws MissingElementException {
		
		String SQL_JOIN = "SELECT hist_func.id as hist_func_id, func.nome as func_name, "
				+ "func.id_soll as func_id_soll, hist_func.mes as hist_func_mes, "
				+ "hist_func.ano as hist_func_ano, hist_func.salario as hist_func_salario, "
				+ "hist_func.codigo_depto as hist_func_dpto "				
				+ "FROM historico_funcionario hist_func INNER JOIN funcionario func ON "
				+ "hist_func.id_funcionario = func.id WHERE hist_func_id = ?";
		
	    try {
	          
	    	PreparedStatement stmt = connection.prepareStatement(SQL_JOIN);

	    	stmt.setLong(1, id);
	        
	        ResultSet rs = stmt.executeQuery();
	        if(rs.next()){
	        	
	        	//montando o objeto de retorno...
	        	Employee emp = new Employee();
	        	emp.setIdSoll(rs.getLong("func_id_soll"));
	        	emp.setIdSoll(rs.getLong("func_name"));
	        	
	        	HistEmployee histEmployee = new HistEmployee(emp);
	        	histEmployee.setId(rs.getLong("hist_func_id"));
	        	histEmployee.setBaseMonth(rs.getString("hist_func_mes"));
	        	histEmployee.setBaseYear(rs.getString("hist_func_ano"));
	        	histEmployee.setDptoCode(rs.getString("hist_func_dpto"));
	        	histEmployee.setBaseSalary(rs.getBigDecimal("hist_func_salario"));
	        	
	        	return histEmployee;
 
	        }
	        stmt.close();
	        
	    } catch(SQLException sqle){
	        
	    	throw new RuntimeException(sqle);
	    }
	    
		return null;
	}

	@Override
	public List<HistEmployee> search(long sollId) throws MissingElementException {
		
		String SQL_JOIN = "SELECT hist_func.id as hist_func_id, func.nome as func_name, "
				+ "func.id_soll as func_id_soll, hist_func.mes as hist_func_mes, "
				+ "hist_func.ano as hist_func_ano, hist_func.salario as hist_func_salario, "
				+ "hist_func.codigo_depto as hist_func_dpto "				
				+ "FROM historico_funcionario hist_func INNER JOIN funcionario func ON "
				+ "hist_func.id_funcionario = func.id WHERE func.id_soll = ?";
		
		List<HistEmployee> histEmployees = new ArrayList<HistEmployee>();
		
	    try {
	          
	    	PreparedStatement stmt = connection.prepareStatement(SQL_JOIN);

	    	stmt.setLong(1, sollId);
	        
	        ResultSet rs = stmt.executeQuery();
	        	        
	        Employee employee = null;
	        while(rs.next()){
	        		        	
	        	//montando o objeto de retorno...
	        	employee = new Employee();
	        	employee.setIdSoll(rs.getLong("func_id_soll"));
	        	employee.setName(rs.getString("func_name"));
	        	
	        	HistEmployee histEmployee = new HistEmployee(employee);
	        	histEmployee.setId(rs.getLong("hist_func_id"));
	        	histEmployee.setBaseMonth(rs.getString("hist_func_mes"));
	        	histEmployee.setBaseYear(rs.getString("hist_func_ano"));
	        	histEmployee.setDptoCode(rs.getString("hist_func_dpto"));
	        	histEmployee.setBaseSalary(rs.getBigDecimal("hist_func_salario"));
	        	
	        	histEmployees.add(histEmployee);
 
	        }
	        stmt.close();
	        
	    } catch(SQLException sqle){
	        
	    	throw new RuntimeException(sqle);
	    }
	    
		return histEmployees;
	}
	
	public List<HistEmployee> searchAll() {
		//Implementar...
		List<HistEmployee> employees = new ArrayList<HistEmployee>();
		long toDo = (long) Math.random();
		try {
			
			employees = this.search(toDo);
			
		} catch (MissingElementException e) {
			
			e.printStackTrace();
		}
		return employees;
	}
}
