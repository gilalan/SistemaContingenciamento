package br.com.glsoftware.conting.interfaces;

import java.util.List;

import br.com.glsoftware.conting.entities.Client;
import br.com.glsoftware.conting.exceptions.MissingElementException;

public interface IClientRepository {
	
	public void create(Client client);
	
	public void update(Client client);
	
	public void delete(Client client);
	
	public Client searchOne(String name) throws MissingElementException;
	
	public List<Client> search(String name);
}
