package com.eatza.delivery.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class DeliveryPersonRepositoryTest {
	
	
	@Autowired
	DeliveryPersonRepository deliveryPersonRepository;
	
 
	
	
	@Test
	public void findByRestaurantId() {
		//assertEquals(4,.getRestaurantId());
		assertNotNull(deliveryPersonRepository.findByRestaurantId(4L).get());
	
		
	}
	
	
}
