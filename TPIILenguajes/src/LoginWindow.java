import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import alice.tuprolog.*;

import java.io.*;


public class LoginWindow extends JFrame {

	ProgramaPrincipal frmMantenimiento;
	
	private JPanel contentPane;
	public JTextArea txtPassword;
	public JTextArea txtUsuario;
	public JButton btnCancelar;
	Prolog engine;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					
					LoginWindow frame = new LoginWindow();
					frame.setVisible(true);
					frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginWindow() {
		setTitle("LOGIN - Tarea Programada II Lenguajes   ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(135, 206, 235));
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setFont(new Font("Arial Black", Font.PLAIN, 13));
		lblUsuario.setBounds(269, 174, 110, 39);
		panel.add(lblUsuario);
		
		txtUsuario = new JTextArea();
		txtUsuario.setBounds(389, 183, 129, 22);
		panel.add(txtUsuario);
		
		JLabel lblPassword = new JLabel("Contrase\u00F1a:");
		lblPassword.setFont(new Font("Arial Black", Font.PLAIN, 13));
		lblPassword.setBounds(269, 230, 110, 39);
		panel.add(lblPassword);
		
		txtPassword = new JTextArea();
		txtPassword.setBounds(389, 239, 129, 22);
		panel.add(txtPassword);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
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
					engine.setTheory(theory);
					} catch(InvalidTheoryException ex){
					}
				SolveInfo info2 = null;
				try {
					String usr = txtUsuario.getText().toLowerCase();
					String pass = txtPassword.getText().toLowerCase();
					info2 = engine.solve("login("+usr+","+pass+").");
					if (info2.isSuccess()){
						System.out.println("¡Bienvenido "+usr+"!");
						//abrir la ventana mantenimiento
						frmMantenimiento = new ProgramaPrincipal();
						//frmConsulta = new Consulta();
						frmMantenimiento.setVisible(true);
						
						//cerrar la ventana activa
						Container thisframe = btnCancelar.getParent();
						do
							thisframe = thisframe.getParent();
						while (!(thisframe instanceof JFrame));
						((JFrame)thisframe).dispose();
					}
					else{
						System.out.println("NOPE, usuario no registrado! \nIntente de nuevo");
						txtUsuario.setText("");
						txtPassword.setText("");
					}
				} catch (MalformedGoalException e) {
					System.out.println("NOPE, CHUCK TESTA!");
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				/*try {
					System.out.println(info2.getSolution());
				} catch (NoSolutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/				
				
			}
		});
		btnAceptar.setFont(new Font("Arial Black", Font.PLAIN, 13));
		btnAceptar.setBounds(248, 369, 117, 23);
		panel.add(btnAceptar);
		
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
