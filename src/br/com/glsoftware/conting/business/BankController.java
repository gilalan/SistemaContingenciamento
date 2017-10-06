package br.com.glsoftware.conting.business;

import br.com.glsoftware.conting.dao.BankDao;
import br.com.glsoftware.conting.entities.Bank;
import br.com.glsoftware.conting.exceptions.ElementAlreadyExistException;
import br.com.glsoftware.conting.exceptions.MissingElementException;
import br.com.glsoftware.conting.interfaces.IBankRepository;

public class BankController {
	
	private IBankRepository bankRepository;
	
	public BankController(){
		this.bankRepository = new BankDao();
	}
	
	public void create(Bank bank) throws ElementAlreadyExistException {
		
		this.bankRepository.create(bank);
	}
	
	public void update(Bank bank) throws MissingElementException {
		
		this.bankRepository.update(bank);
	}
	
	public void delete(Bank bank) throws MissingElementException {
		
		this.bankRepository.delete(bank);
	}
	
	public Bank searchOne(String code){
		
		return this.bankRepository.searchOne(code);
	}
}
