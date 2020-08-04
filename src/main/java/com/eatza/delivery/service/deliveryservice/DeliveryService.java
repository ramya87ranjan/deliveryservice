package com.eatza.delivery.service.deliveryservice;

import com.eatza.delivery.dto.DeliveryRequestDto;
import com.eatza.delivery.model.Delivery;

public interface DeliveryService {
	
	Delivery assignDeliveryPerson(DeliveryRequestDto deliveryRequest);

}
