package com.eatza.delivery.service.deliveryservice;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eatza.delivery.dto.DeliveryRequestDto;
import com.eatza.delivery.exception.RestaurantNotServedException;
import com.eatza.delivery.model.Delivery;
import com.eatza.delivery.model.DeliveryPerson;
import com.eatza.delivery.repository.DeliveryRepository;

@Service
public class DeliveryServiceImpl implements DeliveryService {
	private static final Logger logger = LoggerFactory.getLogger(DeliveryServiceImpl.class);

	@Autowired
	private DeliveryPersonService deliveryPersonService;
	@Autowired
	DeliveryRepository deliveryRepository;

	@Override
	public Delivery assignDeliveryPerson(DeliveryRequestDto deliveryRequest) {
		logger.debug("In  assign deliveryPerson method");

		// Get delivery person for the restaurant Id

		Optional<DeliveryPerson> deliveryPerson = deliveryPersonService
				.getDeliveryPersonByRestaurantId(deliveryRequest.getRestaurantId());
		if (!deliveryPerson.isPresent()) {
			throw new RestaurantNotServedException("No delivery person for given Restaurant ");
		}
		
		// assign delivery person to order
		
		Delivery delivery=new Delivery();
		delivery.setDeliveryPerson(deliveryPerson.get());
		delivery.setOrderId(deliveryRequest.getOrderId());
		return deliveryRepository.save(delivery);
	}

}
