package com.megaStore.product.productRowMapper;

import com.megaStore.abstractDao.abstractRowMapper.AbstractRowMapper;
import com.megaStore.product.bean.Product;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductRowMapper extends AbstractRowMapper<Product> {
    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
        Product product = new Product();
        if(hasColoumn(rs, "id"))
            product.setId(rs.getInt("id"));
        if(hasColoumn(rs, "name"))
            product.setName(rs.getString("name"));
        if(hasColoumn(rs, "price"))
            product.setPrice(rs.getDouble("price"));
        if(hasColoumn(rs, "description"))
            product.setDescription(rs.getString("description"));
        if(hasColoumn(rs, "quantity"))
            product.setQuantity(rs.getInt("quantity"));
        if(hasColoumn(rs, "img_path"))
            product.setImg_path(rs.getString("img_path"));
        return product;
    }
}
