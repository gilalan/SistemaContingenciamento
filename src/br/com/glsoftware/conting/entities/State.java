package br.com.glsoftware.conting.entities;

public class State {
	
	private long id;
	private String abbrev;
	private String name;
	
	public State (String abbrev) {
		this.abbrev = abbrev;
	}
	
	public State (String abbrev, String name){
		
		this.abbrev = abbrev;
		this.name = name;
	}

	public State(long id, String abbrev, String name) {
		
		this.id = id;
		this.abbrev = abbrev;
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAbbrev() {
		return abbrev;
	}

	public void setAbbrev(String abbrev) {
		this.abbrev = abbrev;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
