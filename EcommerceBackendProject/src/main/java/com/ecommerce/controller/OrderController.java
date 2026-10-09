package com.ecommerce.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.entity.Order;
import com.ecommerce.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @PostMapping("/buy")
    public Order buy(@RequestParam Long productId,
            @RequestParam int quantity,
            @RequestParam String payment,
            @RequestParam String address) {

        return service.buyProduct(productId, quantity, payment, address);
    }

    @GetMapping
    public List<Order> getOrders() {
        return service.getOrders();
    }

    @GetMapping("/{id}")
    public Order getOrder(@PathVariable Long id) {
        return service.getOrder(id);
    }

    @GetMapping("/{id}/track")
    public String trackOrder(@PathVariable Long id) {
        return service.trackOrder(id);
    }

    @PostMapping("/{id}/rating")
    public String rateDelivery(@PathVariable Long id,
            @RequestParam int rating) {

        return service.rateDelivery(id, rating);
    }
}
