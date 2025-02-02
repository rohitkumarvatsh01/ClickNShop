package com.clicknshop.controller;

import com.clicknshop.model.CartItem;
import com.clicknshop.model.Product;
import com.clicknshop.service.CartService;
import com.clicknshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/user-login")
    public String showUserLoginForm(Model model) {
        List<Product> products = productService.getAllProduct();
        model.addAttribute("products", products);
        return "user-login"; 
    }

    @PostMapping("/user-login")
    public String userLogin() {
        return "redirect:/"; 
    }
    
    @GetMapping("/user-products")
    public String showAllProductsUser(Model model) {
        List<Product> products = productService.getAllProduct();
        model.addAttribute("products", products);
        return "user-products";
    }

    @GetMapping("/admin-login")
    public String showAdminLoginForm() {
        return "admin-login"; 
    }

    @PostMapping("/admin-login")
    public String adminLogin() {
        return "redirect:/";
    }

    @GetMapping("/create")
    public String showAddProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "add-product";
    }

    @PostMapping("/create")
    public String addProduct(@ModelAttribute("product") Product product) {
        productService.createNewProduct(product);
        return "redirect:/product/viewAll";
    }

    @GetMapping("/viewAll")
    public String showAllProducts(Model model) {
        List<Product> products = productService.getAllProduct();
        model.addAttribute("products", products);
        return "all-products";
    }
    
    @GetMapping("/update/{id}")
    public String showUpdateProductForm(@PathVariable("id") Long id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "update-product";
    }
    
    @PostMapping("/update/{id}")
    public String updateProduct(@PathVariable("id") Long id, @ModelAttribute("product") Product product) {
        productService.updateProduct(id, product);
        return "redirect:/product/viewAll";
    }

    @PostMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProductById(id);
        return "redirect:/product/viewAll";
    }
    
    @Autowired
    private CartService cartService;

    @PostMapping("/add-to-cart")
    @ResponseBody
    public String addToCart(@RequestParam Long id) {
        Product product = productService.getProductById(id);
        CartItem cartItem = new CartItem();
        cartItem.setId(product.getId());
        cartItem.setName(product.getName());
        cartItem.setImageUrl(product.getImageUrl());
        cartItem.setPrice(product.getPrice());
        cartItem.setQuantity(1);
        cartService.addToCart(cartItem);
        return "Product added to cart";
    }

    @GetMapping("/cart")
    public String showCart(Model model) {
        List<CartItem> cartItems = cartService.getCartItems();
        model.addAttribute("cartItems", cartItems);
        return "cart";
    }
}
//
