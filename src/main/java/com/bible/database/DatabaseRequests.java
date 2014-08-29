package com.bible.database;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;

import com.bible.models.Book;
import com.bible.models.BookTLC;
import com.bible.models.Books_Annotation;
import com.bible.models.Books_Annotation_Content;
import com.bible.models.Books_Annotation_Person;
import com.bible.models.Books_Annotation_Place;
import com.bible.models.Books_Annotation_Reference;
import com.bible.models.Books_Annotation_Topic;
import com.bible.models.Person;
import com.bible.models.Place;
import com.bible.models.Topic;
import com.bible.services.utilities.Bible;
import com.bible.xml.models.Annotation;
import com.bible.xml.models.BookTLCXml;
import com.bible.xml.models.Content;
import com.bible.xml.models.PersonXml;
import com.bible.xml.models.PlaceXml;
import com.bible.xml.models.TopicXml;

public class DatabaseRequests {

	private static Bible bible_data=new Bible();
	
	public static Book getBook(Session session, String short_title){
		
		if (session==null) return null;
	
		
		Query query=session.createQuery("from Book b where b.short_title=:short_title");
		query.setParameter("short_title", short_title);
		Book book=(Book)query.uniqueResult();
	
		return book;
	}
	
	/**
	 * Returns the tree root_entry for any book in the database.
	 * @param session
	 * @param short_title
	 * @return
	 */
	public static BookTLC getRootEntry(Session session, String short_title){
		if (session==null) return null;
		
	
		Query query=session.createQuery("from Book b where b.short_title=:short_title");
		query.setParameter("short_title", short_title);
		Book book=(Book)query.uniqueResult();
		
	
		if (book==null){
			return null;
		}
		
		return book.getRootId();
	}
	
	/**
	 * Returns the database entry corresponding to that verse from the Bible
	 * For example, if I want John 3:16, using the King James version, I would use
	 * the following parameters: 
	 * @param session
	 * @param version kjv
	 * @param bible_book Joh
	 * @param chapter_id 3
	 * @param verse_id 16
	 * @return
	 */
	public static BookTLC getBibleEntry(Session session, BookTLC root_entry, String bible_book, long chapter_id,
			long verse_id){
		
		if (root_entry==null || bible_book==null || session==null) return null;
		
		BookTLC verse_entry=null;
		
	
		
		if (bible_data.books_order_map.containsKey(bible_book)){
			int book_number=bible_data.books_order_map.get(bible_book);
			long book_id=root_entry.getLeft_child().getId()+book_number-1;
			BookTLC book_entry=(BookTLC)session.load(BookTLC.class, book_id);
			
			if (chapter_id<=book_entry.getRight_child().getId()-book_entry.getLeft_child().getId()+1 && chapter_id>=1){
				chapter_id=book_entry.getLeft_child().getId()+chapter_id-1;
				
				BookTLC chapter_entry=(BookTLC)session.load(BookTLC.class, chapter_id);
				if (verse_id<=chapter_entry.getRight_child().getId()-chapter_entry.getLeft_child().getId()+1 && verse_id>=1){
					verse_id=chapter_entry.getLeft_child().getId()+verse_id-1;
					
					verse_entry=(BookTLC)session.load(BookTLC.class, verse_id);
					
				}
			}
		}
		
	
		return verse_entry;
	}
	
	/**
	 * Method similar to getBibleEntry, but instead of returning null if the Bible reference is invalid,
	 * it returns a Bible reference likely to be the reference requested.
	 *  
	 * @param session
	 * @param root_entry
	 * @param bible_book
	 * @param chapter_id
	 * @param verse_id
	 * @return
	 */
	public static BookTLC getClosestBibleEntry(Session session, BookTLC root_entry, String bible_book,
			long chapter_id, long verse_id){
		
		//TODO: This is a stub and it will never return null, unless the session or the root_entry are null
		if (session==null || root_entry==null) return null;
		
	
		//1. get the book_id
		int book_number=1;
		if (bible_data.books_order_map.containsKey(bible_book))	
			book_number=bible_data.books_order_map.get(bible_book);
		
		long book_id=root_entry.getLeft_child().getId()+book_number-1;
		BookTLC book_entry=(BookTLC)session.load(BookTLC.class, book_id);
		
		//2. get the chapter_id
		if (chapter_id<1) chapter_id=1;
		if (chapter_id>book_entry.getRight_child().getId()-book_entry.getLeft_child().getId()+1){
			chapter_id=book_entry.getRight_child().getId()-book_entry.getLeft_child().getId()+1;
		}
		chapter_id=book_entry.getLeft_child().getId()+chapter_id-1;
		BookTLC chapter_entry=(BookTLC)session.load(BookTLC.class, chapter_id);
		
		//2. get the verse_id 
		if (verse_id<1) verse_id=1;
		if (verse_id>chapter_entry.getRight_child().getId()-chapter_entry.getLeft_child().getId()+1){
			verse_id=chapter_entry.getRight_child().getId()-chapter_entry.getLeft_child().getId()+1;
			
		}
		verse_id=chapter_entry.getLeft_child().getId()+verse_id-1;
		BookTLC verse_entry=(BookTLC)session.load(BookTLC.class, verse_id);
		
	
		return verse_entry;
	}
	
