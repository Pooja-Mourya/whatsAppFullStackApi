package com.panel.config;
//

//import java.util.Arrays;
//import java.util.Collections;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.reactive.CorsConfigurationSource;
//import org.springframework.web.server.ServerWebExchange;
//
//import jakarta.servlet.http.HttpServletRequest;
//
//public class AppConfig {
//
//	@Bean
//	public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
//		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
//				.authorizeHttpRequests(
//						authorize -> authorize.requestMatchers("/api/**").authenticated().anyRequest().permitAll())
//
//				.addFilterBefore(new JwtTokenValidator(), BasicAuthenticationFilter.class).csrf().disable().cors()
//				.configurationSource(
//						(org.springframework.web.cors.CorsConfigurationSource) new CorsConfigurationSource() {
//
//							@Override
//							public CorsConfiguration getCorsConfiguration(ServerWebExchange exchange) {
//								// TODO Auto-generated method stub
//								CorsConfiguration cf = new CorsConfiguration();
//								cf.setAllowCredentials(true);
//								cf.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
//								cf.setAllowedMethods(Collections.singletonList("*"));
//								cf.setExposedHeaders(Arrays.asList("Authorization"));
//								cf.setMaxAge(3600L);
//								return cf;
//							}
//
//						})
//				.and().formLogin().and().httpBasic();
//
//		return http.build();
//	}
//
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
//}

import java.util.Arrays;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.panel.service.CustomUserService;

@Configuration

public class AppConfig {

	@Autowired
	private CustomUserService userDetailService;

//	@Bean
//	private CorsConfiguration corsConfiguration() {
//		CorsConfiguration corsConfig = new CorsConfiguration();
//		corsConfig.setAllowCredentials(true);
//		corsConfig.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
//		corsConfig.setAllowedMethods(Collections.singletonList("*"));
//		corsConfig.setExposedHeaders(Arrays.asList("Authorization"));
//		corsConfig.setMaxAge(3600L);
//		return corsConfig;
//	}
	
	 @Bean
	    public CorsFilter corsFilter() {
	        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	        CorsConfiguration config = new CorsConfiguration();
	        config.addAllowedOrigin("*"); // Allow all origins, you can customize this to your needs
	        config.addAllowedMethod("*"); // Allow all HTTP methods
	        config.addAllowedHeader("*"); // Allow all headers
	        source.registerCorsConfiguration("/**", config);
	        return new CorsFilter(source);
	    }

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http
//		.cors().configurationSource(exchange -> corsConfiguration()).and()
		.addFilterBefore(corsFilter(), CorsFilter.class)
		.csrf(AbstractHttpConfigurer::disable)
				
		.sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		.securityMatcher("/api**").authorizeHttpRequests(
						registry -> registry
						.requestMatchers("/api/user**").permitAll()
						.anyRequest().authenticated());
		return http.build();
	}

	@Bean
	public DaoAuthenticationProvider getAuthenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(userDetailService);
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		return daoAuthenticationProvider;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
