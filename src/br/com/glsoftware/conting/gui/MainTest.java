/**
 * 
 */
package br.com.glsoftware.conting.gui;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import br.com.glsoftware.conting.entities.Bank;
import br.com.glsoftware.conting.entities.BankInfo;
import br.com.glsoftware.conting.entities.Employee;
import br.com.glsoftware.conting.entities.Function;
import br.com.glsoftware.conting.entities.Schooling;
import br.com.glsoftware.conting.entities.State;
import br.com.glsoftware.conting.entities.Workplace;
import br.com.glsoftware.conting.exceptions.ElementAlreadyExistException;
import br.com.glsoftware.conting.exceptions.InvalidAgeException;
import br.com.glsoftware.conting.exceptions.MissingElementException;
import br.com.glsoftware.conting.fachada.Fachada;

/**
 * @author Gilliard
 *
 */
public class MainTest {

	/**
	 * @param args
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		System.out.println("Main Test!");

		Fachada fachada = new Fachada();
		
		/*
		 * Pode verificar como fazer para o select ser case-insensitive
		 */
		
		//criarBanco(fachada);
		//searchBanco(fachada);
		//atualizarBanco(fachada);
		//deletarBanco(fachada);
		
		//criarFunction(fachada);
//		searchFunction(fachada);
//		atualizarFunction(fachada);
		//deletarFunction(fachada);
		//searchFunction(fachada);
		//criarFunction(fachada);
		
//		criarState(fachada);
//		searchState(fachada);
//		atualizarState(fachada);
//		deletarState(fachada);
//		searchState(fachada);
//		criarState(fachada);
		
//		criarWorkplace(fachada);
//		searchWorkplace(fachada);
//		atualizarWorkplace(fachada);
//		deletarWorkplace(fachada);
//		searchWorkplace(fachada);
//		criarWorkplace(fachada);
		
//		criarSchooling(fachada);
//		searchSchooling(fachada);
//		atualizarSchooling(fachada);
//		deletarSchooling(fachada);
//		searchSchooling(fachada);
//		criarSchooling(fachada);
		
		//Testando Employee
		BigDecimal salary = new BigDecimal(1027.30);
		Calendar birthday = new GregorianCalendar(1987,0,20);
		Calendar admDate = new GregorianCalendar(2016,11,10);
		Function cargo = searchFunction(fachada);
		State uf = searchState(fachada, "PE");
		Workplace departamento = searchWorkplace(fachada);
		Schooling escolaridade = searchSchooling(fachada);
		
		Employee employee = new Employee("José Carlos", "9012312", "01293182321", "067172723-91",
				salary, admDate, birthday, uf, cargo, departamento, escolaridade);
		
		Employee employee1 = new Employee("Diamantino", "280407", "158408777", "067172723-92",
				salary, admDate, birthday, uf, cargo, departamento, escolaridade);
		
		Employee employee2 = new Employee("Alberto Costa", "290407", "158408771", "067172723-93",
				salary, admDate, birthday, uf, cargo, departamento, escolaridade);
		
		Function f1 = new Function("AUX SERV GERAIS");
		Function f2 = new Function("AUX SERV GERAIS");
		System.out.println("Iguais? " + f1.equals(f2));
		
//		employee.setName("Franscisco José Carlos");
//		employee.setId(1);
//		updateEmployee(fachada, employee);
		
		//createEmployee(fachada, employee2);
		
