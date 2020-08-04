package com.eatza.delivery.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class DeliveryRequestDto {
	
	private Long orderId;
	private Long restaurantId;
	@Override
	public String toString() {
		return "DeliveryRequestDto [orderId=" + orderId + ", restaurantId=" + restaurantId + "]";
	}
	

	
	


	
	
	
	

}
