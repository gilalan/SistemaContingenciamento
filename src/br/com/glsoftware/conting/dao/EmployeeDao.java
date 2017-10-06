/**
 * 
 */
package br.com.glsoftware.conting.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.glsoftware.conting.entities.Employee;
import br.com.glsoftware.conting.entities.Function;
import br.com.glsoftware.conting.entities.Schooling;
import br.com.glsoftware.conting.entities.State;
import br.com.glsoftware.conting.entities.Workplace;
import br.com.glsoftware.conting.exceptions.ElementAlreadyExistException;
import br.com.glsoftware.conting.exceptions.MissingElementException;
import br.com.glsoftware.conting.interfaces.IEmployeeRepository;
import br.com.glsoftware.conting.jdbc.ConnectionFactory;

/**
 * @author Gilliard
 *
 */
public class EmployeeDao implements IEmployeeRepository{
	
	private Connection connection;
	
	public EmployeeDao(){
		this.connection = new ConnectionFactory().getConnection();
	}

	@Override
	public void create(Employee employee) throws ElementAlreadyExistException {
		
		String sql = "insert into employees " +
	             "(name,matriculation,pis,cpf,salary,admission_date,birthday,"
	             + "function_id,workplace_id,schooling_id,state_id)" +
	             " values (?,?,?,?,?,?,?,?,?,?,?)";
	 
	     try {
	         // prepared statement para inser��o
	         //PreparedStatement stmt = connection.prepareStatement(sql);
	         PreparedStatement stmt = connection.prepareStatement(sql,
                     Statement.RETURN_GENERATED_KEYS);
	 
	         // seta os valores
	         stmt.setString(1,employee.getName());
	         stmt.setString(2,employee.getMatriculation());
	         stmt.setString(3,employee.getPis());
	         stmt.setString(4,employee.getCpf());
	         stmt.setBigDecimal(5,employee.getSalary());
	         stmt.setDate(6, new Date(
	        		 employee.getAdmissionDate().getTimeInMillis()));
	         stmt.setDate(7, new Date(
	        		 employee.getBirthday().getTimeInMillis()));
	         stmt.setLong(8,employee.getFunction().getId());
	         stmt.setLong(9,employee.getWorkplace().getId());
	         stmt.setLong(10,employee.getSchooling().getId());
	         stmt.setLong(11,employee.getState().getId());
	 
	         // executa
	         stmt.execute();
	         
	         ResultSet rs = stmt.getGeneratedKeys();
	         if (rs.next()) {
	        	 
	        	 long id = rs.getLong(1);
	        	 employee.setId(id);
	        	 
	         } else {
	        	 
	        	 throw new SQLException("Falha na cria��o de usu�rio, nenhuma linha afetada");
	         }
	        
	         stmt.close();
	         
	     } catch (SQLException e) {
	         throw new RuntimeException(e);
	     }
	}

	@Override
	public void update(Employee employee) throws MissingElementException {
		
		String UPDATE_SQL = "UPDATE employees SET name = ?, matriculation = ?, " +
	             "pis = ?, cpf = ?, salary = ?, admission_date = ?, "
	             + "birthday = ?, function_id = ?, workplace_id = ?, "
	             + "schooling_id = ?, state_id = ? " +
	             " WHERE id = ?";
	 
	     try {
	         // prepared statement para inser��o
	         PreparedStatement stmt = connection.prepareStatement(UPDATE_SQL);
	 
	         // seta os valores
	         stmt.setString(1,employee.getName());
	         stmt.setString(2,employee.getMatriculation());
	         stmt.setString(3,employee.getPis());
	         stmt.setString(4,employee.getCpf());
	         stmt.setBigDecimal(5,employee.getSalary());
	         stmt.setDate(6, new Date(
	        		 employee.getAdmissionDate().getTimeInMillis()));
	         stmt.setDate(7, new Date(
	        		 employee.getBirthday().getTimeInMillis()));
	         stmt.setLong(8,employee.getFunction().getId());
	         stmt.setLong(9,employee.getWorkplace().getId());
	         stmt.setLong(10,employee.getSchooling().getId());
	         stmt.setLong(11,employee.getState().getId());
	         stmt.setLong(12,employee.getId());
	 
	         // executa
	         stmt.execute();
	         stmt.close();
	         
	     } catch (SQLException e) {
	         throw new RuntimeException(e);
	     }
	}

	@Override
	public void delete(Employee employee) throws MissingElementException {
		
		String sql = "DELETE FROM employees WHERE id = ?";
		try {
	         // prepared bankInfoment para inser��o
	         PreparedStatement stmt = connection.prepareStatement(sql);
	 
	         // seta os valores
	         stmt.setLong(1, employee.getId());
	         
	         // executa
	         stmt.execute();
	         stmt.close();
	         
	     } catch (SQLException e) {
	         throw new RuntimeException(e);
	     }
	}

