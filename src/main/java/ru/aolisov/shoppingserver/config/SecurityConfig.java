package ru.aolisov.shoppingserver.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.io.support.ResourcePropertySource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.userdetails.UserDetailsServiceConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.context.support.StandardServletEnvironment;

import java.util.*;

/**
 * Created by Alex on 2/23/2016.
 */

@Configuration
@PropertySource(value = { "classpath:users.properties" })
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // Don't know yet how to use it for @PropertySource annotation.
    private final String USERS_PROPERTIES_FILE = "users.properties";

    private static class UserProperty {
        private String username = "";
        private String password = "";
        private String roles = "";

        public UserProperty(String username, String password, String roles) {
            this.username = username;
            this.password = password;
            this.roles = roles;
        }

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }

        public String getRoles() {
            return roles;
        }
    }

    @Autowired
    private Environment environment;

    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        for(UserProperty userProperty: getUserProperties()) {
            auth.inMemoryAuthentication()
                    .withUser(userProperty.getUsername())
                    .password(userProperty.getPassword())
                    .roles(userProperty.getRoles());
        }

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/static**","/static/**","/login**","/404**").permitAll()
                .antMatchers("/**").access("hasRole('ADMIN')")
                .and().formLogin().loginPage("/login")
                .usernameParameter("username").passwordParameter("password");

    }

    private List<UserProperty> getUserProperties() {
        //As Spring doc doesn't tell about JavaConfig ways to use properties file for user authentication,
        // we emulate it in simple way.

        List<UserProperty> result = new ArrayList<>();

        String userSourcePath = String.format("class path resource [%s]", USERS_PROPERTIES_FILE);
        Properties userSource = (Properties) ((StandardServletEnvironment) environment).getPropertySources().get(userSourcePath).getSource();

        for(String userName: userSource.stringPropertyNames()) {
            String[] userDetails = userSource.getProperty(userName).split(",");
            result.add(new UserProperty(userName, userDetails[0],userDetails[1]));
        }

        return result;
    }
}
