package dozerproject.entity;

import org.joda.time.DateTime;
/**
 * 
 * @author sestari
 *
 */
public class HealthProfile {


	private DateTime lastupdate;
	private double weight;
	private double height;
	private double bmi;
	
	
	public DateTime getLastupdate() {
		return lastupdate;
	}
	public void setLastupdate(DateTime lastupdate) {
		this.lastupdate = lastupdate;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	public double getBmi() {
		return bmi;
	}
	public void setBmi(double bmi) {
		this.bmi = bmi;
	}
	@Override
	public String toString() {
		return "HealthProfile [lastupdate=" + lastupdate + ", weight=" + weight + ", height=" + height + ", bmi=" + bmi
				+ "]";
	}
	public HealthProfile() {
		super();
		// TODO Auto-generated constructor stub
	}
	public HealthProfile(double weight, double height, double bmi) {
		super();
		this.weight = weight;
		this.height = height;
		this.bmi = bmi;
		this.lastupdate = new DateTime();
	}

	
	
	

}
