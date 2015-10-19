Assignment 01: Reading/Writing objects to and from XML and JSON

**Introduction to Service Design and Engineering | University of Trento |** 

**Name:** Rodrigo Sestari

**Description:**

This Project contains code written to satisfy tasks reported in Assignment1.





**Code:**

* **src/assigment:** Classes to Evaluation.
* **src/dao:** Classe DAO "data access object"
* **src/dozerproject.delegate:** Classes to manage the mapping operation.
* **src/dozerproject.entity:**   Classes to represent your domain model.
* **src/dozerproject.transfer:** Classes to manage what and how the domain model is going to be mapped to the presentation layer.  
* **src/people.generated:** Code generated with Jaxb through resources/people.xsd.
* **src/util:** helpers to support Jaxb operations.
* **resources:** Contains files xml, xsd, json
* **doc:** JavaDoc






**Tasks:**

* **Evaluation1:** Print all Person contained in resources/people.xml
* **Evaluation2:** Print the HealthProfile from a Person with id=5
* **Evaluation3:** Print  all Person that having  Weight > 90
* **Evaluation4:** Create 2 objects PersonType, marshalling using JAXB into XML and create a file resources/peopleJaxb.xml with the XML 
* **Evaluation5** Get the file  resources/peopleJaxb.xml created in Evaluation4,un-marshalling using JAXB into object People, print the result  
* **Evaluation6:** Get the file resources/people.xml, set a PeopleStore Domain Model, transfor it a Json and put in the File resources/peopleJason.Json and finally Print the result






**How to run:**

Running the project requires java and ant.

Ant source file build.xml is annotated. 
* Main targets are:
* **execute.evaluation:** Evaluation from 1 to 6 to test 
* **dist:** Produce a ZIP archive containing only source code, Javadoc, xml/xsd and build-required files






**References:**

https://sites.google.com/a/unitn.it/introsde_2015-16/lab-sessions/assignments/assignment-1

