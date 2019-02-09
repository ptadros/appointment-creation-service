package outfittery.model.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import outfittery.model.entity.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

    List<Customer> findByEmail(String lastName);
}