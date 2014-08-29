package com.bible.xml;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import com.bible.models.BookTLC;

public class BooksTLCAdapter extends XmlAdapter<NameId, BookTLC>{

	@Override
	public BookTLC unmarshal(NameId v) throws Exception {
		BookTLC tlc=new BookTLC();
		tlc.setHreadable_key(v.getMain_name());
		return tlc;
	}

	@Override
	public NameId marshal(BookTLC v) throws Exception {
		
		String name=v.getHreadable_key();
		Long id=v.getId();
		NameId nameid=new NameId();
		nameid.setId(id);
		nameid.setMain_name(name);
		
		return nameid;
	}
}