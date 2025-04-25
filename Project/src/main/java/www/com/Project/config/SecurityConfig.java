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
import jakarta.servlet.http.HttpServletResponse;
@Configuration
@EnableMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class SecurityConfig {

   @Autowired
   private DataSource dataSource;
   @Bean
   public UserDetailsService userDetailsService() {
       JdbcDaoImpl jdbcDao = new JdbcDaoImpl();
       jdbcDao.setDataSource(dataSource);
       return jdbcDao;
   }
   @Bean
   public AuthenticationProvider authenticationProvider() {
       DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
       authProvider.setUserDetailsService(userDetailsService());
       authProvider.setPasswordEncoder(passwordEncoder()); // Используем наш кастомный encoder
       return authProvider;
   }
   // Настройка DelegatingPasswordEncoder для обработки паролей с {noop}
   @Bean
public PasswordEncoder passwordEncoder() {
	   return new BCryptPasswordEncoder();
   }
   @Bean
   public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
       AuthenticationManagerBuilder builder = http.getSharedObject(AuthenticationManagerBuilder.class);
       builder.authenticationProvider(authenticationProvider());
       return builder.build();
   }
   
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
             .anyRequest().authenticated()

         )
         .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
         .exceptionHandling(ex -> ex
           .authenticationEntryPoint((req, res, ex2) ->
             res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized")))
         .httpBasic();
       return http.build();
   }
}

