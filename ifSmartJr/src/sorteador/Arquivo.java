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
		diretorio += "\\Listas\\";
		System.out.println(diretorio);
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
		return this.getLista(this.diretorioAtual, nomeArquivo);
	}
	
	public boolean[] inserirNaLista(String nomeC,String valorC,String CPF){
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
		File diretorio = new File(this.diretorioAtual+"Listas");
		diretorio.mkdir();
		
		try {
			FileWriter fileListaPro = new FileWriter(diretorioListas+"\\"+"\\"+"lista_processamento.txt",true);
			FileWriter fileListaAmo = new FileWriter(diretorioListas+"\\"+"\\"+"lista_amostragem.txt",true);
			PrintWriter printListaPro = new PrintWriter(fileListaPro);
			PrintWriter printListaAmo = new PrintWriter(fileListaAmo);
			for(int ind = 0; ind < valor; ind+=5) {
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
		telaLog.info("Sucesso!",nomeC+" cadastrado com sucesso!");
		return vetOK;
	}
	
	public String ajeitarDiretorio(String diretorio) {
		while (diretorio.contains("\\")){
			diretorio = diretorio.replace("\\","/");
		}	
		while (diretorio.contains("/")){
			diretorio = diretorio.replace("/","\\"+"\\");
		}
		return diretorio;
	}
	
	private void setDiretorioAtual() {
		this.diretorioAtual = this.ajeitarDiretorio(System.getProperty("user.dir"))+"\\"+"\\";
	}
	
	public String dialogoSelecionarArquivo(String nomeArquivo) {
		Frame frame = null;
		FileDialog frameDialog = new FileDialog(frame, "Escolha o arquivo", FileDialog.LOAD);
		frameDialog.setDirectory(this.diretorioAtual);
		frameDialog.setFile("*"+nomeArquivo);
		frameDialog.setVisible(true);
		String diretorio = frameDialog.getDirectory();
		if (!(diretorio == null)) {
			diretorio = this.ajeitarDiretorio(diretorio);
		}
		return diretorio;
	}
	
	public String getDiretorioAtual() {
		return this.diretorioAtual;
	}
	
	private String StringTrimAndUpper(String string) {
		return string.trim().toUpperCase();
	}
	
	private boolean emptyOrNull(String string) {
		if (string.isEmpty() || string == null) {
			return true;
		}
		return false;
	}
	
	private String ajeitarCPF(String CPF) {
		CPF = CPF.substring(0,3)+"."+CPF.substring(3,6)+"."+CPF.substring(6,9)+"-"+CPF.substring(9);
		return CPF;
	}
	
	private String mascararCPF(String CPF) {
		CPF = "***."+CPF.substring(4,12)+"**";
		return CPF;
	}
	
}
