package com.bible.models;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.bible.xml.BooksTLCAdapter;

@MappedSuperclass
public abstract class Books_Annotation implements Serializable {


	/**
	 * 
	 */
	@Transient
	private static final long serialVersionUID = -3242894645837983637L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	/**
	 * 
	 */
	@XmlJavaTypeAdapter(BooksTLCAdapter.class)
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn
	private BookTLC source;
	
	/**
	 * 
	 */
	@XmlTransient
	@ManyToOne(fetch=FetchType.LAZY)
	private Book book;
	
	/**
	 * 
	 */
	@XmlTransient
	@Basic(fetch=FetchType.EAGER, optional=true)
	private Integer left_offset;
	
	/**
	 * 
	 */
	@XmlTransient
	@Basic(fetch=FetchType.EAGER, optional=true)
	private Integer right_offset;
	
	/**
	 * 
	 */
	@XmlElement
	@Basic(fetch=FetchType.EAGER, optional=true)
	private Double strength;
	
	public Books_Annotation() {
		super();
		
	}

	/**
	 * @return the source
	 */
	public BookTLC getSource() {
		return source;
	}

	/**
	 * @param source the source to set
	 */
	public void setSource(BookTLC source) {
		this.source = source;
	}

	/**
	 * @return the book
	 */
	public Book getBook() {
		return book;
	}
	
	public Long getId(){
		return this.id;
	}

	/**
	 * @param book the book to set
	 */
	public void setBook(Book book) {
		this.book = book;
	}

	/**
	 * @return the left_offset
	 */
	public Integer getLeft_offset() {
		return left_offset;
	}

	/**
	 * @param left_offset the left_offset to set
	 */
	public void setLeft_offset(Integer left_offset) {
		this.left_offset = left_offset;
	}

	/**
	 * @return the right_offset
	 */
	public Integer getRight_offset() {
		return right_offset;
	}

	/**
	 * @param right_offset the right_offset to set
	 */
	public void setRight_offset(Integer right_offset) {
		this.right_offset = right_offset;
	}

	/**
	 * @return the strength
	 */
	public Double getStrength() {
		return strength;
	}

	/**
	 * @param strength the strength to set
	 */
	public void setStrength(Double strength) {
		this.strength = strength;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((book == null) ? 0 : book.hashCode());
		result = prime * result
				+ ((left_offset == null) ? 0 : left_offset.hashCode());
		result = prime * result
				+ ((right_offset == null) ? 0 : right_offset.hashCode());
		result = prime * result + ((source == null) ? 0 : source.hashCode());
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
		Books_Annotation other = (Books_Annotation) obj;
		if (book == null) {
			if (other.book != null)
				return false;
		} else if (!book.equals(other.book))
			return false;
		if (left_offset == null) {
			if (other.left_offset != null)
				return false;
		} else if (!left_offset.equals(other.left_offset))
			return false;
		if (right_offset == null) {
			if (other.right_offset != null)
				return false;
		} else if (!right_offset.equals(other.right_offset))
			return false;
		if (source == null) {
			if (other.source != null)
				return false;
		} else if (!source.equals(other.source))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Books_Annotation [getSource()=" + getSource() + ", getBook()="
				+ getBook() + ", getLeft_offset()=" + getLeft_offset()
				+ ", getRight_offset()=" + getRight_offset()
				+ ", getStrength()=" + getStrength() + "]";
	}
	
	
	
	
}