package com.app.ecom.controller;

import com.app.ecom.dto.CartItemRequest;
import com.app.ecom.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @PostMapping
    public ResponseEntity<String> addToCart(
            @RequestHeader("X-USER-ID") String userId,
            @RequestBody CartItemRequest request) {
        if (!cartService.itemsToCart(userId, request)) {
            return ResponseEntity.badRequest().body("Product not found, user not found or product is out of stock");
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
