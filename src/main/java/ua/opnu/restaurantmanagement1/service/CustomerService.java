package ua.opnu.restaurantmanagement1.service;

import ua.opnu.restaurantmanagement1.entity.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.opnu.restaurantmanagement1.repository.CustomerRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    public Customer getById(Long id) {
        return customerRepository.findById(id).orElseThrow();
    }

    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer update(Long id, Customer updated) {
        Customer c = getById(id);
        c.setFullName(updated.getFullName());
        c.setPhone(updated.getPhone());
        return customerRepository.save(c);
    }

    public void delete(Long id) {
        customerRepository.deleteById(id);
    }
}
