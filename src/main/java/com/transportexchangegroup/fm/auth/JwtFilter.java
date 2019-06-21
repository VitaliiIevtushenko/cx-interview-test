package com.transportexchangegroup.fm.auth;

import com.transportexchangegroup.fm.api.Api;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.ap.internal.util.Collections;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import ua.ardas.jwt.AuthConstProvider;
import ua.ardas.jwt.AuthResult;
import ua.ardas.jwt.JwtAuthFilter;
import ua.ardas.jwt.JwtAuthFilterParams;
import ua.ardas.jwt.JwtService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@Slf4j
public class JwtFilter extends OncePerRequestFilter {
	private static final Set<String> JWT_FILTER_EXCLUDED_URLS = Collections.asSet(
			Api.ROOT_PATH + "/dashboard(/.*)?",
			"/actuator/.*",
			Api.VERSION,
			"/swagger-ui.html", "/swagger-resources(/.*)?", "/webjars/.*", "/favicon.ico", "/v1/private-api-docs"
	);

	private final JwtAuthFilter<AuthJwtToken> jwtAuthFilter;

	public JwtFilter(JwtService jwtService, AuthConstProvider authConst) {
		JwtAuthFilterParams<AuthJwtToken> params = JwtAuthFilterParams
				.builder(AuthJwtToken.class, jwtService)
				.authConst(authConst)
				.excludedUrls(JWT_FILTER_EXCLUDED_URLS)
				.successHandler((httpServletRequest, httpServletResponse, authResult) -> processSuccess(httpServletRequest, authResult))
				.build();

		this.jwtAuthFilter = new JwtAuthFilter<>(params);
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		jwtAuthFilter.filterRequest(request, response, chain);
	}

	private void processSuccess(HttpServletRequest request, AuthResult<AuthJwtToken> result) {
		AuthJwtToken jwtData = result.getJwtData();
		request.setAttribute("jwt_token", result.getJwtData());
		SecurityContextHolder.getContext().setAuthentication(new UserAuthentication(jwtData));

		log.info("Successfully authenticated by jwt: {}", jwtData);
	}
}