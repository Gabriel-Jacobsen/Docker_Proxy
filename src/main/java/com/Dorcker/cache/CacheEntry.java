package com.Dorcker.cache;

public class CacheEntry {

	private int id;
	private String desc;
	private long time;
	
	public CacheEntry(int id, String desc) {
		this.id = id;
		this.desc = desc;
		time = System.currentTimeMillis();
	}

	public int getId() {
		return id;
	}

	public String getDesc() {
		return desc;
	}

	public long getTime() {
		return time;
	}
	
	
}
