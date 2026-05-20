package com.app.ecom.cartItem;

import com.app.ecom.cartItem.dto.CartItemRequest;
import com.app.ecom.product.Product;
import com.app.ecom.product.ProductRepository;
import com.app.ecom.user.User;
import com.app.ecom.user.UserRepository;
import com.app.ecom.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartItemService {
    private final UserService userService;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public boolean addToCart(String userId, CartItemRequest request) {
        Optional<Product> productOpt = productRepository.findById(request.getProductId());
        if(productOpt.isEmpty()) {
            return false;
        }

        Product product = productOpt.get();
        if(product.getStockQuantity() < request.getQuantity()) {
            return false;
        }

        Optional<User> userOpt = userRepository.findById(Long.parseLong(userId));
        if(userOpt.isEmpty()) {
            return false;
        }

        User user = userOpt.get();

        CartItem cartItem = cartItemRepository.findByUserAndProduct(user, product);

        if(cartItem != null){
            cartItem.setQuantity(cartItem.getQuantity() + request.getQuantity());
            cartItem.setPrice(
                    cartItem.getPrice().add(
                            product.getPrice().multiply(BigDecimal.valueOf(request.getQuantity()))
                    )
            );
            product.setStockQuantity(product.getStockQuantity() - request.getQuantity());
            cartItemRepository.save(cartItem);
            productRepository.save(product);
        } else{
            CartItem cartItem1 = new CartItem();
            cartItem1.setUser(user);
            cartItem1.setProduct(product);
            cartItem1.setQuantity(request.getQuantity());
            cartItem1.setPrice(product.getPrice().multiply(BigDecimal.valueOf(request.getQuantity())));
            product.setStockQuantity(product.getStockQuantity() - request.getQuantity());
            cartItemRepository.save(cartItem1);
            productRepository.save(product);
        }

        return true;
    }
}
