/**
 * 
 */
package br.com.glsoftware.conting.business;

import java.util.List;

import br.com.glsoftware.conting.dao.FunctionDao;
import br.com.glsoftware.conting.entities.Function;
import br.com.glsoftware.conting.exceptions.ElementAlreadyExistException;
import br.com.glsoftware.conting.exceptions.MissingElementException;
import br.com.glsoftware.conting.interfaces.IFunctionRepository;

/**
 * @author Gilliard
 *
 */
public class FunctionController {

	private IFunctionRepository functionRepository;
	
	public FunctionController() {
		
		this.functionRepository = new FunctionDao();
	}
	
	//CRUD
	public void create (Function function) throws ElementAlreadyExistException {
		
		//regra de negocios para criação
		//depois chama o criador no banco
		this.functionRepository.create(function);
	}
	
	public void update (Function function) throws MissingElementException {
		
		this.functionRepository.update(function);
	}
	
	public void delete (Function function) throws MissingElementException {
		
		this.functionRepository.delete(function);
	}
	
	public Function searchOne (String cbo) {
		
		return this.functionRepository.searchOne(cbo);
	}
	
	public List<Function> search (String name) {
		
		return this.functionRepository.search(name);
	}
}
