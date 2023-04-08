package com.softagil.hraptgatewayzuul.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableResourceServer //configurando que o projet sera um Resource Server
public class ResourceServiceConfig extends ResourceServerConfigurerAdapter{

	
	@Autowired
	private JwtTokenStore tokenStore;
	

	//CONFIGURANDO AS ROTAS
	private static final String[] PUBLIC = { "/api/hr-oauth/oauth/token" };
	private static final String[] OPERATOR = { "/api/hr-worker/**" };
	private static final String[] ADMIN = { "/api/hr-payroll/**", "/api/hr-user/**", "/actuator/**", "/apt/hr-worker/actuator/**", "/apt/hr-oauth/actuator/**" };
	
	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.tokenStore(tokenStore);
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers(PUBLIC).permitAll()
		.antMatchers(HttpMethod.GET, OPERATOR).hasAnyRole("OPERATOR","ADMIN") //Aquis são os perfis dos user no BD e não a variavel static da classe
		.antMatchers(ADMIN).hasRole("ADMIN")
		.anyRequest().authenticated();
		
	}
	
	
	
}
