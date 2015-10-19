package assigment;

import java.io.File;
import java.util.List;

import dozerproject.entity.Person;

/**
 * 
 * This class executes Print all Person contained in people.xml
 * 
 * @author sestari
 *
 */
public class Evaluation1 {

	public static void main(String[] args) {

		AssigmentOne objAssigmentOne = new AssigmentOne();

		System.out.println("runs instruction 2 based on Lab 3");
		System.out.println("Make a function that prints all people in the list with detail");

		File xmlPeople = new File("resources/people.xml");

		if (xmlPeople.exists()) {

			try {
				//get xml
				objAssigmentOne.loadXML(xmlPeople);
				//get list with all People(un-marshal XML with Jaxb, and map with Dozer)
				List<Person> people = objAssigmentOne.printAllpeople();
				if (people != null) {
					//Print all person
					for (Person person : people) {
						System.out.println(person.toString());
					}
				}

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

		}

	}
}
