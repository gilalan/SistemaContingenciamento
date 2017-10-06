package br.com.glsoftware.conting.entities;

public class Workplace {
	
	private long id;
	private String code;
	private String name;
	
	public Workplace(){}
	
	public Workplace(String code) {
		this.code = code;
	}
	
	public Workplace (long id, String code, String name){
		this.id = id;
		this.code = code;
		this.name = name;
	}
	
	public Workplace (String code, String name){
		
		this.code = code;
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public boolean isEmpty() {
		
		if (this.code != null && !this.code.isEmpty())
			return false;
		
		return true;
	}
}
