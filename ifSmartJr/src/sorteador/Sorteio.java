package sorteador;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Random;
import java.util.Calendar;

public class Sorteio extends Arquivo {
	
	private Random random = new Random();
	private float totalArrecadado;
	// Método que realiza o sorteio
	
	public String sortear(ArrayList <String> listaPro) {
		
		TelaLog telaLog = new TelaLog();
		
		// Embaralhando a ordem de todos os índices da lista
		Collections.shuffle(listaPro);
		
		int tamanhoLista = listaPro.size();
		
		// Sorteando o vencedor
		int vencedor = this.retornarNumeroSorteado(1,tamanhoLista);
		String dadosVencedor = listaPro.get(vencedor);
		
		String dadosSegundoLugar;
		int segundoLugar;
		
		int terceiroLugar;
		String dadosTerceiroLugar;
		
		// Sorteando os demais e os re-sorteando caso iguais
		
		do {
			segundoLugar = this.retornarNumeroSorteado(1,tamanhoLista);
			dadosSegundoLugar = listaPro.get(segundoLugar);
		} while ((segundoLugar == vencedor) || (dadosSegundoLugar.equals(dadosVencedor)));
		
		do {
			terceiroLugar = this.retornarNumeroSorteado(1,tamanhoLista);
			dadosTerceiroLugar = listaPro.get(terceiroLugar);
		} while ((terceiroLugar == vencedor) || (terceiroLugar == segundoLugar)
				|| (dadosTerceiroLugar.equals(dadosVencedor)) || (dadosTerceiroLugar.equals(dadosSegundoLugar)));
		
		// Processando os dados dos vencedores
		
		Arquivo arquivo = new Arquivo();

		String nomeVencedor = dadosVencedor.substring(0,dadosVencedor.indexOf(","));
		String CPFVencedor = arquivo.mascararCPF(dadosVencedor.substring(dadosVencedor.indexOf(",")+2));
		
		String nomeSegundoLugar = dadosSegundoLugar.substring(0,dadosSegundoLugar.indexOf(","));
		String CPFSegundoLugar = arquivo.mascararCPF(dadosSegundoLugar.substring(dadosSegundoLugar.indexOf(",")+2));
	
		String nomeTerceiroLugar = dadosTerceiroLugar.substring(0,dadosTerceiroLugar.indexOf(","));
		String CPFTerceiroLugar = arquivo.mascararCPF(dadosTerceiroLugar.substring(dadosTerceiroLugar.indexOf(",")+2));
		
		String logVencedor = "VENCEDOR: "+nomeVencedor+", CPF: "+CPFVencedor;
		String logSegundoLugar = "SEGUNDO LUGAR: "+nomeSegundoLugar+", CPF: "+CPFSegundoLugar;
		String logTerceiroLugar = "TERCEIRO LUGAR: "+nomeTerceiroLugar+", CPF: "+CPFTerceiroLugar;
		telaLog.info("Premiação Realizada!","Vencedor(a) "+nomeVencedor+"!\nParabéns!");
		telaLog.info("Demais colocados", "Segundo lugar: "+nomeSegundoLugar+"\n"+
				"Terceiro lugar: "+nomeTerceiroLugar);
		
		// Salvando os dados dos vencedores
		
		String diretorio = this.getDiretorioAtual()+"Vencedores\\";
		
		String dataHora;
		try {
			PrintWriter listaVencedores = new PrintWriter(this.criarOutputStreamWriter(diretorio,"vencedores.txt",false));
			dataHora = this.retornarDataHora();
			listaVencedores.printf("DATA E HORA DA PREMIAÇÃO: "+dataHora+"\n\n");
			listaVencedores.printf(logVencedor+"\n"+logSegundoLugar+"\n"+logTerceiroLugar+"\n\n");
			listaVencedores.printf("© 2021 - Desenvolvido pela ifSmartJr.");
			listaVencedores.close();
		} catch (Exception e) {
			return null;
		}
		ArquivoHTML html = new ArquivoHTML();
		html.gerarHTMLVencedores(logVencedor, logSegundoLugar, logTerceiroLugar, dataHora);
		return logVencedor+"\n"+logSegundoLugar+"\n"+logTerceiroLugar;
	}
	
	// Método que retorna o número aleatório no espectro escolhido
	
	public int retornarNumeroSorteado(int minimo,int maximo) {
		
		int numeroSorteado = (minimo + this.random.nextInt(maximo+1-minimo)) - 1;
		return numeroSorteado;
		
	}
	
	// Retorna em uma String a data e a hora correspondente
	
	
	public void contarTotalArrecadado(String linha) {
		String[] vetorAtributos = linha.split(",");
		String stringValor = vetorAtributos[2];
		stringValor = stringValor.substring(stringValor.indexOf("$")+2).trim();
		this.totalArrecadado += Float.parseFloat(stringValor);
	}
	
	public float retornarTotalArrecadado() {
		return this.totalArrecadado;
	}

}