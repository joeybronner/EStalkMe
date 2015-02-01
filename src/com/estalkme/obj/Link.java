package com.estalkme.obj;

public class Link {
	
	String title;
	String link;
	String minimalTitle;
	
	Link() {}
	
	public Link(String title, String link, String minititle) {
		this.title = title;
		this.link = link;
		this.minimalTitle = minititle;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public String getLink() {
		return this.link;
	}
	
	public String getMinimalTitle() {
		return this.minimalTitle;
	}
	
	public void setTitle(String t) {
		this.title = t;
	}
	
	public void setLink(String l) {
		this.link = l;
	}
	
	public void setMinimalTitle(String ml) {
		this.minimalTitle = ml;
	}

}
