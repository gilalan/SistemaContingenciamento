/**
 * 
 */
package br.com.glsoftware.conting.business;

import java.util.List;

import br.com.glsoftware.conting.dao.StateDao;
import br.com.glsoftware.conting.entities.State;
import br.com.glsoftware.conting.exceptions.ElementAlreadyExistException;
import br.com.glsoftware.conting.exceptions.MissingElementException;
import br.com.glsoftware.conting.interfaces.IStateRepository;

/**
 * @author Gilliard
 *
 */
public class StateController {

	private IStateRepository stateRepository;
	
	public StateController() {
		
		this.stateRepository = new StateDao();
	}
	
	//CRUD
	public void create (State state) throws ElementAlreadyExistException {
		
		//regra de negocios para criação
		//depois chama o criador no banco
		this.stateRepository.create(state);
	}
	
	public void update (State state) throws MissingElementException {
		
		this.stateRepository.update(state);
	}
	
	public void delete (State state) throws MissingElementException {
		
		this.stateRepository.delete(state);
	}
	
	public State searchOne (String abbrev) {
		
		return this.stateRepository.searchOne(abbrev);
	}
	
	public List<State> search (String name) {
		
		return this.stateRepository.search(name);
	}
}
