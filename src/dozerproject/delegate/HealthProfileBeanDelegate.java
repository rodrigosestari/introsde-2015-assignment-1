package dozerproject.delegate;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.dozer.DozerBeanMapper;

import dozerproject.entity.HealthProfile;
import dozerproject.transfer.HealthProfileBean;
/**
 * 
 * @author sestari
 *
 */
public class HealthProfileBeanDelegate {

	public final static List<String> myMappingFiles = Arrays.asList("dozerMappings.xml");

	/**
	 * This function map a HealthProfile Domain Model into a HealthProfileBean
	 * Presentation Layer using Dozer
	 * 
	 * @param healthProfile
	 * an object dozerproject.entity.healthProfile
	 * @return 
	 * an object dozerproject.transfer.HealthProfileBean
	 */
	public static HealthProfileBean mapFromHealthProfile(HealthProfile healthProfile) {
		DozerBeanMapper mapper = new DozerBeanMapper();
		mapper.setMappingFiles(myMappingFiles);
		return (HealthProfileBean) mapper.map(healthProfile, HealthProfileBean.class);
	}

	/**
	 * This function map a HealthProfileBean Presentation Layer into a
	 * HealthProfile Domain Model using Dozer
	 * 
	 * @param bean
	 * an object dozerproject.transfer.HealthProfileBean
	 * @return 
	 * an object dozerproject.entity.healthProfile
	 */
	public static HealthProfile mapToHealthProfile(HealthProfileBean bean) {

		DozerBeanMapper mapper = new DozerBeanMapper();
		mapper.setMappingFiles(myMappingFiles);
		return (HealthProfile) mapper.map(bean, HealthProfile.class);
	}

	/**
	 * This function marshal a HealthProfileBean Presentation Layer object into
	 * XML susing JAXB
	 * 
	 * @param bean
	 * an object dozerproject.transfer.HealthProfileBean
	 * @return 
	 * a String that contains the xml node relative a HealthProfile
	 */
	public static String marshal(HealthProfileBean bean) {
		StringWriter writer = new StringWriter();
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(HealthProfileBean.class);
			Marshaller marshaller = jaxbContext.createMarshaller();
			marshaller.setProperty("jaxb.formatted.output", new Boolean(true));
			marshaller.marshal(bean, writer);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return writer.toString();
	}

	/**
	 * This function un-marshal XML back into HealthProfileBean Presentation Layer
	 * object using JAXB
	 * 
	 * @param xml
	 * a String that represent a node xml HealthProfile
	 * @return 
	 * an object dozerproject.transfer.HealthProfileBean
	 */
	public static HealthProfileBean unmarshal(String xml) {

		StringReader reader = new StringReader(xml);
		HealthProfileBean bean = null;
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(HealthProfileBean.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			bean = (HealthProfileBean) unmarshaller.unmarshal(reader);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return bean;
	}
}
