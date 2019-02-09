package outfittery.model.repository;

import org.springframework.data.repository.CrudRepository;

import outfittery.model.entity.Stylist;

/***
 * This class contains all custom methods for Stylist entity
 * @author peter
 *
 */
public interface StylistRepository extends CrudRepository<Stylist, Long> {    
    Boolean existsByEmail(String email);
}