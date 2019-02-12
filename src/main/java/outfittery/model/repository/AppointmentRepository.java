package outfittery.model.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
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
     * Finds available time slots after given data
     * @param fromDate
     * @return  A list of TimeSlot objects.
     */
    @Query("SELECT new outfittery.model.dto.TimeSlot\n" + 
    		"(app.date, app.from) FROM Appointment app WHERE app.date > :fromDate and app.bookedBy IS NULL GROUP BY app.date, app.from ORDER BY app.date, app.from")
    public List<TimeSlot> getAvailableSlots(@Param("fromDate") Date fromDate);
    
    /***
     * Get first available slot by date and slot
     * @param date
     * @param slot
     * @return
     */
    @Query("SELECT app FROM Appointment app WHERE app.date = :date and app.from = :fromSlot and app.bookedBy IS NULL")
    public List<Appointment> findAvailableSlotByDateAndTime(@Param("date") Date date, @Param("fromSlot") String fromSlot);
}