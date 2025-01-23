package fr.eni.ludotheque.dal;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import fr.eni.ludotheque.bo.Utilisateur;

@Repository
public class UtilisateurRepositoryImpl implements UtilisateurRepository {

	Logger logger = LoggerFactory.getLogger(UtilisateurRepositoryImpl.class);

	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private JdbcTemplate jdbcTemplate;

	public UtilisateurRepositoryImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.jdbcTemplate = namedParameterJdbcTemplate.getJdbcTemplate();
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	@Override
	public Optional<Utilisateur> findUtilisateurByEmail(String email) {
		String sql = "select no_utilisateur, email, mot_de_passe, role from utilisateurs where email=?";
		Utilisateur utilisateur = this.jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Utilisateur.class),
				email);
		return Optional.ofNullable(utilisateur);
	}

}
