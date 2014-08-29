package com.bible.services.bible;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.List;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.GET;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.StreamingOutput;
import javax.ws.rs.core.UriBuilder;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.bible.database.DatabaseRequests;
import com.bible.models.BookTLC;
import com.bible.xml.models.BookResponse;
import com.bible.xml.models.BookTLCXml;


@Path("/bible")
public class BibleResource {

	private final SessionFactory sessionFactory;
	public BibleResource(SessionFactory sessionFactory){
		this.sessionFactory=sessionFactory;
	}
	
	
	
	@GET
	@Produces("application/xml")
	public BookResponse getBibleRange(@MatrixParam("book1") String book1,
			@MatrixParam("ch1") String ch1, @MatrixParam("v1") String v1,
			@MatrixParam("book2") String book2, @MatrixParam("ch2") String ch2,
			@MatrixParam("v2") String v2, @MatrixParam("version") String version){
		
		
		Session session=sessionFactory.getCurrentSession();
		session.getTransaction().begin();
		
		long ch1_id=getNumber(ch1);
		long v1_id=getNumber(v1);
		long ch2_id=getNumber(ch2);
		long v2_id=getNumber(v2);
		
		
		LinkedList<BookTLCXml> verses=DatabaseRequests.getClosestBibleRange(session, version, book1, ch1_id, v1_id, book2, ch2_id, v2_id);
		
		BookResponse toReturn=new BookResponse();
		
		Long start_id=verses.getFirst().getId();
		Long end_id=verses.getLast().getId();
		
		
		Link persons_link=Link.fromUri("http://{host}/annotation;"+
				"type=persons;start_id={start_id};end_id={end_id};max_number=-1").
				rel("get").type("application/xml").build("localhost:8080", 
						start_id.toString(), end_id.toString());
	
		Link places_link=Link.fromUri("http://{host}/annotation;"+
				"type=places;start_id={start_id};end_id={end_id};max_number=-1").
				rel("get").type("application/xml").build("localhost:8080", 
						start_id.toString(), end_id.toString());
		
		Link references_link=Link.fromUri("http://{host}/annotation;"+
				"type=references;start_id={start_id};end_id={end_id};max_number=-1").
				rel("get").type("application/xml").build("localhost:8080", 
						start_id.toString(), end_id.toString());
		
		Link topics_link=Link.fromUri("http://{host}/annotation;"+
				"type=topics;start_id={start_id};end_id={end_id};max_number=-1").
				rel("get").type("application/xml").build("localhost:8080", 
						start_id.toString(), end_id.toString());
		
		Link previous=Link.fromUri("http://{host}/book/previous;ref_id={ref_id};"+
				"no_entries=20;short_title={short_title}").rel("get").type("application/xml").
				build("localhost:8080", start_id.toString(), version);
				
		Link next=Link.fromUri("http://{host}/book/next;ref_id={ref_id};"
				+ "no_entries=20;short_title={short_title}").rel("get").
				type("application/xml").build("localhost:8080", end_id.toString(), version);
		
		toReturn.setVerses(verses);
		toReturn.setPlaces_annotation_link(places_link);
		toReturn.setReferences_annotation_link(references_link);
		toReturn.setPersons_annotation_link(persons_link);
		toReturn.setTopics_annotation_link(topics_link);
		toReturn.setPrevious_link(previous);
		toReturn.setNext_link(next);
		
		session.getTransaction().commit();
		
		return toReturn;
	}
	
	private long getNumber(String number){
		long toReturn=1;
		try{
			toReturn=Long.parseLong(number);
		}
		catch(NumberFormatException e){
			//do nothing here
		}
		return toReturn;
	}
}
