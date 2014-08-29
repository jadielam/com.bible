package com.bible.insertion;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.bible.database.DatabaseRequests;
import com.bible.models.Book;
import com.bible.models.BookTLC;
import com.bible.models.Books_Annotation_Person;
import com.bible.models.Books_Annotation_Place;
import com.bible.models.Person;
import com.bible.models.Place;
import com.bible.services.utilities.Bible;
import com.bible.util.SessionUtil;

public class AnnotationsProcessing {

	private final Bible bible_data;
		
	public AnnotationsProcessing(){
		this.bible_data=new Bible();
	}
	
	public void doProcessing(){
		processPlaces();
		processPersons();
		//processTopics();
		//processReferences();
		SessionUtil.close();
	}
	
	
	public void processPlaces(){
		Session session=SessionUtil.getSession();
		Transaction tx=session.beginTransaction();
		
		//1. Get all the places that have not been processed
		Query p_query=session.createQuery("from Place p where p.processed=0");
		List<Place> places=(List<Place>)p_query.list();
		tx.commit();
		
		
		for (Place place : places){
			//2. Get the reference field of those places
			String references=place.getCross_references();
			String [] ref_array=references.split(";");
			
			
			for (String ref : ref_array){
				ref=ref.trim();
				int a=ref.indexOf(' ');
				if (a!=-1){
					String book_abb=ref.substring(0, a);
					
					int b=ref.indexOf(':');
					if (b!=-1){
						long chapter_id=1;
						long verse_id=1;
						
						try{
							chapter_id=Long.parseLong(ref.substring(a+1, b));
							verse_id=Long.parseLong(ref.substring(b+1));
						}
						catch(NumberFormatException e){
							e.printStackTrace();
							continue;
						}
						tx=session.beginTransaction();
						
						//3. For the KJV Bible, get the BooksTLC of that reference
						Book book=DatabaseRequests.getBook(session, "kjv");
						BookTLC version_root=book.getRootId();
						BookTLC verse=DatabaseRequests.getBibleEntry(session, version_root, book_abb, chapter_id, verse_id);
						
						//4. Create a new entry of Books_Annotation_Places
						Books_Annotation_Place annotation=new Books_Annotation_Place();
						if (book==null || place==null || verse==null) {
							tx.commit();
							continue;
						}
						annotation.setBook(book);
						annotation.setSource(verse);
						annotation.setContent(place);
						annotation.setStrength(1.0);
						
						//5. Save the new entry on the database
						session.saveOrUpdate(annotation);
						place.setProcessed(true);
						session.update(place);
		
						//6. Commit.
						tx.commit();
							
					}
				}
			}
		}
		session.close();
	}
	
	public void processPersons(){
		
		Session session=SessionUtil.getSession();
		Transaction tx=session.beginTransaction();
		
		//1. Get all the places that have not been processed
		Query p_query=session.createQuery("from Person p where p.processed=0");
		List<Person> persons=(List<Person>)p_query.list();
		tx.commit();
		
		
		for (Person person : persons){
			//2. Get the reference field of those places
			String references=person.getCross_references();
			String [] ref_array=references.split(";");
			for (String ref : ref_array){
				ref=ref.trim();
				int a=ref.indexOf(' ');
				if (a!=-1){
					String book_abb=ref.substring(0, a);
					
					int b=ref.indexOf(':');
					if (b!=-1){
						
						long chapter_id=1;
						long verse_id=1;
						
						try{
							chapter_id=Long.parseLong(ref.substring(a+1, b));
							verse_id=Long.parseLong(ref.substring(b+1));
						}
						catch (NumberFormatException e){
							e.printStackTrace();
							continue;
						}
						
						tx=session.beginTransaction();
						
						//3. For the KJV Bible, get the BooksTLC of that reference
						Book book=DatabaseRequests.getBook(session, "kjv");
						BookTLC version_root=book.getRootId();
						BookTLC verse=DatabaseRequests.getBibleEntry(session, version_root, book_abb, chapter_id, verse_id);
						
						//4. Create a new entry of Books_Annotation_Places
						Books_Annotation_Person annotation=new Books_Annotation_Person();
						if (book==null || verse==null || person==null){
							tx.commit();
							continue;
						}
						
						annotation.setBook(book);
						annotation.setSource(verse);
						annotation.setContent(person);
						annotation.setStrength(1.0);
						
						//5. Save the new entry on the database
						session.saveOrUpdate(annotation);
						person.setProcessed(true);
						session.update(person);
		
						//6. Commit.
						tx.commit();
							
					}
				}
			}
		}
		session.close();

	}
	
	public void processTopics(){
		
	}
	
	public void processReferences(){
		
	}
}
