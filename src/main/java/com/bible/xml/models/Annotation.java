package com.bible.xml.models;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlRootElement(name="annotation")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso({PersonXml.class, PlaceXml.class, BookTLCXml.class, TopicXml.class})
public class Annotation {

	@XmlElement(name="id")
	private Long bookTLC_id;
	
	@XmlAnyElement
	private Content content;
	
	public Annotation(){
		super();
	}

	/**
	 * @return the bookTLC_id
	 */
	public Long getBookTLC_id() {
		return bookTLC_id;
	}

	/**
	 * @param bookTLC_id the bookTLC_id to set
	 */
	public void setBookTLC_id(Long bookTLC_id) {
		this.bookTLC_id = bookTLC_id;
	}

	/**
	 * @return the content
	 */
	public Content getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(Content content) {
		this.content = content;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Annotation [getBookTLC_id()=" + getBookTLC_id()
				+ ", getContent()=" + getContent() + "]";
	}
	
	
	
}
