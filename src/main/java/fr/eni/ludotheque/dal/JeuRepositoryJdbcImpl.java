package fr.eni.ludotheque.dal;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fr.eni.ludotheque.bo.Exemplaire;
import fr.eni.ludotheque.bo.Genre;
import fr.eni.ludotheque.bo.Jeu;

@Repository
@Primary
public class JeuRepositoryJdbcImpl implements JeuRepository{
	Logger logger = LoggerFactory.getLogger(JeuRepositoryJdbcImpl.class);
	
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private JdbcTemplate jdbcTemplate;

	public JeuRepositoryJdbcImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.jdbcTemplate = namedParameterJdbcTemplate.getJdbcTemplate();
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	
	record JeuGenreDto (Integer noJeu, Integer noGenre) {};
	
	@Override
	@Transactional
	public void add(Jeu newJeu) {
		logger.debug("avant insert into jeux...");
		String sql = "insert into jeux (titre, reference, description, tarif_journee, ageMin, duree)"
				+ " values (:titre, :reference, :description, :tarifJournee, :ageMin, :duree)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(newJeu), keyHolder, new String[]{"no_jeu"});
		Integer cleJeu = keyHolder.getKeyAs(Integer.class);
		logger.debug("apres insert into jeux...cleJeu="+cleJeu);
		
		List<JeuGenreDto> jeuGenreDtos = newJeu.getGenres().stream().map(genre->new JeuGenreDto(cleJeu, genre.getNoGenre())).toList();
		
		logger.debug("avant insert into jeux_genres...");
		sql = "insert into jeux_genres ( no_jeu, no_genre) values (:noJeu,:noGenre)";
	    SqlParameterSource[] batchArgs = SqlParameterSourceUtils.createBatch(jeuGenreDtos);
	    this.namedParameterJdbcTemplate.batchUpdate(sql, batchArgs);
	    logger.debug("après insert into jeux_genres...");
	    
	}

	@Override
	public List<Jeu> getAll() {
		String sql = "select no_jeu, titre, reference, description, tarif_journee, ageMin, duree"
				+ " from jeux";
		List<Jeu> jeux = namedParameterJdbcTemplate.query(sql,
				new BeanPropertyRowMapper<>(Jeu.class));
				
		
		return jeux;
	}

	@Override
	public Optional<Jeu> getById(int id) {
		String sql = "select no_jeu, titre, reference, description, tarif_journee, ageMin, duree"
				+ " from jeux where no_jeu = ?";
		Jeu jeu = jdbcTemplate.queryForObject(sql,
				new BeanPropertyRowMapper<>(Jeu.class), id);

		List<Genre> genres = getGenresByNoJeu(jeu.getNoJeu());
		jeu.setGenres(genres);
		
		return Optional.ofNullable(jeu);
	}

	@Override
	@Transactional
	public void update(Jeu jeu) {
		String sql = "update  jeux set titre=:titre, reference=:reference, description=:description, "
				+ "tarif_journee=:tarifJournee, "
				+ "ageMin=:ageMin, duree=:duree where no_jeu = :noJeu";
		int nbRows = namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(jeu));
		if(nbRows != 1) {
			throw new RuntimeException("La modification du jeu a échouée : " + jeu );
		}

		//Suppression des genres existants
		sql = "delete from jeux_genres  where no_jeu = ? ";
		jdbcTemplate.update(sql, jeu.getNoJeu());
		
		//Ajout des nouveaux  genres
		List<JeuGenreDto> jeuGenreDtos = jeu.getGenres().stream().map(genre->new JeuGenreDto(jeu.getNoJeu(), genre.getNoGenre())).toList();
		sql = "insert into jeux_genres ( no_jeu, no_genre) values (:noJeu,:noGenre)";
	    SqlParameterSource[] batchArgs = SqlParameterSourceUtils.createBatch(jeuGenreDtos);
	    this.namedParameterJdbcTemplate.batchUpdate(sql, batchArgs);

	}

	@Override
	public void delete(int noJeu) {
		String sql = "delete from jeux_genres  where no_jeu = ? ";
		jdbcTemplate.update(sql, noJeu);
		
		sql = "delete from exemplaires_jeux where no_jeu = ? ";
		jdbcTemplate.update(sql, noJeu);
		
		sql = "delete from jeux  where no_jeu = ? ";
		int nbRows = jdbcTemplate.update(sql, noJeu);
		if(nbRows != 1) {
			throw new RuntimeException("La suppression du jeu a échouée : no_jeu= " +noJeu );
		}
		
	}
	
	@Override
	public List<Exemplaire> getAllExemplaires(int noJeu) {
		String sql = "SELECT ej.no_exemplaire_jeu, ej.no_jeu,ej.codebarre, ej.louable FROM exemplaires_jeux as ej INNER JOIN jeux ON jeux.no_jeu = ej.no_jeu WHERE jeux.no_jeu = ? ORDER BY no_exemplaire_jeu ASC";
		List<Exemplaire> exemplaires = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Exemplaire.class), noJeu);
				
		return exemplaires;
		
	}
	
	@Override
	public void deleteExemplaire(int noExemplaire) {
		String sql = "DELETE FROM exemplaires_jeux WHERE no_exemplaire_jeu =  ? ";
		jdbcTemplate.update(sql, noExemplaire);
		
		sql = "DELETE FROM exemplaires_jeux WHERE no_exemplaire_jeu =  ?";
		int nbRows = jdbcTemplate.update(sql, noExemplaire);
		
	}
	
	@Override
	public void updateExemplaire(Exemplaire exemplaire) {
		String sql = "UPDATE exemplaires_jeux SET codebarre = ?, louable = ? WHERE no_exemplaire_jeu = ?";
		jdbcTemplate.update(sql, exemplaire.getCodebarre(), exemplaire.getLouable(), exemplaire.getNo_exemplaire_jeu());
		
	}
	
	@Override
	public void addExemplaire(Exemplaire exemplaire) {
		String sql = "INSERT INTO exemplaires_jeux (no_jeu, codebarre, louable) VALUES (?,?,?)";
		jdbcTemplate.update(sql, exemplaire.getNo_jeu() ,exemplaire.getCodebarre(), exemplaire.getLouable());
		
	}

	@Override
	public Optional<Exemplaire> getExemplaireById(int id) {
		String sql = "SELECT * FROM public.exemplaires_jeux WHERE no_exemplaire_jeu = ?";
		Exemplaire exemplaire = jdbcTemplate.queryForObject(sql,
				new BeanPropertyRowMapper<>(Exemplaire.class), id);

		return Optional.ofNullable(exemplaire);
	}
	@Override
	public List<Genre> getGenresByNoJeu(Integer noJeu) {		
		String sql = "select genres.no_genre as noGenre, libelle "
				+ " from jeux_genres inner join genres on  jeux_genres.no_genre = genres.no_genre"
				+ " where jeux_genres.no_jeu = ? ";
		List<Genre> genres = jdbcTemplate.query(sql,
				new BeanPropertyRowMapper<>(Genre.class), noJeu);
		
		return genres;
	}
	

}
