package com.eatza.delivery.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.eatza.delivery.model.DeliveryPerson;
import com.eatza.delivery.repository.DeliveryPersonRepository;
import com.eatza.delivery.service.deliveryservice.DeliveryPersonServiceImpl;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class DeliveryPersonServiceTest {

	@InjectMocks
	private DeliveryPersonServiceImpl deliveryPersonService;

	@Mock
	private DeliveryPersonRepository deliveryPersonRepository;

	

	@Test
	public void testGetDeliveryPersonByRestaurantId() {

		DeliveryPerson deliveryPerson = new DeliveryPerson();
		deliveryPerson.setId(1L);
		deliveryPerson.setRestaurantId(1L);
		deliveryPerson.setName("Sam");

		when(deliveryPersonRepository.findByRestaurantId(any())).thenReturn(Optional.of(deliveryPerson));
		Optional<DeliveryPerson> optionalDeliveryPerson = deliveryPersonService.getDeliveryPersonByRestaurantId(4L);

		assertNotNull(optionalDeliveryPerson);
		assertNotNull(optionalDeliveryPerson.get().getName());
		assertEquals(new Long(1), optionalDeliveryPerson.get().getId());
		assertEquals(new Long(1), optionalDeliveryPerson.get().getRestaurantId());

	}

}
