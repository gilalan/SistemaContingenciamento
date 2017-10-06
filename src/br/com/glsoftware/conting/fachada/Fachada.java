package br.com.glsoftware.conting.fachada;

import java.util.List;

import br.com.glsoftware.conting.business.BankController;
import br.com.glsoftware.conting.business.BankInfoController;
import br.com.glsoftware.conting.business.ClientController;
import br.com.glsoftware.conting.business.EmployeeController;
import br.com.glsoftware.conting.business.FunctionController;
import br.com.glsoftware.conting.business.SchoolingController;
import br.com.glsoftware.conting.business.StateController;
import br.com.glsoftware.conting.business.WorkplaceController;
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
import br.com.glsoftware.conting.interfaces.IFachada;

public class Fachada implements IFachada {
	
	private ClientController clientController;
	private EmployeeController employeeController;
	private BankController bankController;
	private BankInfoController bankInfoController;
	private StateController stateController;
	private FunctionController functionController;
	private SchoolingController schoolingController;
	private WorkplaceController workplaceController;
	
	public Fachada (){
		this.clientController = new ClientController();
		this.employeeController = new EmployeeController();
		this.bankController = new BankController();
		this.bankInfoController = new BankInfoController();
		this.stateController = new StateController();
		this.functionController = new FunctionController();
		this.schoolingController = new SchoolingController();
		this.workplaceController = new WorkplaceController();
	}
	
	@Override
	public void createClient(Client client) throws ElementAlreadyExistException, InvalidAgeException {
		
		this.clientController.create(client);
	}

	@Override
	public void updateClient(Client client) throws MissingElementException {
		
		this.clientController.update(client);
	}

	@Override
	public void deleteClient(Client client) throws MissingElementException {
		
		this.clientController.delete(client);
	}

	@Override
	public Client searchOneClient(String name) throws MissingElementException {
		
		return this.clientController.searchOne(name);
	}
	
	@Override
	public List<Client> searchClients(String name) throws MissingElementException {
		
		return this.clientController.search(name);
	}

	@Override
	public void createEmployee(Employee employee) throws ElementAlreadyExistException, InvalidAgeException {
		
		this.employeeController.create(employee);
	}

	@Override
	public void updateEmployee(Employee employee) throws MissingElementException {
		
		this.employeeController.update(employee);
	}

	@Override
	public void deleteEmployee(Employee employee) throws MissingElementException {
		
		this.employeeController.delete(employee);
	}

	@Override
	public Employee searchOneEmployee(String matriculation) throws MissingElementException {
		
		return this.employeeController.searchOne(matriculation);
	}

	@Override
	public List<Employee> searchEmployees(String name) throws MissingElementException {
		
		return this.employeeController.search(name);
	}
	
	@Override
	public List<Employee> searchAll() {
		
		return this.employeeController.searchAll();
	}

	@Override
	public void createBank(Bank bank) throws ElementAlreadyExistException {
		
		this.bankController.create(bank);
	}

	@Override
	public void updateBank(Bank bank) throws MissingElementException {
		
		this.bankController.update(bank);
	}

	@Override
	public void deleteBank(Bank bank) throws MissingElementException {
		
		this.bankController.delete(bank);
	}
	
	@Override
	public Bank searchOneBank(String code) {
		
		return this.bankController.searchOne(code);
	}

	@Override
	public void createState(State state) throws ElementAlreadyExistException {
		
		this.stateController.create(state);
	}

	@Override
	public void updateState(State state) throws MissingElementException {
		
		this.stateController.update(state);
	}

	@Override
	public void deleteState(State state) throws MissingElementException {
		
		this.stateController.delete(state);
	}

	@Override
	public State searchOneState(String abbrev) {
		
		return this.stateController.searchOne(abbrev);
	}
	
	@Override
	public List<State> searchStates(String name) {
		
		return this.stateController.search(name);
	}

	@Override
	public void createFunction(Function function) throws ElementAlreadyExistException {
		
		this.functionController.create(function);
	}

	@Override
	public void updateFunction(Function function) throws MissingElementException {
		
		this.functionController.update(function);
	}

	@Override
	public void deleteFunction(Function function) throws MissingElementException {
		
		this.functionController.delete(function);
	}

	@Override
	public Function searchOneFunction(String cbo) {
		
		return this.functionController.searchOne(cbo);
	}
	
	@Override
	public List<Function> searchFunctions(String name) {
		
		return this.functionController.search(name);
	}

	@Override
	public void createSchooling(Schooling schooling) throws ElementAlreadyExistException {

		this.schoolingController.create(schooling);
	}

	@Override
	public void updateSchooling(Schooling schooling) throws MissingElementException {
		
		this.schoolingController.update(schooling);
	}

	@Override
	public void deleteSchooling(Schooling schooling) throws MissingElementException {
		
		this.schoolingController.delete(schooling);
	}

	@Override
	public Schooling searchOneSchooling(long id) {
		
		return this.schoolingController.searchOne(id);
	}
	
	@Override
	public List<Schooling> searchSchoolings(String name) {
		
		return this.schoolingController.search(name);
	}

	@Override
	public void createWorkplace(Workplace workplace) throws ElementAlreadyExistException {
		
		this.workplaceController.create(workplace);
	}

	@Override
	public void updateWorkplace(Workplace workplace) throws MissingElementException {
		
		this.workplaceController.update(workplace);
	}

	@Override
	public void deleteWorkplace(Workplace workplace) throws MissingElementException {
		
		this.workplaceController.delete(workplace);
	}

	@Override
	public Workplace searchOneWorkplace(String code) {
		
		return this.workplaceController.searchOne(code);
	}

	@Override
	public List<Workplace> searchWorkplaces(String name) {
		
		return this.workplaceController.search(name);
	}

	@Override
	public void createBankInfo(BankInfo bankInfo) throws ElementAlreadyExistException {
		
		this.bankInfoController.create(bankInfo);
	}

	@Override
	public void updateBankInfo(BankInfo bankInfo) throws MissingElementException {
		
		this.bankInfoController.update(bankInfo);
	}

	@Override
	public void deleteBankInfo(BankInfo bankInfo) throws MissingElementException {
		
		this.bankInfoController.delete(bankInfo);
	}

	@Override
	public BankInfo searchOneBankInfo(long id) {
		
		return this.bankInfoController.searchOne(id);
	}
	
}
