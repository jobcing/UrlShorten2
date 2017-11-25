package com.shortenurl.api;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * ServletInitializer class.
 * 
 * @author devetude
 */
public class ServletInitializer extends SpringBootServletInitializer {
	private static final String TAG = ServletInitializer.class.getSimpleName();

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ShortenURLApplication.class);
	}
}