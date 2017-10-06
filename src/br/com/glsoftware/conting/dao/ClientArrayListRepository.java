package br.com.glsoftware.conting.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.glsoftware.conting.entities.Client;
import br.com.glsoftware.conting.exceptions.MissingElementException;
import br.com.glsoftware.conting.interfaces.IClientRepository;

public class ClientArrayListRepository implements IClientRepository{
	
	private List<Client> clients;
	
	public ClientArrayListRepository() {
		
		this.clients = new ArrayList<Client>();
	}
	
	public List<Client> getClients() {
		
		return this.clients;
	}

	public void setClients(ArrayList<Client> clients) {
		
		this.clients = clients;
	}
	
	@Override
	public void create(Client client) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Client client) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Client client) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Client searchOne(String name) throws MissingElementException {
		
		Client clienteEncontrado = null;
		
		for (Client c : this.clients) {
			if (name.equals(c.getFantasyName()))
				clienteEncontrado = c;
		}
		
		if (clienteEncontrado != null)
			return clienteEncontrado;
		else
			throw new MissingElementException("Cliente não encontrado no banco de dados");
		
	}

	@Override
	public List<Client> search(String name) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