		//BankInfo bi = searchOneBankInfo(fachada, 1);
		//System.out.println("BI: " + bi.getAgency() + ", " + bi.getAccount());
		String matricula = "280407";
//		try {
//			Employee emp = fachada.searchOneEmployee(matricula);			
//			if (emp!=null){
//				System.out.println("Funcionário encontrado: " + emp.getName());
//	        	System.out.println("Cargo Name: " + emp.getFunction().getName());	        	
//	        	System.out.println("Escolid Name: " + emp.getSchooling().getName());
//	        	System.out.println("UF Name: " + emp.getState().getName());
//	        	System.out.println("Dept Name: " + emp.getWorkplace().getName());
//	        	
//			} else {
//				System.out.println("Funcionário não encontrado!");
//			}
//		} catch (MissingElementException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		List<Employee> emps = fachada.searchAll();
		for (Employee emp : emps){
			System.out.println("Funcionário encontrado: " + emp.getName());
        	System.out.println("Cargo Name: " + emp.getFunction().getName());	        	
        	System.out.println("Escolid Name: " + emp.getSchooling().getName());
        	System.out.println("UF Name: " + emp.getState().getName());
        	System.out.println("Dept Name: " + emp.getWorkplace().getName());
		}
	}
	
	private static void createEmployee(Fachada fachada, Employee employee) {
		
		try {
			
			fachada.createEmployee(employee);
			System.out.println("Usuário criado com sucesso!, id retornado: " + employee.getId());
			createInfoBankForEmployee(fachada, employee);
			
		} catch (ElementAlreadyExistException | InvalidAgeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void updateEmployee(Fachada fachada, Employee employee) {
		
		try {
			
			fachada.updateEmployee(employee);
			System.out.println("Usuário atualizado com sucesso!");
			System.out.println("Novo nome: " + employee.getName());
			
		} catch (MissingElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void createInfoBankForEmployee(Fachada fachada, Employee emp) throws ElementAlreadyExistException{
		
		Bank banco = searchBanco(fachada);//CEF
		BankInfo bankInfoEmployee = new BankInfo(
				banco, emp, "1204", "91205", '1');
		fachada.createBankInfo(bankInfoEmployee);
	}
	
	private static BankInfo searchOneBankInfo(Fachada fachada, long id){
		
		return fachada.searchOneBankInfo(id);
	}
	
	/* 
	 * Testessss
	 */
	private static void criarBanco(Fachada fachada){
		Bank banco1 = new Bank("104", "CEF");
		try {
			fachada.createBank(banco1);
			System.out.println("Banco criado com sucesso");
		} catch (ElementAlreadyExistException e) {
			// TODO Auto-generated catch block
			System.out.println("Erro: " + e.getMessage().toString());
			e.printStackTrace();
		} finally {
			System.out.println("Executado.");
		}
	}
	
	private static void atualizarBanco(Fachada fachada){
		
		Bank banco = searchBanco(fachada);
		System.out.println("atualizando o banco encontrado...");
		banco.setName("Caixa Econômica Federal");
		try {
			fachada.updateBank(banco);
			System.out.println("Banco atualizado com sucesso! Novo nome: " + banco.getName());
		} catch (MissingElementException e) {
			// TODO Auto-generated catch block
			System.out.println("Erro: " + e.getMessage());
			//e.printStackTrace();
		}
	}
	
	private static void deletarBanco(Fachada fachada){
		
		Bank banco = searchBanco(fachada);
		try {
			fachada.deleteBank(banco);
			System.out.println("Banco deletado com sucesso");
		} catch (MissingElementException e) {
			// TODO Auto-generated catch block
			System.out.println("Não existe um banco com o ID fornecido.");
		}
	}
	
	private static Bank searchBanco(Fachada fachada) {
		
		Bank banco = fachada.searchOneBank("104");
		String result = (banco!=null) ? banco.getName() : "nenhum banco encontrado";
		System.out.println("banco encontrado: " + result);
		return banco;
	}
	
	private static void criarFunction(Fachada fachada){
		Function f1 = new Function("Cozinheiro", "Cozinheira", 
				"Organizam e supervisionam serviços de cozinha em hotéis, restaurantes, "
				+ "hospitais, residências e outros locais de refeições, planejando cardápios "
				+ "e elaborando o pré-preparo, o preparo e a finalização de alimentos, observando "
				+ "métodos de cocção e padrões de qualidade dos alimentos.", 
				"5132-05");
		try {
			fachada.createFunction(f1);
			System.out.println("Cargo criado com sucesso");
		} catch (ElementAlreadyExistException e) {
			// TODO Auto-generated catch block
			System.out.println("Erro: " + e.getMessage().toString());
			e.printStackTrace();
		} finally {
			System.out.println("Executado.");
		}
	}
	
	private static void atualizarFunction(Fachada fachada){
		
		Function function = searchFunction(fachada);
		System.out.println("atualizando o cargo encontrado...");
		function.setName("Cozx");
		function.setFemaleName("Cozy");
		try {
			fachada.updateFunction(function);
			System.out.println("Cargo atualizado com sucesso! Novo nome: " + function.getName());
		} catch (MissingElementException e) {
			// TODO Auto-generated catch block
			System.out.println("Erro: " + e.getMessage());
			//e.printStackTrace();
		}
	}
	
	private static void deletarFunction(Fachada fachada){
		
		Function function = searchFunction(fachada);
		try {
			fachada.deleteFunction(function);
			System.out.println("Cargo deletado com sucesso");
		} catch (MissingElementException e) {
			// TODO Auto-generated catch block
			System.out.println("Não existe um cargo com o ID fornecido.");
		}
	}
	
	private static Function searchFunction(Fachada fachada) {
		
		Function function = fachada.searchOneFunction("5132-05");
		String result = (function!=null) ? function.getName() : "nenhum cargo encontrado";
		System.out.println("Cargo encontrado: " + result);
		return function;
	}
	
	private static void criarState(Fachada fachada){
		State state = new State("PE", "Pernambuco");
		try {
			fachada.createState(state);
			System.out.println("UF criado com sucesso");
		} catch (ElementAlreadyExistException e) {
			// TODO Auto-generated catch block
			System.out.println("Erro: " + e.getMessage().toString());
			e.printStackTrace();
		} finally {
			System.out.println("Executado.");
		}
	}
	
	private static void atualizarState(Fachada fachada, String uf){
		
		State state = searchState(fachada, uf);
		System.out.println("atualizando o UF encontrado...");
		state.setAbbrev("PX");
		state.setName("Pernx");
		try {
			fachada.updateState(state);
			System.out.println("UF atualizado com sucesso! Novo nome: " + state.getName());
		} catch (MissingElementException e) {
			// TODO Auto-generated catch block
			System.out.println("Erro: " + e.getMessage());
			//e.printStackTrace();
		}
	}
	
	private static void deletarState(Fachada fachada, String uf){
		
		State state = searchState(fachada, uf);
		try {
			fachada.deleteState(state);
			System.out.println("UF deletado com sucesso");
		} catch (MissingElementException e) {
			// TODO Auto-generated catch block
			System.out.println("Não existe um UF com o ID fornecido.");
		}
	}
	
	private static State searchState(Fachada fachada, String uf) {
		
		State state = fachada.searchOneState(uf);
		String result = (state!=null) ? state.getName() : "nenhum UF encontrado";
		System.out.println("UF encontrado: " + result);
		return state;
	}
	
	private static void criarSchooling(Fachada fachada){
		Schooling schooling = new Schooling("Primário Incompleto");
		try {
			fachada.createSchooling(schooling);
			System.out.println("Escolaridade criado com sucesso");
		} catch (ElementAlreadyExistException e) {
			// TODO Auto-generated catch block
			System.out.println("Erro: " + e.getMessage().toString());
			e.printStackTrace();
		} finally {
			System.out.println("Executado.");
		}
	}
	
	private static void atualizarSchooling(Fachada fachada){
		
		Schooling schooling = searchSchooling(fachada);
		System.out.println("atualizando o escolaridade encontrado...");
		schooling.setName("Primário x");
		try {
			fachada.updateSchooling(schooling);
			System.out.println("Escolaridade atualizado com sucesso! Novo nome: " + schooling.getName());
		} catch (MissingElementException e) {
			// TODO Auto-generated catch block
			System.out.println("Erro: " + e.getMessage());
			//e.printStackTrace();
		}
	}
	
	private static void deletarSchooling(Fachada fachada){
		
		Schooling schooling = searchSchooling(fachada);
		try {
			fachada.deleteSchooling(schooling);
			System.out.println("Schooling deletado com sucesso");
		} catch (MissingElementException e) {
			// TODO Auto-generated catch block
			System.out.println("Não existe um Schooling com o ID fornecido.");
		}
	}
	
	private static Schooling searchSchooling(Fachada fachada) {
		
		Schooling schooling = fachada.searchOneSchooling(2);
		String result = (schooling!=null) ? schooling.getName() : "nenhum Schooling encontrado";
		System.out.println("Schooling encontrado: " + result);
		return schooling;
	}
	
	private static void criarWorkplace(Fachada fachada){
		Workplace workplace = new Workplace("004", "Diretoria");
		try {
			fachada.createWorkplace(workplace);
			System.out.println("Departamento criado com sucesso");
		} catch (ElementAlreadyExistException e) {
			// TODO Auto-generated catch block
			System.out.println("Erro: " + e.getMessage().toString());
			e.printStackTrace();
		} finally {
			System.out.println("Executado.");
		}
	}
	
	private static void atualizarWorkplace(Fachada fachada){
		
		Workplace workplace = searchWorkplace(fachada);
		System.out.println("atualizando o departamento encontrado...");
		workplace.setName("Diretoria X");
		try {
			fachada.updateWorkplace(workplace);
			System.out.println("Departamento atualizado com sucesso! Novo nome: " + workplace.getName());
		} catch (MissingElementException e) {
			// TODO Auto-generated catch block
			System.out.println("Erro: " + e.getMessage());
			//e.printStackTrace();
		}
	}
	
	private static void deletarWorkplace(Fachada fachada){
		
		Workplace workplace = searchWorkplace(fachada);
		try {
			fachada.deleteWorkplace(workplace);
			System.out.println("Departamento deletado com sucesso");
		} catch (MissingElementException e) {
			// TODO Auto-generated catch block
			System.out.println("Não existe um departamento com o ID fornecido.");
		}
	}
	
	private static Workplace searchWorkplace(Fachada fachada) {
		
		Workplace workplace = fachada.searchOneWorkplace("004");
		String result = (workplace!=null) ? workplace.getName() : "nenhum Departamento encontrado";
		System.out.println("Departamento encontrado: " + result);
		return workplace;
	}

}
