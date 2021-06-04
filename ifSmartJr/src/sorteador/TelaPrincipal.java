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
		
		JButton botaoCadastrar = new JButton("Cadastrar Contribuições");
		botaoCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaCadastro telaCadastro = new TelaCadastro();
				telaCadastro.setVisible(true);
			}
		});
		botaoCadastrar.setFocusPainted(false);
		botaoCadastrar.setBackground(Color.WHITE);
		botaoCadastrar.setFont(new Font("Arial", Font.PLAIN, 11));
		botaoCadastrar.setBounds(109, 92, 165, 23);
		getContentPane().add(botaoCadastrar);
		
		JButton botaoPremiacao = new JButton("Realizar Premia\u00E7\u00E3o");
		botaoPremiacao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ArrayList <String> listaProcessamento = new ArrayList<String>();
				ArrayList <String> listaAmostragem = new ArrayList<String>();
				listaProcessamento = arquivo.getLista("lista_processamento.txt");
				System.out.println(listaProcessamento);
			}
		});
		botaoPremiacao.setFont(new Font("Arial", Font.PLAIN, 11));
		botaoPremiacao.setBackground(Color.WHITE);
		botaoPremiacao.setBounds(109, 126, 165, 23);
		getContentPane().add(botaoPremiacao);
		
		JButton botaoListaInscritos = new JButton("Gerar Lista de Inscritos");
		botaoListaInscritos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Arquivo arquivo = new Arquivo();
				System.out.println(arquivo.getDiretorioAtual());
			}
		});
		botaoListaInscritos.setFont(new Font("Arial", Font.PLAIN, 11));
		botaoListaInscritos.setBackground(Color.WHITE);
		botaoListaInscritos.setBounds(109, 160, 165, 23);
		getContentPane().add(botaoListaInscritos);
		
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
		
		JButton botaoSobre = new JButton("Notas e Informa\u00E7\u00F5es");
		botaoSobre.setFont(new Font("Arial", Font.PLAIN, 11));
		botaoSobre.setBackground(Color.WHITE);
		botaoSobre.setBounds(109, 190, 165, 23);
		getContentPane().add(botaoSobre);
		
	}
}
