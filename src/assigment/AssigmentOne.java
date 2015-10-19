package assigment;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import dozerproject.delegate.HealthProfileBeanDelegate;
import dozerproject.delegate.PersonBeanDelegate;
import dozerproject.entity.HealthProfile;
import dozerproject.entity.Person;
import dozerproject.transfer.HealthProfileBean;
import dozerproject.transfer.PersonBean;
/**
 * 
 * @author sestari
 *
 */
public class AssigmentOne {

	private Document doc;
	private DocumentBuilder builder;
	private Transformer transformer;
	private XPath xpath;

	/**
	 * Given a Person Id is possible to get your Weight this function get the
	 * Weight from people.xml using Xpath if the Person Id is not found, the
	 * function return null
	 * 
	 * @param personId
	 * a long that represent the Person Id
	 * @return 
	 * a String that represent the Weight
	 * @throws XPathExpressionException
	 */
	public String getWeight(Long personId) throws XPathExpressionException {
		String result = null;
		XPathExpression expr = xpath
				.compile("/people/person[@id=" + Long.toString(personId) + "]/healthprofile/weight/text()");
		Node node = (Node) expr.evaluate(doc, XPathConstants.NODE);
		if (null != node) {
			result = node.getTextContent();
		}
		return result;
	}

	/**
	 * Given a Person Id is possible to get your Height this function get the
	 * Weight from people.xml using Xpath. if the Person Id is not found, the
	 * function return null
	 * 
	 * @param personId
	 * a long that represent the Person Id
	 * @return 
	 * a String that represent the Height
	 * @throws XPathExpressionException
	 */
	public String getHeight(Long personId) throws XPathExpressionException {
		String result = null;
		XPathExpression expr = xpath
				.compile("/people/person[@id=" + Long.toString(personId) + "]/healthprofile/height/text()");
		Node node = (Node) expr.evaluate(doc, XPathConstants.NODE);
		if (null != node) {
			result = node.getTextContent();
		}
		return result;
	}

