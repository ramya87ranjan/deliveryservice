
package com.eatza.delivery.service;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.eatza.delivery.dto.DeliveryRequestDto;
import com.eatza.delivery.exception.RestaurantNotServedException;
import com.eatza.delivery.model.Delivery;
import com.eatza.delivery.model.DeliveryPerson;
import com.eatza.delivery.repository.DeliveryRepository;
import com.eatza.delivery.service.deliveryservice.DeliveryPersonServiceImpl;
import com.eatza.delivery.service.deliveryservice.DeliveryServiceImpl;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class DeliveryServiceTest {

	@InjectMocks
	DeliveryServiceImpl deliveryService;

	@Mock
	DeliveryPersonServiceImpl deliverypersonService;

	@Mock
	DeliveryRepository deliveryRepository;

	@Test (expected = RestaurantNotServedException.class)
	public void testAssignDeliveryPerson() { 
		DeliveryRequestDto deliveryRequestDto=new DeliveryRequestDto();
        deliveryRequestDto.setOrderId(6L); 
        deliveryRequestDto.setRestaurantId(4L);
        
       
  
  
         // when(deliverypersonService.getDeliveryPersonByRestaurantId( deliveryRequestDto.getRestaurantId())).thenReturn(Optional.of(deliveryPerson)); 
            Delivery delivery=deliveryService.assignDeliveryPerson(deliveryRequestDto);
  
   assertNotNull(delivery);
	/*
	 * assertNotNull(optionalDeliveryPerson.get().getName()); assertEquals(new
	 * Long(1), optionalDeliveryPerson.get().getId()); assertEquals(new Long(1),
	 * optionalDeliveryPerson.get().getRestaurantId());
	 */
  
  }

}
