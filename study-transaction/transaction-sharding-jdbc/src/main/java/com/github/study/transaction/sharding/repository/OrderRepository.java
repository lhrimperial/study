package com.github.study.transaction.sharding.repository;

import com.github.study.transaction.sharding.domain.Order;
import org.springframework.data.repository.CrudRepository;

/**
 *
 */
public interface OrderRepository extends CrudRepository<Order, Long> {
}
