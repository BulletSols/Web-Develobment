package com.megaStore.product.dao;

import com.megaStore.abstractDao.MegaStoreAbstractDao;
import com.megaStore.product.bean.Product;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDao extends MegaStoreAbstractDao {

    public List<Product> getProducts(){
        String sql = "select * from product;";
        return getNamedParameterJdbcTemplate().query(sql, new MapSqlParameterSource(), new BeanPropertyRowMapper<>(Product.class));
    }

    public Product getProductById(int id){
        String sql = "select * from product where id = :id;";
        return getNamedParameterJdbcTemplate().queryForObject(sql, new MapSqlParameterSource().addValue("id", id), new BeanPropertyRowMapper<>(Product.class));
    }

    public int getNumberOfProducts(){
        String sql = "select count(*) from product;";
        return getNamedParameterJdbcTemplate().queryForObject(sql, new MapSqlParameterSource(), Integer.class);
    }

    public void insertNewProduct(Product product){
        String sql = "insert into product(name, price, description, quantity) VALUES (:name, :price, :description, :quantity);";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("name", product.getName());
        parameterSource.addValue("price", product.getPrice());
        parameterSource.addValue("description", product.getDescription());
        parameterSource.addValue("quantity", product.getQuantity());
        parameterSource.addValue("img_path", product.getImg_path());
        getNamedParameterJdbcTemplate().update(sql, parameterSource);
    }

    public  void updateProduct(Product product){
        String sql = "update product set name = :name, price = :price, description= :description, quantity= :quantity where id= :id;";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id", product.getId());
        parameterSource.addValue("name", product.getName());
        parameterSource.addValue("price", product.getPrice());
        parameterSource.addValue("description", product.getDescription());
        parameterSource.addValue("quantity", product.getQuantity());
        parameterSource.addValue("img_path", product.getImg_path());
        getNamedParameterJdbcTemplate().update(sql, parameterSource);
    }

    public  void deleteProduct(int id){
        String sql = "delete from product where id= :id";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id", id);
        getNamedParameterJdbcTemplate().update(sql, parameterSource);
    }
}
