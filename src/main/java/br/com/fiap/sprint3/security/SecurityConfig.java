package br.com.fiap.sprint3.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import jakarta.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http       	
        		.csrf(csrf -> csrf.disable()) //permite requisições via Postman
        		.authorizeHttpRequests(       				
        				auth -> {   
        					// regras para web       					
        					auth.requestMatchers(HttpMethod.GET, "/web/*/listar").hasAnyRole("ADMIN", "USER");
        					auth.requestMatchers("/web/*/cadastrar").hasRole("ADMIN");
        					auth.requestMatchers("/web/*/salvar").hasRole("ADMIN");
        					auth.requestMatchers("/web/*/editar/*").hasRole("ADMIN");
        					auth.requestMatchers("/web/*/excluir/*").hasRole("ADMIN");
        					
        					// regras para api        					
        	                auth.requestMatchers(HttpMethod.POST, "/api/**").hasRole("ADMIN");
        	                auth.requestMatchers(HttpMethod.PUT, "/api/**").hasRole("ADMIN");
        	                auth.requestMatchers(HttpMethod.DELETE, "/api/**").hasRole("ADMIN");
        	                auth.requestMatchers(HttpMethod.GET, "/api/**").hasAnyRole("ADMIN", "USER");
        	                
        	                auth.anyRequest().authenticated();
        					
        				})
        		.httpBasic(Customizer.withDefaults()) //login via Basic Auth

        		.formLogin(form -> form //login via formulario na web
                        .defaultSuccessUrl("/web/sensores/listar", true) 
                        .permitAll()      
                )
        		.exceptionHandling(exception -> exception
        			    .accessDeniedHandler((request, response, accessDeniedException) -> {
        			        String uri = request.getRequestURI();

        			        if (uri.startsWith("/api/")) {
        			            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Acesso negado"); // response com status code 403 Forbidden
        			        } else {
        			            response.sendRedirect("/403"); // retorna página de Acesso Negado
        			        }
        			    })
        		)
        		.logout(Customizer.withDefaults())
        		.build();
    }

	@Bean
	public PasswordEncoder passwordEncoder() {
	    return PasswordEncoderFactories.createDelegatingPasswordEncoder(); // senha salva em texto puro
	}


}
