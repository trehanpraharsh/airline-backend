//package com.example.api_gateway;
//
//import javax.sql.DataSource;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
//import org.springframework.security.provisioning.JdbcUserDetailsManager;
//import org.springframework.security.provisioning.UserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//public class AppSecurity {
//	
//	@Autowired
//	private DataSource dataSource;
//	
//	@Bean
//	public UserDetailsManager authenticateUsers() {
//		JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
//		users.setUsersByUsernameQuery("select secureuemail,secureupassword from secureuser where secureuemail=?");
//		users.setAuthoritiesByUsernameQuery("select secureuemail,roles from secureuser where secureuemail=?");
//		return users;
//		
//	}
//
//	@Bean
//	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
//		http.authorizeHttpRequests(auth -> auth
//				.requestMatchers("/search").permitAll()
////				.requestMatchers("/welcome").authenticated()
//				.requestMatchers("/admin").hasAuthority("ADMIN")
//				.requestMatchers("/users").hasAuthority("USER")
//				.requestMatchers("/owner").hasAuthority("OWNER")
//				.requestMatchers("/flights").hasAnyAuthority("OWNER", "USER")
//				.anyRequest().authenticated())
//		.formLogin(formLogin -> formLogin.defaultSuccessUrl("/search", true))
//		.logout(logout -> logout.logoutUrl("/logout"))
//		.exceptionHandling(eh -> eh.accessDeniedPage("/search"));
//		
//		return http.build();
//	}
//	
//}




package com.example.api_gateway;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
//@EnableAutoConfiguration(exclude = ManagementWebSecurityAutoConfiguration.class)
public class AppSecurity {

    @Autowired
    private DataSource dataSource;

    @Bean
    public UserDetailsManager authenticateUsers() {
    	System.out.println("Reading from auth users");
        JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
        users.setUsersByUsernameQuery("select secureuemail,secureupassword from secureuser where secureuemail=?");
        users.setAuthoritiesByUsernameQuery("select secureuemail,roles from secureuser where secureuemail=?");
        return users;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/search").permitAll()
                .requestMatchers("/admin").hasAuthority("ADMIN")
                .requestMatchers("/users").hasAuthority("USER")
                .requestMatchers("/owner").hasAuthority("OWNER")
                .requestMatchers("/flights").hasAnyAuthority("OWNER", "USER")
                .anyRequest().authenticated())
            .formLogin(formLogin -> formLogin.defaultSuccessUrl("/search", true))
            .logout(logout -> logout.logoutUrl("/logout"))
            .exceptionHandling(eh -> eh.accessDeniedPage("/search"));

        return http.build();
    }
}

