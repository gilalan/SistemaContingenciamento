/**
 * 
 */
package br.com.glsoftware.conting.business;

import java.util.List;

import br.com.glsoftware.conting.dao.SchoolingDao;
import br.com.glsoftware.conting.entities.Schooling;
import br.com.glsoftware.conting.exceptions.ElementAlreadyExistException;
import br.com.glsoftware.conting.exceptions.MissingElementException;
import br.com.glsoftware.conting.interfaces.ISchoolingRepository;

/**
 * @author Gilliard
 *
 */
public class SchoolingController {
	
	private ISchoolingRepository schoolingRepository;
	
	public SchoolingController() {
		
		this.schoolingRepository = new SchoolingDao();
	}
	
	//CRUD
	public void create (Schooling schooling) throws ElementAlreadyExistException {
		
		//regra de negocios para criação
		//depois chama o criador no banco
		this.schoolingRepository.create(schooling);
	}
	
	public void update (Schooling schooling) throws MissingElementException {
		
		this.schoolingRepository.update(schooling);
	}
	
	public void delete (Schooling schooling) throws MissingElementException {
		
		this.schoolingRepository.delete(schooling);
	}
	
	public Schooling searchOne (long id) {
		
		return this.schoolingRepository.searchOne(id);
	}
	
	public List<Schooling> search (String name) {
		
		return this.schoolingRepository.search(name);
	}
}
