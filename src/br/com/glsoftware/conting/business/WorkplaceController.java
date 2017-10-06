package br.com.glsoftware.conting.business;

import java.util.List;

import br.com.glsoftware.conting.dao.WorkplaceDao;
import br.com.glsoftware.conting.entities.Workplace;
import br.com.glsoftware.conting.exceptions.ElementAlreadyExistException;
import br.com.glsoftware.conting.exceptions.MissingElementException;
import br.com.glsoftware.conting.interfaces.IWorkplaceRepository;

public class WorkplaceController {
	
	private	IWorkplaceRepository workplaceRepository;
	
	public WorkplaceController() {
		
		this.workplaceRepository = new WorkplaceDao();
	}
	
	//CRUD
	public void create (Workplace workplace) throws ElementAlreadyExistException {
		
		//regra de negocios para criação
		//depois chama o criador no banco
		this.workplaceRepository.create(workplace);
	}
	
	public void update (Workplace workplace) throws MissingElementException {
		
		this.workplaceRepository.update(workplace);
	}
	
	public void delete (Workplace workplace) throws MissingElementException {
		
		this.workplaceRepository.delete(workplace);
	}
	
	public Workplace searchOne (String code) {
		
		return this.workplaceRepository.searchOne(code);
	}
	
	public List<Workplace> search (String name) {
		
		return this.workplaceRepository.search(name);
	}
}
