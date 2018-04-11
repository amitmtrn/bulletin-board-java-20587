package com.openu.forum;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * The security configurations of the application.
 * 
 * @author amit and nir
 *
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private PasswordEncoder passwordEncoder;
  
  /**
   * Uses BCryptPasswordEncoder to hash a password
   * @return the hash password
   */
  @Bean
  public PasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder();
  }

 @Autowired
 DataSource dataSource;

 /**
  * Performs a login authentication
  * @param auth - authentication manager builder 
  * @throws Exception
  */
 @Autowired
 public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
   auth.jdbcAuthentication()
        .dataSource(dataSource)
        .passwordEncoder(passwordEncoder)
        .usersByUsernameQuery("select username, password, enabled from users where username=?")
        .authoritiesByUsernameQuery("select username, role from users where username=?");
 } 

 
 @Override
 protected void configure(HttpSecurity http) throws Exception {
   http
   .authorizeRequests()
   .antMatchers(HttpMethod.POST, "/api/users").permitAll()			// permission to all users 
   .antMatchers(HttpMethod.GET, "/api/topics").permitAll()			// permission to all users
   .antMatchers(HttpMethod.GET, "/api/subjects").permitAll()		// permission to all users
   .antMatchers(HttpMethod.GET, "/api/users/me").authenticated()	// permission only to login users
   .antMatchers(HttpMethod.POST, "/api/topics").authenticated()		// permission only to login users
   .antMatchers(HttpMethod.POST, "/api/subjects").hasRole("ADMIN")	// permission only to an admin
  .anyRequest()
    .permitAll()
  .and()
    .httpBasic();

  http.cors();
  http.csrf().disable();

 }

}