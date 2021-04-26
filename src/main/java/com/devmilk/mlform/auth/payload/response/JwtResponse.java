package com.devmilk.mlform.auth.payload.response;

import com.devmilk.mlform.auth.models.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
public class JwtResponse {
	private String token;
	private String type = "Bearer";
	private Long id;
	private String email;
	private List<String> roles;

	public JwtResponse(String accessToken, Long id, String email, List<String> roles) {
		this.token = accessToken;
		this.id = id;
		this.email = email;
		this.roles = roles;
	}
}
