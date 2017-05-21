package io.github.jimmyfm.smartgwt.restdatasource.server;

import org.glassfish.jersey.server.ResourceConfig;

// @ApplicationPath("/")
public class RestApp extends ResourceConfig {

	public RestApp() {
		// packages(true, "com.github");
		register(RestDataSource.class);
	}
}