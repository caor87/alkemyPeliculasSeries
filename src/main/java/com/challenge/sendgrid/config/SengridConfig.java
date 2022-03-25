package com.challenge.sendgrid.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import com.sendgrid.SendGrid;

@Configuration
public class SengridConfig {

	@Value("${app.sendgrid.key}")
	private String appkey;
	@Bean
	public SendGrid getSenGrit() {
		
		return new SendGrid(appkey);
	}
}
