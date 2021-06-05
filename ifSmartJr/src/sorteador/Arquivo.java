package sorteador;

import java.util.ArrayList;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.awt.FileDialog;
import java.awt.Frame;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;

public class Arquivo {
	
	public String diretorioAtual;
	
	Arquivo(){
		this.setDiretorioAtual();
	}
	
	public ArrayList <String> getLista(String diretorio,String nomeArquivo) {
		ArrayList <String> lista = new ArrayList<String>();
		try {
			BufferedReader leitor = new BufferedReader(new InputStreamReader(new FileInputStream(diretorio+nomeArquivo), "UTF-8"));
			String linha;
			linha = leitor.readLine();
			while (!((linha == null) || (linha.isEmpty()))) {
				lista.add(linha);
				linha = leitor.readLine();
			}
		} catch (Exception e) {
			TelaLog telaLog = new TelaLog();
			if (telaLog.erro("Erro!","O arquivo '"+nomeArquivo+"' não foi encontrado!")) {
				if (telaLog.simNao("Atenção!","Deseja selecionar o arquivo?") == 0) {
					return this.getLista(this.dialogoSelecionarArquivo(nomeArquivo), nomeArquivo);
				} else {
					return null;
				}
			}
		}
		return lista;
	}
	
	public ArrayList <String> getLista(String nomeArquivo) {
		return this.getLista(this.diretorioAtual+"Listas\\",nomeArquivo);
	}
	
	// Método que insere os dados na lista correspondente
	
	public boolean[] inserirNaLista(String nomeC,String valorC,String CPF,boolean ignorarTela){
		
		// O vetor 'vetOK' tem 3 posições e cada uma delas representa verdadeiro para caso
		// seja necessário limpar a entrada de dados da tela 'TelaCadastro'
		
		TelaLog telaLog = new TelaLog();
		boolean[] vetOK = new boolean[3];
		float valor;
		// Validando o nome
		nomeC = this.StringTrimAndUpper(nomeC);
		if (this.emptyOrNull(nomeC)) {
			telaLog.erro("Erro!","Nome do contribuinte não inserido!");
			vetOK[0] = true;
			return vetOK;
		}
		// Validando o valor contribuído
		if (this.emptyOrNull(valorC)) {
			telaLog.erro("Erro!","Valor contribuído não inserido!");
			vetOK[1] = true;
			return vetOK;
		}
		try {
			valor = Float.parseFloat(this.StringTrimAndUpper(valorC.replace(",", ".")));
		} catch (Exception e) {
			telaLog.erro("Erro!","O valor contribuído não é numérico!");
			vetOK[1] = true;
			return vetOK;
		}
		// Validando o CPF
		CPF = this.StringTrimAndUpper(CPF);
		if (this.emptyOrNull(CPF)) {
			telaLog.erro("Erro!","CPF do contribuinte não inserido!");
			vetOK[2] = true;
			return vetOK;
		}
		if (CPF.length() != 11) {
			telaLog.erro("Erro!","CPF inserido não contém 11 dígitos!");
			vetOK[2] = true;
			return vetOK;
		}
		try {
			Double.parseDouble(CPF);
		} catch (Exception e) {
			telaLog.erro("Erro!","CPF inserido não é numérico!");
			vetOK[2] = true;
			return vetOK;
		}
		CPF = this.ajeitarCPF(CPF);
		
		// Criando diretorio para as listas
		
		String diretorioListas = this.diretorioAtual+"Listas";
		File diretorio = new File(diretorioListas);
		diretorio.mkdir();
		
		// Escrevendo os dados na lista de processamento e de amostragem
		
		try {
			FileWriter fileListaPro = new FileWriter(diretorioListas+"\\lista_processamento.txt",true);
			FileWriter fileListaAmo = new FileWriter(diretorioListas+"\\lista_amostragem.txt",true);
			PrintWriter printListaPro = new PrintWriter(fileListaPro);
			PrintWriter printListaAmo = new PrintWriter(fileListaAmo);
			for(int ind = 0; ind < valor; ind += 5) {
				printListaPro.printf("%s, %s\n",nomeC,CPF);
			}
			printListaPro.close();
			long pontos = (long) valor/5;
			printListaAmo.printf("NOME: %s, CPF: %s, VALOR CONTRIBUÍDO: R$ %.0f, PONTOS: %d\n"
					,nomeC,this.mascararCPF(CPF),valor,pontos);
			printListaAmo.close();
		} catch (Exception e) {
			return null;
		}
		
		// Verificando se a tela de informações precisa ser ignorada
		
		if (!ignorarTela) {
			telaLog.info("Sucesso!",nomeC+" cadastrado com sucesso!");
		}
		return vetOK;
	}
	
