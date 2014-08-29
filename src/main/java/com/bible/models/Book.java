package com.bible.models;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;



/**
 * This class models the Books table in the database.
 * It is very simple. It indexes a list of books by id, storing different representation versions of those texts.
 * @author jadiel
 *
 */
@Entity
@Table(name="books")
public class Book implements Serializable {

	/**
	 * 
	 */
	
	@Transient
	private static final long serialVersionUID = 3302714421868634485L;

	/**
	 * book unique id
	 */
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	/**
	 * Book title
	 */
	@Column(nullable=false, length=255)
	private String title;
	
	/**
	 * Book short title
	 */
	@Column(unique=true, nullable=false, length=10)
	private String short_title;
	
	/**
	 * text representation
	 */
	@Lob
	@Column(columnDefinition="LONGTEXT", length=1048576000)
	@Basic(fetch=FetchType.LAZY)
	private String text;
	
	/**
	 * Structured text representation, most likely some sort of xml, and maybe epub format.
	 */
	@Lob
	@Column(columnDefinition="LONGTEXT", length=1048576000)
	@Basic(fetch=FetchType.LAZY)
	private String structured_text;
	
	/**
	 * Annotated text using semantic annotations created by our annotation engines.
	 * 
	 */
	@Lob
	@Column(columnDefinition="LONGTEXT", length=1048576000)
	@Basic(fetch=FetchType.LAZY, optional=true)
	private String annotated_text;
	
	/**
	 * Metadata of the book.
	 */
	@Column(length=1000, nullable=false)
	private String metadata;
	
	
	/**
	 * id of the root-node of the TLC in the BooksTLC table.
	 * If the id is null, the book has not been processed yet.
	 */
	@OneToOne
	@JoinColumn(nullable=true)
	private BookTLC rootId;
	
	/**
	 * It contains the hierarchy of levels in order.
	 * I.e: structure="[html, book, chapter, verse]"
	 */
	@Basic(fetch=FetchType.LAZY, optional=false)
	private String structure;
	
	public Book() {
		
	}

	public long getId() {
		return id;
	}

	private void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getShort_title() {
		return short_title;
	}

	public void setShort_title(String short_title) {
		this.short_title = short_title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getStructured_text() {
		return structured_text;
	}

	public void setStructured_text(String structured_text) {
		this.structured_text = structured_text;
	}

	public String getAnnotated_text() {
		return annotated_text;
	}

	public void setAnnotated_text(String annotated_text) {
		this.annotated_text = annotated_text;
	}

	public String getMetadata() {
		return metadata;
	}

	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}

	public BookTLC getRootId() {
		return rootId;
	}

	public void setRootId(BookTLC rootId) {
		this.rootId = rootId;
	}

	public String getStructure() {
		return structure;
	}

	public void setStructure(String structure) {
		this.structure = structure;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((annotated_text == null) ? 0 : annotated_text.hashCode());
		result = prime * result
				+ ((metadata == null) ? 0 : metadata.hashCode());
		result = prime * result
				+ ((short_title == null) ? 0 : short_title.hashCode());
		result = prime * result
				+ ((structure == null) ? 0 : structure.hashCode());
		result = prime * result
				+ ((structured_text == null) ? 0 : structured_text.hashCode());
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (annotated_text == null) {
			if (other.annotated_text != null)
				return false;
		} else if (!annotated_text.equals(other.annotated_text))
			return false;
		if (metadata == null) {
			if (other.metadata != null)
				return false;
		} else if (!metadata.equals(other.metadata))
			return false;
		if (short_title == null) {
			if (other.short_title != null)
				return false;
		} else if (!short_title.equals(other.short_title))
			return false;
		if (structure == null) {
			if (other.structure != null)
				return false;
		} else if (!structure.equals(other.structure))
			return false;
		if (structured_text == null) {
			if (other.structured_text != null)
				return false;
		} else if (!structured_text.equals(other.structured_text))
			return false;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Books [getId()=" + getId() + ", getTitle()=" + getTitle()
				+ ", getShort_title()=" + getShort_title() + ", getText()="
				+ getText() + ", getStructured_text()=" + getStructured_text()
				+ ", getAnnotated_text()=" + getAnnotated_text()
				+ ", getMetadata()=" + getMetadata() + ", getRootId()="
				+ getRootId() + ", getStructure()=" + getStructure() + "]";
	}

	
	
	
	
	
		
	
}
