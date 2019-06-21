package com.transportexchangegroup.fm.api.version;

import com.transportexchangegroup.fm.api.Api;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersionController {

	@Value("${app.version:unknown}")
	private String version;
	@Value("${build.time:unknown}")
	private String buildTime;

	@GetMapping(value = Api.VERSION)
	public VersionDto getVersion() {
		return VersionDto.builder()
				.version(version)
				.buildTime(buildTime)
				.build();
	}
}
