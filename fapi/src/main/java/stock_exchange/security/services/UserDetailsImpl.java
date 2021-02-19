package stock_exchange.security.services;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import stock_exchange.model.User;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UserDetailsImpl implements UserDetails {
	private static final long serialVersionUID = 1L;

	private int id;
	private String name;
	private String email;
	@JsonIgnore
	private String password;
	private String role;
	private String status;


	public UserDetailsImpl(User user) {
		this.email = user.getEmail();
		this.password = user.getPassword();
		this.role = user.getRole();
		this.id= user.getId();
		this.name=getUsername();
		this.status=user.getStatus();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singletonList(new SimpleGrantedAuthority(role));
	}

	public int getId() {
		return id;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return email;
	}

	public String getName() {
		return name;
	}

	public String getRole() {
		return role;
	}

	public String getStatus() {
		return status;
	}

	@Override
	public boolean isAccountNonExpired() {
		return "Unblock".equals(status);
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UserDetailsImpl user = (UserDetailsImpl) o;
		return Objects.equals(id, user.id);
	}
}