	/**
	 * Given a Person Id is possible your HealthProfile this function return the
	 * HealthProfile from people.xml using Xpath Once selected tab, the function
	 * un-marshal XML back into HealthProfile object if the Person Id is not
	 * found, the function return null
	 * 
	 * @param personId
	 * a long that represent the Person Id
	 * @return 
	 * a dozerproject.entity.HealthProfile object
	 */
	public HealthProfile getHealthProfile(Long personId) {
		HealthProfile profile = null;
		try {
			Node node = (Node) xpath.evaluate("/people/person[@id=" + Long.toString(personId) + "]", doc,
					XPathConstants.NODE);

			if (node != null) {
				Node n = (Node) xpath.evaluate("healthprofile", node, XPathConstants.NODE);
				if (n != null) {
					profile = this.parseHealthProfile(n);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return profile;
	}

	/**
	 * Given an operator('>','<','=') and a value that represents weight the function
	 * get all Person that meet the condition. The function get the people from 
	 * people.xml using Xpath, Once selected tab, the function un-marshal XML
	 * back into Person and HealthProfile, after set HealthProfile in Person and
	 * put it in a List.
	 * 
	 * @param op
	 * a String that represent the operator ('>','<','=')
	 * @param value
	 * value Integer value that represent the weight
	 * @return 
	 * a List of dozerproject.entity.Person that represent the Person
	 */
	public List<Person> weightOperator(String op, Integer value) {

		List<Person> people = null;
		if (op.equals(">") || op.equals("<") || op.equals("=") || op.equals(">=") || op.equals("<=")) {

			try {
				String query = "/people/person/healthprofile[weight" + op + value + "]";
				NodeList nodes = (NodeList) xpath.evaluate(query, doc, XPathConstants.NODESET);

				if (nodes != null) {
					people = new ArrayList<Person>();

					for (int i = 0; i < nodes.getLength(); i++) {
						Node node = nodes.item(i);
						if (node.getNodeType() == Node.ELEMENT_NODE) {
							// nodeList.add(node.getParentNode());
							Person person = this.parsePerson(node.getParentNode());
							Node n = (Node) xpath.evaluate("healthprofile", node.getParentNode(), XPathConstants.NODE);
							if (n != null) {
								HealthProfile healthProfile = this.parseHealthProfile(n);
								person.setHealthProfile(healthProfile);
								people.add(person);
							}
						}
					}

				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return people;
	}

	/**
	 * The function return all Person contained in a people.xml 
	 * the function get the people through Xpath, Once selected it, the function un-marshal XML
	 * back into dozerproject.entity.Person and dozerproject.entity.HealthProfile, 
	 * after set HealthProfile in Person and put it in a List.
	 * 
	 * @return 
	 * a List of dozerproject.entity.Person
	 */
	public List<Person> printAllpeople() {
		List<Person> people = null;

		try {

			XPathExpression expr = xpath.compile("/people/person");
			NodeList nodes = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);

			if (nodes != null) {
				people = new ArrayList<Person>();

				for (int i = 0; i < nodes.getLength(); i++) {
					Node node = nodes.item(i);
					if (node.getNodeType() == Node.ELEMENT_NODE) {
						// nodeList.add(node);
						Person person = this.parsePerson(node);
						Node n = (Node) xpath.evaluate("healthprofile", node, XPathConstants.NODE);
						if (n != null) {
							HealthProfile healthProfile = this.parseHealthProfile(n);
							person.setHealthProfile(healthProfile);
							people.add(person);
						}
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return people;

	}
	
	/**
	 * The function return a Person contained in a people.xml through a id
	 * the function get the Person through Xpath, Once selected it, the function un-marshal XML
	 * back into dozerproject.entity.Person. 
	 * 
	 * @return 
	 * a  dozerproject.entity.Person
	 */
	public Person getPeople(Long personId) {
		Person person = null;
		try {
			Node node = (Node) xpath.evaluate("/people/person[@id=" + Long.toString(personId) + "]", doc,
					XPathConstants.NODE);

			if (node != null) {
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					// nodeList.add(node);
					person = this.parsePerson(node);
				}	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return person;

	}

	/**
	 * This function open a xml File,
	 *  
	 * 
	 * @param xml
	 * a xml File
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public void loadXML(File xml) throws ParserConfigurationException, SAXException, IOException {

		DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
		domFactory.setNamespaceAware(true);
		builder = domFactory.newDocumentBuilder();
		doc = builder.parse(xml.getAbsolutePath());

		// creating xpath object
		getXPathObj();
	}

	
	/**
	 * This function get a Node Person transformer it in XML,
	 * un-marshal into a PersonBean using Jaxb
	 * and finally map it into Person using Dozer
	 * 
	 * @param node
	 * an object Node that represents a Node Person
	 * @return
	 * an object dozerproject.entity.People
	 */
	private Person parsePerson(Node node) {
		Person person = null;
		try {
			Document document = builder.newDocument();
			Node newNode = document.importNode(node, true);
			document.appendChild(newNode);

			StringWriter writer = new StringWriter();
			transformer.transform(new DOMSource(document), new StreamResult(writer));
			String xml = writer.getBuffer().toString();

			PersonBean personBean = PersonBeanDelegate.unmarshal(xml);

			person = PersonBeanDelegate.mapToPerson(personBean);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return person;

	}

	/**
	 * This function get a Node HealthProfile transformer it in XML,
	 * un-marshal into a HealthProfileBean using Jaxb
	 * and finally map it into HealthProfile using Dozer
	 * 
	 * @param node
	 * an object Node that represents a Node HealthProfile
	 * @return
	 * an object dozerproject.entity.HealthProfile
	 */
	private HealthProfile parseHealthProfile(Node node) {
		HealthProfile healthProfile = null;
		try {

			Document document = builder.newDocument();
			Node newNode = document.importNode(node, true);
			document.appendChild(newNode);

			StringWriter writer = new StringWriter();
			transformer.transform(new DOMSource(document), new StreamResult(writer));
			String xml = writer.getBuffer().toString();

			HealthProfileBean healthBean = HealthProfileBeanDelegate.unmarshal(xml);

			healthProfile = HealthProfileBeanDelegate.mapToHealthProfile(healthBean);

		} catch (Exception e) {

		}

		return healthProfile;
	}

	private XPath getXPathObj() {

		XPathFactory factory = XPathFactory.newInstance();
		xpath = factory.newXPath();

		try {
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return xpath;
	}

}
