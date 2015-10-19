package assigment;

import java.io.File;

import dozerproject.entity.HealthProfile;
/**
 * This class print the HealthProfile from
 * a Person with id=5
 * 
 * @author sestari
 *
 */
public class Evaluation2 {

	public static void main(String[] args) {

		AssigmentOne objAssigmentOne = new AssigmentOne();

		System.out
				.println("runs instruction 3 based on Lab 3 with id = 5 (make sure you have a person with such an id)");
		System.out.println(
				"A function that accepts id as parameter and prints the HealthProfile of the person with that id");

		//get the file xml
		File xmlPeople = new File("resources/people.xml");

		if (xmlPeople.exists()) {

			try {
				//open xml
				objAssigmentOne.loadXML(xmlPeople);
				//get profile from people id 5(um-marshal xml with jaxb and map HealthProfile with Dozer)
				HealthProfile profile = objAssigmentOne.getHealthProfile(Long.valueOf(5));
				//print the profile
				System.out.println(profile.toString());
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

		}

	}

}
