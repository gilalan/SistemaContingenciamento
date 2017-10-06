package br.com.glsoftware.conting.interfaces;

import java.util.List;

import br.com.glsoftware.conting.entities.State;
import br.com.glsoftware.conting.exceptions.ElementAlreadyExistException;
import br.com.glsoftware.conting.exceptions.MissingElementException;

public interface IStateRepository {
	
	public void create(State state) throws ElementAlreadyExistException;
	
	public void update(State state) throws MissingElementException;
	
	public void delete (State state) throws MissingElementException;
	
	public State searchOne(String abbrev);
	
	public List<State> search(String name);
}
