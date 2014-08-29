package com.bible.services.book;

import java.util.LinkedList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Link;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.bible.database.DatabaseRequests;
import com.bible.xml.models.BookResponse;
import com.bible.xml.models.BookTLCXml;

@Path("/book")
public class BookResource {

	private final SessionFactory sessionFactory;
	
	public BookResource(SessionFactory sessionFactory){
		this.sessionFactory=sessionFactory;
	}
	
	@GET
	@Produces("application/xml")
	@Path("/next")
	public BookResponse getNext(@MatrixParam("ref_id") long ref_id,
			@MatrixParam("no_entries") int no_entries, 
			@MatrixParam("short_title") String short_title){
		Session session=sessionFactory.getCurrentSession();
		session.getTransaction().begin();
		LinkedList<BookTLCXml> entries=DatabaseRequests.getNextEntries(session, ref_id, no_entries, short_title);
		BookResponse toReturn=new BookResponse();
		
		Long start_id=entries.getFirst().getId();
		Long end_id=entries.getLast().getId();
				
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
				build("localhost:8080", start_id.toString(), short_title);
				
		Link next=Link.fromUri("http://{host}/book/next;ref_id={ref_id};"
				+ "no_entries=20;short_title={short_title}").rel("get").
				type("application/xml").build("localhost:8080", end_id.toString(), short_title);
		
		toReturn.setVerses(entries);
		toReturn.setPlaces_annotation_link(places_link);
		toReturn.setReferences_annotation_link(references_link);
		toReturn.setPersons_annotation_link(persons_link);
		toReturn.setTopics_annotation_link(topics_link);
		toReturn.setPrevious_link(previous);
		toReturn.setNext_link(next);
			
		toReturn.setVerses(entries);
		
		
		
		session.getTransaction().commit();
		return toReturn;
	}
	
	@GET
	@Produces("application/xml")
	@Path("/previous")
	public BookResponse getPrevious(@MatrixParam("ref_id") long ref_id,
			@MatrixParam("no_entries") int no_entries, 
			@MatrixParam("short_title") String short_title){
		
		Session session=sessionFactory.getCurrentSession();
		session.getTransaction().begin();
		LinkedList<BookTLCXml> entries=DatabaseRequests.getPreviousEntries(session, ref_id, no_entries, short_title);
		BookResponse toReturn=new BookResponse();
		
		Long start_id=entries.getFirst().getId();
		Long end_id=entries.getLast().getId();
				
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
				build("localhost:8080", start_id.toString(), short_title);
				
		Link next=Link.fromUri("http://{host}/book/next;ref_id={ref_id};"
				+ "no_entries=20;short_title={short_title}").rel("get").
				type("application/xml").build("localhost:8080", end_id.toString(), short_title);
		
		toReturn.setVerses(entries);
		toReturn.setPlaces_annotation_link(places_link);
		toReturn.setReferences_annotation_link(references_link);
		toReturn.setPersons_annotation_link(persons_link);
		toReturn.setTopics_annotation_link(topics_link);
		toReturn.setPrevious_link(previous);
		toReturn.setNext_link(next);
				
		toReturn.setVerses(entries);
				
		session.getTransaction().commit();
		return toReturn;
	}
	
	
}
