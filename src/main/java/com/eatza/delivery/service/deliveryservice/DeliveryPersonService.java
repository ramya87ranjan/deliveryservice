package com.eatza.delivery.service.deliveryservice;

import java.util.Optional;

import com.eatza.delivery.model.DeliveryPerson;

public interface DeliveryPersonService {
	
	Optional<DeliveryPerson> getDeliveryPersonByRestaurantId(Long restaurantId);

}
