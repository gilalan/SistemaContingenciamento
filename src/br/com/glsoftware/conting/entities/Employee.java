package br.com.glsoftware.conting.entities;

import java.math.BigDecimal;
import java.util.Calendar;

public class Employee {
	
	private long id;
	private long idSoll;
	private String name;
	private String matriculation;
	private String pis;
	private String cpf;
	private Calendar admissionDate;
	private Calendar birthday;
	private BigDecimal salary;
	private Function function;
	private Workplace workplace;
	//private BankInfo bankInfo;
	private State state;
	private Schooling schooling;
	
	public Employee() {
		
		this.idSoll = -1;
	}
	
	public Employee(long id, String name, String matriculation, String pis, String cpf, 
			BigDecimal salary, Calendar admissionDate, Calendar birthday) {
		
		this.id = id;
		this.name = name;
		this.matriculation = matriculation;
		this.admissionDate = admissionDate;
		this.birthday = birthday;
		this.pis = pis;
		this.cpf = cpf;
		this.salary = salary;		
	}
	
	public Employee(long id, String name, String matriculation, String pis, String cpf, 
			BigDecimal salary, Calendar admissionDate, Calendar birthday, State state, 
			Function function, Workplace workplace, Schooling schooling) {
		
		this.id = id;
		this.name = name;
		this.matriculation = matriculation;
		this.function = function;
		this.workplace = workplace;
		this.admissionDate = admissionDate;
		this.birthday = birthday;
		this.state = state;
		this.pis = pis;
		this.cpf = cpf;
		//this.bankInfo = bankInfo;
		this.salary = salary;
		this.schooling = schooling;
	}

	public Employee(String name, String matriculation, String pis, String cpf, 
			BigDecimal salary, Calendar admissionDate, Calendar birthday, State state, 
			Function function, Workplace workplace, Schooling schooling) {
		//super();
		this.name = name;
		this.matriculation = matriculation;
		this.function = function;
		this.workplace = workplace;
		this.admissionDate = admissionDate;
		this.birthday = birthday;
		this.state = state;
		this.pis = pis;
		this.cpf = cpf;
		//this.bankInfo = bankInfo;
		this.salary = salary;
		this.schooling = schooling;
	}

	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public long getIdSoll() {
		return idSoll;
	}

	public void setIdSoll(long idSoll) {
		this.idSoll = idSoll;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMatriculation() {
		return matriculation;
	}

	public void setMatriculation(String matriculation) {
		this.matriculation = matriculation;
	}

	public Function getFunction() {
		return function;
	}

	public void setFunction(Function function) {
		this.function = function;
	}

	public Workplace getWorkplace() {
		return workplace;
	}

	public void setWorkplace(Workplace workplace) {
		this.workplace = workplace;
	}

	public Calendar getAdmissionDate() {
		return admissionDate;
	}

	public void setAdmissionDate(Calendar admissionDate) {
		this.admissionDate = admissionDate;
	}

	public Calendar getBirthday() {
		return birthday;
	}

	public void setBirthday(Calendar birthday) {
		this.birthday = birthday;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public String getPis() {
		return pis;
	}

	public void setPis(String pis) {
		this.pis = pis;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

//	public BankInfo getBankInfo() {
//		return bankInfo;
//	}
//
//	public void setBankInfo(BankInfo bankInfo) {
//		this.bankInfo = bankInfo;
//	}

	public BigDecimal getSalary() {
		return salary;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}

	public Schooling getSchooling() {
		return schooling;
	}

	public void setSchooling(Schooling schooling) {
		this.schooling = schooling;
	}

	public boolean isEmpty() {
		
		if (this.idSoll != -1)
			return false;
		
		return true;
	}
	
	@Override
    public boolean equals(Object o) {

        if (o == this) return true;
        if (!(o instanceof Employee)) {
            return false;
        }

        Employee employee = (Employee) o;

        return employee.getName().toLowerCase().equals(this.name.toLowerCase()) &&
                employee.getIdSoll() == this.idSoll;
    }

    //Idea from effective Java : Item 9
    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + this.name.hashCode();
        result = (int) (31 * result + this.idSoll);
        return result;
    }

}
