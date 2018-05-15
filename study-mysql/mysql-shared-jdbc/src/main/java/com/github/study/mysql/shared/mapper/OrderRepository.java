package com.github.study.mysql.shared.mapper;

import com.github.study.mysql.shared.domain.Order;
import org.springframework.data.repository.CrudRepository;

/**
 *
 */
public interface OrderRepository extends CrudRepository<Order, Long> {
}