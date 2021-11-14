package mul.com.sns;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		// TODO Auto-generated method stub
		WebMvcConfigurer.super.addCorsMappings(registry);
		
		registry.addMapping("/**").allowedOrigins("http://localhost:8090")
			.allowedMethods("*")
			.allowedHeaders("*")
			.allowCredentials(true)
			.exposedHeaders("Authorization", "RefreshToken");// 클라이언트에서도 토큰을 추출할 수 있도록 함
	}
	

}
