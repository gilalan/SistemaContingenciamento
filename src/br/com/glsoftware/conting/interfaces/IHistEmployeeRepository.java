package br.com.glsoftware.conting.interfaces;

import java.util.List;

import br.com.glsoftware.conting.entities.HistEmployee;
import br.com.glsoftware.conting.exceptions.ElementAlreadyExistException;
import br.com.glsoftware.conting.exceptions.MissingElementException;

public interface IHistEmployeeRepository {

	public void create(HistEmployee histEmployee) throws ElementAlreadyExistException;
	
	public void update(HistEmployee histEmployee) throws MissingElementException;
	
	public void delete (HistEmployee HistEmployee) throws MissingElementException;
	
	public HistEmployee searchOne(long id) throws MissingElementException;
	
	public List<HistEmployee> search(long sollId) throws MissingElementException;
	
	public List<HistEmployee> searchAll();

	public void createBatch(List<HistEmployee> histEmployees) throws ElementAlreadyExistException;
}
