package br.com.glsoftware.conting.interfaces;

import java.util.List;

import br.com.glsoftware.conting.entities.Employee;
import br.com.glsoftware.conting.exceptions.ElementAlreadyExistException;
import br.com.glsoftware.conting.exceptions.MissingElementException;

public interface IEmployeeRepository {
	
	public void create(Employee employee) throws ElementAlreadyExistException;
	
	public void update(Employee employee) throws MissingElementException;
	
	public void delete (Employee employee) throws MissingElementException;
	
	public Employee searchOne(String matriculation) throws MissingElementException;
	
	public Employee searchById(long id) throws MissingElementException;
	
	public List<Employee> search(String name) throws MissingElementException;
	
	public List<Employee> searchAll();

	public void createBatch(List<Employee> employees);
}
