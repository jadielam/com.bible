package com.bible.xml;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import com.bible.models.Place;

public class PlaceAdapter extends XmlAdapter<NameId, Place>{

	@Override
	public Place unmarshal(NameId v) throws Exception {
		Place place=new Place();
		place.setName(v.getMain_name());
		return place;
	}

	@Override
	public NameId marshal(Place v) throws Exception {
		
		String name=v.getName();
		Long id=(long) v.getId();
		NameId nameid=new NameId();
		nameid.setId(id);
		nameid.setMain_name(name);
		
		return nameid;
	}

}