	public static String getBibleRangeText(Session session, String version, String book1, long chapter1, long verse1,
			String book2, long chapter2, long verse2){
		
		if (session==null) return null;
		Book book=getBook(session, version);
		if (book==null) book=getBook(session, "kjv");
		BookTLC left_entry=getBibleEntry(session, book.getRootId(), book1, chapter1, verse1);
		BookTLC right_entry=getBibleEntry(session, book.getRootId(), book2, chapter2, verse2);
		
		if (left_entry!=null && right_entry!=null){
			int start_offset=left_entry.getLeft_offset();
			int end_offset=right_entry.getRight_offset();
			
			String text=book.getText();
			
			if (text!=null)	return text.substring(start_offset, end_offset);
		}
		
		return null;
		
	}
	
	
	public static List<BookTLCXml> getBibleRange(Session session, String version, String book1, long chapter1, long verse1,
			String book2, long chapter2, long verse2){
		final List<BookTLCXml> toReturn=new LinkedList<>();
		
		Book book=getBook(session, version);
		if (book==null) book=getBook(session, "kjv");
		BookTLC left_entry=getBibleEntry(session, book.getRootId(), book1, chapter1, verse1);
		BookTLC right_entry=getBibleEntry(session, book.getRootId(), book2, chapter2, verse2);
		
		if (left_entry!=null && right_entry!=null){
			if (right_entry.getId()<left_entry.getId()){
				toReturn.add(new BookTLCXml(left_entry));
				
			}else{
				Query query=session.createQuery("from BookTLCXml b where b.id>=:id1 and b.id<=:id2");
				query.setLong("id1", left_entry.getId());
				query.setLong("id2", right_entry.getId());
				
				for (final Object o : query.list()){
					toReturn.add((BookTLCXml)o);
				}
			}
		}

		return toReturn;
	
	}
	
	
	public static LinkedList<BookTLCXml> getNextEntries(Session session, long ref_id, 
			int no_entries, String short_title){
		final LinkedList<BookTLCXml> toReturn=new LinkedList<>();
		Book book=getBook(session, short_title);
		if (book==null) return toReturn;
		
		BookTLC node=book.getRootId();
		Long last_entry=new Long(0);
		while (node!=null){
			last_entry=node.getId();
			node=node.getRight_child();
		}
		
		Long end_id=ref_id+(long)no_entries;
		if (last_entry<end_id) end_id=last_entry;
		Query query=session.createQuery("from BookTLCXml b where b.id>=:start_id and b.id<=:end_id");
		query.setLong("start_id", ref_id+1);
		query.setLong("end_id", end_id);
		
		for (final Object o : query.list()){
			toReturn.add((BookTLCXml)o);
		}
		
		return toReturn;
	}
	
	
	public static LinkedList<BookTLCXml> getPreviousEntries(Session session, long ref_id, 
			int no_entries, String short_title){
		final LinkedList<BookTLCXml> toReturn=new LinkedList<>();
		Book book=getBook(session, short_title);
		if (book==null) return toReturn;
		
		BookTLC node=book.getRootId();
		Long first_entry=book.getRootId().getId();
		while (node!=null){
			first_entry=node.getId();
			node=node.getLeft_child();
		}
		
		Long start_id=(ref_id-no_entries);
	
		if (first_entry>start_id) start_id=first_entry;
		Query query=session.createQuery("from BookTLCXml b where b.id>=:start_id and b.id<=:end_id");
				
		query.setLong("start_id", start_id);
		query.setLong("end_id", ref_id-1);
		
		for (final Object o : query.list()){
			toReturn.add((BookTLCXml)o);
		}
				
		return toReturn;
	}
	
	
	public static LinkedList<BookTLCXml> getClosestBibleRange(Session session, String version, 
			String book1, long chapter1, long verse1, String book2, long chapter2, long verse2){
		
		final LinkedList<BookTLCXml> toReturn=new LinkedList<>();
				
		Book book=getBook(session, version);
		if (book==null) book=getBook(session, "kjv");
		BookTLC left_entry=getClosestBibleEntry(session, book.getRootId(), book1, chapter1, verse1);
		BookTLC right_entry=getClosestBibleEntry(session, book.getRootId(), book2, chapter2, verse2);
		
		if (left_entry!=null && right_entry!=null){
			if (right_entry.getId()<left_entry.getId()){
				toReturn.add(new BookTLCXml(left_entry));
				
			}else{
				Query query=session.createQuery("from BookTLCXml b where b.id>=:id1 and b.id<=:id2");
				query.setLong("id1", left_entry.getId());
				query.setLong("id2", right_entry.getId());
				
				for (final Object o : query.list()){
					toReturn.add((BookTLCXml)o);
				}
				
			}
		}
		
		return toReturn;
	}
	
