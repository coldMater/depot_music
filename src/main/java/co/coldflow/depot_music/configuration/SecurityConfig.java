package co.coldflow.depot_music.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
            .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/favicon.ico").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/login-error").permitAll()
                .antMatchers("/styles/**").permitAll()
                .antMatchers("/images/**").permitAll()
                .antMatchers("/portrait/*").permitAll()
                .antMatchers("/instructor/**").hasRole("INSTRUCTOR")
                .antMatchers("/student/**").hasRole("STUDENT")
                .anyRequest().hasRole("ADMIN");

        http.formLogin()
                .loginPage("/login")
                .failureUrl("/login-error");

        http.logout()
                .logoutSuccessUrl("/");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/h2-console/**");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
