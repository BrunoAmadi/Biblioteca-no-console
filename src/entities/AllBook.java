package entities;

import java.util.ArrayList;
import java.util.List;

public class AllBook {

	
	private static List<BookClient> listLivros = new ArrayList<>();
	
	
	public static List<BookClient> getListLivros() {
		return listLivros;
	}

	public static void livrosDisponiveis() {
		List<BookClient> list = listLivros.stream().filter(x -> x.isDisponivel() == true).toList();
		for(BookClient livro : list) {
			if(livro.isDisponivel()) {
				System.out.println(livro);
				System.out.println();
			
			}
		}
								
	}
	
	public static void livrosIndisponiveis() {
		List<BookClient> list = AllBook.listLivros.stream().filter(x -> x.isDisponivel() == false).toList();
		for(BookClient livroInd : list) {
			if(!livroInd.isDisponivel()) {
				System.out.println(livroInd);
				System.out.println();
			}
		}
	}
	
	
	public static void addLivro(BookClient livro) {
		listLivros.add(livro);
	}
	public static void removeLivro(BookClient livro) {
		listLivros.remove(livro);
	}

	}
	
	
	


