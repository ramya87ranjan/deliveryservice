package com.eatza.delivery.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.eatza.delivery.dto.DeliveryRequestDto;
import com.eatza.delivery.exception.RestaurantNotServedException;
import com.eatza.delivery.service.deliveryservice.DeliveryService;

@RestController
public class DeliveryController {
	
	@Value(value = "${kafka-topic}")
    private String TOPIC;

	@Autowired
	DeliveryService deliveryService;
	private static final Logger logger = LoggerFactory.getLogger(DeliveryController.class);

	

	@GetMapping("/delivery")
	public String getAllDeliveries(@RequestHeader String authorization) {
		return "delivery";

	}
	
	

	@PostMapping("/deliver/deliveryid")
	public ResponseEntity<String> assidgnDeliveryPerson(@RequestBody DeliveryRequestDto deliveryRequest) {
		System.out.println("Its working");
		logger.debug("In assidgnDeliveryPerson method");

		deliveryService.assignDeliveryPerson(deliveryRequest);
		logger.debug("Assigned delivery person successfully");

		return ResponseEntity.status(HttpStatus.OK).body("Assigned delivery person to  order");

	}
	
	 @KafkaListener(topics = "${kafka-topic}", groupId = "${spring.kafka.consumer.group-id}")
	    public void receiveKafkaMessage(DeliveryRequestDto delivery){
	        logger.debug("Received Messasge,group= message= " + delivery);
	        try {
	        deliveryService.assignDeliveryPerson(delivery);
	        }
	        catch(RestaurantNotServedException exc){
	        	
	        	logger.debug("No deliverry person for this restaurant" );
	        	
	        }
	        
	        
	       
	    }
	 

}