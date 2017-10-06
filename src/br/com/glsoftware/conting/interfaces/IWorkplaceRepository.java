package br.com.glsoftware.conting.interfaces;

import java.util.List;

import br.com.glsoftware.conting.entities.Workplace;
import br.com.glsoftware.conting.exceptions.ElementAlreadyExistException;
import br.com.glsoftware.conting.exceptions.MissingElementException;

public interface IWorkplaceRepository {
	
	public void create(Workplace workplace) throws ElementAlreadyExistException;
	
	public void update(Workplace workplace) throws MissingElementException;
	
	public void delete (Workplace workplace) throws MissingElementException;
	
	public Workplace searchOne(String code);
	
	public List<Workplace> search(String name);
}
