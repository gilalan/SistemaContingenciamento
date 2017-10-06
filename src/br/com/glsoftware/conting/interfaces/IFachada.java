package br.com.glsoftware.conting.interfaces;

import java.util.List;

import br.com.glsoftware.conting.entities.Bank;
import br.com.glsoftware.conting.entities.BankInfo;
import br.com.glsoftware.conting.entities.Client;
import br.com.glsoftware.conting.entities.Employee;
import br.com.glsoftware.conting.entities.Function;
import br.com.glsoftware.conting.entities.Schooling;
import br.com.glsoftware.conting.entities.State;
import br.com.glsoftware.conting.entities.Workplace;
import br.com.glsoftware.conting.exceptions.ElementAlreadyExistException;
import br.com.glsoftware.conting.exceptions.InvalidAgeException;
import br.com.glsoftware.conting.exceptions.MissingElementException;

public interface IFachada {

	public void createClient(Client client) throws ElementAlreadyExistException, InvalidAgeException;
	
	public void updateClient(Client client) throws MissingElementException;
	
	public void deleteClient(Client client) throws MissingElementException;
	
	public Client searchOneClient(String name) throws MissingElementException;
	
	public List<Client> searchClients(String name) throws MissingElementException;
	
	public void createEmployee(Employee employee) throws ElementAlreadyExistException, InvalidAgeException;
	
	public void updateEmployee(Employee employee) throws MissingElementException;
	
	public void deleteEmployee(Employee employee) throws MissingElementException;
	
	public Employee searchOneEmployee(String matriculation) throws MissingElementException;
	
	public List<Employee> searchEmployees(String name) throws MissingElementException;
	
	public List<Employee> searchAll();
	
	public void createBank(Bank bank) throws ElementAlreadyExistException;
	
	public void updateBank(Bank bank) throws MissingElementException;
	
	public void deleteBank(Bank bank) throws MissingElementException;

	public Bank searchOneBank(String code);
	
	public void createBankInfo(BankInfo bankInfo) throws ElementAlreadyExistException;
	
	public void updateBankInfo(BankInfo bankInfo) throws MissingElementException;
	
	public void deleteBankInfo(BankInfo bankInfo) throws MissingElementException;

	public BankInfo searchOneBankInfo(long id);
	
	public void createState(State state) throws ElementAlreadyExistException;
	
	public void updateState(State state) throws MissingElementException;
	
	public void deleteState(State state) throws MissingElementException;

	public State searchOneState(String abbrev);
	
	public List<State> searchStates(String name);
	
	public void createFunction(Function function) throws ElementAlreadyExistException;
	
	public void updateFunction(Function function) throws MissingElementException;
	
	public void deleteFunction(Function function) throws MissingElementException;

	public Function searchOneFunction(String cbo);
	
	public List<Function> searchFunctions(String name);
	
	public void createSchooling(Schooling schooling) throws ElementAlreadyExistException;
	
	public void updateSchooling(Schooling schooling) throws MissingElementException;
	
	public void deleteSchooling(Schooling schooling) throws MissingElementException;

	public Schooling searchOneSchooling(long id);
	
	public List<Schooling> searchSchoolings(String name);
	
	public void createWorkplace(Workplace workplace) throws ElementAlreadyExistException;
	
	public void updateWorkplace(Workplace workplace) throws MissingElementException;
	
	public void deleteWorkplace(Workplace workplace) throws MissingElementException;

	public Workplace searchOneWorkplace(String code);

	public List<Workplace> searchWorkplaces(String name);

}
