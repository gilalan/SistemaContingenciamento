/**
 * 
 */
package br.com.glsoftware.conting.business;

import java.util.ArrayList;
import java.util.List;

import br.com.glsoftware.conting.entities.Client;
import br.com.glsoftware.conting.interfaces.IClientRepository;

/**
 * @author Gilliard
 *
 */
public class ClientController {
	
	private IClientRepository clientRep;
	
	public ClientController () {
		this.clientRep = null;//new JDBCconn - pesquisar
	}
	
	public void create (Client client) {
		
		//quaisquer testes referentes ao negócio do sistema
		//para que seja efetuada a inserção
		if (1 > 4) {
			if (2 < 3)
				this.clientRep.create(client);
		} else {
			
		}
	}
	
	public void update (Client client) {
		
	}
	
	public void delete (Client client) {
		
	}
	
	public Client searchOne (String name) {
		
		return null;
	}
	
	public List<Client> search (String name) {
		
		return new ArrayList<Client>();
	}
}
