
package dozerproject.transfer;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.dozer.Mapping;
import org.joda.time.DateTime;

import util.DateTimeAdapter;
/**
 * 
 * @author sestari
 *
 */
@XmlRootElement(name = "healthprofile")
@XmlAccessorType(XmlAccessType.FIELD)
public class HealthProfileBean {

	@Mapping("lastupdate")
	@XmlJavaTypeAdapter(type = DateTime.class, value = DateTimeAdapter.class)
	private DateTime lastupdate;
	
	@Mapping("weight")
	private double weight;
	@Mapping("height")
	private double height;
	@Mapping("bmi")
	private double bmi;



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


	public DateTime getLastupdate() {
		return lastupdate;
	}

	public void setLastupdate(DateTime lastupdate) {
		this.lastupdate = lastupdate;
	}

	@Override
	public String toString() {
		return "HealthProfileBean [weight=" + weight + ", height=" + height + ", bmi=" + bmi + ", lastUpdate="
				+ lastupdate + "]";
	}

}
