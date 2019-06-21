package com.transportexchangegroup.fm.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.ardas.jwt.JwtData;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthJwtToken implements JwtData {
	private Integer userId;
	private Integer companyId;
	private String userRole;
	private String xsrfToken;

	@Override
	public String getXsrfToken() {
		return xsrfToken;
	}
}