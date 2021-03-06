package hh.swd20.CostSharing;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import hh.swd20.CostSharing.web.UserDetailServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private UserDetailServiceImpl userDetailsService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
//			.csrf().disable()
//	        .authorizeRequests()
//		        .antMatchers(HttpMethod.GET, "/**").permitAll()
//		        .antMatchers(HttpMethod.POST, "/**").permitAll()
//		        .and()
//		        .headers().frameOptions().disable()
//		        .and()
//		    .authorizeRequests().antMatchers("/h2-console/**").permitAll()
//		        .and()
//		        .csrf().ignoringAntMatchers("/h2-console/**")
//		        .and()
//		        .headers().frameOptions().sameOrigin()
//		        .and()
			.authorizeRequests()
				.anyRequest().authenticated()
//				.anyRequest().permitAll()
				.and()
			.formLogin()
				.defaultSuccessUrl("/trips", true)
				.permitAll()
				.and()
			.logout()
				.permitAll();
	}

	  @Autowired
	    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	    }
	
	
	

}
