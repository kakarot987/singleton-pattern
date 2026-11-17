package com.singleton.controller;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class SingletonControllerTest {
    private final MockMvc mvc = MockMvcBuilders.standaloneSetup(new SingletonController()).build();

    @Test
    void reportsSameInstanceAcrossCalls() throws Exception {
        mvc.perform(get("/api/singleton/lazy"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.isSameInstance").value(true));
        mvc.perform(get("/api/singleton/double-checked"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.isSameInstance").value(true));
        mvc.perform(get("/api/singleton/bill-pugh"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.isSameInstance").value(true));
        mvc.perform(get("/api/singleton/enum"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.isSameInstance").value(true));
    }

    @Test
    void explainsDatabaseExampleIsSimulated() throws Exception {
        mvc.perform(get("/api/singleton/database"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.isSameInstance").value(true))
                .andExpect(jsonPath("$.description").value(
                        org.hamcrest.Matchers.containsString("does NOT open a JDBC connection")));
    }

    @Test
    void overviewEnumeratesAllSixExamples() throws Exception {
        mvc.perform(get("/api/singleton/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.patterns.eager").exists())
                .andExpect(jsonPath("$.patterns.lazy").exists())
                .andExpect(jsonPath("$.patterns.doubleChecked").exists())
                .andExpect(jsonPath("$.patterns.billPugh").exists())
                .andExpect(jsonPath("$.patterns.enum").exists())
                .andExpect(jsonPath("$.patterns.database").exists());
    }
}
