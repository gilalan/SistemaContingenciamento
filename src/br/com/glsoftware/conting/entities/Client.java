package br.com.glsoftware.conting.entities;

import java.util.List;

public class Client {
	
	private int id;
	private List<Contract> contracts;
	private String fantasyName;
	private String cnpj;
	private Address address;
	private Contact contact;
	
	public Client(int id, List<Contract> contracts, String fantasyName, String cnpj, Address address,
			Contact contact) {
		super();
		this.id = id;
		this.contracts = contracts;
		this.fantasyName = fantasyName;
		this.cnpj = cnpj;
		this.address = address;
		this.contact = contact;
	}
	
	public Client(List<Contract> contracts, String fantasyName, String cnpj, Address address,
			Contact contact) {
		super();
		this.contracts = contracts;
		this.fantasyName = fantasyName;
		this.cnpj = cnpj;
		this.address = address;
		this.contact = contact;
	}
	
	public int getId() {
		return id;
	}
	
	public List<Contract> getContracts() {
		return contracts;
	}
	public void setContracts(List<Contract> contracts) {
		this.contracts = contracts;
	}
	public String getFantasyName() {
		return fantasyName;
	}
	public void setFantasyName(String fantasyName) {
		this.fantasyName = fantasyName;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public Contact getContact() {
		return contact;
	}
	public void setContact(Contact contact) {
		this.contact = contact;
	}
}
