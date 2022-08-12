package com.spring.codeblog.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    //Contém todas as URIs que não precisam de autenticação
    private static final String[] AUTH_LIST = {
            "/",
            "/posts",
            "/posts/{id}"
    };

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.csrf().disable().authorizeRequests()
                //Passando URIs que serão permitidas sem autenticação
                .antMatchers(AUTH_LIST).permitAll()
                // Indica que cada request precisa ser autenticado
                .anyRequest().authenticated()
                //Permissão para acessar a página de login default
                .and().formLogin().permitAll()
                .and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
    }

    //Função que define o usuário para autenticação em memória.
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.inMemoryAuthentication()
                .withUser("enock").password("{noop}123").roles("ADMIN"); //Obs: {noop} serve para resolver um certo bug do springsecurity que impede que a senha seja 123
    }

    //Passar o caminho das pastas estáticas. Descenessário pela utilização do bootstrap CDN.
    @Override
    public void configure(WebSecurity web) throws Exception{
        web.ignoring().antMatchers("/bootstrap/**");
    }
}
