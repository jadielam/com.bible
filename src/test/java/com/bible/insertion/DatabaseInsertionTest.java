package com.bible.insertion;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
//import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.bible.TLC.Node;
import com.bible.TLC.TLCAlgorithm;
import com.bible.models.Book;
import com.bible.models.BookTLC;
import com.bible.models.Person;
import com.bible.models.Place;
import com.bible.util.SessionUtil;
import com.google.common.base.Charsets;
import com.google.common.io.Files;



public class DatabaseInsertionTest {
	
	public static void main(String [] args){
		
		
		DatabaseInsertionTest insert=new DatabaseInsertionTest();
		Session session=SessionUtil.getSession();
		//insert.saveBooks(session);
		
		//insert.createTLC();		
		insert.savePersons(session);
		insert.savePlaces(session);
		session.close();
		SessionUtil.close();
		
	}
	@BeforeSuite
	public void setup(){
		
	}
	
	public void savePlaces(Session session){
		PlacesParser parser=new PlacesParser();
		String filePath="src/main/resources/data/places.xml";
		List<Place> places=parser.parse(filePath);
		
		
		DatabaseInsertion insertion=new DatabaseInsertion();
		insertion.insertPlaces(session, places);
	}
	
	public void savePersons(Session session){
		PersonsParser parser=new PersonsParser();
		String filePath="src/main/resources/data/persons.xml";
		List<Person> persons=parser.parse(filePath);
		DatabaseInsertion insertion=new DatabaseInsertion();
		insertion.insertPersons(session, persons);
		
	}
	@Test
	public void saveBooks(Session session){
		
		String filePath="src/main/resources/file_list";
		List<Book> books=new LinkedList<Book>();
		try {
			//1. Read lines
			List<String> filePaths=Files.readLines(new File(filePath), Charsets.UTF_8);
			
			//2. Create documents
		
			for (String file_p : filePaths){
				
				int a=file_p.lastIndexOf("/");
				int b=file_p.lastIndexOf(".");
				String html_path=file_p.substring(0, b)+".html";
				
				String text=Files.toString(new File(html_path), Charsets.UTF_8);
				String structured_text=Files.toString(new File(file_p), 
						Charsets.UTF_8);
				String annotated_text=structured_text;
				String metadata=getMetadata(structured_text);
				
				String version=file_p.substring(a+1, b);
				String title="Bible "+version+" version";
				String short_title=version;
				String structure=getStructure(structured_text);
				
				Book book=new Book();
				book.setText(text);
				book.setStructured_text(structured_text);
				book.setAnnotated_text(annotated_text);
				book.setMetadata(metadata);
				book.setTitle(title);
				book.setShort_title(short_title);
				book.setStructure(structure);
								
				books.add(book);
			}
		}
		catch(IOException e){
			e.printStackTrace();
		}
		
		Transaction tx=session.beginTransaction();
		
		for (Book book : books){
			session.save(book);
		}
		
		tx.commit();
		session.close();	
		
		
	}
	
	@Test(dependsOnMethods="saveBooks")
	public void retrieveBooks(){
		
	}
	
	@Test(dependsOnMethods="retrieveBooks")
	public void createTLC(Session session){

		Transaction tx=session.beginTransaction();
		
		//1. Read the BOOKS table and check read all the strings that
		//still do not have a root_node_id entry from the BOOKSTLC table.
				
		Query query=session.createQuery("from Books r "
				+"where r.rootId is null");
		
		List<Book> books=(List<Book>)query.list();
		System.out.println(books.size());
		
		tx.commit();
		
		//2. For each of those strings:
		TLCAlgorithm algorithm=new TLCAlgorithm();
		for (Book book : books){
			
			tx=session.beginTransaction();
			String text=book.getText();
			System.out.println(book.getShort_title());
			try{
				//2.0. Process all those strings with the BookTLC Algorithm
				Node root_node=algorithm.parseText(text);
				
				//2.1. Get the last database_id from the BOOKSTLC table
				
				Query query1=session.createQuery("select max(b.id) from BooksTLC b");
				long max_id;
				try{
					//TODO: FIx here because obviously, I don't know what this is
					//actually doing. There is a big bug here.
					max_id=(Long)query1.uniqueResult();
					
					
				}
				catch(java.lang.NullPointerException e){
					max_id=-1;
				}
				
				System.out.println("max_id: "+Long.toString(max_id));
				
				
				
				//2.2. Update the database_id, parent_id, left_child_id and right_
				//child_id of the nodes by addding to it + last database_id + 1
				
				LinkedList<Node> queue=new LinkedList<Node>();
				queue.push(root_node);
				while (!queue.isEmpty()){
					Node node=queue.pollFirst();
					node.setDatabase_id(node.getDatabase_id()+max_id+1);
					node.setFather_id(node.getFather_id()+max_id+1);
					node.setRight_child_id(node.getRight_child_id()+max_id+1);
					node.setLeft_child_id(node.getLeft_child_id()+max_id+1);
					
					for (Node child_node : node.getChildren()){
						queue.add(child_node);
					}
				}
								
				//2.3 Convert all the nodes in the document tree into database entries.
				//2.4 Update the BOOKS table with the database_id of the root node.
				//2.5  Save all those entries in a transaction.

				queue.clear();
				queue.push(root_node);
				
				session.saveOrUpdate(book);
				int count=0;
				while (!queue.isEmpty()){
					Node node=queue.pollFirst();
					
					BookTLC tlc=new BookTLC(node, book);
					if (count==0) book.setRootId(tlc);
					session.save(tlc);
					count++;
					
					for (Node child_node : node.getChildren()){
						queue.add(child_node);
					}
				}
				
				System.out.println("Elements inserted: "+Integer.toString(count));
				tx.commit();
			}
			
			catch(Exception e){
				e.printStackTrace();
				continue;
			}
			
		}
		
		//2.6 Commit the entries to the database.
		
		session.close();
		
	}
	
	private String getMetadata(String text){
		
		int a=text.indexOf("<metadata>");
		int b=text.indexOf("</metadata>");
		
		return text.substring(a, b+11);
	}
	
	private String getStructure(String text){
		int a=text.indexOf("<structure>");
		int b=text.indexOf("</structure>");
		
		return text.substring(a+11, b);
	}
}
