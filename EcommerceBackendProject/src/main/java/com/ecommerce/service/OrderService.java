package com.ecommerce.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ecommerce.entity.Order;
import com.ecommerce.entity.Product;
import com.ecommerce.repository.OrderRepository;
import com.ecommerce.repository.ProductRepository;

@Service
public class OrderService {

    private OrderRepository orderRepo;
    private ProductRepository productRepo;

    public OrderService(OrderRepository orderRepo, ProductRepository productRepo) {
        this.orderRepo = orderRepo;
        this.productRepo = productRepo;
    }

    public Order buyProduct(Long productId, int qty, String payment, String address) {

        Product p = productRepo.findById(productId).orElse(null);

        if (p == null) {
            return null;
        }

        if (qty <= 0 || qty > p.getQuantity()) {
            return null;
        }

        if (!payment.equalsIgnoreCase("UPI")
                && !payment.equalsIgnoreCase("CARD")
                && !payment.equalsIgnoreCase("COD")) {
            return null;
        }

        double oneItemPrice = p.getPrice();

        if (p.getDiscount() > 0) {
            oneItemPrice = oneItemPrice
                    - (oneItemPrice * p.getDiscount() / 100);
        }

        double total = oneItemPrice * qty;

        Order order = new Order();

        order.setProductId(p.getId());
        order.setProductName(p.getName());
        order.setQuantity(qty);
        order.setAmount(total);
        order.setPayment(payment.toUpperCase());
        order.setAddress(address);
        order.setStatus("Order Placed");
        order.setDeliveryRating(0);

        p.setQuantity(p.getQuantity() - qty);
        productRepo.save(p);

        // simple payment simulation
        if (!payment.equalsIgnoreCase("COD")) {
            System.out.println("Payment processing...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.println("Payment successful");
        }

        return orderRepo.save(order);
    }

    public List<Order> getOrders() {
        return orderRepo.findAll();
    }

    public Order getOrder(Long id) {
        return orderRepo.findById(id).orElse(null);
    }

    public String trackOrder(Long id) {

        Order order = orderRepo.findById(id).orElse(null);

        if (order == null) {
            return "Order not found";
        }

        return "Chennai ----- * ----- Guntur ----- * ----- Bayyavaram"

                + "Current status: " + order.getStatus();
    }

    public String rateDelivery(Long id, int rating) {

        Order order = orderRepo.findById(id).orElse(null);

        if (order == null) {
            return "Order not found";
        }

        if (rating < 1 || rating > 5) {
            return "Rating should be between 1 and 5";
        }

        order.setDeliveryRating(rating);
        orderRepo.save(order);

        return "Thank you for your review";
    }
}
