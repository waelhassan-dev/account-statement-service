package com.nagarro.account.statement.controller;

import com.nagarro.account.statement.dto.StatementRequest;
import com.nagarro.account.statement.service.StatementService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc(addFilters = false)
@SpringBootTest
class StatementControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StatementService mockStatementService;

    @Test
    @WithMockUser(username = "user")
    void testGetStatements_userRole() throws Exception {
        // Setup
        when(mockStatementService.processRequest(new StatementRequest(1L))).thenReturn(new StatementRequest(1L));

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/api/v1/statements")
                .param("accountId", "1")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    @WithMockUser(username = "admin",roles = {"ADMIN"})
    void testGetStatements_adminRole_withOutPagination() throws Exception {
        // Setup
        when(mockStatementService.processRequest(new StatementRequest(1L))).thenReturn(new StatementRequest(1L));

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/api/v1/statements")
                .param("accountId", "1")
                .param("fromDate", "2018-10-10")
                .param("toDate", "2019-11-11")
                .param("fromAmount", "10.0")
                .param("toAmount", "300.0")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    @WithMockUser(username = "admin",roles = {"ADMIN"})
    void testGetStatements_adminRole_withPagination() throws Exception {
        // Setup
        when(mockStatementService.processRequest(new StatementRequest(1L))).thenReturn(new StatementRequest(1L));

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/api/v1/statements")
                .param("accountId", "1")
                .param("fromDate", "2018-10-10")
                .param("toDate", "2019-11-11")
                .param("fromAmount", "10.0")
                .param("toAmount", "300.0")
                .param("size", "10")
                .param("index", "1")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

}
