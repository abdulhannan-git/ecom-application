package com.app.ecom.services;

import com.app.ecom.dto.CartItemRequest;
import com.app.ecom.model.CartItem;
import com.app.ecom.model.Product;
import com.app.ecom.model.User;
import com.app.ecom.repositories.CartRepository;
import com.app.ecom.repositories.ProductRepository;
import com.app.ecom.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public boolean itemsToCart(String userId, CartItemRequest request) {
        Optional<Product> productOpt = productRepository.findById(request.getProductId());
        if (productOpt.isEmpty()) {
            return false;
        }
        Product product = productOpt.get();
        if (product.getStockQuantity() < request.getQuantity()) {
            return false;
        }
        Optional<User> userOpt = userRepository.findById(Long.valueOf(userId));
        if (userOpt.isEmpty()) {
            return false;
        }
        User user = userOpt.get();
        CartItem existingCartItem = cartRepository.findByUserAndProduct(user, product);
        if (existingCartItem != null) {
            existingCartItem.setQuantity(existingCartItem.getQuantity() + request.getQuantity());
            existingCartItem.setPrice(product.getPrice().multiply(BigDecimal.valueOf(existingCartItem.getQuantity())));
            cartRepository.save(existingCartItem);
        } else {
            CartItem newCart = new CartItem();
            newCart.setUser(user);
            newCart.setProduct(product);
            newCart.setQuantity(request.getQuantity());
            newCart.setPrice(product.getPrice().multiply(BigDecimal.valueOf(request.getQuantity())));
            cartRepository.save(newCart);
        }
        System.out.println("User exists? " + userRepository.findById(Long.valueOf(userId)).isPresent());
        System.out.println("All users: " + userRepository.findAll());

        return true;
    }

}
