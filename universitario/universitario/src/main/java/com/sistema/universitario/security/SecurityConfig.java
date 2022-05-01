package com.sistema.universitario.security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests()
                .antMatchers("/cursos").permitAll()
                .antMatchers("/disciplina").permitAll()
                .antMatchers("/aluno").hasAnyRole("ALUNO, PROFESSOR, ADMIN")
                .antMatchers("/turma").hasAnyRole("ALUNO, PROFESSOR, ADMIN")
                .antMatchers("/turma/**").hasAnyRole("PROFESSOR, ADMIN")
                .antMatchers("/aluno/**").hasAnyRole("ALUNO, PROFESSOR, ADMIN")
                .antMatchers("/professor").hasAnyRole("PROFESSOR, ADMIN")
                .antMatchers("/professor/**").hasAnyRole("ADMIN");

        http.csrf().disable();
    }



}


/*
.hasAnyRole("ADMIN","USER")
        .antMatchers("/alunos/**").hasRole("ADMIN")
        .and()
        .formLogin().loginPage("/login").permitAll()
        .usernameParameter("usu").passwordParameter("psw").defaultSuccessUrl("/alunos")
        .and().logout().permitAll().and().exceptionHandling().accessDeniedPage("/403");

        http.csrf().disable();
        http.addFilterBefore(new CustomFilter(), BasicAuthenticationFilter.class);*/
