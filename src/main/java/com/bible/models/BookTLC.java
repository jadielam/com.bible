package com.bible.models;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.bible.TLC.Node;
import com.bible.xml.models.Content;

/**
 * Class used to model the Books_TCL table in the database.
 * The table can represent a hierarchical structure, such as the one present in the Table of Contents (TLC) of a book.
 * Each node has an id, and it also has a parent_id, and left_child and right_child ids. 
 * The left_child is the id of the first child of the node, and the right_child id is the id of the last child of the node.
 * Note that this representation implies that all the children of a node are entered using consecutive ids in the table.
 * @author jadiel de armas
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name="BooksTLC")
public class BookTLC implements Serializable, Content {

	/**
	 * 
	 */
	@XmlTransient
	@Transient
	private static final long serialVersionUID = -8539238913694655414L;

	/**
	 * The id of the entry in the database
	 */
	@XmlAttribute
	@Id
	private Long id;
	
	/**
	 * A foreign key to the document_id that contains the text of the document we are representing
	 */
	@XmlTransient
	@ManyToOne
	@JoinColumn(name="book_id")
	@Basic(optional=true, fetch=FetchType.LAZY)
	private Book document;
	
	/**
	 * A reference to another id in this table.
	 */
	@XmlTransient
	@ManyToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="parent_id")
	@Basic(optional=true, fetch=FetchType.LAZY)
	private BookTLC parent;
	
	/**
	 * A reference to another id in this table
	 */
	@XmlTransient
	@OneToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="left_child_id")
	@Basic(optional=true, fetch=FetchType.EAGER)
	private BookTLC left_child;
	
	/**
	 * A reference to another id in this table.
	 */
	@XmlTransient
	@OneToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="right_child_id")
	@Basic(optional=true, fetch=FetchType.EAGER)
	private BookTLC right_child;
	
	/**
	 * The String beginning offset, inclusive,  where the text of the current node on the entire document text of the document represented by document_id
	 */
	@XmlTransient
	@Basic(optional=true, fetch=FetchType.EAGER)
	private Integer left_offset;
	
	/**
	 * The String ending offset, not inclusive, where the text of the current node on the entire document text of the document represented by document_id
	 */
	@XmlTransient
	@Basic(optional=true, fetch=FetchType.EAGER)
	private Integer right_offset;
	
	/**
	 * The text from the books table in this table, to save time and be faster. 
	 */
	@XmlElement
	@Lob
	@Column(columnDefinition="LONGTEXT")
	@Basic(optional=true, fetch=FetchType.EAGER)
	private String text;
	
	/**
	 * A human readable key to the document, so that we can quickly access elements by this key, if we know the structure of the document beforehand.
	 * Some examples of keys for the Bible would be: "(Mat, 3, 16)", "(Mat)", "(Mat, 3)"
	 */
	@XmlAttribute
	@Basic(optional=false, fetch=FetchType.EAGER)
	private String hreadable_key;
	
	@XmlTransient
	@OneToMany(mappedBy="source")
	@Basic(fetch=FetchType.LAZY)
	private Set<Books_Annotation_Place> places=new HashSet<Books_Annotation_Place>();
	
	@XmlTransient
	@OneToMany(mappedBy="source")
	@Basic(fetch=FetchType.LAZY)
	private Set<Books_Annotation_Topic> topics=new HashSet<Books_Annotation_Topic>();
	
	@XmlTransient
	@OneToMany(mappedBy="source")
	@Basic(fetch=FetchType.LAZY)
	private Set<Books_Annotation_Reference> cross_references=new HashSet<Books_Annotation_Reference>();
	
	@XmlTransient
	@OneToMany(mappedBy="content")
	@Basic(fetch=FetchType.LAZY)
	private Set<Books_Annotation_Reference> cross_references_content=new HashSet<Books_Annotation_Reference>();

	@XmlTransient
	@OneToMany(mappedBy="source")
	@Basic(fetch=FetchType.LAZY)
	private Set<Books_Annotation_Person> persons=new HashSet<Books_Annotation_Person>();
	
	/**
	 * An aggregate field that will be updated from time to time that will contain topics of this element.
	 * A more proper (but slower way) of acccesing more up-to-date information on the topics of this element of the table would be to query the Books_Annotation_Topic table 
	 */
	@XmlTransient
	@Basic(optional=true, fetch=FetchType.EAGER)
	private String topics_aggregate;

	/**
	 * Similar to topics_aggregate
	 */
	
	@XmlTransient
	@Basic(optional=true, fetch=FetchType.EAGER)
	private String persons_aggregate;
	
	/**
	 * Similar to persons_aggregate
	 */
	@XmlTransient
	@Basic(optional=true, fetch=FetchType.EAGER)
	private String places_aggregate;
	
	/**
	 * Similar to places_aggregate
	 */
	@XmlTransient
	@Basic(optional=true, fetch=FetchType.EAGER)
	private String cross_references_aggregate;

	/**
	 * 
	 */
	@XmlTransient
	@Basic(optional=false, fetch=FetchType.EAGER)
	private boolean processed=false;
	
	@XmlTransient
	@Basic(optional=false, fetch=FetchType.EAGER)
	private int depth;
	
	/**
	 * Dictates what is his position among the children of the parent
	 */
	@XmlTransient
	@Basic(optional=true, fetch=FetchType.EAGER)
	private int ordering;
	
	/**
	 * This non-argument constructor is required by Hibernate. It might be deprecated in the future.
	 */
	public BookTLC(){
		super();
	}
	
	public BookTLC(Node node, Book book){
		this.id=node.getDatabase_id();
		this.hreadable_key=node.getStructure_key();
		this.left_offset=node.getAbsolute_start_offset();
		this.right_offset=node.getAbsolute_end_offset();
		this.depth=node.getOrdering();
		this.document=book;
	}

	public Long getId() {
		return id;
	}

	@SuppressWarnings("unused")
	private void setId(Long id) {
		this.id = id;
	}

	public Book getDocument() {
		return document;
	}

	public void setDocument(Book document) {
		this.document = document;
	}

	public BookTLC getParent() {
		return parent;
	}

	public void setParent(BookTLC parent) {
		this.parent = parent;
	}

	public BookTLC getLeft_child() {
		return left_child;
	}

	public void setLeft_child(BookTLC left_child) {
		this.left_child = left_child;
	}

	public BookTLC getRight_child() {
		return right_child;
	}

	public void setRight_child(BookTLC right_child) {
		this.right_child = right_child;
	}

	public Integer getLeft_offset() {
		return left_offset;
	}

	public void setLeft_offset(Integer left_offset) {
		this.left_offset = left_offset;
	}

	public Integer getRight_offset() {
		return right_offset;
	}

	public void setRight_offset(Integer right_offset) {
		this.right_offset = right_offset;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getHreadable_key() {
		return hreadable_key;
	}

	public void setHreadable_key(String hreadable_key) {
		this.hreadable_key = hreadable_key;
	}

	public Set<Books_Annotation_Place> getPlaces() {
		return places;
	}

	public void setPlaces(Set<Books_Annotation_Place> places) {
		this.places = places;
	}

	public Set<Books_Annotation_Topic> getTopics() {
		return topics;
	}

	public void setTopics(Set<Books_Annotation_Topic> topics) {
		this.topics = topics;
	}

	public Set<Books_Annotation_Reference> getCross_references() {
		return cross_references;
	}

	public void setCross_references(Set<Books_Annotation_Reference> cross_references) {
		this.cross_references = cross_references;
	}

	public Set<Books_Annotation_Person> getPersons() {
		return persons;
	}

	public void setPersons(Set<Books_Annotation_Person> persons) {
		this.persons = persons;
	}

	public String getTopics_aggregate() {
		return topics_aggregate;
	}

	public void setTopics_aggregate(String topics_aggregate) {
		this.topics_aggregate = topics_aggregate;
	}

	public String getPersons_aggregate() {
		return persons_aggregate;
	}

	public void setPersons_aggregate(String persons_aggregate) {
		this.persons_aggregate = persons_aggregate;
	}

	public String getPlaces_aggregate() {
		return places_aggregate;
	}

	public void setPlaces_aggregate(String places_aggregate) {
		this.places_aggregate = places_aggregate;
	}

	public String getCross_references_aggregate() {
		return cross_references_aggregate;
	}

	public void setCross_references_aggregate(String cross_references_aggregate) {
		this.cross_references_aggregate = cross_references_aggregate;
	}

	public boolean isProcessed() {
		return processed;
	}

	public void setProcessed(boolean processed) {
		this.processed = processed;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public int getOrdering() {
		return ordering;
	}

	public void setOrdering(int order) {
		this.ordering = order;
	}

	
	/**
	 * @return the cross_references_content
	 */
	public Set<Books_Annotation_Reference> getCross_references_content() {
		return cross_references_content;
	}

	/**
	 * @param cross_references_content the cross_references_content to set
	 */
	public void setCross_references_content(
			Set<Books_Annotation_Reference> cross_references_content) {
		this.cross_references_content = cross_references_content;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + depth;
		result = prime * result
				+ ((document == null) ? 0 : document.hashCode());
		result = prime * result
				+ ((hreadable_key == null) ? 0 : hreadable_key.hashCode());
		result = prime * result
				+ ((left_offset == null) ? 0 : left_offset.hashCode());
		result = prime * result + ordering;
		result = prime * result
				+ ((right_offset == null) ? 0 : right_offset.hashCode());
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
		BookTLC other = (BookTLC) obj;
		if (depth != other.depth)
			return false;
		if (document == null) {
			if (other.document != null)
				return false;
		} else if (!document.equals(other.document))
			return false;
		if (hreadable_key == null) {
			if (other.hreadable_key != null)
				return false;
		} else if (!hreadable_key.equals(other.hreadable_key))
			return false;
		if (left_offset == null) {
			if (other.left_offset != null)
				return false;
		} else if (!left_offset.equals(other.left_offset))
			return false;
		if (ordering != other.ordering)
			return false;
		if (right_offset == null) {
			if (other.right_offset != null)
				return false;
		} else if (!right_offset.equals(other.right_offset))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BookTLC [getId()=" + getId() + 
				", getLeft_offset()=" + getLeft_offset()
				+ ", getRight_offset()=" + getRight_offset() + ", getText()="
				+ getText() + ", getHreadable_key()=" + getHreadable_key()
				
				+ ", getTopics_aggregate()=" + getTopics_aggregate()
				+ ", getPersons_aggregate()=" + getPersons_aggregate()
				+ ", getPlaces_aggregate()=" + getPlaces_aggregate()
				+ ", getCross_references_aggregate()="
				+ getCross_references_aggregate() + ", isProcessed()="
				+ isProcessed() + ", getDepth()=" + getDepth()
				+ ", getOrder()=" + getOrdering() + "]";
	}

		
}
