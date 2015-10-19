package dozerproject.delegate;

import java.io.File;
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

import dao.PeopleStore;
import dozerproject.transfer.PeopleBean;
import dozerproject.transfer.PersonBean;
/**
 * 
 * @author sestari
 *
 */
public class PeopleBeanDelegate {

	public final static List<String> myMappingFiles = Arrays.asList("dozerMappings.xml");

	
	/**
	 * This function map a PeopleStore Domain Model into a PeopleBean
	 * Presentation Layer using Dozer
	 * 
	 * @param store
	 * an object dao.PeopleStore
	 * @return 
	 * an object dozerproject.transfer.PeopleBean
	 */
	public static PeopleBean mapFromPerson(PeopleStore store) {

		DozerBeanMapper mapper = new DozerBeanMapper();
		mapper.setMappingFiles(myMappingFiles);
		return (PeopleBean) mapper.map(store, PeopleBean.class);
	}

	/**
	 * This function map a PeopleBean Presentation Layer into a
	 * PeopleStore Domain Model using Dozer
	 * 
	 * @param bean
	 * an object dozerproject.transfer.PeopleBean
	 * @return 
	 * an object dao.PeopleStore
	 */
	public static PeopleStore mapToPerson(PeopleBean bean) {
		DozerBeanMapper mapper = new DozerBeanMapper();
		mapper.setMappingFiles(myMappingFiles);
		return (PeopleStore) mapper.map(bean, PeopleStore.class);
	}

	
	/**
	 * This function marshal a PeopleBean Presentation Layer object into
	 * XML susing JAXB
	 * 
	 * @param bean
	 *  an object dozerproject.transfer.PeopleBean
	 * @return 
	 * a String that contains the xml node relative a People
	 */
	public static String marshal(PeopleBean bean) {
		StringWriter writer = new StringWriter();
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(PeopleBean.class);
			Marshaller marshaller = jaxbContext.createMarshaller();
			marshaller.setProperty("jaxb.formatted.output", new Boolean(true));
			marshaller.marshal(bean, writer);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return writer.toString();
	}

	
	/**
	 * This function un-marshal XML back into PeopleBean Presentation Layer
	 * object using JAXB
	 * 
	 * @param xml
	 * a String that represent xml node Person
	 * @return 
	 * an object dozerproject.transfer.PeopleBean
	 */
	public static PeopleBean unmarshal(String xml) {

		StringReader reader = new StringReader(xml);
		PeopleBean bean = null;
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(PersonBean.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			bean = (PeopleBean) unmarshaller.unmarshal(reader);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return bean;
	}

	
	/**
	 * Given a PeopleStore Domain Model and File,
	 * this function write the Json relative at 
	 * Domain Model into File, and return a
	 * String contained a Json.
	 * 
	 * @param store
	 * an object dao.PeopleStore
	 * @param json
	 * a File that contains Json
	 * @return
	 * a String that contains a Json representation
	 */
	public static String storeToJson(PeopleStore store, File json) {
		String result = null;

		try {

			ObjectMapper mapper = new ObjectMapper();
			mapper.registerModule(new JaxbAnnotationModule());

			mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
			mapper.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);

			mapper.configure(com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

			PeopleBean bean = PeopleBeanDelegate.mapFromPerson(store);
			mapper.writeValue(json, bean);
			result = mapper.writeValueAsString(bean);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}
}
