package com.shortenurl.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * ShortenURLApplication class.
 * 
 * @author devetude
 */
@SpringBootApplication
@EnableAspectJAutoProxy
@EnableConfigurationProperties
public class ShortenURLApplication {
	private static final String TAG = ShortenURLApplication.class.getSimpleName();

	public static void main(String[] args) {
		SpringApplication.run(ShortenURLApplication.class, args);
	}
}