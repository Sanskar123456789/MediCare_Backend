package com.example.demo.Security;

//import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.CorsConfigurationSource;
//import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import com.example.demo.JWTFilter;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private JWTFilter filter;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf()
			.disable()
			.authorizeRequests()
			.antMatchers("/newProduct").hasRole("ADMIN")
			.antMatchers("/updateProduct").hasRole("ADMIN")
			.antMatchers("/updateProductNotImage").hasRole("ADMIN")
			.antMatchers("/delteProduct").hasRole("ADMIN")
			.antMatchers("/updateProductType").hasRole("ADMIN")
			.antMatchers("/delteProductType").hasRole("ADMIN")
			.antMatchers("/fileCheck").hasRole("ADMIN")
			.antMatchers("/allOrders").hasRole("ADMIN")
			.antMatchers("/allUsers").hasRole("ADMIN")
			.antMatchers("/user").hasAnyRole("USER","ADMIN")
			.antMatchers("/onlinePayment").hasAnyRole("USER","ADMIN")
			.antMatchers("/getOrder/*").hasAnyRole("USER","ADMIN")
			.antMatchers(
						 "/authenticate",
						 "/newUser",
						 "/getProducts",
						 "/getOneProduct",
						 "/getProductsType",
						 "/getOneProductType",
						 "/checkOrder",
						 "/getOrder/*"
						 )
			.permitAll()
			.anyRequest()
			.authenticated()
			.and()
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
			http.addFilterBefore(filter,UsernamePasswordAuthenticationFilter.class);
	}
//	
//	 @Bean
//	    CorsConfigurationSource corsConfigurationSource() {
//	        CorsConfiguration configuration = new CorsConfiguration();
//	        configuration.setAllowedOrigins(Arrays.asList("*"));
//	        configuration.setAllowedMethods(Arrays.asList("*"));
//	        configuration.setAllowedHeaders(Arrays.asList("*"));
//	        configuration.setAllowCredentials(true);
//	        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//	        source.registerCorsConfiguration("/**", configuration);
//	        return (CorsConfigurationSource) source;
//	    }

//	@Bean
//	@Override
//	public AuthenticationManager authenticationManagerBean() throws Exception {
//		// TODO Auto-generated method stub
//		return super.authenticationManagerBean();
//	}
	
	
	
	
}
