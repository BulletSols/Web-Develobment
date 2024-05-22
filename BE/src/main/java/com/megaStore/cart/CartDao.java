package com.megaStore.cart;

import com.megaStore.abstractDao.MegaStoreAbstractDao;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class CartDao  extends MegaStoreAbstractDao {

    public Cart getCartByCustomerId(int customerId) {
        try {
            String sql = "select c.cart_id , c.product_id, c.quantity as cartProductQuantity from cart_product c \n" +
                    "left join product p on c.product_id = p.id \n" +
                    "left join cart ca on c.cart_id = ca.id \n" +
                    " where ca.customer_id = :customerId";
            return getNamedParameterJdbcTemplate().queryForObject(sql, new MapSqlParameterSource().addValue("customerId", customerId), new CartRowMapper());
        } catch (EmptyResultDataAccessException e){
            return null;
        }

    }

    public void upsertCartRecord(int cartId, int productId, int quantity) {
        String sql = "INSERT INTO Cart_product (cart_id, product_id, quantity) " +
                "VALUES (:cart_id, :product_id, :quantity) " +
                "ON CONFLICT (cart_id, product_id) " +
                "DO UPDATE SET quantity = Cart_product.quantity + EXCLUDED.quantity";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("cart_id", cartId);
        parameterSource.addValue("product_id", productId);
        parameterSource.addValue("quantity", quantity);
        getNamedParameterJdbcTemplate().update(sql, parameterSource);
    }

    public void updateCartRecord(int cartId, int productId, int quantity) {
        String sql = "update Cart_product set quantity= :quantity where product_id = :product_id and cart_id = :cart_id";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("cart_id", cartId);
        parameterSource.addValue("product_id", productId);
        parameterSource.addValue("quantity", quantity);
        getNamedParameterJdbcTemplate().update(sql, parameterSource);
    }

    public void deleteCart(int cartId) {
        String sql = "delete from Cart_product where cart_id = :cart_id";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("cart_id", cartId);
        getNamedParameterJdbcTemplate().update(sql, parameterSource);
    }
}
