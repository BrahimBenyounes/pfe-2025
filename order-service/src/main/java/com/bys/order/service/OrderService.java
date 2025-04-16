package com.bys.order.service;

import com.bys.order.dto.request.OrderRequest;
import com.bys.order.dto.response.OrderResponse;
import com.bys.order.dto.response.ProductStockResponse;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    public OrderResponse createOrder(OrderRequest orderRequest);

    public List<OrderResponse> getAllOrders();

    public Optional<OrderResponse> getOrderById(Long id);

    public void deleteOrderById(Long id);

    public ProductStockResponse getProductStock(String id, Double stock);
}