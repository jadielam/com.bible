package com.bible.xml.models;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessType;

import com.bible.models.Topic;

@XmlRootElement(name="topic")
@XmlAccessorType(XmlAccessType.FIELD)
public class TopicXml implements Content {

	@XmlAttribute
	private Long id;
	
	@XmlElement
	private String name;
	
	@XmlElement
	private Long main_name_id;
	
	@XmlElement
	private String description;
	
	public TopicXml(Topic topic){
		this.id=topic.getId();
		this.name=topic.getName();
		this.description=topic.getDescription();
		
	}
	
	public TopicXml(){
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the main_name_id
	 */
	public Long getMain_name_id() {
		return main_name_id;
	}

	/**
	 * @param main_name_id the main_name_id to set
	 */
	public void setMain_name_id(Long main_name_id) {
		this.main_name_id = main_name_id;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((main_name_id == null) ? 0 : main_name_id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		TopicXml other = (TopicXml) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (main_name_id == null) {
			if (other.main_name_id != null)
				return false;
		} else if (!main_name_id.equals(other.main_name_id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TopicXml [getId()=" + getId() + ", getName()=" + getName()
				+ ", getMain_name_id()=" + getMain_name_id()
				+ ", getDescription()=" + getDescription() + "]";
	}
	
	
	
	
	
}
