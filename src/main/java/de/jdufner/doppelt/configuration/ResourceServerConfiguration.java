package de.jdufner.doppelt.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

  @Override
  public void configure(final HttpSecurity http) throws Exception {
    // @formatter:off
    http
      .authorizeRequests()
      .antMatchers("/manage/health/**").permitAll()
      .antMatchers("/manage/**").hasAuthority("Admin")
      .antMatchers("/api/**").hasAuthority("User")
      .antMatchers("/ui/**").hasAuthority("User")
    ;
    // @formatter:on
  }

}
