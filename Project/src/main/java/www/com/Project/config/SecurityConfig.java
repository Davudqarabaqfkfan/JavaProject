package www.com.Project.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.*;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.*;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import  www.com.Project.jwt.JwtAtutenthicationFilter;
import www.com.Project.jwt.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
@EnableMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private DataSource dataSource;

    // Конфигурация UserDetailsService с использованием базы данных
    @Bean
    public UserDetailsService userDetailsService() {
        JdbcDaoImpl jdbcDao = new JdbcDaoImpl();
        jdbcDao.setDataSource(dataSource);
        return jdbcDao;
    }

    // Провайдер для аутентификации
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder()); // Используем BCryptPasswordEncoder
        return authProvider;
    }

    // Настройка BCryptPasswordEncoder
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private JwtUtil jwtUtil;

    // Фильтр для проверки JWT токенов
    @Bean
    public JwtAtutenthicationFilter jwtAuthenticationFilter() {
        return new JwtAtutenthicationFilter(jwtUtil, userDetailsService());
    }

    // Конфигурация AuthenticationManager
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder builder = http.getSharedObject(AuthenticationManagerBuilder.class);
        builder.authenticationProvider(authenticationProvider());
        return builder.build();
    }

    // Основная конфигурация безопасности для HTTP
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authenticationProvider(authenticationProvider())
            .authorizeRequests(auth -> auth
       		     .requestMatchers("/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**").permitAll()
       		    .requestMatchers("/users/register").permitAll()
       		    .requestMatchers("/companies").permitAll()
       		   .requestMatchers("/teachers/get-all").permitAll()
       		   .requestMatchers("/companies/get-all").permitAll()
       		   .requestMatchers("/teachers/search").permitAll()
       		   .requestMatchers("/companies/search").permitAll()
       		   .requestMatchers("/teachers/{id}").permitAll()
       		   .requestMatchers("/comapnies/{id}").permitAll()
       		   .requestMatchers("/teachers/{id}/company").permitAll()
       		   .requestMatchers("/users/login").permitAll()
                .anyRequest().authenticated()

            )
            .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Без сессий, только JWT
            .exceptionHandling(ex -> ex
                .authenticationEntryPoint((req, res, ex2) -> 
                    res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized")) // Ошибка 401 при отсутствии токена
            )
            .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class); // Добавление фильтра JWT

        return http.build();
    }
}