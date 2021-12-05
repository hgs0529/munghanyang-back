package mul.com.sns;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;
import mul.com.sns.dao.UserDao;
import mul.com.sns.jwt.JwtAuthenticationFilter;
import mul.com.sns.jwt.JwtUtil;
import mul.com.sns.service.CustomOAuth2UserService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

   private final JwtUtil jwtUtil;
   private final UserDao userDao;
   
   private final OAuth2SuccessHandler oAuth2SuccessHandler;
   private final CustomOAuth2UserService customOAuth2UserService;
   
   @Bean
   public PasswordEncoder getPasswordEncoder() {
      return new BCryptPasswordEncoder();
   }
   
   @Override
   protected void configure(HttpSecurity http) throws Exception {
      
      http
		      .csrf()
		    	.disable()
		      .formLogin()
		    	.disable()
		      .sessionManagement()
		    	.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	      .and()
		      .httpBasic()
		      	.disable()
	          .authorizeRequests()
	          .antMatchers("/**", "/error",
                      "/favicon.ico",
                      "/**/*.png",
                      "/**/*.gif",
                      "/**/*.svg",
                      "/**/*.jpg",
                      "/**/*.html",
                      "/**/*.css",
                      "/**/*.js")
	          	.permitAll()
	          .antMatchers("/user/join/**", "/user/login/**", "/oauth2/**")
	          	.permitAll() 		// 검증 없이 접근 허용
	          /*
	          .antMatchers("/admin/**")
	          	.hasRole("ADMIN")
	          */
	          .anyRequest()
	            .authenticated()
          .and()
          	.oauth2Login()
	          	.successHandler(oAuth2SuccessHandler)
	          	.userInfoEndpoint().userService(customOAuth2UserService);
      
     http.addFilterBefore(new JwtAuthenticationFilter(jwtUtil, userDao), UsernamePasswordAuthenticationFilter.class);
      
   }
}