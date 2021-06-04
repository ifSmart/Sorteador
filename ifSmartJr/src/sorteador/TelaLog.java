package sorteador;

import java.awt.Frame;
import javax.swing.JOptionPane;

public class TelaLog {

	private Frame frame = null;
	
	public int simNao(String titulo, String log) {
		Object[] options = {"Sim","Não"};
		return JOptionPane.showOptionDialog(frame, log, titulo, JOptionPane.INFORMATION_MESSAGE,
				JOptionPane.INFORMATION_MESSAGE,null, options, null);
	}
	
	public boolean erro(String titulo, String log) {
		Object[] options = {"OK"};
		JOptionPane.showOptionDialog(frame, log, titulo, JOptionPane.INFORMATION_MESSAGE,
				JOptionPane.ERROR_MESSAGE,null, options, null);
		return true;
	}
	
	
	
}
