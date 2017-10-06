package br.com.glsoftware.conting.entities;

public class Bank {
	
	private long id;
	private String code;
	private String name;
	
	public Bank(){}
	
	public Bank (long id, String code, String name){
		this.id = id;
		this.code = code;
		this.name = name;
	}
	
	public Bank (String name) {
		this.name = name;
	}
	
	public Bank (String code, String name){
		this.code = code;
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
