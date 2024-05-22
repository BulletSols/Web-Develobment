package com.megaStore.customer;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.megaStore.customer.bean.Customer;
import com.megaStore.customer.dao.CustomerDao;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import static com.megaStore.Utility.hashPassword;

@Service
public class CustomerService {
    
    private final CustomerDao customerDao;

    public CustomerService(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public Customer getCustomerByCredantials(String email, String password)  {
        try {
            Customer customer = customerDao.getCustomerByCredantials(email);
            if(BCrypt.verifyer().verify(password.toCharArray(), customer.getPassword()).verified)
                return customer;
            return null;
        } catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    public void insertNewCustomer(Customer customer) {
        // Hash the password before storing it (security best practice)
        customer.setPassword(hashPassword(customer.getPassword()));
        customerDao.insertNewCustomer(customer);
    }

    public void updateCustomer(Customer customer) {
        // Hash the password before storing it (security best practice)
        customer.setPassword(hashPassword(customer.getPassword()));
        customerDao.updateCustomer(customer);
    }

    public void deleteCustomer(int id) {
        customerDao.deleteCustomer(id);
    }

}
