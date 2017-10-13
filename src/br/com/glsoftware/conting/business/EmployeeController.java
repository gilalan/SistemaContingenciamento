/**
 * 
 */
package br.com.glsoftware.conting.business;

import java.util.List;

import br.com.glsoftware.conting.dao.EmployeeDao;
import br.com.glsoftware.conting.entities.Employee;
import br.com.glsoftware.conting.exceptions.ElementAlreadyExistException;
import br.com.glsoftware.conting.exceptions.MissingElementException;
import br.com.glsoftware.conting.interfaces.IEmployeeRepository;

/**
 * @author Gilliard
 *
 */
public class EmployeeController {
	
	private IEmployeeRepository employeeRep;
	
	public EmployeeController(){
		this.employeeRep = new EmployeeDao() ;
	}
	
	public void create (Employee employee) throws ElementAlreadyExistException {
		
		//regra de negocios para criação
		//depois chama o criador no banco
		this.employeeRep.create(employee);
	}
	
	public void createBatch(List<Employee> employees) {
		
		this.employeeRep.createBatch(employees);
	}
	
	public void update (Employee employee) throws MissingElementException {
		
		this.employeeRep.update(employee);
	}
	
	public void delete (Employee employee) throws MissingElementException {
		
		this.employeeRep.delete(employee);
	}
	
	public Employee searchOne (String matriculation) throws MissingElementException {
		
		return this.employeeRep.searchOne(matriculation);
	}
	
	public Employee searchById (long id) throws MissingElementException {
		
		return this.employeeRep.searchById(id);
	}
	
	public List<Employee> search (String name) throws MissingElementException {
		
		return this.employeeRep.search(name);
	}
	
	public List<Employee> searchAll() {
		
		return this.employeeRep.searchAll();
	}
}
