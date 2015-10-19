package dozerproject.entity;

import org.joda.time.DateTime;
/**
 * 
 * @author sestari
 *
 */
public class Person {

	private long personId;
	private String firstName;
	private String lastName;
	private DateTime birthDate;
	private HealthProfile healthProfile;

	public long getPersonId() {
		return personId;
	}

	public void setPersonId(long personId) {
		this.personId = personId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public DateTime getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(DateTime birthDate) {
		this.birthDate = birthDate;
	}

	public HealthProfile getHealthProfile() {
		return healthProfile;
	}

	public void setHealthProfile(HealthProfile healthProfile) {
		this.healthProfile = healthProfile;
	}
	

	public Person(long personId, String firstName, String lastName, DateTime birthDate, HealthProfile healthProfile) {
		super();
		this.personId = personId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.healthProfile = healthProfile;
	}

	@Override
	public String toString() {
		return "Person [personId=" + personId + ", firstName=" + firstName + ", lastName=" + lastName + ", birthDate="
				+ birthDate + ", healthProfile=" + healthProfile + "]";
	}

	public Person() {
		super();
		// TODO Auto-generated constructor stub
	}

	

}
