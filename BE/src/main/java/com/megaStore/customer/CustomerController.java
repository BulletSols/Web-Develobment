package com.megaStore.customer;

import com.megaStore.customer.bean.Customer;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/customer") // Base path for customer endpoints
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/login")
    public ResponseEntity<Customer> login(@RequestBody LoginCredentials credentials){
        Customer customer = customerService.getCustomerByCredantials(credentials.getEmail(), credentials.getPassword());
        if (customer == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(customer);
    }

    @PostMapping("/signup")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        customerService.insertNewCustomer(customer);
        return ResponseEntity.status(HttpStatus.CREATED).build(); // Created (201) response
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCustomer(@PathVariable int id, @RequestBody Customer customer) {
        customer.setId(id); // Ensure ID from path matches customer object
        customerService.updateCustomer(customer);
        return ResponseEntity.noContent().build(); // No Content (204) response
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable int id) {
        customerService.deleteCustomer(id); // Assuming ID is stored as int in the database
        return ResponseEntity.noContent().build(); // No Content (204) response
    }

    @Data
    public static class LoginCredentials {
        private String email;
        private String password;

    }

}

