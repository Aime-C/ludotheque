package fr.eni.ludotheque.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import fr.eni.ludotheque.bo.Client;
import fr.eni.ludotheque.dal.ClientRepository;
import fr.eni.ludotheque.exceptions.ClientNotFoundException;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public void add(Client client) {
        clientRepository.add(client);
    }

    @Override
    public List<Client> getAll() {
        return clientRepository.getAll();
    }

    @Override
    public Optional<Client> getById(int id) {
        return clientRepository.getById(id);
    }

    public void update(Client client) {
    	 Optional<Client> clientOpt = getById(client.getNoClient());
         if (clientOpt.isPresent()) {
        	 clientRepository.update(client);	 
         }else {
        	 //TODO gerer l'erreur
        	 throw new ClientNotFoundException();
         }
        
    }

    public void delete(int id) {
        clientRepository.delete(id);
    }

	@Override
	public void save(Client entity) {
		
		if(entity.getNoClient()==null) {
			this.add(entity);
			return;
		}
		this.update(entity);
		
	}
}
