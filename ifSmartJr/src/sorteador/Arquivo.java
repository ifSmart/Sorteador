package sorteador;

import java.util.ArrayList;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.awt.FileDialog;
import java.awt.Frame;
import java.io.BufferedReader;
import java.io.FileInputStream;

public class Arquivo {
	
	public String diretorioAtual;
	
	Arquivo(){
		this.setDiretorioAtual();
	}
	
	public ArrayList <String> getLista(String diretorio, String nomeArquivo) {
		ArrayList <String> lista = new ArrayList<String>();
		try {
			System.out.println("Diretorio = "+diretorio+nomeArquivo);
			BufferedReader leitor = new BufferedReader(new InputStreamReader(new FileInputStream(diretorio+nomeArquivo), "UTF-8"));
			String linha;
			linha = leitor.readLine();
			while (!((linha == null) || (linha == ""))) {
				lista.add(linha);
				linha = leitor.readLine();
			}
		} catch (Exception e) {
			TelaLog telaLog = new TelaLog();
			if (telaLog.erro("Erro!","O arquivo '"+nomeArquivo+"' não foi encontrado!")) {
				if (telaLog.simNao("Atenção!", "Deseja selecionar o arquivo?") == 0) {
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
	
	public void inserirNaLista(){
		
	}
	
	public String ajeitarDiretorio(String diretorio) {
		while (diretorio.contains("\\")){
			diretorio = diretorio.replace("\\", "/");
		}	
		while (diretorio.contains("/")){
			diretorio = diretorio.replace("/", "\\" + "\\");
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

}
