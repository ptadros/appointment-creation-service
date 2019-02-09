package outfittery.model.repository;

import org.springframework.data.repository.CrudRepository;

import outfittery.model.entity.Customer;

/***
 * This class contains all custom methods for Customer entity
 * @author peter
 *
 */
public interface CustomerRepository extends CrudRepository<Customer, Long> {    
    Boolean existsByEmail(String email);
}