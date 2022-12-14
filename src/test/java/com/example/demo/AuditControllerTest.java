package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.demo.audit.Audit;
import com.example.demo.audit.AuditController;
import com.example.demo.audit.AuditService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.assertions.Assertions;

@WebMvcTest(controllers = AuditController.class)
public class AuditControllerTest{
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper mapper;
	
	@MockBean
	private AuditService service;
	
	private List<Audit> audits;
	
	private Optional<Audit> auditById;
	
	private Audit audit;
	
	private Audit auditUpdate;
	
	
	@BeforeEach
	void setup() {
		audits = List.of(new Audit("1234", "Hello", "TestApp", "404"), new Audit("1234", "Hello", "TestApp", "404"));
		audit = new Audit("123qwe4", "Hello", "TestApp", "404");
		auditById = Optional.of(new Audit("1234", "Hello", "TestApp", "404"));
	}
	
	@Test
	void getAuditTest() throws Exception{
		Mockito.when(service.getAll()).thenReturn(audits);
		
		MvcResult result = ((MockHttpServletRequestBuilder) mockMvc.perform(MockMvcRequestBuilders.get("/audits")))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn();
		
		Assertions.assertThat(result).isNotNull();
		String auditJson = result.getResponse().getContentAsString();
		Assertions.assertThat(auditJson).isEqualToIgnoringCase(mapper.writeValueAsString(audits));
	}
}