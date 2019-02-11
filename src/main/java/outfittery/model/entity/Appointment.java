package outfittery.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/***
 * This class represents Customer entity with all fields and validations
 * 
 * @author peter
 *
 */
@Entity
public class Appointment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@ManyToOne(fetch = FetchType.LAZY)
	private Stylist stylist;
	@ManyToOne(fetch = FetchType.LAZY)
	private Customer bookedBy;
	@NotNull
	@Temporal(TemporalType.DATE)
	private Date date;
	@NotNull
	@Column(name = "from_slot")
	private String from;
	@NotNull
	@Column(name = "to_slot")
	private String to;
	private Date createdAt = new Date();
	private Date bookedAt;

	protected Appointment() {
	}

	public Appointment(Stylist stylist, Customer customer, Date date, String from, String to) {
		this.stylist = stylist;
		this.bookedBy = customer;
		this.date = date;
		this.from = from;
		this.to = to;
	}

	public Long getId() {
		return id;
	}

	public Stylist getStylist() {
		return stylist;
	}

	public Customer getBookedBy() {
		return bookedBy;
	}

	public Date getDate() {
		return date;
	}

	public String getFrom() {
		return from;
	}

	public String getTo() {
		return to;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public Date getBookedAt() {
		return bookedAt;
	}

	@Override
	public String toString() {
		return String.format("Appointment[id=%d, Stylist='%d', Date='%s', From='%s', To='%s', bookedBy='%s']", id,
				stylist.getId(), date.toString(), from, to, bookedBy.getId());
	}

}