package fr.eni.ludotheque;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests((requests) -> requests
				.requestMatchers("/", "/login", "/images/*", "/chiffrer").permitAll()
				.requestMatchers(HttpMethod.GET, "/jeux", "/jeux/*/afficher").permitAll()
				//.requestMatchers("/*/hello").hasAnyRole(null)
				.requestMatchers(HttpMethod.GET,
						"/*/ajouter", "/*/modifier", "/*/supprimer","/*/enregistrer").authenticated()
				.requestMatchers(HttpMethod.POST).authenticated()
				.anyRequest().authenticated()
			)
			.formLogin((form) -> form
				.loginPage("/login")
				.permitAll()
			)
			.logout((logout) -> logout.permitAll()
					.logoutSuccessUrl("/"));

		return http.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		//return new BCryptPasswordEncoder();
		//NoOpPasswordEncoder si on ne veut pas chiffrer les mots de passe !!
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
	
//	@Bean
//	public UserDetailsService userDetailsService() {
//		UserDetails user =
//			 User.withDefaultPasswordEncoder()
//				.username("user")
//				.password("password")
//				.roles("USER")
//				.build();
//
//		return new InMemoryUserDetailsManager(user);
//	}
}
