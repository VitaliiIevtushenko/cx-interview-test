package com.transportexchangegroup.fm.api.version;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VersionDto {
	private String version;
	private String buildTime;
}
