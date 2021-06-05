package sorteador;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.TextArea;
import java.awt.Button;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class TelaSorteio extends JFrame {

	private JPanel contentPane;

	public TelaSorteio(ArrayList <String> listaPro,ArrayList <String> listaAmo) {
		setTitle("Realizar Premiação");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 470);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel labelPremiacao = new JLabel("Premiação");
		labelPremiacao.setHorizontalTextPosition(SwingConstants.CENTER);
		labelPremiacao.setHorizontalAlignment(SwingConstants.CENTER);
		labelPremiacao.setFont(new Font("Arial", Font.PLAIN, 20));
		labelPremiacao.setBounds(10, 11, 574, 35);
		contentPane.add(labelPremiacao);
		
		TextArea taLog = new TextArea();
		taLog.setFont(new Font("Arial", Font.PLAIN, 12));
		taLog.setEditable(false);
		taLog.setBounds(10, 70, 574, 231);
		contentPane.add(taLog);
		for (int ind = 0; ind < listaAmo.size(); ind++) {
			taLog.append(listaAmo.get(ind)+"\n");
		}
		taLog.append("\n");
		taLog.append("TOTAL DE PESSOAS: "+String.valueOf(listaAmo.size()));
		
		JLabel labelLista = new JLabel("Lista dos Contribuintes");
		labelLista.setHorizontalAlignment(SwingConstants.CENTER);
		labelLista.setFont(new Font("Arial", Font.PLAIN, 11));
		labelLista.setBounds(227, 50, 140, 14);
		contentPane.add(labelLista);
		
		JButton botaoPremiacao = new JButton("Realizar Premiação");
		botaoPremiacao.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent arg0) {
				Sorteio sorteio = new Sorteio();
				String logSorteio = sorteio.sortear(listaPro);
				taLog.append("\n\n"+logSorteio);
			}
		});
		botaoPremiacao.setBackground(Color.WHITE);
		botaoPremiacao.setHorizontalTextPosition(SwingConstants.CENTER);
		botaoPremiacao.setFont(new Font("Arial", Font.PLAIN, 11));
		botaoPremiacao.setBounds(227, 323, 140, 35);
		contentPane.add(botaoPremiacao);
		
		JButton botaoSair = new JButton("Sair");
		botaoSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		botaoSair.setHorizontalTextPosition(SwingConstants.CENTER);
		botaoSair.setFont(new Font("Arial", Font.PLAIN, 11));
		botaoSair.setBackground(Color.WHITE);
		botaoSair.setBounds(227, 369, 140, 35);
		contentPane.add(botaoSair);
		setLocationRelativeTo(null);
		
	}

}
