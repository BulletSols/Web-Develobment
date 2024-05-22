package com.megaStore.customer.dao;

import com.megaStore.abstractDao.MegaStoreAbstractDao;
import com.megaStore.customer.bean.Customer;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public class CustomerDao extends MegaStoreAbstractDao {
    public Customer getCustomerByCredantials(String email){
        try {
            String sql = "select * from customer where email = :email";
            MapSqlParameterSource parameterSource = new MapSqlParameterSource();
            parameterSource.addValue("email", email);
            return getNamedParameterJdbcTemplate().queryForObject(sql, parameterSource, new BeanPropertyRowMapper<>(Customer.class));
        } catch (EmptyResultDataAccessException e){
            throw new EmptyResultDataAccessException("No Record found for this user.", 1);
        }

    }

    @Transactional
    public void insertNewCustomer(Customer customer) {
        String customerSql = "INSERT INTO customer (first_name, last_name, email, password, phone_number, address, card_number, cvv, expiry_date) " +
                "VALUES (:firstName, :lastName, :email, :password, :phoneNumber, :address, :cardNumber, :cvv, :expiryDate)";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("firstName", customer.getFirstName());
        parameterSource.addValue("lastName", customer.getLastName());
        parameterSource.addValue("email", customer.getEmail());
        parameterSource.addValue("password", customer.getPassword());
        parameterSource.addValue("phoneNumber", customer.getPhoneNumber());
        parameterSource.addValue("address", customer.getAddress());
        parameterSource.addValue("cardNumber", customer.getCardNumber());
        parameterSource.addValue("cvv", customer.getCvv());
        parameterSource.addValue("expiryDate", customer.getExpiryDate());

        KeyHolder keyHolder = new GeneratedKeyHolder();
        getNamedParameterJdbcTemplate().update(customerSql, parameterSource, keyHolder, new String[] {"id"});

        Number key = keyHolder.getKey();
        if (key != null) {
            int customerId = key.intValue();

            String cartSql = "INSERT INTO cart (id, customer_id) VALUES (:id, :id)";
            MapSqlParameterSource cartParameterSource = new MapSqlParameterSource();
            cartParameterSource.addValue("id", customerId);
            getNamedParameterJdbcTemplate().update(cartSql, cartParameterSource);
        } else {
            throw new IllegalStateException("Failed to retrieve the customer ID.");
        }
    }

    public  void updateCustomer(Customer customer){
        String sql = "update customer set first_name= :firstName, last_name= :lastName, email= :email, password= :password, phone_number= :phoneNumber, address= :address, card_number= :cardNumber, cvv= :cvv, expiry_date= :expiryDate where id= :id;";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id", customer.getId());
        parameterSource.addValue("firstName", customer.getFirstName());
        parameterSource.addValue("lastName", customer.getLastName());
        parameterSource.addValue("email", customer.getEmail());
        parameterSource.addValue("password", customer.getPassword());
        parameterSource.addValue("phoneNumber", customer.getPhoneNumber());
        parameterSource.addValue("address", customer.getAddress());
        parameterSource.addValue("cardNumber", customer.getCardNumber());
        parameterSource.addValue("cvv", customer.getCvv());
        parameterSource.addValue("expiryDate", customer.getExpiryDate());
        getNamedParameterJdbcTemplate().update(sql, parameterSource);
    }

    public  void deleteCustomer(int id){
        String sql = "delete from customer where id= :id";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id", id);
        getNamedParameterJdbcTemplate().update(sql, parameterSource);
    }

}
