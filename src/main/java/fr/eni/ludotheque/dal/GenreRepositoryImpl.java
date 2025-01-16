package fr.eni.ludotheque.dal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import fr.eni.ludotheque.bo.Genre;

@Repository
@Primary
public class GenreRepositoryImpl implements GenreRepository {
	Logger logger = LoggerFactory.getLogger(GenreRepositoryImpl.class);
	
    private JdbcTemplate jdbcTemplate;
    
    class GenreRowMapper implements RowMapper<Genre> {
        @Override
        public Genre mapRow(ResultSet rs, int rowNum) throws SQLException {
            Genre genre = new Genre();
            genre.setNoGenre(rs.getInt("no_genre"));
            genre.setLibelle(rs.getString("libelle"));
            return genre;
        }
    }
    
    public GenreRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
	@Override
	public void add(Genre entity) {
		throw new RuntimeException("Not implemented !!!");
	}
	@Override
	public List<Genre> getAll() {
		String sql = "SELECT no_genre, libelle FROM genres";
        List<Genre> genres = jdbcTemplate.query(sql, new  GenreRowMapper());
        return genres;
	}
	
	@Override
	public Optional<Genre> getById(int noGenre) {
		String sql = "SELECT no_genre, libelle FROM genres where no_genre=?";
		Genre genre = null;
		try {
			genre = jdbcTemplate.queryForObject(sql, new  GenreRowMapper(), noGenre);
		}catch(IncorrectResultSizeDataAccessException exc) {
			exc.printStackTrace();
			logger.warn("Le genre " + noGenre + " n'a pas été trouvé.");
		}
		
		return Optional.ofNullable(genre);
	}
	@Override
	public void update(Genre entity) {
		throw new RuntimeException("Not implemented !!!");
		
	}
	@Override
	public void delete(int id) {
		throw new RuntimeException("Not implemented !!!");
		
	}
   
    
}
