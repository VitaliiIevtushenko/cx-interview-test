package com.transportexchangegroup.fm.auth;

import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import ua.ardas.jwt.AuthConstProvider;

@Data
@ConfigurationProperties("auth")
public class AuthProperties implements AuthConstProvider {
	private String jwtTokenCookie;
	private String xsrfTokenCookie;
	private String xsrfTokenHeader;
	private int tokenExpireHours;

	private String publicKeyPath;
	private String privateKeyPath;
	private SignatureAlgorithm signatureAlgorithm;

	private String serviceUserName;
	private String serviceUserPassword;

	@Override
	public String getXsrfTokenCookie() {
		return null;
	}
}
