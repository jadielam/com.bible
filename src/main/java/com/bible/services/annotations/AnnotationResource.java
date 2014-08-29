package com.bible.services.annotations;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.List;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.GET;
import javax.ws.rs.core.StreamingOutput;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.bible.database.DatabaseRequests;
import com.bible.xml.models.Annotation;
import com.bible.xml.models.AnnotationResponse;


@Path("/annotation")
public class AnnotationResource {

	private final SessionFactory sessionFactory;
	
	public AnnotationResource(SessionFactory sessionFactory){
		this.sessionFactory=sessionFactory;
	}
	
	/***
	 * This method returns up to max_number of annotations of type type that are within the BooksTLC ids start_id and end_id
	 */
	@GET
	@Produces("application/xml")
	public AnnotationResponse getAnnotations(@MatrixParam("type") String type, @MatrixParam("start_id") long start_id, 
			@MatrixParam("end_id") long end_id, @MatrixParam("max_number") int max_number){
		
		
		AnnotationResponse toReturn=new AnnotationResponse();
		
		
		Session session=sessionFactory.getCurrentSession();
		session.getTransaction().begin();
		List<Annotation> annotations=DatabaseRequests.getAnnotations(session,  start_id, end_id, max_number, type);
		
		toReturn.setAnnotations(annotations);
				
		session.getTransaction().commit();
		return toReturn;
				
	}
}
