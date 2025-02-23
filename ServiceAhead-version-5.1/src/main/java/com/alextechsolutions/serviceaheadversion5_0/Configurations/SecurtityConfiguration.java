package com.alextechsolutions.serviceaheadversion5_0.Configurations;

import com.alextechsolutions.serviceaheadversion5_0.Service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurtityConfiguration {

    @Autowired
    public MyUserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity)throws Exception{
        return httpSecurity
                .csrf(customizer->customizer.disable())
                .authorizeHttpRequests(registry->registry
                        .requestMatchers("/html/login.html","/css/**","/Images/**","/register_process","/html/register.html","/html/404.html")
                        .permitAll()
                        .anyRequest().authenticated())
                .formLogin(form->form
                        .loginPage("/html/login.html")
                        .loginProcessingUrl("/perform_login")
                        .defaultSuccessUrl("/",true)
                        .failureUrl("/html/404.html?error=true"))
                .httpBasic(Customizer.withDefaults())
                
                .build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder(12));
        return daoAuthenticationProvider;
    }

}
