package com.test.pro06.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.test.pro06.common.interceptor.LoginCheckInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// TODO Auto-generated method stub
		registry.addInterceptor(new LoginCheckInterceptor())
		.addPathPatterns("/**")
		.excludePathPatterns("/", "home", "/member/login*", "/member/joinMember*", 
				"/css/**", "/js/**", "/image/**");
		// 우선순위는 예외처리가 높다.
	}

}
