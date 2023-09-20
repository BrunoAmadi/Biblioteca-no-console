package application;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

import org.apache.commons.io.FileDeleteStrategy;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import entities.AllBook;
import entities.BookClient;
import entities.Client;

public class Program {
	public static void main(String[] args) throws IOException, ParseException {

		// usando filewriter para escrever o json
		FileWriter fileWriter = null;

		JSONObject jsonObj = new JSONObject();
		JSONObject jsonObj2 = new JSONObject();
		JSONParser jsonParser = new JSONParser();

		File file = new File("livros.txt");
		Scanner scanLivros = null;

		try {
			scanLivros = new Scanner(file);
			while (scanLivros.hasNextLine()) {

				// cortando a linha do arquivo csv, fazendo split e cortando na virgula para
				// separar a string

				String[] line = scanLivros.nextLine().split(",");

				int id = Integer.parseInt(line[0].trim());
				String nome = line[1].trim();
				String autor = line[2].trim();
				String descricao = line[3].trim();
				LocalDate date = LocalDate.parse(line[4].trim());
				boolean disponivel = true;

				// instanciando o objeto livro a partir do csv com todos os livros
				BookClient book = new BookClient(id, nome, autor, descricao, date, disponivel);
				AllBook.addLivro(book);
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());

		} finally {

			scanLivros.close();
		}

		// scanner para pegar os dados de entrada do usuario no sistema
		Scanner sc = new Scanner(System.in);

		// Variaveis que serao usadas dentro do switch
		int n = 0;
		char confirm;
		boolean continuar = true;
		Client client = null;

		System.out.println("----------SEJA BEM VINDO A BIBLIOTECA-------------");
		while (continuar) {

			switch (n) {
			case 0:
				System.out.println("----------MENU-------------\"");
				System.out.println("Escolha uma das opções abaixo: ");
				System.out.println();
				System.out.println("1 - Criar cadastro / consultar");
				System.out.println("2 - Consultar livros disponiveis");
				System.out.println("3 - Alugar um livro");
				System.out.println("4 - Devolver um livro");
				System.out.println("5 - Exportar livros alugados para csv");
				System.out.println("6 - Sair");
				System.out.println();
				System.out.print("Opçao desejada: ");
				n = sc.nextInt();

				break;

			case 1:

				sc.nextLine();
				System.out.println("Digite os dados do usuario que será cadastrado");
				System.out.print("Nome: ");
				String name = sc.nextLine();
				System.out.print("CPF: ");
				String cpf = sc.nextLine();
				System.out.print("Email: ");
				String email = sc.nextLine();
				System.out.print("Telefone com DDD: ");
				String tel = sc.nextLine();
				client = new Client(name, cpf, email, tel);
				System.out.println();
				System.out.println("Confirme os dados abaixo para efetuar o cadastro: ");
				System.out.println();
				System.out.println(client);
				System.out.print("Os dados acima estão corretos: (S/N): ");
				confirm = sc.next().charAt(0);
				if (confirm == 's' || confirm == 'S') {

					jsonObj.put("nome", client.getNome());
					jsonObj.put("cpf", client.getCpf());
					jsonObj.put("email", client.getEmail());
					jsonObj.put("telefone", client.getTelefone());

					fileWriter = new FileWriter("Cadastros-Clientes.json");
					fileWriter.write(jsonObj.toJSONString());
					fileWriter.close();
					System.out.println();
					System.out.println("Cadastro efetuado com sucesso");
					System.out.println();
					n = 0;
				}

				break;

			case 2:
				System.out.println("Digite a opção abaixo para ver a lista de livros: ");
				System.out.println("1 - livros disponiveis ");
				System.out.println("2 - livros Indisponiveis ");

				System.out.println("0 - Voltar ao menu ");
				System.out.print("Opção: ");

				int confirmUser = sc.nextInt();

				if (confirmUser == 1) {
					System.out.println("-----------LIVROS DISPONIVEIS----------");
					AllBook.livrosDisponiveis();
					System.out.println();
					System.out.println("Deseja alugar um livro da lista? (S/N)");
					confirm = sc.next().charAt(0);

					if (confirm == 's' || confirm == 'S') {
						n = 3;
					} else if (confirm == 'n' || confirm == 'N') {
						n = 0;
					}

				} else if (confirmUser == 2) {
					System.out.println("-----------LIVROS INDISPONIVEIS----------");
					AllBook.livrosIndisponiveis();
					System.out.print("Não é possivel alugar livros indisponiveis aperte S ou s para voltar ao menu: ");
					confirm = sc.next().charAt(0);
					if (confirm == 's' || confirm == 'S') {
						n = 0;
					}
				} else if (confirmUser == 0) {
					n = 0;
				}

				break;

			case 3:

				if (client != null) {
					System.out.println(client);
					System.out.println();

					if (client.getLivro() == null) {
						System.out.print("Digite o Id do livro: ");
						int idLivro = sc.nextInt();
						client.alugarLivro(idLivro);
						System.out.println();
						System.out.println("deseja alugar o livro: (S/N) ");
						System.out.println("Livro: " + client.getLivro().getNome());
						System.out.println("Autor: " + client.getLivro().getAutor());
						System.out.print("Digite 's' ou 'n': ");
						confirm = sc.next().charAt(0);

						if (confirm == 's' || confirm == 'S') {
							System.out.println();
							System.out.println("Livro alugado com sucesso");
							System.out.println("Dados atualizados");
							System.out.println();
							System.out.println(client);
							System.out.println();
							n = 0;

						} else if (confirm == 'n' || confirm == 'N') {
							client.devolverLivro(client.getLivro());
							n = 0;
						}
					} else if (client.getLivro() != null) {

						System.out.println(
								"Voce ja possui um livro alugado, para alugar outro voce deve devolver o que ja esta alugado");
						System.out.println("Deseja devolver o livro que voce alugou (S/N)");
						confirm = sc.next().charAt(0);
						if (confirm == 's' || confirm == 'S') {
							n = 4;
						} else {
							n = 0;
						}
					}

				} else {
					System.out.println("Usuario n cadastrado, voce deseja se cadastrar? (S/N)");
					confirm = sc.next().charAt(0);
					if (confirm == 's' || confirm == 'S') {
						n = 1;
					} else {
						n = 0;
					}

				}
				break;

			case 4:
				if (client != null) {
					if (client.getLivro() != null) {
						System.out.println(client);
						System.out.println();
						System.out.print("Voce possui um livro alugado, deseja devolver (S/N)? ");
						confirm = sc.next().charAt(0);
						if (confirm == 's' || confirm == 'S') {
							System.out.println();
							System.out.println("Livro: " + client.getLivro().getNome());
							System.out.println("Autor: " + client.getLivro().getAutor());
							System.out.println("deseja devolver o livro: (S/N) ");
							System.out.print("opção: ");
							char confirm2 = sc.next().charAt(0);
							if (confirm2 == 's' || confirm2 == 'S') {
								client.devolverLivro(client.getLivro());

								System.out.println("Dados atualizados");
								System.out.println(client);
								System.out.println();
								System.out.println("Livro devolvido com sucesso");
								n = 0;
							} else if (confirm2 == 'n' || confirm2 == 'N') {
								n = 0;
							}
						} else {
							n = 0;
						}

					}
				}
				break;

			case 5:
				
				System.out.print("Deseja exportar a lista de alugados em CSV? (S/N)? ");
				confirm = sc.next().charAt(0);
				if (confirm == 's' || confirm == 'S') {
					
					FileWriter csvLivros = null;
					try {
						StringBuilder sb = new StringBuilder();
						sb.append("id");
						sb.append(",");
						sb.append("nome");
						sb.append(",");
						sb.append("autor");
						sb.append(",");
						sb.append("descrição");
						sb.append(",");
						sb.append("lançamento");
						sb.append(",");
						sb.append("locado" + "\n");
						for (BookClient book : AllBook.getListLivros()) {
							sb.append(book.getId());
							sb.append(",");
							sb.append(book.getNome());
							sb.append(",");
							sb.append(book.getAutor());
							sb.append(",");
							sb.append(book.getDescricao());
							sb.append(",");
							sb.append(book.getLancamento());
							if (book.isDisponivel() == true) {
								sb.append("não" + "\n");
							}
						}
						csvLivros = new FileWriter("livroDisponiveis.csv");
						csvLivros.write(sb.toString());

					} catch (IOException e) {
						System.out.println(e.getMessage());
					} finally {
						csvLivros.close();
					}
					n = 0;
					
				}else if(confirm == 'n' || confirm == 'N') {
					n = 0;
				}
				System.out.println();
				System.out.println("CSV exportado com sucesso dentro do proprio projeto.");
				System.out.println();
				break;

			default: 
				File cadastroClientes = new File("Cadastros-Clientes.json");
				File livrosCSV = new File("livroDisponiveis.csv");
				
				try {
					
					FileDeleteStrategy.FORCE.delete(cadastroClientes);
					FileDeleteStrategy.FORCE.delete(livrosCSV);
					
					System.out.println("Arquivos deletado com sucesso");
					n = 0;
					
				} 
				catch (IOException e) {
					e.printStackTrace();
				}

				

				continuar = false;
				System.out.println("fim do programa");
			}

		}

	}

}
