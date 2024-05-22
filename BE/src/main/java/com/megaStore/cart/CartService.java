package com.megaStore.cart;

import com.megaStore.product.ProductService;
import com.megaStore.product.bean.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CartService {
    
    private final CartDao cartDao;
    private final ProductService productService;

    public CartService(CartDao cartDao, ProductService productService) {
        this.cartDao = cartDao;
        this.productService = productService;
    }

    public Cart getCartByCustomerId(int id) {
        Cart cart = cartDao.getCartByCustomerId(id);
        if (cart != null){
            cart.getQuantities().keySet().stream().forEach(productId -> {
                if (cart.getProducts() == null)
                    cart.setProducts(new ArrayList<>());
                Product product = productService.getProductById(productId);
                cart.getProducts().add(product);
            });
        }
        return cart;
    }
    
    public void upsertCartRecord(Cart cart) {
        cart.getQuantities().entrySet().stream().forEach(entry -> {
            cartDao.upsertCartRecord(cart.getId(), entry.getKey(), entry.getValue());
        });
    }

    public void updateCartRecord(Cart cart) {
        cart.getQuantities().entrySet().stream().forEach(entry -> {
            cartDao.updateCartRecord(cart.getId(), entry.getKey(), entry.getValue());
        });
    }

    public void deleteCart(int id) {
            cartDao.deleteCart(id);
    }
}
