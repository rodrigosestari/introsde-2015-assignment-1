package dozerproject.transfer;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
/**
 * 
 * @author sestari
 *
 */
@XmlRootElement(name = "people")
@XmlAccessorType(XmlAccessType.FIELD)
public class PeopleBean {

	private List<PersonBean> person;

	public List<PersonBean> getPerson() {
		return person;
	}

	public void setPerson(List<PersonBean> person) {
		System.out.println("setPerson size " + person.size());
		this.person = person;
	}

}
