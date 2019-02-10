package outfittery.model.repository;

import org.springframework.data.repository.CrudRepository;

import outfittery.model.entity.Appointment;

/***
 * This class contains all custom methods for Appointment entity
 * @author peter
 *
 */
public interface AppointmentRepository extends CrudRepository<Appointment, Long> {}