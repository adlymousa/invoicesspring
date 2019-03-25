package com.harri.invoicesspring.configurations;

import com.harri.invoicesspring.repositories.UserRepository;
import com.harri.invoicesspring.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.InMemoryUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.AbstractPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.harri.invoicesspring.models.Role;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    DataSource dataSource;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Value("select username, password, active from users where username=?")
    private String usersQuery;

    @Value("select username, role from users where username=?")
    private String rolesQuery;

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.
                jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery(usersQuery)
                .authoritiesByUsernameQuery(rolesQuery)
                .passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                authorizeRequests()
                .antMatchers("/**").permitAll()
                //.antMatchers("/login.html").permitAll()
                .antMatchers("/register.html", "/api/register").permitAll()
                //.antMatchers(HttpMethod.POST, "/api/users").permitAll()
                .antMatchers("/api/audit/**").hasAuthority(Role.ADMIN.toString())
                .antMatchers("/invoices-history.html").hasAuthority(Role.ADMIN.toString())
                .antMatchers("/attachments-history.html").hasAuthority(Role.ADMIN.toString())
                .antMatchers("/api/**").hasAnyAuthority(Role.USER.toString(), Role.ADMIN.toString())
                .anyRequest()
                .authenticated().and().csrf().and().formLogin()
                //.loginPage("/login")//.failureUrl("../templates/login?error").defaultSuccessUrl("/")
                .usernameParameter("username")
                .passwordParameter("password")
                .and().logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .invalidateHttpSession(false).logoutSuccessUrl("/login").deleteCookies("JSESSIONID").and().exceptionHandling()
                .accessDeniedPage("/forbidden.html");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("resources/**", "/static/**", "/css/**", "/js/**", "/vendor/**", "scss/**");

    }

}