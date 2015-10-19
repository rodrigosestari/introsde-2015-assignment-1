package assigment;

import java.io.File;
import java.util.List;

import dozerproject.entity.Person;
/**
 * This class print
 * all person that having 
 * Weight '>' 90
 * 
 * @author sestari
 *
 */
public class Evaluation3 {
	public static void main(String[] args) {

		AssigmentOne objAssigmentOne = new AssigmentOne();

		//get xml
		File xmlPeople = new File("resources/people.xml");
		System.out.println("runs instruction 4 based on Lab 3 with weight > 90");
		System.out.println(
				"A function which accepts a weight and an operator (=, > , <) as parameters and prints people that fulfill that condition (i.e., >80Kg, =75Kg, etc.).");

		if (xmlPeople.exists()) {

			try {
				//open xml
				objAssigmentOne.loadXML(xmlPeople);
				//get all person where weight > 90 (um-marshal Xml with Jaxb, map Person with Dozer)
				List<Person> people = objAssigmentOne.weightOperator(">", 90);
				if (people != null) {
					//Print all person with weight > 90
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
