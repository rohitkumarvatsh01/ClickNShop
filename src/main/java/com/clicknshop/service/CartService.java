package com.clicknshop.service;

import org.springframework.stereotype.Service;

import com.clicknshop.model.CartItem;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {
    private List<CartItem> cartItems = new ArrayList<>();

    public void addToCart(CartItem item) {
        for (CartItem cartItem : cartItems) {
            if (cartItem.getId().equals(item.getId())) {
                cartItem.setQuantity(cartItem.getQuantity() + 1);
                return;
            }
        }
        cartItems.add(item);
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void clearCart() {
        cartItems.clear();
    }
}
