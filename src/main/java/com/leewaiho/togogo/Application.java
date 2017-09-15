package com.leewaiho.togogo;

import com.leewaiho.togogo.module.wechat.WeChat;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@ServletComponentScan
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Bean
	public RestTemplate restTemplate() {
		SimpleClientHttpRequestFactory simpleClientHttpRequestFactory = new SimpleClientHttpRequestFactory();
		simpleClientHttpRequestFactory.setConnectTimeout(60000);
		simpleClientHttpRequestFactory.setReadTimeout(60000);
		return new RestTemplate(simpleClientHttpRequestFactory);
	}
	
	@Bean
	public WeChat weChat() {
		return new WeChat("wx8af3bc82b4ca8ef3", "33f5804dc34c72aa09a740b19b768651");
	}
}
