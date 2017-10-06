/**
 * 
 */
package br.com.glsoftware.conting.business;

import br.com.glsoftware.conting.dao.BankDao;
import br.com.glsoftware.conting.dao.BankInfoDao;
import br.com.glsoftware.conting.entities.Bank;
import br.com.glsoftware.conting.entities.BankInfo;
import br.com.glsoftware.conting.exceptions.ElementAlreadyExistException;
import br.com.glsoftware.conting.exceptions.MissingElementException;
import br.com.glsoftware.conting.interfaces.IBankInfoRepository;
import br.com.glsoftware.conting.interfaces.IBankRepository;

/**
 * @author Gilliard
 *
 */
public class BankInfoController {

	private IBankInfoRepository bankInfoRepository;
	
	public BankInfoController(){
		this.bankInfoRepository = new BankInfoDao();
	}
	
	public void create(BankInfo bankInfo) throws ElementAlreadyExistException {
		
		this.bankInfoRepository.create(bankInfo);
	}
	
	public void update(BankInfo bankInfo) throws MissingElementException {
		
		this.bankInfoRepository.update(bankInfo);
	}
	
	public void delete(BankInfo bankInfo) throws MissingElementException {
		
		this.bankInfoRepository.delete(bankInfo);
	}
	
	public BankInfo searchOne(long id){
		
		return this.bankInfoRepository.searchOne(id);
	}

}
