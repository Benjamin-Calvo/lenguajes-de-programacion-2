import java.awt.BorderLayout;
import java.awt.Container;
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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import alice.tuprolog.*;
public class mantenimiento extends JFrame {
	
	private JPanel contentPane;
	private	JPanel		topPanel;
	public JTextArea txtUsuario;
	public JButton btnAgregar;
	public JButton btnCancelar;
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
		
		btnAgregar = new JButton("Aceptar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Prolog engine=new Prolog();
				
				Theory theory = null;
				try {
					//se define la librería con la que trabajará
					theory = new Theory(new FileInputStream("back-end.pl"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.out.println("ERROR: EL NOMBRE DEL ARCHIVO DE LA BASE DE CONOCIMIENTOS ES INCORRECTO. ");
				}
				try {
					String usr = txtUsuario.getText().toLowerCase();
					FileWriter archivo = new FileWriter("back-end.pl",true);
				    PrintWriter pw = null;
				    pw= new PrintWriter(archivo);
				    pw.println(usr); 
				    archivo.close();
				    
				}catch (Exception ex){
					   ex.printStackTrace();
				}}}
		);
		btnAgregar.setFont(new Font("Arial Black", Font.PLAIN, 13));
		btnAgregar.setBounds(248, 369, 117, 23);
		panel.add(btnAgregar);
		
		//Button btnAceptar = new JButton("Aceptar");
		
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Container thisframe = btnCancelar.getParent();
				do
					thisframe = thisframe.getParent();
				while (!(thisframe instanceof JFrame));
				((JFrame)thisframe).dispose();
			}
		});
		btnCancelar.setFont(new Font("Arial Black", Font.PLAIN, 13));
		btnCancelar.setBounds(412, 369, 117, 23);
		panel.add(btnCancelar);
	
	}

}
