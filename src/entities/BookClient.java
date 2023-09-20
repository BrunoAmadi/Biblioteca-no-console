package entities;

import java.time.LocalDate;

public class BookClient {

	private int id;
	private String nome;
	private String autor;
	private String descricao;
	private LocalDate lancamento;
	private boolean disponivel;
	private Client cliente;
	
	
	
	
	public BookClient(int id, String nome, String autor, String descricao, LocalDate lancamento, boolean disponivel) {
		this.id = id;
		this.nome = nome;
		this.autor = autor;
		this.descricao = descricao;
		this.lancamento = lancamento;
		this.disponivel = disponivel;
	}




	public int getId() {
		return id;
	}


	public String getNome() {
		return nome;
	}


	public String getAutor() {
		return autor;
	}


	public String getDescricao() {
		return descricao;
	}



	public LocalDate getLancamento() {
		return lancamento;
	}
	

	public boolean isDisponivel() {
		return disponivel;
	}




	public void setDisponivel(boolean disponivel) {
		this.disponivel = disponivel;
	}




	public Client getCliente() {
		return cliente;
	}



	public void setCliente(Client cliente) {
		this.cliente = cliente;
	}




	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Id: " + id + "\n");
		sb.append("Nome: " + nome + "\n");
		sb.append("Autor: " + autor + "\n");
		sb.append("Descrição: " + descricao + "\n");
		sb.append("Lançamento: " + lancamento + "\n");
		sb.append("Disponivel: " + disponivel);
		
		
		return sb.toString();
	}
	
	
	
	
}
