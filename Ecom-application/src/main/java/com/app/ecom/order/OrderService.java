package com.app.ecom.order;

import com.app.ecom.cartItem.CartItem;
import com.app.ecom.cartItem.CartItemRepository;
import com.app.ecom.cartItem.CartItemService;
import com.app.ecom.order.dto.OrderItemDto;
import com.app.ecom.order.dto.OrderResponse;
import com.app.ecom.user.User;
import com.app.ecom.user.UserRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private final UserRepository userRepository;
    private final CartItemRepository cartItemRepository;
    private final OrderRepository orderRepository;
    private final CartItemService cartItemService;

    public OrderService(UserRepository userRepository, CartItemRepository cartItemRepository, OrderRepository orderRepository, CartItemService cartItemService) {
        this.userRepository = userRepository;
        this.cartItemRepository = cartItemRepository;
        this.orderRepository = orderRepository;
        this.cartItemService = cartItemService;
    }


    public OrderResponse createOrder(String userId) {

        User user = userRepository.findById(Long.parseLong(userId))
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<CartItem> cartItems = cartItemRepository.findByUser(user);

        if(cartItems.isEmpty()){
            return OrderResponse.builder()
                    .build();
        }

        BigDecimal totalAmount = cartItems.stream()
                .map(CartItem::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        Order order = new Order();
        order.setUser(user);
        order.setStatus(OrderStatus.CONFIRMED);
        order.setTotalAmount(totalAmount);
        order.setItem(
                cartItems.stream()
                        .map(item -> new OrderItem(
                                null,
                                item.getProduct(),
                                item.getQuantity(),
                                item.getPrice(),
                                order
                        ))
                        .collect(Collectors.toList())
        );

        Order savedOrder = orderRepository.save(order);

        cartItemService.clearCart(userId);

        return mapToOrderResponse(savedOrder);
    }

    private OrderResponse mapToOrderResponse(Order savedOrder){
        return new OrderResponse(
                savedOrder.getId(),
                savedOrder.getTotalAmount(),
                savedOrder.getStatus(),
                savedOrder.getItem().stream()
                        .map(orderItem -> new OrderItemDto(
                                orderItem.getId(),
                                orderItem.getProduct().getId(),
                                orderItem.getQuantity(),
                                orderItem.getPrice()
                        )).toList(),
                savedOrder.getCreatedAt()
        );
    }
}
