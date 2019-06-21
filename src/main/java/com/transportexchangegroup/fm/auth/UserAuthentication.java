package com.transportexchangegroup.fm.auth;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Collections;

public class UserAuthentication implements Authentication {

	private boolean authenticated = true;
	private final AuthJwtToken authJwtToken;

	public UserAuthentication(AuthJwtToken authJwtToken) {
		this.authJwtToken = authJwtToken;
	}

	@Override
	public String getName() {
		return authJwtToken.getUserId().toString();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singleton(new SimpleGrantedAuthority(authJwtToken.getUserRole()));
	}

	@Override
	public Object getCredentials() {
		return authJwtToken.getUserId();
	}

	@Override
	public AuthJwtToken getDetails() {
		return authJwtToken;
	}

	@Override
	public Object getPrincipal() {
		return authJwtToken.getUserId();
	}

	@Override
	public boolean isAuthenticated() {
		return authenticated;
	}

	@Override
	public void setAuthenticated(boolean authenticated) {
		this.authenticated = authenticated;
	}
}