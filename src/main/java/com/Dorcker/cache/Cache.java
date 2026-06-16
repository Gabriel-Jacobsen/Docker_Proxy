package com.Dorcker.cache;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Cache {

	private List<CacheEntry> registros = new ArrayList<CacheEntry>();
	private long ttlMillis;
	
	public Cache(long ttlMillis) {
		this.ttlMillis = ttlMillis;
		
		ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
		scheduler.scheduleAtFixedRate(this::limpar, 10, 10, TimeUnit.SECONDS);
	}
	
	public String get(String desc) {
		for(CacheEntry cache: registros) {
			if (cache.getDesc().equals(desc)) {
				return "ID = " + cache.getId() + " DESC = " + cache.getDesc();
			}
		}
		return null;
	}
	
	public void add(int id, String desc) {
		registros.add(new CacheEntry(id,desc));
	}
	
	private void limpar() {
		long now = System.currentTimeMillis();

	    registros.removeIf(cache ->
	        now - cache.getTime() >= ttlMillis
	    );
	}
	
}
