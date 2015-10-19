package dozerproject.transfer;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.dozer.Mapping;
import org.joda.time.DateTime;

import util.DateTimeAdapter;
/**
 * 
 * @author sestari
 *
 */
@XmlRootElement(name = "person")
@XmlType(propOrder = { "firstname", "lastname", "birthdate", "healthprofile" })
@XmlAccessorType(XmlAccessType.FIELD)
public class PersonBean {

	@Mapping("personId")
	@XmlAttribute
	private long id;

	@Mapping("firstName")
	private String firstname;

	@Mapping("lastName")
	private String lastname;

	@Mapping("birthDate")
	@XmlJavaTypeAdapter(type = DateTime.class, value = DateTimeAdapter.class)
	private DateTime birthdate;

	@Mapping("healthProfile")
	private HealthProfileBean healthprofile;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstname;
	}

	public void setFirstName(String firstName) {
		this.firstname = firstName;
	}

	public String getLastName() {
		return lastname;
	}

	public void setLastName(String lastName) {
		this.lastname = lastName;
	}

	public HealthProfileBean getHealthprofile() {
		return healthprofile;
	}

	public void setHealthprofile(HealthProfileBean healthprofile) {
		this.healthprofile = healthprofile;
	}

	public DateTime getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(DateTime birthdate) {
		this.birthdate = birthdate;
	}

	@Override
	public String toString() {
		return "PersonBean [id=" + id + ", firstName=" + firstname + ", lastName=" + lastname + ", healthprofile="
				+ healthprofile + ", birthdate=" + birthdate + "]";
	}

}
