package com.openu.forum;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
@EnableWebMvcSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

 @Autowired
 DataSource dataSource;

 @Autowired
 public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
   auth.jdbcAuthentication().dataSource(dataSource)
   .passwordEncoder(NoOpPasswordEncoder.getInstance())
  .usersByUsernameQuery(
   "select username,password, enabled from users where username=?")
  .authoritiesByUsernameQuery(
   "select username, role from users where username=?");
 } 

 @Override
 protected void configure(HttpSecurity http) throws Exception {
   http
   .authorizeRequests()
   .antMatchers(HttpMethod.POST, "/topics")
   .authenticated()
  .anyRequest()
    .permitAll()
  .and()
    .httpBasic()
  .and()
    .csrf();

 }

}