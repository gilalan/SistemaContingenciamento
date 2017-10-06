package br.com.glsoftware.conting.interfaces;

import br.com.glsoftware.conting.entities.BankInfo;
import br.com.glsoftware.conting.exceptions.ElementAlreadyExistException;
import br.com.glsoftware.conting.exceptions.MissingElementException;

public interface IBankInfoRepository {
	
	public void create(BankInfo bankInfo) throws ElementAlreadyExistException;
	
	public void update(BankInfo bankInfo) throws MissingElementException;
	
	public void delete (BankInfo bankInfo) throws MissingElementException;
	
	public BankInfo searchOne(long id);
}
