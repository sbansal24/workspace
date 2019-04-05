package com.springboot.microservice.fx;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExchangeValueRepository extends JpaRepository<ExchangeValue, Integer> {
    ExchangeValue findByFromAndTo(String from, String to);
}
