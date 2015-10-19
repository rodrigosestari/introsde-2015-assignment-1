package assigment;

import java.io.File;
import java.io.FileWriter;
import java.util.Date;

import people.generated.HealthprofileType;
import people.generated.People;
import people.generated.PersonType;
import util.JaxbUtil;
/**
 * This class:
 * 1)create 2 objects PersonType
 * 2) marshalling using JAXB into XML 
 * 3) create a file resources/peopleJaxb.xml with the XML
 * 
 * @author sestari
 *
 */
public class Evaluation4 {

	public static void main(String[] args) {

		//get xml
		File xmlPeopleJaxb = new File("resources/peopleJaxb.xml");
		//get xsd
		File xsdPeople = new File("resources/people.xsd");

		System.out.println("runs instruction 2 based on Lab 4 (marshalling to XML)");
		System.out.println(
				"Write a java application that does the marshalling and un-marshalling using JAXB and generated classes with JAXB XJC.");

		if (xsdPeople.exists()) {
			try {
				
				//create an object PersonType, this object was generated  automatically through Jaxb
				PersonType personOne = new PersonType();
				personOne.setId(21);
				personOne.setFirstname("Rodrigo Joni");
				personOne.setLastname("Sestari");
				//Converte the Date into dateToXmlGregorianCalendar
				personOne.setBirthdate(JaxbUtil.dateToXmlGregorianCalendar(new Date(87,10,4,3,34,0)));

				//profile type also generated automatically through jaxb
				HealthprofileType healthProfileOne = new HealthprofileType();
				healthProfileOne.setBmi(2);
				healthProfileOne.setHeight(60);
				healthProfileOne.setWeight(34);
				//Converte the Date into dateToXmlGregorianCalendar
				healthProfileOne.setLastupdate(JaxbUtil.dateToXmlGregorianCalendar(new Date()));
				personOne.setHealthprofile(healthProfileOne);

				//create second object
				PersonType personTwo = new PersonType();
				personTwo.setId(22);
				personTwo.setFirstname("Maria");
				personTwo.setLastname("Rozario");
				personTwo.setBirthdate(JaxbUtil.dateToXmlGregorianCalendar(new Date(90, 2, 10,4,2,0)));

				HealthprofileType healthProfileTwo = new HealthprofileType();
				healthProfileTwo.setBmi(34);
				healthProfileTwo.setHeight(90);
				healthProfileTwo.setWeight(64);
				healthProfileTwo.setLastupdate(JaxbUtil.dateToXmlGregorianCalendar(new Date()));
				personTwo.setHealthprofile(healthProfileTwo);
				
				
				//Third second object
				PersonType personThree = new PersonType();
				personThree.setId(23);
				personThree.setFirstname("Jose");
				personThree.setLastname("Maria");
				personThree.setBirthdate(JaxbUtil.dateToXmlGregorianCalendar(new Date(93, 1, 12,7,10,2)));

				HealthprofileType healthProfileThree = new HealthprofileType();
				healthProfileThree.setBmi(34);
				healthProfileThree.setHeight(76);
				healthProfileThree.setWeight(39);
				healthProfileThree.setLastupdate(JaxbUtil.dateToXmlGregorianCalendar(new Date()));
				personThree.setHealthprofile(healthProfileThree);

				People people = new People();
				people.getPerson().add(personOne);
				people.getPerson().add(personTwo);
				people.getPerson().add(personThree);

				//marshal the object people into XML through Jaxb
				String xml = JaxbUtil.jaxbToXml("people.generated", people, xsdPeople);

				//Print the result
				System.out.println(xml);

				// put the XML in xmlPeappleJaxb, it will be used in Evaluation5
				FileWriter fileWriter = new FileWriter(xmlPeopleJaxb);
				try {
					fileWriter.write(xml);
				} finally {
					fileWriter.flush();
					fileWriter.close();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}
