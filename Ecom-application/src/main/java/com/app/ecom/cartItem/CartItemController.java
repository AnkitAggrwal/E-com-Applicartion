package com.app.ecom.cartItem;

import com.app.ecom.cartItem.dto.CartItemRequest;
import com.app.ecom.cartItem.dto.CartItemResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart-item")
public class CartItemController {
    private final CartItemService cartItemService;

    public CartItemController(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addToCart(@RequestHeader("X-User-ID") String userId,
                                          @RequestBody CartItemRequest request){
        if(!cartItemService.addToCart(userId, request)){
            return ResponseEntity.badRequest().body("Product out of stock or user not found or product not found");
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/remove/{productId}")
    public ResponseEntity<String> removeFromCart(@RequestHeader("X-User-ID") String userId,
                                             @PathVariable Long productId){
        if(!cartItemService.removeFromCart(userId, productId)){
            return ResponseEntity.badRequest().body("Product not in cart or user not found");
        }
        return ResponseEntity.ok("Product removed from cart");
    }

    @GetMapping("/get-all")
    public ResponseEntity<CartItemResponse> getCartItems(@RequestHeader("X-User-ID") String userId){
        return ResponseEntity.ok(cartItemService.getCartItems(userId));
    }
}
