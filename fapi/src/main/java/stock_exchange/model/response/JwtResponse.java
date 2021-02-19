package stock_exchange.model.response;

import java.util.List;

public class JwtResponse {
	private String token;
	private int id;
	private String name;
	private String email;
	private String role;
	private String status;

	public JwtResponse(String accessToken, int id, String name, String email, String role, String status) {
		this.token = accessToken;
		this.id = id;
		this.name = name;
		this.email = email;
		this.role = role;
		this.status=status;
	}

	public String getAccessToken() {
		return token;
	}

	public void setAccessToken(String accessToken) {
		this.token = accessToken;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRole() {
		return role;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
