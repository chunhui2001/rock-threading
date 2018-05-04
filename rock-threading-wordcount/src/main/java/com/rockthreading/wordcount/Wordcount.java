package com.rockthreading.wordcount;

import java.util.HashMap;

public class Wordcount {
	
	class Pages {
		
		public Pages(int pageCount, String fileName) {
			
		}
	}
	
	class Page {
		public Page(int pageCount, String fileName) {
			
		}
	}

	private static final HashMap<String, Integer> counts = new HashMap<String, Integer>();
	
	public void calculate(String fileName) {
		
		Iterable<Page> pages = (Iterable<Page>) new Page(10000, fileName);
		
		for (Page page : pages) {
			
		}
		
	}
	
}
