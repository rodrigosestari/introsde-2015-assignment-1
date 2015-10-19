package assigment;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import dao.PeopleStore;
import dozerproject.delegate.PeopleBeanDelegate;
import dozerproject.entity.Person;

/**
 * This class:
 * 1) get the file resources/people.xml
 * 2) get all Person through printAllpeople()
 * 3) set a PeopleStore Domain Model
 * 4) transfor it a Json and put in the File resources/peopleJason.Json
 * 5) Print the result
 * 
 * @author sestari
 *
 */
public class Evaluation6 {

	public static void main(String[] args) {

		AssigmentOne objAssigmentOne = new AssigmentOne();

		//get xml
		File xmlPeople = new File("resources/people.xml");
		//file json to save
		File peopleJason = new File("resources/peopleJason.Json");

		System.out.println("runs instruction 3 based on Lab 4 (marshalling to JSON)");
		System.out.println("Make your java application to convert also JSON");

		if (xmlPeople.exists()) {

			ArrayList<Person> people = new ArrayList<>();
			try {
				//open xml
				objAssigmentOne.loadXML(xmlPeople);
				//get 3 People(un-marshal XML with Jaxb, and map with Dozer)
				people.add(objAssigmentOne.getPeople(Long.valueOf(1)));
				people.add(objAssigmentOne.getPeople(Long.valueOf(2)));
				people.add(objAssigmentOne.getPeople(Long.valueOf(3)));
				
				if (people != null) {
					PeopleStore peopleStore = new PeopleStore();
					peopleStore.setData(people);

					//marshal object PeopleStore into Json and put it into File peopleJason
					String line = PeopleBeanDelegate.storeToJson(peopleStore, peopleJason);

					//prin the resul
					System.out.println(line);

				}

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

		}

	}

}