	@Override
	public Employee searchOne(String matriculation) throws MissingElementException {
		
		String SQL_JOIN = "SELECT emp.id as emp_id, fun.id as fun_id, sch.id as sch_id, "
				+ "sta.id as sta_id, wrk.id as wrk_id, "
				+ "emp.name as emp_name, emp.matriculation as emp_matr, emp.cpf as emp_cpf, "
				+ "emp.pis as emp_pis, emp.birthday as emp_bir, emp.admission_date as emp_adm, "
				+ "emp.salary as emp_sal, fun.name as fun_name, fun.female_name as fun_fem, "
				+ "fun.description as fun_des, fun.cbo as fun_cbo, sch.name as sch_name, "
				+ "sta.name as sta_name, sta.abbrev as sta_abv, wrk.name as wrk_name, wrk.code as wrk_code "
				+ "FROM (employees emp INNER JOIN "
				+ "functions fun ON (emp.function_id = fun.id)) "
				+ "INNER JOIN schooling sch ON (emp.schooling_id = sch.id) "
				+ "INNER JOIN states sta ON (emp.state_id = sta.id) "
				+ "INNER JOIN workplaces wrk ON (emp.workplace_id = wrk.id) "
				+ "WHERE emp.matriculation=?";
	    try {
	          
	    	PreparedStatement stmt = connection.prepareStatement(SQL_JOIN);

	    	stmt.setString(1, matriculation);
	        
	        ResultSet rs = stmt.executeQuery();
	        if(rs.next()){
	        	
	        	// montando a data atrav�s do Calendar
	            Calendar birthday = Calendar.getInstance();
	            birthday.setTime(rs.getDate("emp_bir"));
	            
	            Calendar admDate = Calendar.getInstance();
	            admDate.setTime(rs.getDate("emp_adm"));
	            
	            Schooling schooling = new Schooling(rs.getLong("sch_id"), 
	            		rs.getString("sch_name"));
	            State state = new State(rs.getLong("sta_id"), 
	            		rs.getString("sta_abv"), rs.getString("sta_name"));
	            Function func = new Function(rs.getLong("fun_id"), 
	            		rs.getString("fun_name"), rs.getString("fun_fem"), 
	            		rs.getString("fun_des"), rs.getString("fun_cbo"));
	            Workplace workplace = new Workplace(rs.getLong("wrk_id"), 
	            		rs.getString("wrk_code"), rs.getString("wrk_name"));
	            
	        	Employee employee = new Employee(rs.getLong("emp_id"), 
	        			rs.getString("emp_name"), rs.getString("emp_matr"), 
	        			rs.getString("emp_pis"), rs.getString("emp_cpf"), 
	        			rs.getBigDecimal("emp_sal"), admDate, birthday, 
	        			state, func, workplace, schooling);
	        	
	        	return employee;
 
	        }
	        stmt.close();
	        
	    } catch(SQLException sqle){
	        
	    	throw new RuntimeException(sqle);
	    }
	    
		return null;
	}

	@Override
	public List<Employee> search(String name) throws MissingElementException {
		
		String SQL_JOIN = "SELECT emp.id as emp_id, fun.id as fun_id, sch.id as sch_id, "
				+ "sta.id as sta_id, wrk.id as wrk_id, "
				+ "emp.name as emp_name, emp.matriculation as emp_matr, emp.cpf as emp_cpf, "
				+ "emp.pis as emp_pis, emp.birthday as emp_bir, emp.admission_date as emp_adm, "
				+ "emp.salary as emp_sal, fun.name as fun_name, fun.female_name as fun_fem, "
				+ "fun.description as fun_des, fun.cbo as fun_cbo, sch.name as sch_name, "
				+ "sta.name as sta_name, sta.abbrev as sta_abv, wrk.name as wrk_name, wrk.code as wrk_code "
				+ "FROM (employees emp INNER JOIN "
				+ "functions fun ON (emp.function_id = fun.id)) "
				+ "INNER JOIN schooling sch ON (emp.schooling_id = sch.id) "
				+ "INNER JOIN states sta ON (emp.state_id = sta.id) "
				+ "INNER JOIN workplaces wrk ON (emp.workplace_id = wrk.id) "
				+ "WHERE emp.name LIKE ?";
		
		List<Employee> employees = new ArrayList<Employee>();
		
	    try {
	          
	    	PreparedStatement stmt = connection.prepareStatement(SQL_JOIN);

	    	stmt.setString(1, "%"+name+"%");//global match string
	        
	        ResultSet rs = stmt.executeQuery();
	        Schooling schooling = null;
	        State state = null;
	        Function func = null; 
	        Workplace workplace = null;
	        Employee employee = null;
	        while(rs.next()){
	        	
	        	// montando a data atrav�s do Calendar
	            Calendar birthday = Calendar.getInstance();
	            birthday.setTime(rs.getDate("emp_bir"));
	            
	            Calendar admDate = Calendar.getInstance();
	            admDate.setTime(rs.getDate("emp_adm"));
	            
	            schooling = new Schooling(rs.getLong("sch_id"), 
	            		rs.getString("sch_name"));
	            state = new State(rs.getLong("sta_id"), 
	            		rs.getString("sta_abv"), rs.getString("sta_name"));
	            func = new Function(rs.getLong("fun_id"), 
	            		rs.getString("fun_name"), rs.getString("fun_fem"), 
	            		rs.getString("fun_des"), rs.getString("fun_cbo"));
	            workplace = new Workplace(rs.getLong("wrk_id"), 
	            		rs.getString("wrk_code"), rs.getString("wrk_name"));
	            
	        	employee = new Employee(rs.getLong("emp_id"), 
	        			rs.getString("emp_name"), rs.getString("emp_matr"), 
	        			rs.getString("emp_pis"), rs.getString("emp_cpf"), 
	        			rs.getBigDecimal("emp_sal"), admDate, birthday, 
	        			state, func, workplace, schooling);
	        	
	        	employees.add(employee);
 
	        }
	        stmt.close();
	        
	    } catch(SQLException sqle){
	        
	    	throw new RuntimeException(sqle);
	    }
	    
		return employees;
	}
	
	public List<Employee> searchAll() {
		
		List<Employee> employees = new ArrayList<Employee>();
		try {
			
			employees = this.search("");
			
		} catch (MissingElementException e) {
			
			e.printStackTrace();
		}
		return employees;
	}
}
