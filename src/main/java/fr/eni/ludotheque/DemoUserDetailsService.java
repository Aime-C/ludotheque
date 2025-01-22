package fr.eni.ludotheque;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DemoUserDetailsService implements UserDetailsService {
	
	private PasswordEncoder passwordEncoder;
	
	public DemoUserDetailsService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
	
	//TODO : injection d'un repo 
	
	// Méthode appellée à chaque fois qu'un utilisateur tente de se connecter
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		UserBuilder userBuilder = User.builder();
		if ("toto".equals(username)) {
			String motDePasse = passwordEncoder.encode("toto");
			userBuilder.username(username).password(motDePasse).roles("UTILISATEUR", "ADMIN");
		} else if ("titi".equals(username)) {
			String motDePasse = passwordEncoder.encode("titi");
			userBuilder.username(username).password(motDePasse).roles("UTILISATEUR", "ADMIN");
		}else {
			throw new UsernameNotFoundException(username);
		}

		return userBuilder.build();
	}

}
