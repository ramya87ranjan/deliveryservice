package com.eatza.delivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eatza.delivery.model.Delivery;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {

}
