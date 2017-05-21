package io.github.jimmyfm.smartgwt.restdatasource.server;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/restDataSource")
public class RestDataSource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Object testGet() {
		Map<String, Object> res = new HashMap<>();
		res.put("string", "asdasd");
		res.put("num", 2.3);
		res.put("array", Arrays.asList(new String[] { "asd", "asdas", "sdsd" }));
		return res;
	}
}