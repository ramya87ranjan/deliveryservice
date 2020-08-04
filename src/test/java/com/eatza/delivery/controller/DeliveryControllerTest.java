package com.eatza.delivery.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

import javax.servlet.ServletException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.eatza.delivery.dto.DeliveryRequestDto;
import com.eatza.delivery.model.Delivery;
import com.eatza.delivery.model.DeliveryPerson;
import com.eatza.delivery.service.deliveryservice.DeliveryService;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RunWith(SpringRunner.class)
@WebMvcTest(value = DeliveryController.class)
public class DeliveryControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private DeliveryService deliveryService;

	@Autowired
	private ObjectMapper objectMapper;

	String jwt = "";
	private static final long EXPIRATIONTIME = 900000;

	@Before
	public void setup() {
		jwt = "Bearer " + Jwts.builder().setSubject("user").claim("roles", "user").setIssuedAt(new Date())
				.signWith(SignatureAlgorithm.HS256, "secretkey")
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME)).compact();
	}

	@Test
	public void assignDeliveryPerson() throws Exception {

		DeliveryRequestDto deliveryRequest = new DeliveryRequestDto();
		deliveryRequest.setOrderId(1L);
		deliveryRequest.setRestaurantId(1L);

		DeliveryPerson deliveryPerson = new DeliveryPerson();
		deliveryPerson.setId(1L);
		deliveryPerson.setName("sam");
		deliveryPerson.setRestaurantId(1L);

		Delivery delivery = new Delivery();
		delivery.setDeliveryPerson(deliveryPerson);
		delivery.setId(1L);
		delivery.setOrderId(1L);

		when(deliveryService.assignDeliveryPerson(any(DeliveryRequestDto.class))).thenReturn(delivery);
		RequestBuilder request = MockMvcRequestBuilders.post("/deliver/deliveryid")
				.contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString((deliveryRequest)));

		mockMvc.perform(request).andExpect(status().is(200)).andReturn();

	}

	@Test
	public void getAllRestaurants_basic() throws Exception {

		RequestBuilder request = MockMvcRequestBuilders.get("/delivery").accept(MediaType.ALL)
				.header(HttpHeaders.AUTHORIZATION, jwt);

		mockMvc.perform(request).andExpect(status().isOk())

				.andReturn();
	}

	@Test(expected = ServletException.class)
	public void getAllRestaurants_basic1() throws Exception {

		RequestBuilder request = MockMvcRequestBuilders.get("/delivery").accept(MediaType.ALL)
				.header(HttpHeaders.AUTHORIZATION, "");

		mockMvc.perform(request).andExpect(status().isBadRequest())

				.andReturn();
	}


}
