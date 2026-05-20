package com.app.ecom.cartItem;

import com.app.ecom.product.Product;
import com.app.ecom.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {


    CartItem findByUserAndProduct(User user, Product product);
}
