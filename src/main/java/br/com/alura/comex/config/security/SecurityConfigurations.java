package br.com.alura.comex.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.alura.comex.repository.UsuarioRepository;

@EnableWebSecurity
@Configuration
public class SecurityConfigurations {
	
	@Autowired
	private AutenticacaoService autenticacaoService;
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	// Configurações de autenticação
	@Bean
	public AuthenticationManager authenticationManagerBean(HttpSecurity http) throws Exception {
		AuthenticationManagerBuilder auth = http
				.getSharedObject(AuthenticationManagerBuilder.class);
		auth.userDetailsService(autenticacaoService).passwordEncoder(new BCryptPasswordEncoder());
		return auth.build();
	}

	// Configurações de autorização
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//		String[] recursos = new String[] { "/**.html", "/v2/api-docs", "/webjars/**", "/configuration/**",
//				"/swagger-resources/**", "/v3/api-docs/**", "/swagger-ui/**" };

		// http.csrf().disable().authorizeHttpRequests((authz) ->
		// authz.antMatchers(recursos).permitAll());

//		http.authorizeRequests().antMatchers(recursos).permitAll().antMatchers("/**").permitAll().and()
//				.authorizeRequests().anyRequest().permitAll();

		// Libera o spring de qualquer autorização...
		// http.authorizeRequests().anyRequest().permitAll();

		http.authorizeRequests()
				// .antMatchers(HttpMethod.GET, recursos).permitAll().and()
				// .authorizeRequests()
				//.antMatchers(HttpMethod.POST, "/api/clientes").permitAll()
				.antMatchers(HttpMethod.POST, "/api/produtos").permitAll()
				.antMatchers(HttpMethod.POST, "/api/categorias").permitAll()
				.antMatchers(HttpMethod.POST, "/api/pedidos").permitAll()
				.antMatchers(HttpMethod.GET, "/api/categorias").permitAll()
				.antMatchers(HttpMethod.GET, "/api/produtos").permitAll()
				.antMatchers(HttpMethod.POST, "/auth").permitAll()
				.antMatchers(HttpMethod.GET, "/actuator/**").permitAll()
				.anyRequest().authenticated()
				//.and().formLogin();
				.and().csrf().disable()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and().addFilterBefore(new AutenticacaoViaTokenFilter(tokenService, usuarioRepository), UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}

	// Configurações de recursos estáticos(js, css, imagens, etc...)
	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return (web) -> web.ignoring().antMatchers("/**.html", "/v2/api-docs", "/webjars/**", "/configuration/**",
				"/swagger-resources/**", "/v3/api-docs/**", "/swagger-ui/**");
	}
	
	@Bean
	PasswordEncoder getPasswordEncoder() {
	    return new BCryptPasswordEncoder();
	}
}