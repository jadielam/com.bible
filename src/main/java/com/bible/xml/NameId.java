package com.bible.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class NameId {
	
	@XmlElement
	private String main_name_string;
	
	@XmlAttribute
	private Long main_name_id;
	
	
	public NameId() {
		super();
		// TODO Auto-generated constructor stub
	}


	public String getMain_name() {
		return main_name_string;
	}


	public void setMain_name(String name) {
		this.main_name_string = name;
	}


	public Long getId() {
		return main_name_id;
	}


	public void setId(Long id) {
		this.main_name_id = id;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((main_name_id == null) ? 0 : main_name_id.hashCode());
		result = prime * result + ((main_name_string == null) ? 0 : main_name_string.hashCode());
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
		NameId other = (NameId) obj;
		if (main_name_id == null) {
			if (other.main_name_id != null)
				return false;
		} else if (!main_name_id.equals(other.main_name_id))
			return false;
		if (main_name_string == null) {
			if (other.main_name_string != null)
				return false;
		} else if (!main_name_string.equals(other.main_name_string))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "NameId [getName()=" + getMain_name() + ", getId()=" + getId() + "]";
	}

	
}
