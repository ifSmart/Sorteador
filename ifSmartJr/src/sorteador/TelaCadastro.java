package sorteador;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;

public class TelaCadastro extends JFrame {

	private static final long serialVersionUID = -7531075302898442579L;
	private JPanel contentPane;
	private JTextField tfNome;
	private JTextField tfValor;
	private JTextField tfCPF;

	public TelaCadastro() {
		setTitle("Cadastrar Contribuições");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 500, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel labelTitulo = new JLabel("Cadastrar Contribuições");
		labelTitulo.setFont(new Font("Arial", Font.PLAIN, 20));
		labelTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		labelTitulo.setHorizontalTextPosition(SwingConstants.CENTER);
		labelTitulo.setBounds(10, 11, 464, 54);
		contentPane.add(labelTitulo);
		
		tfNome = new JTextField();
		tfNome.setFont(new Font("Arial", Font.PLAIN, 11));
		tfNome.setHorizontalAlignment(SwingConstants.CENTER);
		tfNome.setBounds(143, 107, 198, 20);
		contentPane.add(tfNome);
		tfNome.setColumns(10);
		
		JLabel labelNome = new JLabel("Nome do Contribuinte:");
		labelNome.setHorizontalTextPosition(SwingConstants.CENTER);
		labelNome.setHorizontalAlignment(SwingConstants.CENTER);
		labelNome.setFont(new Font("Arial", Font.PLAIN, 11));
		labelNome.setBounds(143, 76, 198, 20);
		contentPane.add(labelNome);
		
		JLabel labelValor = new JLabel("Valor Contribuído:");
		labelValor.setHorizontalTextPosition(SwingConstants.CENTER);
		labelValor.setHorizontalAlignment(SwingConstants.CENTER);
		labelValor.setFont(new Font("Arial", Font.PLAIN, 11));
		labelValor.setBounds(143, 138, 198, 20);
		contentPane.add(labelValor);

		tfValor = new JTextField();
		tfValor.setFont(new Font("Arial", Font.PLAIN, 11));
		tfValor.setHorizontalAlignment(SwingConstants.CENTER);
		tfValor.setColumns(10);
		tfValor.setBounds(216, 169, 51, 20);
		contentPane.add(tfValor);
		
		JLabel labelCPF = new JLabel("CPF:");
		labelCPF.setHorizontalTextPosition(SwingConstants.CENTER);
		labelCPF.setHorizontalAlignment(SwingConstants.CENTER);
		labelCPF.setFont(new Font("Arial", Font.PLAIN, 11));
		labelCPF.setBounds(221, 200, 41, 20);
		contentPane.add(labelCPF);
		
		tfCPF = new JTextField();
		tfCPF.setFont(new Font("Arial", Font.PLAIN, 11));
		tfCPF.setHorizontalAlignment(SwingConstants.CENTER);
		tfCPF.setColumns(10);
		tfCPF.setBounds(194, 231, 96, 20);
		contentPane.add(tfCPF);
		
		JButton botaoCadastrar = new JButton("Cadastrar");
		botaoCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Arquivo arquivo = new Arquivo();
				boolean[] vetOK = arquivo.inserirNaLista(tfNome.getText(),tfValor.getText(),tfCPF.getText());
				if (vetOK[0]) {
					tfNome.setText("");
					tfNome.requestFocus();
				}
				if (vetOK[1]) {
					tfValor.setText("");
					tfValor.requestFocus();
				}
				if (vetOK[2]) {
					tfCPF.setText("");
					tfCPF.requestFocus();
				}
				if (!(vetOK[0] || vetOK[1] || vetOK[2])) {
					tfNome.setText("");
					tfValor.setText("");
					tfCPF.setText("");
					tfNome.requestFocus();
				}
			}
		});
		botaoCadastrar.setBackground(Color.WHITE);
		botaoCadastrar.setFont(new Font("Arial", Font.PLAIN, 11));
		botaoCadastrar.setBounds(197, 273, 89, 23);
		contentPane.add(botaoCadastrar);
		
		JButton btnFechar = new JButton("Fechar");
		btnFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnFechar.setFont(new Font("Arial", Font.PLAIN, 11));
		btnFechar.setBackground(Color.WHITE);
		btnFechar.setBounds(197, 307, 89, 23);
		contentPane.add(btnFechar);
		setLocationRelativeTo(null);

	}
	
}
