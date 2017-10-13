package br.com.glsoftware.conting.business;

import java.util.List;

import br.com.glsoftware.conting.dao.HistEmployeeDao;
import br.com.glsoftware.conting.entities.HistEmployee;
import br.com.glsoftware.conting.exceptions.ElementAlreadyExistException;
import br.com.glsoftware.conting.exceptions.MissingElementException;
import br.com.glsoftware.conting.interfaces.IHistEmployeeRepository;

public class HistEmployeeController {
	
	private IHistEmployeeRepository histEmployeeRep;
	
	public HistEmployeeController(){
		this.histEmployeeRep = new HistEmployeeDao() ;
	}
	
	public void create (HistEmployee histEmployee) throws ElementAlreadyExistException {
		
		//regra de negocios para criação
		//depois chama o criador no banco
		this.histEmployeeRep.create(histEmployee);
	}
	
	public void update (HistEmployee histEmployee) throws MissingElementException {
		
		this.histEmployeeRep.update(histEmployee);
	}
	
	public void delete (HistEmployee histEmployee) throws MissingElementException {
		
		this.histEmployeeRep.delete(histEmployee);
	}
	
	public HistEmployee searchOne (long id) throws MissingElementException {
		
		return this.histEmployeeRep.searchOne(id);
	}
	
	public List<HistEmployee> search (long sollId) throws MissingElementException {
		
		return this.histEmployeeRep.search(sollId);
	}
	
	public List<HistEmployee> searchAll() {
		
		return this.histEmployeeRep.searchAll();
	}

	public void createBatch(List<HistEmployee> histEmployees) throws ElementAlreadyExistException {
		// TODO Auto-generated method stub
		this.histEmployeeRep.createBatch(histEmployees);
	}
}
