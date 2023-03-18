package com.myprojects.dogs.config;

import com.myprojects.dogs.filter.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfiguration {
    @Autowired
UserDetailsService userDetailsService;

    @Autowired
    JwtRequestFilter jwtRequestFilter;

    @Bean
    protected UserDetailsService userDetailsService(){
        return userDetailsService;
    }

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws  Exception{
        http.csrf().disable().authorizeRequests().requestMatchers(new AntPathRequestMatcher("/api/dogs")).hasAnyRole("ADMIN","USER").
                requestMatchers(new AntPathRequestMatcher("/api/dogs/**")).hasAnyRole("ADMIN","USER").
                requestMatchers(new AntPathRequestMatcher("/authenticate")).permitAll().
                anyRequest().authenticated().and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
    @Bean
    protected PasswordEncoder encoder(){

        //plain text encoder -> won't do any encoding
        return NoOpPasswordEncoder.getInstance();
        //bcrypt encoder -> do actual encoding, popular algorithm but there are other encoders you can use

        //return new BCryptPasswordEncoder();

    }
    // load the encoder & user details service that are needed for spring security to do authentication
    @Bean
    protected DaoAuthenticationProvider authenticationProvider() {

        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder( encoder() );

        return authProvider;
    }

    // can autowire and access the authentication manager (manages authentication (login) of our project)
    @Bean
    protected AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

}
