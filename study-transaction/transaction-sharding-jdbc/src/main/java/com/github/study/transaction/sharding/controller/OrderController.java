package com.github.study.transaction.sharding.controller;

import com.dangdang.ddframe.rdb.sharding.id.generator.IdGenerator;
import com.github.study.transaction.sharding.domain.Order;
import com.github.study.transaction.sharding.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;


    @Autowired
    private IdGenerator idGenerator;
    @RequestMapping("/add")
    public Object add() {

        for (int i = 0; i < 50; i++) {
            Order order = new Order();
            order.setUserId(Long.valueOf(i));
            order.setOrderId(idGenerator.generateId().longValue());
            orderRepository.save(order);
        }

        /*for (int i = 0; i < 10; i++) {
            Order order = new Order();
            order.setUserId((long) i);
            order.setOrderId((long) i);
            orderRepository.save(order);
        }
        for (int i = 10; i < 20; i++) {
            Order order = new Order();
            order.setUserId((long) i + 1);
            order.setOrderId((long) i);
            orderRepository.save(order);
        }*/
        return "success";
    }

    @RequestMapping("query")
    private Object queryAll() {
        return orderRepository.findAll();
    }

}
