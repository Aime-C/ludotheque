package fr.eni.ludotheque.dal;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import fr.eni.ludotheque.bo.Client;

@Repository
public class ClientRepositoryImpl implements ClientRepository {

    private static int idxClient = 1;
    private List<Client> clients;

    public ClientRepositoryImpl() {
        clients = new ArrayList<>();
        clients.add(new Client(idxClient++, "Hanks", "Tom", "tom.hanks@email.com", "123-456-7890", "34 Forrest Avenue", "90210", "Los Angeles"));
        clients.add(new Client(idxClient++, "Streep", "Meryl", "meryl.streep@email.com", "234-567-8901", "15 Devil Wears Prada Street", "10001", "New York"));
        clients.add(new Client(idxClient++, "DiCaprio", "Leonardo", "leo.dicaprio@email.com", "345-678-9012", "23 Titanic Boulevard", "33139", "Miami"));
        clients.add(new Client(idxClient++, "Roberts", "Julia", "julia.roberts@email.com", "456-789-0123", "17 Pretty Woman Lane", "90077", "Beverly Hills"));
        clients.add(new Client(idxClient++, "Washington", "Denzel", "denzel.washington@email.com", "567-890-1234", "7 Training Day Road", "20001", "Washington D.C."));
    }

    @Override
    public void add(Client client) {
        idxClient++;
        client.setNoClient(idxClient);
        System.out.println(client);
        this.clients.add(client);
    }

    @Override
    public List<Client> getAll() {
        return clients.stream().collect(Collectors.toList());
    }

    @Override
    public  Optional<Client> getById(int id) {
        return this.clients.stream().filter(c ->  c.getNoClient() == id).findFirst();
    }

    public void update(Client client) {
        Optional<Client> oldClientOptional = getById(client.getNoClient());
        if (oldClientOptional.isPresent()) {
        	
            Client oldClient = oldClientOptional.get();
            BeanUtils.copyProperties(client, oldClient);
            /*
            oldClient.setNom(client.getNom());
            oldClient.setPrenom(client.getPrenom());
            oldClient.setRue(client.getRue());
            oldClient.setCodePostal(client.getCodePostal());
            oldClient.setVille(client.getVille());
            oldClient.setEmail(client.getEmail());
            oldClient.setNoTelephone(client.getNoTelephone());
            */
        }
    }

    public void delete(int id) {
        Optional<Client> clientOptional = getById(id);
        if (clientOptional.isPresent()) {
            Client client = clientOptional.get();
           clients.remove(client);
        }
    }
}
