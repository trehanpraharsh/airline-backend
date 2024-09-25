package com.example.api_gateway;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class AppSecurity {
	
//	@Autowired
//	private DataSource dataSource;
	
//	@Bean
//	public UserDetailsManager authenticateUsers() {
//		JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
//		users.setUsersByUsernameQuery("select username,password,enabled from users where username=?");
//		users.setAuthoritiesByUsernameQuery("select username,roles, enabled from users where username=?");
//		return users;
//		
//	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		http.authorizeHttpRequests(auth -> auth
				.requestMatchers("/search").permitAll()
				.requestMatchers("/welcome").authenticated()
				.requestMatchers("/admin").hasAuthority("ADMIN")
				.requestMatchers("/users").hasAuthority("USER")
				.requestMatchers("/owner").hasAuthority("OWNER")
				.requestMatchers("/common").hasAnyAuthority("OWNER", "USER", "QA")
				.anyRequest().authenticated())
		.formLogin(formLogin -> formLogin.defaultSuccessUrl("/landingpage", true))
		.logout(logout -> logout.logoutUrl("/logout"))
		.exceptionHandling(eh -> eh.accessDeniedPage("/ad"));
		
		return http.build();
	}
	
}