	// (Sobrecarga) O método faz com que uma tela de informações seja mostrada a cada cadastro
	
	public boolean[] inserirNaLista(String nomeC,String valorC,String CPF){
		return this.inserirNaLista(nomeC, valorC, CPF, false);
	}
	
	// Método que faz o cadastro dos contribuintes lendo um arquivo .csv do Google Forms
	
	public boolean inserirNaListaCSV() {
		ArrayList <String> listaCSV = new ArrayList<String>();
		listaCSV = this.getLista("lista_processamento_csv.csv");
		TelaLog telaLog = new TelaLog();
		try {
			for (int ind = 1; ind < listaCSV.size(); ind++) {
				String[] itemLista = (listaCSV.get(ind)).split(",");
				String nome = itemLista[1];
				nome = nome.substring(1,nome.length()-1);
				String CPF = itemLista[2];
				CPF = CPF.substring(1,CPF.length()-1);
				String valor = itemLista[8];
				valor = valor.substring(4,valor.indexOf("(")-1);
				this.inserirNaLista(nome,valor,CPF,true);
			}
			telaLog.info("Sucesso!", "Lista CSV importada e processada com sucesso!");
			return true;
		} catch (Exception e) {
			telaLog.erro("Erro!", "Falha na operação!");
			return false;
		}
	}
	
	// Método que retorna o diretório atual
	
	public String getDiretorioAtual() {
		return this.diretorioAtual;
	}
	
	// Método invocado pela classe quando instanciada que seta o diretório atual
	
	private void setDiretorioAtual() {
		this.diretorioAtual = System.getProperty("user.dir")+"\\";
	}
	
	// Método que invoca uma tela para selecionar o diretório com o arquivo desejado
	
	public String dialogoSelecionarArquivo(String nomeArquivo) {
		Frame frame = null;
		FileDialog frameDialog = new FileDialog(frame, "Escolha o arquivo", FileDialog.LOAD);
		frameDialog.setDirectory(this.diretorioAtual);
		frameDialog.setFile("*"+nomeArquivo);
		frameDialog.setVisible(true);
		String diretorio = frameDialog.getDirectory();
		return diretorio;
	}
	
	// Método que retorna uma string em maiúsculo e sem espaços vazios
	
	private String StringTrimAndUpper(String string) {
		return string.trim().toUpperCase();
	}
	
	// Método que retorna verdadeiro se uma string for nula ou vazia
	
	private boolean emptyOrNull(String string) {
		if (string.isEmpty() || string == null) {
			return true;
		}
		return false;
	}
	
	// Método que coloca os caracteres do CPF
	
	private String ajeitarCPF(String CPF) {
		CPF = CPF.substring(0,3)+"."+CPF.substring(3,6)+"."+CPF.substring(6,9)+"-"+CPF.substring(9);
		return CPF;
	}
	
	// Método que mascara o CPF escondendo os 3 primeiros e 2 últimos dígitos
	
	public String mascararCPF(String CPF) {
		CPF = "***."+CPF.substring(4,12)+"**";
		return CPF;
	}
	
}
