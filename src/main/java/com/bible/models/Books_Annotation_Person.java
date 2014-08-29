package com.bible.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;


@Entity
public class Books_Annotation_Person extends Books_Annotation implements Books_Annotation_Content {
	
	/**
	 * 
	 */
	@Transient
	private static final long serialVersionUID = -342480563677361546L;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn
	private Person content;

	public Books_Annotation_Person() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the person
	 */
	public Person getContent() {
		return content;
	}

	/**
	 * @param person the person to set
	 */
	public void setContent(Person person) {
		this.content = person;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Books_Annotation_Person other = (Books_Annotation_Person) obj;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Books_Annotation_Person [getPerson()=" + getContent()
				+ ", getSource()=" + getSource() + ", getBook()=" + getBook()
				+ ", getLeft_offset()=" + getLeft_offset()
				+ ", getRight_offset()=" + getRight_offset()
				+ ", getStrength()=" + getStrength() + "]";
	}

	
			
}
