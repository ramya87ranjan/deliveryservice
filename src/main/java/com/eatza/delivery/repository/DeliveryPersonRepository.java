package com.eatza.delivery.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eatza.delivery.model.DeliveryPerson;

public interface DeliveryPersonRepository extends JpaRepository<DeliveryPerson, Long> {
	
	
	Optional<DeliveryPerson> findByRestaurantId(Long id);

}
