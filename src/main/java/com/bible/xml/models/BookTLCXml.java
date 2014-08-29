package com.bible.xml.models;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.bible.models.BookTLC;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name="BooksTLC")
public class BookTLCXml implements Content {

	@XmlAttribute
	@Id
	private Long id;
	
	@XmlAttribute
	@Basic(optional=false, fetch=FetchType.EAGER)
	private String hreadable_key;
	
	@XmlElement
	@Lob
	@Column(columnDefinition="LONGTEXT")
	@Basic(optional=true, fetch=FetchType.EAGER)
	private String text;
	
	public BookTLCXml(BookTLC entry){
		this.id=entry.getId();
		this.hreadable_key=entry.getHreadable_key();
		this.text=entry.getText();
	}
	
	public BookTLCXml(){
		super();
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the hreadable_key
	 */
	public String getHreadable_key() {
		return hreadable_key;
	}

	/**
	 * @param hreadable_key the hreadable_key to set
	 */
	public void setHreadable_key(String hreadable_key) {
		this.hreadable_key = hreadable_key;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((hreadable_key == null) ? 0 : hreadable_key.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BookTLCXml other = (BookTLCXml) obj;
		if (hreadable_key == null) {
			if (other.hreadable_key != null)
				return false;
		} else if (!hreadable_key.equals(other.hreadable_key))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BookTLCXml [getId()=" + getId() + ", getHreadable_key()="
				+ getHreadable_key() + ", getText()=" + getText() + "]";
	}
	
	
}
