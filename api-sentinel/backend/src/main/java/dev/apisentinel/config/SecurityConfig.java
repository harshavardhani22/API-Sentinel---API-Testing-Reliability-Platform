package dev.apisentinel.config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.*;
import java.util.List;
@Configuration @SuppressWarnings("deprecation")
public class SecurityConfig {
 @Bean SecurityFilterChain filterChain(HttpSecurity http)throws Exception{
  return http.csrf(c->c.disable()).cors(Customizer.withDefaults()).authorizeHttpRequests(a->a
   .requestMatchers("/api/health","/api/docs","/api/reports/**").permitAll()
   .requestMatchers(HttpMethod.GET,"/api/requests","/api/collections","/api/monitors").permitAll()
   .requestMatchers("/api/admin/**").hasRole("ADMIN").anyRequest().authenticated())
   .httpBasic(Customizer.withDefaults()).build();
 }
 @Bean UserDetailsService users(){return new InMemoryUserDetailsManager(
  User.withUsername("sentinel").password("sentinel123").roles("USER").build(),
  User.withUsername("admin").password("admin123").roles("USER","ADMIN").build());}
 @Bean PasswordEncoder passwordEncoder(){return NoOpPasswordEncoder.getInstance();}
 @Bean CorsConfigurationSource corsConfigurationSource(@Value("${app.cors.allowed-origins}")String origins){
  CorsConfiguration c=new CorsConfiguration();c.setAllowedOrigins(List.of(origins.split(",")));
  c.setAllowedMethods(List.of("GET","POST","PUT","PATCH","DELETE","OPTIONS"));c.setAllowedHeaders(List.of("Authorization","Content-Type"));
  UrlBasedCorsConfigurationSource s=new UrlBasedCorsConfigurationSource();s.registerCorsConfiguration("/**",c);return s;
 }
}
