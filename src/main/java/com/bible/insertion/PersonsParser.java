package com.bible.insertion;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;

import org.xml.sax.SAXException;

import com.bible.models.Person;
import com.sun.org.apache.xerces.internal.parsers.DOMParser;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class PersonsParser {

	public PersonsParser(){
		
	}
	
	public List<Person> parse(String filePath){
		List<Person> persons=new LinkedList<Person>();
		HashMap<String, Person> name_person_d=new HashMap<String, Person>();
		HashMap<String, Person> mainname_person_d=new HashMap<String, Person>();
		
		DOMParser parser=new DOMParser();
		try {
			parser.parse(filePath);
			Document doc=parser.getDocument();
			NodeList root=doc.getChildNodes();
			for (int i=0; i<root.getLength(); ++i){
				Node root_node=root.item(i);
				
				NodeList persons_list=root_node.getChildNodes();
				for (int j=0; j<persons_list.getLength(); ++j){
					
					
					Node person_node=persons_list.item(j);
					if (person_node!= null && person_node.getNodeName().equals("person")){
						NodeList person_properties=person_node.getChildNodes();
						String name=getNodeValue("name", person_properties);
						String main_name=getNodeValue("main_name", person_properties);
						String other_names=getNodeValue("other_names", person_properties);
						String disambiguation=getNodeValue("disambiguation", person_properties);
						String cross_references=getNodeValue("cross_references", person_properties);
						String biography=getNodeValue("biography", person_properties);
						
						Person person=new Person();
						person.setName(name);
						person.setOther_names(other_names);
						person.setDisambiguation(disambiguation);
						person.setCross_references(cross_references);
						person.setBiography(biography);
						name_person_d.put(name, person);
						
						if (main_name!=null){
							mainname_person_d.put(main_name, person);
						}
						else{
							persons.add(person);	
						}
						
						
					}
					
				}
			}
			
			
			
		} catch (SAXException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		Set<Entry<String, Person>> entrySet=mainname_person_d.entrySet();
		for (Entry<String, Person> e : entrySet){
			String main_name=e.getKey();
			Person p=e.getValue();
			if (name_person_d.containsKey(main_name)){
				p.setMain_name(name_person_d.get(main_name));
				persons.add(p);
			}
		}
		return persons;
	}
	
	private String getNodeValue(String tagName, NodeList nodes ) {
	    for ( int x = 0; x < nodes.getLength(); x++ ) {
	        Node node = nodes.item(x);
	        if (node.getNodeName().equalsIgnoreCase(tagName)) {
	            NodeList childNodes = node.getChildNodes();
	            for (int y = 0; y < childNodes.getLength(); y++ ) {
	                Node data = childNodes.item(y);
	                if ( data.getNodeType() == Node.TEXT_NODE )
	                    return data.getNodeValue();
	            }
	        }
	    }
	    return null;
	}
}