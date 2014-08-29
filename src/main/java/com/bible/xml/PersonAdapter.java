package com.bible.xml;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import com.bible.models.Person;

public class PersonAdapter extends XmlAdapter<NameId, Person>{

	@Override
	public Person unmarshal(NameId v) throws Exception {
		Person person=new Person();
		person.setName(v.getMain_name());
		return person;
	}

	@Override
	public NameId marshal(Person v) throws Exception {
		
		String name=v.getName();
		Long id=(long) v.getId();
		NameId nameid=new NameId();
		nameid.setId(id);
		nameid.setMain_name(name);
		
		return nameid;
	}
}