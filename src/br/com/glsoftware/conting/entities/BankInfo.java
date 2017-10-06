/**
 * 
 */
package br.com.glsoftware.conting.entities;

/**
 * @author Gilliard
 * Na modelagem, a tabela bank_info armazena o id do employee
 */
public class BankInfo {
	
	private long id;
	private Employee employee;
	private Bank bank;
	private String agency;
	private String account;
	private char vd;
	
	public BankInfo() {
		
	}
	
	public BankInfo(long id, Bank bank, Employee emp, String agency, String account, char vd) {
	//	super();
		this.id = id;
		this.bank = bank;
		this.employee = emp;
		this.agency = agency;
		this.account = account;
		this.vd = vd;
	}
	
	public BankInfo(Bank bank, Employee emp, String agency, String account, char vd) {
		//	super();
		this.bank = bank;
		this.employee = emp;
		this.agency = agency;
		this.account = account;
		this.vd = vd;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Bank getBank() {
		return bank;
	}

	public void setBank(Bank bank) {
		this.bank = bank;
	}

	public String getAgency() {
		return agency;
	}

	public void setAgency(String agency) {
		this.agency = agency;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public char getVd() {
		return vd;
	}

	public void setVd(char vd) {
		this.vd = vd;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
}
