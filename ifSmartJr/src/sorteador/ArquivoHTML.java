package sorteador;

import java.io.PrintWriter;
import java.util.ArrayList;

public class ArquivoHTML extends Arquivo {
	
	public ArquivoHTML() {
		super();
	}
	
	public void gerarHTMLAmostragem(ArrayList <String> listaAmo) {
		ArrayList <String> lista = listaAmo;
		PrintWriter printHTML = new PrintWriter(this.criarOutputStreamWriter(this.getDiretorioAtual()+"\\HTML\\","lista_amostragem.html",false));
		printHTML.printf("<!DOCTYPE html>\n");
		printHTML.printf("<html lang=\"pt-br\">\n");
		printHTML.printf("<head>\n");
		printHTML.printf("<meta charset=\"UTF-8\">\n");
		printHTML.printf("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n");
		printHTML.printf("<title>Lista de Amostragem</title>\n");
		printHTML.printf("<style>\n");
		printHTML.print("table {font-family: arial; border-collapse: collapse; width: 100%;}\n");
		printHTML.print("td, th {border: 1px solid #dddddd; text-align: center; padding: 8px;}\n");
		printHTML.print("tr:nth-child(even) {background-color: #dddddd;}\n");
		printHTML.print("h2 {font-family: arial; text-align: center;}\n");
		printHTML.print("p {font-family: arial; text-align: center;}\n");
		printHTML.printf("</style>\n");
		printHTML.printf("</head>\n");
		printHTML.printf("<body>\n");
		printHTML.print("<h2>Lista de Amostragem dos Contribuintes</h2>\n");
		printHTML.print("<br>\n");
		printHTML.printf("<table>\n");
		printHTML.printf("<tr>\n");
		printHTML.printf("<th>Nome</th>\n");
		printHTML.printf("<th>CPF</th>\n");
		printHTML.printf("<th>Valor Contribuído</th>\n");
		printHTML.printf("<th>Pontos</th>\n");
		printHTML.printf("</tr>\n");
		
		for (int ind = 0; ind < lista.size(); ind++) {
			String[] valores = lista.get(ind).split(",");
			String nome = valores[0];
			nome = nome.substring(6);
			String CPF = valores[1];
			CPF = CPF.substring(6);
			String valor = valores[2];
			valor = valor.substring(20);
			String pontos = valores[3];
			pontos = pontos.substring(8);
			
			printHTML.printf("<tr>\n");
			printHTML.printf("<td>"+nome+"</td>\n");
			printHTML.printf("<td>"+CPF+"</td>\n");
			printHTML.printf("<td>"+valor+"</td>\n");
			printHTML.printf("<td>"+pontos+"</td>\n");
			printHTML.printf("</tr>\n");
			
		}
		printHTML.printf("</table>\n");
		printHTML.print("<p>Data e hora de processamento: "+this.retornarDataHora()+"</p>\n");
		printHTML.print("<p>© 2021 - Desenvolvido pela ifSmartJr.</p>\n");
		printHTML.printf("</body>\n");
		printHTML.printf("<html>\n");
		printHTML.close();
	}
	
	public void gerarHTMLVencedores(String vencedor,String segundoLugar,String terceiroLugar,String data) {
		PrintWriter printHTML = new PrintWriter(this.criarOutputStreamWriter(this.getDiretorioAtual()+"\\HTML\\","vencedores.html",false));
		printHTML.printf("<!DOCTYPE html>\n");
		printHTML.printf("<html lang=\"pt-br\">\n");
		printHTML.printf("<head>\n");
		printHTML.printf("<meta charset=\"UTF-8\">\n");
		printHTML.printf("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n");
		printHTML.printf("<title>Lista de Vencedores</title>\n");
		printHTML.printf("<style>\n");
		printHTML.print("table {font-family: arial; border-collapse: collapse; width: 100%;}\n");
		printHTML.print("td, th {border: 1px solid #dddddd; text-align: center; padding: 8px;}\n");
		printHTML.print("tr:nth-child(even) {background-color: #dddddd;}\n");
		printHTML.print("h2 {font-family: arial; text-align: center;}\n");
		printHTML.print("p {font-family: arial; text-align: center;}\n");
		printHTML.printf("</style>\n");
		printHTML.printf("</head>\n");
		printHTML.printf("<body>\n");
		printHTML.print("<h2>Lista de Vencedores</h2>\n");
		printHTML.print("<br>\n");
		printHTML.printf("<table>\n");
		printHTML.printf("<tr>\n");
		printHTML.printf("<th>Nome</th>\n");
		printHTML.printf("<th>CPF</th>\n");
		printHTML.printf("<th>Posição</th>\n");
		printHTML.printf("</tr>\n");
			
		printHTML.printf("<tr>\n");
		printHTML.printf("<td>"+vencedor.substring(10,vencedor.indexOf(","))+"</td>\n");
		printHTML.printf("<td>"+vencedor.substring(vencedor.indexOf(",")+7)+"</td>\n");
		printHTML.printf("<td>VENCEDOR</td>\n");
		printHTML.printf("</tr>\n");
		
		printHTML.printf("<tr>\n");
		printHTML.printf("<td>"+segundoLugar.substring(15,segundoLugar.indexOf(","))+"</td>\n");
		printHTML.printf("<td>"+segundoLugar.substring(segundoLugar.indexOf(",")+7)+"</td>\n");
		printHTML.printf("<td>SEGUNDO LUGAR</td>\n");
		printHTML.printf("</tr>\n");
		
		printHTML.printf("<tr>\n");
		printHTML.printf("<td>"+terceiroLugar.substring(16,terceiroLugar.indexOf(","))+"</td>\n");
		printHTML.printf("<td>"+terceiroLugar.substring(terceiroLugar.indexOf(",")+7)+"</td>\n");
		printHTML.printf("<td>TERCEIRO LUGAR</td>\n");
		printHTML.printf("</tr>\n");

		printHTML.printf("</table>\n");
		printHTML.print("<p>Data e hora da premiação: "+data+"</p>\n");
		printHTML.print("<p>© 2021 - Desenvolvido pela ifSmartJr.</p>\n");
		printHTML.printf("</body>\n");
		printHTML.printf("<html>\n");
		printHTML.close();
		
	}
	
	
}
