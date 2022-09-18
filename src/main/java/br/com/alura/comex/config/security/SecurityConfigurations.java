package br.com.alura.comex.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfigurations {
	
	// Configurações de autorização
	@Bean
	public SecurityFilterChain  filterChain(HttpSecurity http) throws Exception {
		String[] recursos = new String[] { "/**.html", "/v2/api-docs", "/webjars/**", "/configuration/**",
				"/swagger-resources/**", "/v3/api-docs/**", "/swagger-ui/**" };

		// http.csrf().disable().authorizeHttpRequests((authz) ->
		// authz.antMatchers(recursos).permitAll());

//		http.authorizeRequests().antMatchers(recursos).permitAll().antMatchers("/**").permitAll().and()
//				.authorizeRequests().anyRequest().permitAll();
		
		// Libera o spring de qualquer autorização...
		http.authorizeRequests().anyRequest().permitAll();

		return http.build();
	}

	// Configurações de recursos estáticos(js, css, imagens, etc...)
//	@Bean
//	public WebSecurityCustomizer webSecurityCustomizer() {
//		return (web) -> web.ignoring().antMatchers("/**.html", "/v2/api-docs", "/webjars/**", "/configuration/**",
//				"/swagger-resources/**", "/v3/api-docs/**", "/swagger-ui/**");
//	}
}