	@SuppressWarnings("unchecked")
	public static List<Annotation> getAnnotations(Session session, long start_id, long end_id, int max_number, String type){
		
		List<Annotation> toReturn=new LinkedList<Annotation>();
		if (session==null) return toReturn;
		Query query=session.createQuery("from BookTLC b where b.id>=:id1 and b.id<=:id2");
		query.setLong("id1", start_id);
		query.setLong("id2", end_id);
		
		List<BookTLC> entries=(List<BookTLC>)query.list();
		
		if (type.equals("places")){
			toReturn=getPlaceAnnotations(entries, max_number);
		}
		else if (type.equals("topics")){
			toReturn=getTopicAnnotations(entries, max_number);
		}
		else if (type.equals("references")){
			toReturn=getReferenceAnnotations(entries, max_number);
		}
		else {
			toReturn=getPersonAnnotations(entries, max_number);
		}
		
		return toReturn;
	}
	
	private static List<Annotation> getPersonAnnotations(List<BookTLC> entries, int max_number){
		
		List<Annotation> toReturn=new LinkedList<Annotation>();
		for (BookTLC entry : entries){
			Set<Books_Annotation_Person> set=entry.getPersons();
			Long id=entry.getId();
			
			
			for (Books_Annotation_Person p_a : set){
				PersonXml person=new PersonXml(p_a.getContent());
				Annotation annotation=new Annotation();
				annotation.setBookTLC_id(id);
				annotation.setContent(person);
				
				toReturn.add(annotation);
			}
		}
		
		return toReturn;
	}
	
	private static List<Annotation> getPlaceAnnotations(List<BookTLC> entries, int max_number){
		List<Annotation> toReturn=new LinkedList<Annotation>();
		for (BookTLC entry : entries){
			Set<Books_Annotation_Place> set=entry.getPlaces();
			Long id=entry.getId();
			
			for (Books_Annotation_Place p_a : set){
				PlaceXml place=new PlaceXml(p_a.getContent());
				 
				Annotation annotation=new Annotation();
				annotation.setBookTLC_id(id);
				annotation.setContent(place);
				
				toReturn.add(annotation);
			}
		}
		
		return toReturn;
	}
	
	private static List<Annotation> getTopicAnnotations(List<BookTLC> entries, int max_number){
		List<Annotation> toReturn=new LinkedList<Annotation>();
		for (BookTLC entry : entries){
			Set<Books_Annotation_Topic> set=entry.getTopics();
			Long id=entry.getId();
			
			for (Books_Annotation_Topic p_a : set){
				TopicXml topic=new TopicXml(p_a.getContent());
				 
				Annotation annotation=new Annotation();
				annotation.setBookTLC_id(id);
				annotation.setContent(topic);
				
				toReturn.add(annotation);
			}
		}
		
		return toReturn;
	}
	
	private static List<Annotation>getReferenceAnnotations(List<BookTLC> entries, int max_number){
		List<Annotation> toReturn=new LinkedList<Annotation>();
		for (BookTLC entry : entries){
			Set<Books_Annotation_Reference> set=entry.getCross_references();
			Long id=entry.getId();
			
			for (Books_Annotation_Reference p_a : set){
				BookTLCXml reference=new BookTLCXml(p_a.getContent());
				 
				Annotation annotation=new Annotation();
				annotation.setBookTLC_id(id);
				annotation.setContent(reference);
				
				toReturn.add(annotation);
			}
		}
		
		return toReturn;
	}
}