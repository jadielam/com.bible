package com.bible.insertion;



import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.xml.sax.SAXException;

import com.bible.models.Place;
import com.sun.org.apache.xerces.internal.parsers.DOMParser;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class PlacesParser {

	public PlacesParser(){
		
	}
	
	public List<Place> parse(String filePath){
		List<Place> places=new LinkedList<Place>();
		HashMap<String, Place> name_place_d=new HashMap<String, Place>();
		HashMap<String, Place> mainname_place_d=new HashMap<String, Place>();
		DOMParser parser=new DOMParser();
		
		try {
			parser.parse(filePath);
			Document doc=parser.getDocument();
			NodeList root=doc.getChildNodes();
			for (int i=0; i<root.getLength(); ++i){
				Node root_node=root.item(i);
				
				NodeList places_list=root_node.getChildNodes();
				for (int j=0; j<places_list.getLength(); ++j){
					
					Node place_node=places_list.item(j);
					
					if (place_node!=null  && place_node.getNodeName().equals("place")){
						NodeList places_properties=place_node.getChildNodes();
						String name=getNodeValue("name", places_properties);
						String coordinates=getNodeValue("coordinates", places_properties);
						String main_name=getNodeValue("main_name", places_properties);
						String source_of_coordinates=getNodeValue("source_of_coordinates", places_properties);
						String disambiguation=getNodeValue("disambiguation", places_properties);
						String cross_references=getNodeValue("cross_references", places_properties);
						String wikipedia_url=getNodeValue("wikipedia_url", places_properties);
						String classification=getNodeValue("classification", places_properties);
						
						Place place=new Place();
						place.setName(name);
						place.setCoordinates(coordinates);
						place.setSource_of_coordinates(source_of_coordinates);
						place.setDisambiguation(disambiguation);
						place.setCross_references(cross_references);
						place.setWikipedia_url(wikipedia_url);
						place.setClassification(classification);
						name_place_d.put(name, place);
						
						if (main_name!=null){
							mainname_place_d.put(main_name, place);
						}
						else{
							places.add(place);
						}
					}
					
				}
			}
			
			
			
		} catch (SAXException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		Set<Entry<String, Place>> entrySet=mainname_place_d.entrySet();
		for (Entry<String, Place> e : entrySet){
			String main_name=e.getKey();
			Place p=e.getValue();
			if (name_place_d.containsKey(main_name)){
				p.setMain_name(name_place_d.get(main_name));
				places.add(p);
			}
		}
		return places;
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

