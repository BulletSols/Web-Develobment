package com.megaStore.product_invoice.dao;

import com.megaStore.abstractDao.MegaStoreAbstractDao;
import com.megaStore.invoice.bean.Invoice;
import com.megaStore.product_invoice.bean.Product_Invoice;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import java.util.List;

public class Product_InvoiceDao extends MegaStoreAbstractDao {
    public List<Product_Invoice> getProductsInInvoices(){
        String sql = "select * from product_invoice;";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        return getNamedParameterJdbcTemplate().query(sql, parameterSource, new BeanPropertyRowMapper<>(Product_Invoice.class));
    }

    public void insertNewProductToInvoice(Product_Invoice draft){
        String sql = "insert into product_invoice (product_id, invoice_id) VALUES (:product_id, :invoice_id)";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("product_id", draft.getProduct_id());
        parameterSource.addValue("invoice_id", draft.getInvoice_id());
        getNamedParameterJdbcTemplate().update(sql, parameterSource);
    }

    public  void updateProductsInInvoice(Product_Invoice draft){
        String sql = "update product_invoice set product_id= :product_id, invoice_id= :invoice_id where id= :id;";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id", draft.getId());
        parameterSource.addValue("product_id", draft.getProduct_id());
        parameterSource.addValue("invoice_id", draft.getInvoice_id());
        getNamedParameterJdbcTemplate().update(sql, parameterSource);
    }

    public  void deleteInvoice(Product_Invoice draft){
        String sql = "delete from product_invoice where id= :id";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id", draft.getId());
        getNamedParameterJdbcTemplate().update(sql, parameterSource);
    }
}
