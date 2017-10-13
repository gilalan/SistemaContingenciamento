package br.com.glsoftware.conting.entities;

import java.math.BigDecimal;

public class HistEmployee {
	
	private long id;
	private Employee employee;
	private String dptoCode;
	private BigDecimal baseSalary;
	private String baseMonth;
	private String baseYear;
	
	public HistEmployee(){
	}
	
	public HistEmployee(Employee emp) {
		
		this.employee = emp;
	}
	
	public HistEmployee(Employee emp, String dCode, BigDecimal baseSalary
			, String baseMonth, String baseYear){
		
		this.employee = emp;
		this.dptoCode = dCode;
		this.baseSalary = baseSalary;
		this.baseMonth = baseMonth;
		this.baseYear = baseYear;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public String getDptoCode() {
		return dptoCode;
	}
	public void setDptoCode(String dptoCode) {
		this.dptoCode = dptoCode;
	}
	public BigDecimal getBaseSalary() {
		return baseSalary;
	}
	public void setBaseSalary(BigDecimal baseSalary) {
		this.baseSalary = baseSalary;
	}
	public String getBaseMonth() {
		return baseMonth;
	}
	public void setBaseMonth(String baseMonth) {
		this.baseMonth = baseMonth;
	}
	public String getBaseYear() {
		return baseYear;
	}
	public void setBaseYear(String baseYear) {
		this.baseYear = baseYear;
	}
	
	
}
