import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Sobre extends JDialog{
	JLabel lbNome1, lbNome2, lbFoto1, lbFoto2, lbSala1, lbSala2;
	public Sobre(){
		this.setTitle("Sobre Nós");
		this.setModal(true);
		this.setSize(400, 210);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
		lbFoto1 = new JLabel(new ImageIcon(((new ImageIcon(getClass().getResource("images/icon.png"))).getImage()).getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH)));
		lbFoto1.setBounds(30,10,100,100);
		add(lbFoto1);
		
		lbNome1 = new JLabel();
		lbNome1.setText("Daniel Lopes");
		lbNome1.setFont(new Font("", Font.BOLD, 15));
		lbNome1.setBounds(30, 115, 100, 20);
		add(lbNome1);
		
		lbSala1 = new JLabel();
		lbSala1.setText("2* DS A");
		lbSala1.setFont(new Font("", Font.BOLD, 15));
		lbSala1.setBounds(30, 140, 100, 20);
		add(lbSala1);
		
		lbFoto2 = new JLabel(new ImageIcon(((new ImageIcon(getClass().getResource("images/icon.png"))).getImage()).getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH)));
		lbFoto2.setBounds(260,10,100,100);
		add(lbFoto2);
		
		lbNome2 = new JLabel();
		lbNome2.setText("Rhuan Passos");
		lbNome2.setFont(new Font("", Font.BOLD, 15));
		lbNome2.setBounds(260, 115, 110, 20);
		add(lbNome2);
		
		lbSala1 = new JLabel();
		lbSala1.setText("2* DS A");
		lbSala1.setFont(new Font("", Font.BOLD, 15));
		lbSala1.setBounds(260, 140, 100, 20);
		add(lbSala1);
		
		this.setLayout(null);
		this.setVisible(true);
	}
}
