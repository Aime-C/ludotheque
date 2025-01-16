package fr.eni.ludotheque.dal;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import fr.eni.ludotheque.bo.Client;

@Repository
@Primary
public class ClientRepositoryJdbcImpl implements ClientRepository{
	Logger logger = LoggerFactory.getLogger(ClientRepositoryJdbcImpl.class);
	
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private JdbcTemplate jdbcTemplate;

	public ClientRepositoryJdbcImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.jdbcTemplate = namedParameterJdbcTemplate.getJdbcTemplate();
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	
	@Override
	public void add(Client newClient) {
		
		String sql = "insert into clients (nom, prenom, email, no_telephone, rue, code_postal, ville)"
				+ " values (:nom, :prenom, :email, :noTelephone, :rue, :codePostal, :ville)";
		namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(newClient));
		
	}

	@Override
	public List<Client> getAll() {
		String sql = "select no_client as noClient, nom, prenom, email, no_telephone as noTelephone, rue, code_postal as codePostal, ville"
				+ " from clients";
		List<Client> clients = namedParameterJdbcTemplate.query(sql,
				new BeanPropertyRowMapper<>(Client.class));
				
		
		return clients;
	}

	@Override
	public Optional<Client> getById(int id) {
		String sql = "select no_client as noClient, nom, prenom, email, no_telephone as noTelephone, rue, code_postal as codePostal, ville"
				+ " from clients where no_client = ?";
		Client client = null;
		try {
		client = jdbcTemplate.queryForObject(sql,
				new BeanPropertyRowMapper<>(Client.class), id);				
		}catch(DataAccessException exc) {
			exc.printStackTrace();
			logger.warn(exc.getMessage());
		}
				
		return Optional.ofNullable(client);
	}

	@Override
	public void update(Client client) {
		String sql = "update  clients set nom=:nom, prenom=:prenom, email=:email, no_telephone=:noTelephone, "
				+ "rue=:rue, code_postal=:codePostal, ville=:ville where no_client = :noClient";
		int nbRows = namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(client));
		if(nbRows != 1) {
			throw new RuntimeException("La modification du client a échouée : " + client );
		}

		
	}

	@Override
	public void delete(int id) {
		String sql = "delete from clients  where no_client = ? ";
		int nbRows = jdbcTemplate.update(sql, id);
		if(nbRows != 1) {
			throw new RuntimeException("La suppression du client a échouée : id= " +id );
		}
		
	}
	

}
