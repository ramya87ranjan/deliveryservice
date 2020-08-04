package com.eatza.delivery.service.deliveryservice;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eatza.delivery.model.DeliveryPerson;
import com.eatza.delivery.repository.DeliveryPersonRepository;
@Service
public class DeliveryPersonServiceImpl implements DeliveryPersonService {
	private static final Logger logger = LoggerFactory.getLogger(DeliveryServiceImpl.class);

	@Autowired
	private DeliveryPersonRepository deliveryPersonRepository;

	@Override
	public Optional<DeliveryPerson> getDeliveryPersonByRestaurantId(Long restaurantId) {
		logger.debug("In get delivery  by restaurant id method, calling repo");
		return deliveryPersonRepository.findByRestaurantId(restaurantId);
	}

	
		
	

}
