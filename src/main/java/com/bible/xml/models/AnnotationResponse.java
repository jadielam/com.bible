package com.bible.xml.models;

import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessType;

@XmlRootElement(name="annotation_response")
@XmlAccessorType(XmlAccessType.FIELD)
public class AnnotationResponse {

	private List<Annotation> annotation=new LinkedList<Annotation>();
	
	public AnnotationResponse(){
		super();
	}

	/**
	 * @return the annotations
	 */
	public List<Annotation> getAnnotations() {
		return annotation;
	}

	/**
	 * @param annotations the annotations to set
	 */
	public void setAnnotations(List<Annotation> annotations) {
		this.annotation = annotations;
	}
	
	
}
