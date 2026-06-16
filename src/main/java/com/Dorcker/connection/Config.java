package com.Dorcker.connection;

import java.io.File;
import java.io.IOException;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Config {

	private int cacheTTL;
    private int retryCount;
    private String senha;
    private String tabela;
    private List<String> connections;
	
    public Config() {
    	
    }
    
	public Config(String arquivo) {
		 try {
	            ObjectMapper mapper = new ObjectMapper();

	            Config config = mapper.readValue(new File(arquivo),Config.class);

	            this.cacheTTL = config.cacheTTL;
	            this.retryCount = config.retryCount;
	            this.connections = config.connections;
	            this.senha =  config.senha;
	            this.tabela =  config.tabela;

	        } catch (IOException e) {
	            throw new RuntimeException(
	                    "Erro ao carregar config.json", e);
	        }
	}

	public int getCacheTTL() {
		return cacheTTL;
	}

	public int getRetryCount() {
		return retryCount;
	}

	public List<String> getConnections() {
		return connections;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public String getTabela() {
		return tabela;
	}
	
}
