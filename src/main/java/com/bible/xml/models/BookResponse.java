package com.bible.xml.models;

import java.util.List;

import javax.ws.rs.core.Link;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement(name="bible_response")
@XmlAccessorType(XmlAccessType.FIELD)
public class BookResponse {

	@XmlElementWrapper(name="verses")
	@XmlElement(name="verse")
	private List<BookTLCXml> verses;
	
	
	@XmlElement
	@XmlJavaTypeAdapter(Link.JaxbAdapter.class)
	private Link next_link;
	
	@XmlElement
	@XmlJavaTypeAdapter(Link.JaxbAdapter.class)
	private Link previous_link;
	
	@XmlElement
	@XmlJavaTypeAdapter(Link.JaxbAdapter.class)
	private Link places_annotation_link;
	
	@XmlElement
	@XmlJavaTypeAdapter(Link.JaxbAdapter.class)
	private Link topics_annotation_link;
	
	@XmlElement
	@XmlJavaTypeAdapter(Link.JaxbAdapter.class)
	private Link persons_annotation_link;
	
	@XmlElement
	@XmlJavaTypeAdapter(Link.JaxbAdapter.class)
	private Link references_annotation_link;
	
	public BookResponse(){
		super();
	}

	/**
	 * @return the verses
	 */
	public List<BookTLCXml> getVerses() {
		return verses;
	}

	/**
	 * @param verses the verses to set
	 */
	public void setVerses(List<BookTLCXml> verses) {
		this.verses = verses;
	}

	/**
	 * @return the next_link
	 */
	public Link getNext_link() {
		return next_link;
	}

	/**
	 * @param next_link the next_link to set
	 */
	public void setNext_link(Link next_link) {
		this.next_link = next_link;
	}

	/**
	 * @return the previous_link
	 */
	public Link getPrevious_link() {
		return previous_link;
	}

	/**
	 * @param previous_link the previous_link to set
	 */
	public void setPrevious_link(Link previous_link) {
		this.previous_link = previous_link;
	}

	/**
	 * @return the places_annotation_link
	 */
	public Link getPlaces_annotation_link() {
		return places_annotation_link;
	}

	/**
	 * @param places_annotation_link the places_annotation_link to set
	 */
	public void setPlaces_annotation_link(Link places_annotation_link) {
		this.places_annotation_link = places_annotation_link;
	}

	/**
	 * @return the topics_annotation_link
	 */
	public Link getTopics_annotation_link() {
		return topics_annotation_link;
	}

	/**
	 * @param topics_annotation_link the topics_annotation_link to set
	 */
	public void setTopics_annotation_link(Link topics_annotation_link) {
		this.topics_annotation_link = topics_annotation_link;
	}

	/**
	 * @return the persons_annotation_link
	 */
	public Link getPersons_annotation_link() {
		return persons_annotation_link;
	}

	/**
	 * @param persons_annotation_link the persons_annotation_link to set
	 */
	public void setPersons_annotation_link(Link persons_annotation_link) {
		this.persons_annotation_link = persons_annotation_link;
	}

	/**
	 * @return the references_annotation_link
	 */
	public Link getReferences_annotation_link() {
		return references_annotation_link;
	}

	/**
	 * @param references_annotation_link the references_annotation_link to set
	 */
	public void setReferences_annotation_link(Link references_annotation_link) {
		this.references_annotation_link = references_annotation_link;
	}

	
	

}
