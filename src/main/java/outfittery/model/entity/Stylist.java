package outfittery.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
/***
 * This class represents Stylist entity with all fields and validations
 * @author peter
 *
 */
@Entity
public class Stylist {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotNull
	private String name;
	@Column(unique = true)
	@NotNull
	private String email;

	protected Stylist() {
	}

	public Stylist(String name, String email) {
		this.name = name;
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public String toString() {
		return String.format("Stylist[id=%d, name='%s', email='%s']", id, name, email);
	}

}