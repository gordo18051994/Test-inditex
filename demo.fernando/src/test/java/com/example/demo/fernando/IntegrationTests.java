package com.example.demo.fernando;

import com.example.demo.fernando.infrastructure.db.config.DbJpaConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {DbJpaConfig.class})
@SpringBootTest(
		webEnvironment = SpringBootTest.WebEnvironment.MOCK,
		classes = Application.class)
@AutoConfigureMockMvc
@TestPropertySource(
		locations = "classpath:application.yml")
public class IntegrationTests {

	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	@BeforeEach
	public void setup() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
	}

	@Test
	public void when_params_is_passed_thenResponseOK() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/fernando/priceOnDemandDate")
						.param("brandId", "1")
						.param("productId", "35455")
						.param("demandDate", "2020-06-15T00:00:00")
				)
				.andDo(print())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(3))
				.andExpect(MockMvcResultMatchers.jsonPath("$.price").value(30.50D))
				.andExpect(status().isOk());
	}

	@Test
	public void when_request_day_fourteen_at_ten_in_morning_thenResponseOneElement() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/fernando/priceOnDemandDate")
						.param("brandId", "1")
						.param("productId", "35455")
						.param("demandDate", "2020-06-14T10:00:00")
				)
				.andDo(print())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
				.andExpect(MockMvcResultMatchers.jsonPath("$.price").value(35.50D))
				.andExpect(status().isOk());
	}

	@Test
	public void when_request_day_fourteen_at_four_in_evening_thenResponse_max_priority() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/fernando/priceOnDemandDate")
						.param("brandId", "1")
						.param("productId", "35455")
						.param("demandDate", "2020-06-14T16:00:00")
				)
				.andDo(print())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(2))
				.andExpect(MockMvcResultMatchers.jsonPath("$.price").value(25.45D))
				.andExpect(status().isOk());
	}

	@Test
	public void when_request_day_fourteen_at_nine_in_evening_thenResponseOneElement() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/fernando/priceOnDemandDate")
						.param("brandId", "1")
						.param("productId", "35455")
						.param("demandDate", "2020-06-14T21:00:00")
				)
				.andDo(print())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
				.andExpect(MockMvcResultMatchers.jsonPath("$.price").value(35.50D))
				.andExpect(status().isOk());
	}

	@Test
	public void when_request_day_fifteen_at_ten_in_morning_thenResponse_max_priority() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/fernando/priceOnDemandDate")
						.param("brandId", "1")
						.param("productId", "35455")
						.param("demandDate", "2020-06-15T10:00:00")
				)
				.andDo(print())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(3))
				.andExpect(MockMvcResultMatchers.jsonPath("$.price").value(30.50D))
				.andExpect(status().isOk());
	}

	@Test
	public void when_request_day_sixteen_at_nine_in_evening_thenResponse_max_priority() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/fernando/priceOnDemandDate")
						.param("brandId", "1")
						.param("productId", "35455")
						.param("demandDate", "2020-06-16T21:00:00")
				)
				.andDo(print())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(4))
				.andExpect(MockMvcResultMatchers.jsonPath("$.price").value(38.95D))
				.andExpect(status().isOk());
	}

	@Test
	public void when_is_bad_request() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/fernando/priceOnDemandDate")
						.param("brandId", "aa")
						.param("productId", "35455")
						.param("demandDate", "2020-06-16T21:00:00")
				)
				.andDo(print())
				.andExpect(status().isBadRequest());
	}

}
