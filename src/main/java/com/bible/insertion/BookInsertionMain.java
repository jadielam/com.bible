package com.bible.insertion;

import org.hibernate.Session;

import com.bible.util.SessionUtil;

public class BookInsertionMain {

	public static void main(String[] args) {
		
		Session session=SessionUtil.getSession();
		DatabaseInsertion.insertBooks(session, "src/main/resources/file_list1");
		DatabaseInsertion.createTLC1(session);
		session.close();
		SessionUtil.close();
	}

}
