package com.example.movice_ticket.controller;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/*
* 图片管理路径
* */
@Configuration
public class RecordTheScoewMvcConfiuration implements WebMvcConfigurer{

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// 配置路径映射
		registry.addResourceHandler("/RSIMAGE/**")
				.addResourceLocations("file:F:\\mihayo\\movie\\filmPicture\\");

		registry.addResourceHandler("/PlayIMAGE/**")
				.addResourceLocations("file:F:\\mihayo\\movie\\playPicture\\");

		registry.addResourceHandler("/UserIMAGE/**")
				.addResourceLocations("file:F:\\mihayo\\movie\\userPicture\\");
	}
	
}
