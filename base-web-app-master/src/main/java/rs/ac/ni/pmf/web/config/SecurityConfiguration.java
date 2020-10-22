package rs.ac.ni.pmf.web.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.spi.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

import rs.ac.ni.pmf.web.exception.ErrorInfo;
import rs.ac.ni.pmf.web.exception.ErrorInfo.ResourceType;

//@Configuration
//@EnableWebSecurity
public class SecurityConfiguration/*extends WebSecurityConfigurerAdapter */{
//
//	@Bean(name = "passwordEncoder")
//	public PasswordEncoder getPasswordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
//	
//	
//	@Autowired
//	private PasswordEncoder passwordEncoder;
//	
//
//	@Autowired
//	private ObjectMapper objectMapper;
//	
//
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		// TODO Auto-generated method stub
//		super.configure(auth);
//	}
//
//
//
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
////		http
////			.authorizeRequests()
////				.antMatchers(HttpMethod.GET, "services/rests/projects")
////					.permitAll()
////				.antMatchers(HttpMethod.PUT, "services/rests/users")
////					.hasRole("ADMIN")
////				.antMatchers(HttpMethod.POST, "services/rest/projects")
////					.hasRole("PROFESSOR")
////				.antMatchers(HttpMethod.PUT, "services/rest/projects/student*/**")
////					.hasRole("STUDENTS")
////				.anyRequest()
////					.authenticated()
////			.and()
////			.httpBasic()
////				.authenticationEntryPoint((request, responce,e) ->{
////					final ErrorInfo errorInfo = ErrorInfo.builder()
////							.errorCode(rs.ac.ni.pmf.web.exception.ErrorInfo.ErrorCode.AUTHENTICATIO_FAILD)
////							.resourceType(ResourceType.ACCES)
////							.message("Faild to authenticate the user. Bad username and/or password.")
////							.build();
////					
////					responce.setContentType("application/json;charset=UTF-8");
////					responce.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
////					responce.getWriter().write(""/*objectMapper.writeValueAsString(errorInfo)*/);
////				})
////			.and()
////			.exceptionHandling()
////				.accessDeniedHandler((request, responce,e) ->{
////					final ErrorInfo errorInfo = ErrorInfo.builder()
////							.errorCode(rs.ac.ni.pmf.web.exception.ErrorInfo.ErrorCode.UNAUTHORIZED)
////							.resourceType(ResourceType.ACCES)
////							.message("User attempted to acces the protected URL:" + request.getRequestURI())
////							.build();
////					
////					responce.setContentType("application/json;charset=UTF-8");
////					responce.setStatus(HttpServletResponse.SC_FORBIDDEN);
////					responce.getWriter().write("objectMapper.writeValueAsString(errorInfo)");
////				});
//	}
//	
	
	
}
