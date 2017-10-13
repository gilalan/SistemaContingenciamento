package br.com.glsoftware.conting.gui;

import java.util.List;

import br.com.glsoftware.conting.entities.HistEmployee;
import br.com.glsoftware.conting.exceptions.MissingElementException;
import br.com.glsoftware.conting.fachada.Fachada;

public class MaintTest2 {
	
	public static void main(String[] args) {
		
		Fachada fachada = new Fachada();
		
		//Testando Funcionario
//		BigDecimal salary = new BigDecimal(1027.30);
//		String name = "Flavio José 2";
//		long idSoll = 19124;
//		
//		Employee emp = new Employee();
//		emp.setName(name);
//		emp.setIdSoll(idSoll);
		
//		Employee emp;
//		try {
//			
//			emp = fachada.searchByEmployeeId(1);
//			fachada.deleteEmployee(emp);
//			//System.out.println("Usuário removido com sucesso!, id retornado: " + emp.getId());
//			System.out.println("Usuário "+ emp.getName() + 
//					" removido com sucesso!");
//			
//		} catch (MissingElementException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		//22433	TANIA GRANJEIRO DE MOURA SILVA	28005	953	5	2017
		long idSoll = 22434;
//		String name = "TANIA GRANJEIRO DE MOURA SILVA";
//		String codDepto = "028005";
//		BigDecimal salary = new BigDecimal(953.00);
//		String mes = "7";
//		String ano = "2017";
//		Employee emp;
//		try {
//			emp = fachada.searchByEmployeeId(22433);
//			if (emp == null)
//				System.out.println("Usuário de idSoll = " + 22431 +" não encontrado!");
//			
//			else {
//				HistEmployee hemp = new HistEmployee(emp, codDepto, 
//						salary, mes, ano);
//				fachada.createHistEmployee(hemp);
//				System.out.println("Historico de Empregado adicionado com sucesso!");
//			}
//		} 
//		catch (MissingElementException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		catch (ElementAlreadyExistException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		try {
			List<HistEmployee> hemps = fachada.searchHistEmployee(idSoll);
			if (hemps.isEmpty())
				System.out.println("Não foram encontrados resultados");
			else {
				for (HistEmployee hemp : hemps){
					System.out.println("Hemp: " + hemp.getId() + ", Nome: " + hemp.getEmployee().getName());
					System.out.println("Período: " + hemp.getBaseMonth() + "//" + hemp.getBaseYear());
				}
			}
			
		} catch (MissingElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
