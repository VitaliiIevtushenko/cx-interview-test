package com.transportexchangegroup.fm.api;

import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.transportexchangegroup.fm.config.EmbeddedPostgresConfig;
import com.transportexchangegroup.fm.config.TestContext;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = {TestContext.class, EmbeddedPostgresConfig.class})
@TestExecutionListeners(listeners = {TransactionDbUnitTestExecutionListener.class},
        mergeMode = TestExecutionListeners.MergeMode.MERGE_WITH_DEFAULTS)
public class VehicleControllerTest extends BaseControllerTest {

    @Test
    @DatabaseSetup("classpath:datasets/vehicles/VehicleControllerTest.xml")
    public void findVehicles() throws Exception {
        mvc.perform(get(Api.VEHICLES)
                .param("date", "2019-01-01T00:00:00Z")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(content().json("[\n" +
                        "   {\n" +
                        "      \"vehicleId\":1,\n" +
                        "      \"name\":\"AE 7809 CH\",\n" +
                        "      \"orders\":[\n" +
                        "         {\n" +
                        "            \"orderId\":4,\n" +
                        "            \"date\":\"2019-01-01T23:51:17Z\"\n" +
                        "         },\n" +
                        "         {\n" +
                        "            \"orderId\":5,\n" +
                        "            \"date\":\"2019-01-01T13:13:13Z\"\n" +
                        "         },\n" +
                        "         {\n" +
                        "            \"orderId\":1,\n" +
                        "            \"date\":\"2019-01-01T00:00:00Z\"\n" +
                        "         }\n" +
                        "      ]\n" +
                        "   },\n" +
                        "   {\n" +
                        "      \"vehicleId\":2,\n" +
                        "      \"name\":\"AE 5593 AA\",\n" +
                        "      \"orders\":[\n" +
                        "         {\n" +
                        "            \"orderId\":2,\n" +
                        "            \"date\":\"2019-01-01T00:00:00Z\"\n" +
                        "         }\n" +
                        "      ]\n" +
                        "   }\n" +
                        "]"));
    }

    @Test
    @DatabaseSetup("classpath:datasets/vehicles/VehicleControllerTest.xml")
    public void validateRequiredParams() throws Exception {
        mvc.perform(get(Api.VEHICLES)
                .param("date", "bla bla")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string("'Date' parameter is incorrect"));
    }
}
