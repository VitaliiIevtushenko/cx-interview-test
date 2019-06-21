package com.transportexchangegroup.fm.api.version;

import com.transportexchangegroup.fm.Application;
import com.transportexchangegroup.fm.api.Api;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(classes = {Application.class})
@ActiveProfiles("test")
public class VersionControllerTest {

	@Autowired
    MockMvc mockMvc;

	@Test
	public void shouldReturnVersion() throws Exception {
		mockMvc.perform(get(Api.VERSION))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.version", is("latest")))
				.andExpect(jsonPath("$.buildTime", is("short")));
	}
}
