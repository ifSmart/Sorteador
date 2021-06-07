package sorteador;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.Color;

public class TelaPrincipal extends JFrame {

	private static final long serialVersionUID = 5276202835505338736L;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal telaPrincipal = new TelaPrincipal();
					telaPrincipal.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public TelaPrincipal () {
		Arquivo arquivo = new Arquivo();
		getContentPane().setFont(new Font("Arial", Font.PLAIN, 11));
		setTitle("Sorteador - ifSmartJr.");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 350);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		
		JLabel textoSorteio = new JLabel("Sorteador - ifSmartJr.");
		textoSorteio.setBounds(10, 33, 364, 48);
		textoSorteio.setFont(new Font("Arial", Font.PLAIN, 15));
		textoSorteio.setHorizontalTextPosition(SwingConstants.CENTER);
		textoSorteio.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(textoSorteio);
		
		JButton botaoCadastro = new JButton("Realizar Cadastro");
		botaoCadastro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaCadastro telaCadastro = new TelaCadastro();
				telaCadastro.setVisible(true);
			}
		});
		botaoCadastro.setFocusPainted(false);
		botaoCadastro.setBackground(Color.WHITE);
		botaoCadastro.setFont(new Font("Arial", Font.PLAIN, 11));
		botaoCadastro.setBounds(109, 92, 165, 23);
		getContentPane().add(botaoCadastro);
		
		JButton botaoPremiacao = new JButton("Iniciar Premiação");
		botaoPremiacao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaLog telaLog = new TelaLog();
				ArrayList <String> listaPro = new ArrayList<String>();
				ArrayList <String> listaAmo = new ArrayList<String>();
				listaPro = arquivo.getLista("lista_processamento.txt");
				listaAmo = arquivo.getLista("lista_amostragem.txt");
				if (listaPro.isEmpty() || listaPro == null) {
					telaLog.erro("Erro!","A lista de processamento está vazia!");
				} else {
					if (listaAmo.isEmpty() || listaPro == null) {
						telaLog.erro("Erro!","A lista de amostragem está vazia!");
					} else {
						TelaSorteio telaSorteio = new TelaSorteio(listaPro,listaAmo);
						ArquivoHTML html = new ArquivoHTML();
						html.gerarHTMLAmostragem(listaAmo);
						telaSorteio.setVisible(true);
					}
					
				}
			}
		});
		botaoPremiacao.setFont(new Font("Arial", Font.PLAIN, 11));
		botaoPremiacao.setBackground(Color.WHITE);
		botaoPremiacao.setBounds(109, 156, 165, 23);
		getContentPane().add(botaoPremiacao);
		
		JButton botaoCadastroCSV = new JButton("Realizar Cadastro (CSV)");
		botaoCadastroCSV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				arquivo.inserirNaListaCSV();
			}
		});
		botaoCadastroCSV.setFont(new Font("Arial", Font.PLAIN, 11));
		botaoCadastroCSV.setBackground(Color.WHITE);
		botaoCadastroCSV.setBounds(109, 122, 165, 23);
		getContentPane().add(botaoCadastroCSV);
		
		JButton botaoSair = new JButton("Sair");
		botaoSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		botaoSair.setFont(new Font("Arial", Font.PLAIN, 11));
		botaoSair.setBackground(Color.WHITE);
		botaoSair.setBounds(109, 224, 165, 23);
		getContentPane().add(botaoSair);
		
		JLabel lblNewLabel = new JLabel("\u00A9 2021 - ifSmartJr.");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 11));
		lblNewLabel.setBounds(10, 286, 364, 14);
		getContentPane().add(lblNewLabel);
		
		JButton botaoSobre = new JButton("Notas e Informações");
		botaoSobre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaLog telaLog = new TelaLog();
				telaLog.info("Notas e Informações","Coordenação do Projeto: Aloísio Nunes da Mota Filho.\n\n"
						+ "Desenvolvimento: Aloísio Nunes da Mota Filho,\n"
						+ "Raphael Rodrigues de Sena,\n"
						+ "Ian da Silva Conceição,\n"
						+ "Narciso Francisco de Oliveira.\n\n"
						+ "Demais informações, acesse Github.com/ifSmart/sorteador\n\n"
						+ "© 2021 - Desenvolvido pela ifSmartJr");
			}
		});
		botaoSobre.setFont(new Font("Arial", Font.PLAIN, 11));
		botaoSobre.setBackground(Color.WHITE);
		botaoSobre.setBounds(109, 190, 165, 23);
		getContentPane().add(botaoSobre);
		
	}
}
