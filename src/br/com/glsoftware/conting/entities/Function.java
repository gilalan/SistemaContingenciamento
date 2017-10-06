package br.com.glsoftware.conting.entities;

public class Function {
	
	private long id;
	private String name;
	private String femaleName;
	private String description;
	private String cbo;
	
	public Function () {
		this.id = -1;
		this.name = "";
		this.femaleName = "";
		this.description = "";
		this.cbo = "";
	}
	
	public Function (String name) {
		this.id = -1;
		this.name = name;
		this.femaleName = "";
		this.description = "";
		this.cbo = "";
	}
	
	public Function(long id, String name, String femaleName, String description, String cbo) {
		//super();
		this.id = id;
		this.name = name;
		this.femaleName = femaleName;
		this.description = description;
		this.cbo = cbo;
	}
	
	public Function(String name, String femaleName, String description, String cbo) {
		
		this.name = name;
		this.femaleName = femaleName;
		this.description = description;
		this.cbo = cbo;
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

	public String getFemaleName() {
		return femaleName;
	}

	public void setFemaleName(String femaleName) {
		this.femaleName = femaleName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCbo() {
		return cbo;
	}

	public void setCbo(String cbo) {
		this.cbo = cbo;
	}

	public boolean isEmpty() {
		
		if (this.name != null && !this.name.isEmpty())
			return false;
		
		return true;
	}
	
	@Override
    public boolean equals(Object o) {

        if (o == this) return true;
        if (!(o instanceof Function)) {
            return false;
        }

        Function function = (Function) o;

        return function.name.equals(name) &&
                function.id == id &&
                function.cbo.equals(cbo);
    }

    //Idea from effective Java : Item 9
    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + name.hashCode();
        result = (int) (31 * result + id);
        result = 31 * result + cbo.hashCode();
        return result;
    }

}
