package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * 
 * Essa classe representa um candidato do sisu
 * ela possui 2 atributos: curso e estado que ser�o utilizados
 * na cria��o do gr�fico de alunos por estado
 *
 */
public class Candidato {
	String curso;
	String estado;	
	
	/**
	 * M�todo construtor da classe candidato
	 * 
	 * @param linha
	 * @throws Exception
	 */
	Candidato (String linha) throws Exception{
		Scanner varredor = new Scanner (linha).useDelimiter("\\s{2,}");
		varredor.useLocale(new Locale("pt","BR"));
		varredor.next();
		varredor.next();
		curso = varredor.next();
		varredor.next();
		varredor.next();
		varredor.next();
		varredor.next();
		estado = varredor.next();
		varredor.close();
	}
	
	/**
	 * O parserListaSisu varre o arquivo com dados do sisu e
	 * instancia um novo candidato a cada linha.
	 * 
	 * @param file
	 * 
	 * @throws exception - Essa excess�o acontece quando a linha n�o est� formatada
	 * de acordo com o esperado por�m ela n�o � lancada
	 * 
	 * @throws e - essa excess�o acontece quando o arquivo n�o � encontrado, ent�o �
	 * lan�ada uma mensagem no console informando o problema.
	 *
	 * @return dicionario - retorna um dicion�rio que cont�m os
	 * cursos como chave e como valor um outro dicion�rio com os estados e suas
	 * respectivas quantidades de alunos
	 * 
	 */
	public static Hashtable<String, Hashtable<String, Integer>> parserListaSisu (File file){
		Hashtable<String, Integer> AlunosEstado = new Hashtable<>();
		Hashtable<String, Hashtable<String, Integer>> dicionario = new Hashtable<>();
		Scanner leitor;
		int qtdPorEstado = 1;
		
		try {
			leitor = new Scanner(file);
			while (leitor.hasNext()) {
				String linha = leitor.nextLine();
				try{
					Candidato candidato = new Candidato(linha);
					
					if(dicionario.containsKey(candidato.curso.toUpperCase())) {
						if(dicionario.get(candidato.curso.toUpperCase()).containsKey(candidato.estado)) {
							qtdPorEstado = dicionario.get(candidato.curso.toUpperCase()).get(candidato.estado)+1;
							AlunosEstado.put(candidato.estado, qtdPorEstado);
							dicionario.put(candidato.curso.toUpperCase(), AlunosEstado);
						}else {
							qtdPorEstado = 1;
							AlunosEstado.put(candidato.estado, qtdPorEstado);
							dicionario.put(candidato.curso.toUpperCase(), AlunosEstado);
						}
					}else {
						qtdPorEstado = 1;
						dicionario.put(candidato.curso.toUpperCase(), AlunosEstado = new Hashtable<>());
						AlunosEstado.put(candidato.estado, qtdPorEstado);
						dicionario.put(candidato.curso.toUpperCase(), AlunosEstado);
					}
				}catch(Exception exception){
				}
			}
			leitor.close();
		}catch(FileNotFoundException e){
			System.out.println("Arquivo n�o encontrado");
		}
		return dicionario;
	}
}
