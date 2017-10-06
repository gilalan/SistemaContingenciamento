package br.com.glsoftware.conting.interfaces;

import java.util.List;

import br.com.glsoftware.conting.entities.Schooling;
import br.com.glsoftware.conting.exceptions.ElementAlreadyExistException;
import br.com.glsoftware.conting.exceptions.MissingElementException;

public interface ISchoolingRepository {
	
	public void create(Schooling schooling) throws ElementAlreadyExistException;
	
	public void update(Schooling schooling) throws MissingElementException;
	
	public void delete (Schooling schooling) throws MissingElementException;
	
	public Schooling searchOne(long id);
	
	public List<Schooling> search(String name);
}
