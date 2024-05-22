package com.megaStore.invoice.dao;

import com.megaStore.abstractDao.MegaStoreAbstractDao;
import com.megaStore.invoice.bean.Invoice;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import java.util.List;

public class InvoiceDao extends MegaStoreAbstractDao {

    public List<Invoice> getInvoices(){
        String sql = "select * from invoice;";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        return getNamedParameterJdbcTemplate().query(sql, parameterSource, new BeanPropertyRowMapper<>(Invoice.class));
    }

    public void insertNewInvoice(Invoice invoice){
        String sql = "insert into invoice(customer_id, status) VALUES (:customer_id, 'draft');";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("customer_id", invoice.getCustomer_id());
        getNamedParameterJdbcTemplate().update(sql, parameterSource);
    }

    public  void updateInvoice(Invoice invoice){
        String sql = "update invoice set customer_id= :customer_id, status= :status where id= :id;";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id", invoice.getId());
        parameterSource.addValue("customer_id", invoice.getCustomer_id());
        parameterSource.addValue("status", invoice.getStatus());
        getNamedParameterJdbcTemplate().update(sql, parameterSource);
    }

    public  void deleteInvoice(Invoice invoice){
        String sql = "delete from invoice where id= :id";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id", invoice.getId());
        getNamedParameterJdbcTemplate().update(sql, parameterSource);
    }
}
