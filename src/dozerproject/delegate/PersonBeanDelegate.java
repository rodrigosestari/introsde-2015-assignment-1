package dozerproject.delegate;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.dozer.DozerBeanMapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;

import dozerproject.entity.Person;
import dozerproject.transfer.PersonBean;
/**
 * 
 * @author sestari
 *
 */
public class PersonBeanDelegate {

	public final static List<String> myMappingFiles = Arrays.asList("dozerMappings.xml");

	
	/**
	 * This function map a Person Domain Model into a PersonBean
	 * Presentation Layer using Dozer
	 * 
	 * @param person
	 * an object dozerproject.entity.PeopleStore
	 * @return 
	 * an object dozerproject.transfer.PersonBean
	 */
	public static PersonBean mapFromPerson(Person person) {
		DozerBeanMapper mapper = new DozerBeanMapper();
		mapper.setMappingFiles(myMappingFiles);
		return (PersonBean) mapper.map(person, PersonBean.class);
	}

	/**
	 * This function map a PersonBean Presentation Layer into a
	 * Person Domain Model using Dozer
	 * 
	 * @param bean
	 * an object dozerproject.transfer.PersonBean
	 * @return 
	 * an object dozerproject.entity.Person
	 */
	public static Person mapToPerson(PersonBean bean) {
		DozerBeanMapper mapper = new DozerBeanMapper();
		mapper.setMappingFiles(myMappingFiles);
		return (Person) mapper.map(bean, Person.class);
	}

	/**
	 * This function marshal a PersonBean Presentation Layer object into
	 * XML susing JAXB
	 * 
	 * @param bean
	 * an object dozerproject.transfer.PeopleBean
	 * @return 
	 * a String that contains the xml node relative a Person
	 */
	public static String marshal(PersonBean bean) {
		StringWriter writer = new StringWriter();
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(PersonBean.class);
			Marshaller marshaller = jaxbContext.createMarshaller();
			marshaller.setProperty("jaxb.formatted.output", new Boolean(true));
			marshaller.marshal(bean, writer);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return writer.toString();
	}

	/**
	 * This function un-marshal XML back into PersonBean Presentation Layer
	 * object using JAXB
	 * 
	 * @param xml
	 * a String that represent xml node Person
	 * @return 
	 * an object dozerproject.transfer.PersonBean
	 */
	public static PersonBean unmarshal(String xml) {

		StringReader reader = new StringReader(xml);
		PersonBean bean = null;
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(PersonBean.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			bean = (PersonBean) unmarshaller.unmarshal(reader);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return bean;
	}

	/**
	 * This function firstly map a Person Domain Model into 
	 * PersonBean Presentation Layer through Dozer, 
	 * later get it and map into Json.
	 * 
	 * @param person
	 * an object dozerproject.entity.Person
	 * @return 
	 * a String that contains the a Json representation 
	 */
	public static String personToJson(Person person) {
		String result = null;
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.registerModule(new JaxbAnnotationModule());

			mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
			mapper.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);

			mapper.configure(com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

			PersonBean bean = PersonBeanDelegate.mapFromPerson(person);
			result = mapper.writeValueAsString(bean);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

}
