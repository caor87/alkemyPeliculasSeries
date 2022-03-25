package com.challenge.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.challenge.filter.CostumAuthenticationFilter;
import com.challenge.filter.CustomAuthorizationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	private final UserDetailsService userDetailsService;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//super.configure(auth);
		//En donde puede buscar los usuarios, cuando los usuarios intentan iniciar sesion en la apliacion
		//auth.inMemoryAuthentication();
		// Para hacer mis propias solicitudes y luego anular la configuracion del administrador de detalle del usuario jdbc
		//auth.jdbcAuthentication();
		//Acepta un servicio de detalle del usuario, llama al servicio de detalle del usuario
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
	}

	//Configurando seguridad HTTP
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//cambio de end point de seguridad
		CostumAuthenticationFilter costumAuthenticationFilter = new CostumAuthenticationFilter(authenticationManagerBean());
		costumAuthenticationFilter.setFilterProcessesUrl("/auth/login");
		//costumAuthenticationFilter.setFilterProcessesUrl("/api/login");
		//Se desabilita la proteccion csrf
		http.csrf().disable();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		//http.authorizeRequests().antMatchers("/auth/login/**").permitAll();
		http.authorizeRequests().antMatchers("/auth/login/**","/auth/token/refresh/**").permitAll();
		http.authorizeRequests().antMatchers(HttpMethod.GET,"/auth/user/**").hasAnyAuthority("ROLE_USER");
		http.authorizeRequests().antMatchers(HttpMethod.POST,"/auth/register/**").permitAll();
		http.authorizeRequests().antMatchers(HttpMethod.POST,"/character/save/**").hasAnyAuthority("ROLE_ADMIN");
		http.authorizeRequests().antMatchers(HttpMethod.POST,"/movieserie/save/**").hasAnyAuthority("ROLE_ADMIN");
		http.authorizeRequests().antMatchers(HttpMethod.POST,"/genre/save/**").hasAnyAuthority("ROLE_ADMIN");
		http.authorizeRequests().antMatchers(HttpMethod.DELETE,"/character/{id}/**").hasAnyAuthority("ROLE_ADMIN");
		http.authorizeRequests().antMatchers(HttpMethod.PUT,"/character/{id}/**").hasAnyAuthority("ROLE_ADMIN");
		http.authorizeRequests().antMatchers(HttpMethod.GET,"/character/**").hasAnyAuthority("ROLE_USER");
		http.authorizeRequests().antMatchers(HttpMethod.GET,"/characters/**").hasAnyAuthority("ROLE_USER");
		//http.authorizeRequests().antMatchers(HttpMethod.GET,"/sendMail/**").hasAnyAuthority("ROLE_USER");
		http.authorizeRequests().antMatchers(HttpMethod.GET,"/mail/send/**").hasAnyAuthority("ROLE_USER");
		
		http.authorizeRequests().antMatchers("/").permitAll().antMatchers("/h2-console/**").permitAll();
		http.authorizeRequests().antMatchers("/").permitAll().antMatchers("/sendMail/{}/**").permitAll();
		http.headers().frameOptions().disable();
		//http.authorizeRequests().anyRequest().permitAll();
		//Se permite que todos puedan acceder a la aplicacion
		http.authorizeRequests().anyRequest().authenticated();
		//Se aplica un filtro para saber quien ingresa a la aplicacion (Autenticacion)
		http.addFilter(costumAuthenticationFilter);
		//Se interceptan todas las solicitudes(Autorizacion)
		http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception{
		return super.authenticationManagerBean();
	}
	
	@Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/v2/api-docs",
        						   "/sendMail/**",
                                   "/configuration/ui",
                                   "/swagger-resources/**",
                                   "/configuration/security",
                                   "/swagger-ui.html",
                                   "/webjars/**");
    }

}
