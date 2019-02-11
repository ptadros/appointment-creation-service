package outfittery.model.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import outfittery.model.dto.TimeSlot;
import outfittery.model.entity.Appointment;

/***
 * This class contains all custom methods for Appointment entity
 * @author peter
 *
 */
public interface AppointmentRepository extends CrudRepository<Appointment, Long> {
	
	/**
     * Finds a person by using the last name as a search criteria.
     * @param lastName
     * @return  A list of persons whose last name is an exact match with the given last name.
     *          If no persons is found, this method returns an empty list.
     */
    @Query("SELECT new outfittery.model.dto.TimeSlot\n" + 
    		"(app.date, app.from) FROM Appointment app WHERE app.date > :fromDate GROUP BY app.date, app.from ORDER BY app.date, app.from")
    public List<TimeSlot> getAvailableSlots(@Param("fromDate") Date fromDate);
}