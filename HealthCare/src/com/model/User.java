package com.model;

import java.util.HashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.Dao.DBManager;

@Path("/user")
public class User {

	@Path("/register")
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)

	public HashMap<String, String> getUserRegister(@FormParam("username") String username,
			@FormParam("password") String password, @FormParam("email") String email,
			@FormParam("address") String address, @FormParam("phoneNo") String phoneNo, @FormParam("age") String age,
			@FormParam("sex") String sex) {

		HashMap<String, String> h = new HashMap<String, String>();

		int i = DBManager.getUser(username, password, email, address, phoneNo, age, sex);

		if (i > 0) {
			h.put("register", "success");
		} else {
			h.put("register", "failed");
		}

		return h;
	}
}