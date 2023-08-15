package com.example.WebAppWithVaadin;

import com.example.WebAppWithVaadin.UI.LoginView;
import com.vaadin.flow.spring.security.VaadinWebSecurity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@SpringBootApplication
public class WebAppWithVaadinApplication extends VaadinWebSecurity {

	public static void main(String[] args) {

		SpringApplication.run(WebAppWithVaadinApplication.class, args);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		super.configure(http);
		setLoginView(http, LoginView.class);
	}

	@Bean
	UserDetailsService userDetailsService() throws Exception{
		return new InMemoryUserDetailsManager(
				User.withUsername("Stephen")
						.password("{noop}owino")
						.roles("ADMIN")
						.build(),
				User.withUsername("Beryl")
						.password("{noop}owino")
						.roles("ADMIN")
						.build()
		);
	}
}
