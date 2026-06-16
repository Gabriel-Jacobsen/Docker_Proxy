package com.Dorcker.connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.Dorcker.cache.Cache;

public class Proxy {

	private List<DockerConnect> dockerConnect = new ArrayList<DockerConnect>();
	private Cache cache;
	
	public Proxy(String caminhoConfig) {
		Config config = new Config(caminhoConfig);
		int i = 1;
		for(String conn: config.getConnections()) {
			dockerConnect.add(new DockerConnect(conn, "Conexão"+i, config.getSenha(),config.getTabela()));
			i++;
		}
		cache = new Cache(config.getCacheTTL());
	}
	
	public String buscar(String descricao) {
		String desc = cache.get(descricao);
		if (desc != null) {
			return "Registro encotrado pelo Cache: "+desc;
		}
		
		for(DockerConnect dock: dockerConnect) {
			ResultSet rs = dock.select();
			try {
				while (rs.next()) {
				    desc = rs.getString("descricao");
				    if (desc.equals(descricao)) {
				    	int id =  rs.getInt("id");
				    	cache.add(id, desc);
				    	String result = "Registro encotrado pela "+dock.getNome() + ": ID = "+ id + " Descrição = "+ desc;
				    	return result;
				    }
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return "Registro não encontrado";
	}
	
}
