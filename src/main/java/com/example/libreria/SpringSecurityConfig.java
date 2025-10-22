package com.example.libreria;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

//import com.example.libreria.models.service.UsuarioDetailService;

@Configuration
public class SpringSecurityConfig {
   /*  private final UsuarioDetailService usuarioDetailService;

    public SpringSecurityConfig(UsuarioDetailService usuarioDetailService){
        this.usuarioDetailService = usuarioDetailService;
    }*/

    @Bean
    BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean 
    AuthenticationManager authenticationManager(AuthenticationConfiguration configuration)throws Exception{
        return configuration.getAuthenticationManager();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests(authz -> authz
        .requestMatchers("/", "/css/**", "/js/**", "/img/**", "/libreria/libroslistar").permitAll()
        .requestMatchers("/libreria/usuarionuevo").hasAnyRole("USER")
        .requestMatchers("/libreria/libroeditar/**", "/libreria/libroelimnar/**", "/libreria/libronuevo").hasAnyRole("ADMIN")
        .anyRequest().authenticated()
        )
        .formLogin(login -> login
            .loginPage("/login")
            .defaultSuccessUrl("/libreria/libroslistar", true)
            //.defaultSuccessUrl("/?loginSuccess", true)
            .permitAll()
        ) 
        .logout(logout -> logout
            .permitAll()
        )
        .exceptionHandling(ex -> ex
            .accessDeniedPage("/error_403")
        )
        .exceptionHandling(ex -> ex
	        .accessDeniedPage("/error_403")
        );

        return http.build();
        
    }

}
