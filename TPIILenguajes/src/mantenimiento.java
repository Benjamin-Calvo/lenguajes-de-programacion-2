import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.io.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.GridLayout;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import alice.tuprolog.Prolog;
import alice.tuprolog.*;
public class mantenimiento extends JFrame {
	
	private JPanel contentPane;
	private	JPanel		topPanel;
	public JTextArea txtUsuario;
	Prolog engine;
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mantenimiento frame = new mantenimiento();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public mantenimiento() {
		setTitle("Mantenimiento - Tarea Programada II Lenguajes  ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(135, 206, 235));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(135, 206, 235));
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		
		JLabel lblUsuario = new JLabel("Nuevo Predicado Back-end:");
		lblUsuario.setFont(new Font("Arial Black", Font.PLAIN, 13));
		lblUsuario.setBounds(185, 174, 200, 39);
		panel.add(lblUsuario);
		
		txtUsuario = new JTextArea();
		txtUsuario.setBounds(389, 183, 150, 22);
		panel.add(txtUsuario);
		
		try {
			String usr = txtUsuario.getText().toLowerCase();
			FileWriter archivo = new FileWriter("login.pl",true);
		    PrintWriter pw = null;
		    pw= new PrintWriter(archivo);
		    pw.println(usr); 
		    archivo.close();
		    
		}catch (Exception ex){
			   ex.printStackTrace();
		  }
	
	}

}
