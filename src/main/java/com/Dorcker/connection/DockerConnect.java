package com.Dorcker.connection;
import java.sql.*;

public class DockerConnect {

	private String nome;
	private String tabela;
	private Connection conn;
	private Statement stmt;
	
	public DockerConnect(String url, String nome, String senha, String tabela) {
		this.nome = nome;
		this.tabela = tabela;
		
		try {
			conn = DriverManager.getConnection(url,"root",senha);
			stmt = conn.createStatement();
	       
			System.out.println(nome+": "+"Conectado!");
		} catch (SQLException e) {
			System.out.println(nome+": "+"Erro ao conectar!");
			System.out.println(e.getMessage());
		}
	}
	
	public ResultSet select() {
		try {
	        return stmt.executeQuery("SELECT * FROM "+tabela);
	    } catch (SQLException e) {
	        System.out.println(nome + ": Erro ao executar SELECT!");
	        e.printStackTrace();
	        return null;
	    }
	}
	
	public String getNome() {
		return nome;
	}
}
