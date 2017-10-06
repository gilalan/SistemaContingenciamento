package br.com.glsoftware.conting.interfaces;

import java.util.List;

import br.com.glsoftware.conting.entities.Function;
import br.com.glsoftware.conting.exceptions.ElementAlreadyExistException;
import br.com.glsoftware.conting.exceptions.MissingElementException;

public interface IFunctionRepository {
	
	public void create(Function function) throws ElementAlreadyExistException;
	
	public void update(Function function) throws MissingElementException;
	
	public void delete (Function function) throws MissingElementException;
	
	public Function searchOne(String cbo);
	
	public List<Function> search(String name);
}	
