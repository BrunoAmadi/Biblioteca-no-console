package entities;

public class Client {

	private String nome;
	private String cpf;
	private String email;
	private String telefone;
	private BookClient livro;

	public Client(String nome, String cpf, String email, String telefone) {
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
		this.telefone = telefone;
	}



	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public BookClient getLivro() {
		return livro;
	}

	public void setLivro(BookClient livro) {
		this.livro = livro;
	}

	public void alugarLivro(int id) {
		livro = AllBook.getListLivros().stream().filter(x -> x.getId() == id).toList().get(0);
		if (livro.getId() == id) {
			livro.setDisponivel(false);
		}

	}
	public void devolverLivro(BookClient book) {	
		if (livro == book) {
			book.setDisponivel(true);
		}
		livro = null;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Nome Cliente: " + nome + "\n");
		sb.append("CPF: " + cpf + "\n");
		sb.append("Email: " + email + "\n");
		sb.append("Telefone: " + telefone + "\n");
		sb.append("Livro alugado pelo cliente: ");
		if(livro != null) {
			sb.append(livro.getNome() + "\n");
			sb.append("Autor livro: " + livro.getAutor());
		}else {
			sb.append("Nenhum livro alugado");
		}
	
		
		
		return sb.toString();
	}
	
	
	

}
