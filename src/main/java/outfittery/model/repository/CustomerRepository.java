package outfittery.model.repository;

import org.springframework.data.repository.CrudRepository;

import outfittery.model.entity.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {    
    Boolean existsByEmail(String email);
}