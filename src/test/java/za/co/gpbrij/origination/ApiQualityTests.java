package za.co.gpbrij.origination;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ApiQualityTests {

    @Autowired
    MockMvc mvc;

    @Test
    void addsCorrelationIdAndReturnsStandardNotFoundError() throws Exception {

        mvc.perform(
                get("/api/v1/applications/missing-id")
                        .header("X-Correlation-ID", "SYNTH-CORR-001"))
                .andExpect(status().isNotFound())
                .andExpect(
                        header().string(
                                "X-Correlation-ID",
                                "SYNTH-CORR-001"));
    }

    @Test
    void returnsBadRequestForInvalidApplication() throws Exception {

        mvc.perform(
                post("/api/v1/applications")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isBadRequest());
    }
}