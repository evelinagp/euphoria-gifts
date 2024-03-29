package com.example.euphoriagifts2.config;

import com.example.euphoriagifts2.model.entity.enums.RoleNameEnum;
import com.example.euphoriagifts2.repository.UserRepository;
import com.example.euphoriagifts2.service.AppUserDetailsService;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration

public class ApplicationSecurityConfiguration {

    //Here we have to expose 3 things:
    // 1. PasswordEncoder
    // 2. SecurityFilterChain
    // 3. UserDetailsService

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new Pbkdf2PasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // csrf().disable().
        http.
                // define which requests are allowed and which not
                        authorizeRequests().
                // everyone can download static resources (css, js, images)
                // antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**").permitAll() OR
                        requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll().
                // everyone can login and register
                        antMatchers("/", "/users/login", "/users/register", "/gifts/**", "/about", "/categories", "/contacts",  "/cart").permitAll().
                // pages available only for users
//                    // pages available onny for admins
        antMatchers("/pages/admins/**", "/pictures/**", "/statistics").hasRole(RoleNameEnum.ADMIN.name()).
                // all other pages are available for logger in users    also->"/api/**"
                        anyRequest().
                authenticated().
                and()
                .exceptionHandling().accessDeniedPage("/access-denied").
                and().
                // configuration of form login
                        formLogin().
                // the custom login form
                        loginPage("/users/login").
                // the name of the username form field
                        usernameParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY).
                // the name of the password form field
                        passwordParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY).
                // where to go in case that the login is successful
                        defaultSuccessUrl("/").
                // where to go in case that the login failed
                        failureForwardUrl("/users/login-error").
                and().
                // configure logout
                        logout().
                // which is the logout url
                        logoutUrl("/users/logout").
                //  where to go in case that the logout is successful
                        logoutSuccessUrl("/").
                // invalidate the session and delete the cookies
                        invalidateHttpSession(true).
                deleteCookies("JSESSIONID");


        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return new AppUserDetailsService(userRepository);
    }

}
