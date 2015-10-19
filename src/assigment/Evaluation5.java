package assigment;

import java.io.File;

import people.generated.People;
import people.generated.PersonType;
import util.JaxbUtil;

/**
 * 
 * This class:
 * 1) get the file  resources/peopleJaxb.xml created in Evaluation4
 * 2) un-marshalling using JAXB into object People 
 * 3) print the result
 * 
 * @author sestari
 *
 */
public class Evaluation5 {

	public static void main(String[] args) {

		//get xml created in Evaluation4
		File xmlPeopleJaxb = new File("resources/peopleJaxb.xml");
		//get xsd
		File xsdPeople = new File("resources/people.xsd");

		System.out.println("runs instruction 2 based on Lab 4 (unmarshalling from XML)");
		System.out.println(
				"Write a java application that does the marshalling and un-marshalling using JAXB and generated classes with JAXB XJC.");

		if (xsdPeople.exists() && xmlPeopleJaxb.exists()) {
			try {

				//um-marshal Xml into object people,
				People people = (People) JaxbUtil.xmlToJaxb("people.generated", xmlPeopleJaxb, xsdPeople);

				if (people != null) {
					
					//Print the result
					for (PersonType person : people.getPerson()) {

						System.out.println("PersonType [ id=" + person.getId() + ", firstname=" + person.getFirstname()
								+ ", lastname=" + person.getLastname() + ", birthdate="
								+ JaxbUtil.xmlGregorianCalendarToDate(person.getBirthdate())
								+ " HealthprofileType [lastupdate="
								+ JaxbUtil.xmlGregorianCalendarToDate(person.getHealthprofile().getLastupdate())
								+ ", weight=" + person.getHealthprofile().getWeight() + ", height="
								+ person.getHealthprofile().getHeight() + ", bmi=" + person.getHealthprofile().getBmi()
								+ "] ]");

					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}
