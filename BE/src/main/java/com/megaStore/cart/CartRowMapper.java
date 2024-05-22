package com.megaStore.cart;

import com.megaStore.abstractDao.abstractRowMapper.AbstractRowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class CartRowMapper extends AbstractRowMapper<Cart> {
    @Override
    public Cart mapRow(ResultSet rs, int rowNum) throws SQLException {
        Cart cart = new Cart();
        Map<Integer, Integer> quantities = new HashMap<>();
        if (hasColoumn(rs, "cart_id"))
            cart.setId(rs.getInt("cart_id"));

        do {
            int productId = 0;
            int quantity = 0;
            if (hasColoumn(rs, "cartProductQuantity"))
                quantity = rs.getInt("cartProductQuantity");
            if (hasColoumn(rs, "product_id"))
                productId = rs.getInt("product_id");

            quantities.put(productId, quantity);

        } while (rs.next()); // Process rows for the current cart only

        cart.setQuantities(quantities);
        return cart;
    }
}
