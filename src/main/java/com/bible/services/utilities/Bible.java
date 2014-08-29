package com.bible.services.utilities;

import java.util.concurrent.ConcurrentHashMap;

public class Bible {

	
	public ConcurrentHashMap<String, Integer> books_order_map=new ConcurrentHashMap<String, Integer>();
	
	public Bible(){
		super();
		books_order_map.put("Gen", 1); books_order_map.put("Exo", 2); books_order_map.put("Lev", 3);
		books_order_map.put("Num", 4); books_order_map.put("Deu", 5); books_order_map.put("Jos", 6);
		books_order_map.put("Jdg", 7); books_order_map.put("Rut", 8); books_order_map.put("1Sa", 9);
		books_order_map.put("2Sa", 10); books_order_map.put("1Ki", 11); books_order_map.put("2Ki", 12);
		books_order_map.put("1Ch", 13); books_order_map.put("2Ch", 14); books_order_map.put("Ezr", 15);
		books_order_map.put("Neh", 16); books_order_map.put("Est", 17); books_order_map.put("Job", 18);
		books_order_map.put("Psa", 19); books_order_map.put("Pro", 20); books_order_map.put("Ecc", 21);
		books_order_map.put("Sol", 22); books_order_map.put("Isa", 23); books_order_map.put("Jer", 24);
		books_order_map.put("Lam", 25); books_order_map.put("Eze", 26); books_order_map.put("Dan", 27);
		books_order_map.put("Hos", 28); books_order_map.put("Joe", 29); books_order_map.put("Amo", 30);
		books_order_map.put("Oba", 31); books_order_map.put("Jon", 32); books_order_map.put("Mic", 33);
		books_order_map.put("Nah", 34); books_order_map.put("Hab", 35); books_order_map.put("Zep", 36);
		books_order_map.put("Hag", 37); books_order_map.put("Zec", 38); books_order_map.put("Mal", 39);
		books_order_map.put("Mat", 40); books_order_map.put("Mar", 41); books_order_map.put("Luk", 42);
		books_order_map.put("Joh", 43); books_order_map.put("Act", 44); books_order_map.put("Rom", 45);
		books_order_map.put("1Co", 46); books_order_map.put("2Co", 47); books_order_map.put("Gal", 48);
		books_order_map.put("Eph", 49); books_order_map.put("Phi", 50); books_order_map.put("Col", 51);
		books_order_map.put("1Th", 52); books_order_map.put("2Th", 53); books_order_map.put("1Ti", 54);
		books_order_map.put("2Ti", 55); books_order_map.put("Tit", 56); books_order_map.put("Phm", 57);
		books_order_map.put("Heb", 58); books_order_map.put("Jam", 59); books_order_map.put("1Pe", 60);
		books_order_map.put("2Pe", 61); books_order_map.put("1Jo", 62); books_order_map.put("2Jo", 63);
		books_order_map.put("3Jo", 64); books_order_map.put("Jud", 65); books_order_map.put("Rev", 66);
	}
}