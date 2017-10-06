package br.com.glsoftware.conting.entities;

public class Schooling {
	
	private long id;
	private String name;
	
	public Schooling(long id, String name) {
		
		this.id = id;
		this.name = name;
	}

	public Schooling (String name){
		
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
