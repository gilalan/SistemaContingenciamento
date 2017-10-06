package br.com.glsoftware.conting.interfaces;

import br.com.glsoftware.conting.entities.Bank;
import br.com.glsoftware.conting.exceptions.ElementAlreadyExistException;
import br.com.glsoftware.conting.exceptions.MissingElementException;

public interface IBankRepository {
	
	public void create(Bank bank) throws ElementAlreadyExistException;
	
	public void update(Bank bank) throws MissingElementException;
	
	public void delete (Bank bank) throws MissingElementException;
	
	public Bank searchOne(String code);
}
