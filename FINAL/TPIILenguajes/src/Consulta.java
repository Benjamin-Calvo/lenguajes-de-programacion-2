import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import java.awt.GridLayout;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
//import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import alice.tuprolog.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import java.io.*;
//import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;

public class Consulta extends JFrame {

	private JPanel contentPane;
	private	JPanel		topPanel;
	private JPanel pnlPrincipal;
	private JLabel lblConsulta;
	private JButton btnCancelar;
	private JButton btnAceptar;
	private JTextField txtConsulta;
	private JTextPane txtInfo;

	
	 //Launch the application.	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Consulta frame = new Consulta();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	//Create the frame.
	 
	public Consulta() {
		setTitle("CONSULTA - Tarea Programada II Lenguajes  ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(135, 206, 235));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		
		
		// Create a panel to hold all other components
		pnlPrincipal = new JPanel();
		contentPane.add(pnlPrincipal, BorderLayout.CENTER);
		
		// Create columns names
		String columnNames[] = { "Nombre", "Apellido", "Pasatiempo",
				 "Años de Practica", "Soltero(a)"};

		// Create some data
		Object[][] dataValues = {
				 {"Mary", "Campione", "Esquiar", new Integer(5), new Boolean(false)},
				 {"Lhucas", "Huml", "Patinar", new Integer(3), new Boolean(true)},
				 {"Kathya", "Walrath", "Escalar", new Integer(2), new Boolean(false)},
				 {"Marcus", "Andrews", "Correr", new Integer(7), new Boolean(true)},
				 {"Angela", "Lalth", "Nadar", new Integer(4), new Boolean(false)}
				 };
		
		lblConsulta = new JLabel("Digite una consulta:   ");
		lblConsulta.setHorizontalAlignment(SwingConstants.CENTER);
		
		btnCancelar = new JButton("Cerrar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Container thisframe = btnCancelar.getParent();
				do
					thisframe = thisframe.getParent();
				while (!(thisframe instanceof JFrame));
				((JFrame)thisframe).dispose();
			}
		});
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			//ACCION DE BOTON CONSULTA 
			public void actionPerformed(ActionEvent e) {
				txtInfo.setText("");
				String consulta = new String();
				consulta = txtConsulta.getText();
				System.out.println(consulta);
				Prolog engine=new Prolog();
				
				Theory theory = null;
				try {
					//se define la librería con la que trabajará
					theory = new Theory(new FileInputStream("back-end.pl"));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					System.out.println("ERROR: EL NOMBRE DEL ARCHIVO DE LA BASE DE CONOCIMIENTOS ES INCORRECTO. ");
				}
				try {
					engine.setTheory(theory);
					} catch(InvalidTheoryException ex){
					}
				SolveInfo info2 = null;
				try {
					String strConsulta = txtConsulta.getText();
					info2 = engine.solve(strConsulta);
					if (info2.isSuccess()){
						System.out.println(strConsulta);
						txtInfo.setText(""+info2);
					}
					else{
						System.out.println("NOPE, consulta no válida! \nIntente de nuevo");
						txtInfo.setText("NOPE, consulta no válida! \nIntente de nuevo");
					}
				} catch (MalformedGoalException e1) {
					System.out.println("NOPE, CHUCK TESTA!");
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		txtConsulta = new JTextField();
		txtConsulta.setColumns(10);
		
		txtInfo = new JTextPane();
		GroupLayout gl_pnlPrincipal = new GroupLayout(pnlPrincipal);
		gl_pnlPrincipal.setHorizontalGroup(
			gl_pnlPrincipal.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_pnlPrincipal.createSequentialGroup()
					.addContainerGap(424, Short.MAX_VALUE)
					.addComponent(btnCancelar)
					.addGap(339))
				.addGroup(Alignment.TRAILING, gl_pnlPrincipal.createSequentialGroup()
					.addGroup(gl_pnlPrincipal.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_pnlPrincipal.createSequentialGroup()
							.addContainerGap()
							.addComponent(txtInfo, GroupLayout.PREFERRED_SIZE, 625, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_pnlPrincipal.createSequentialGroup()
							.addGap(27)
							.addComponent(lblConsulta, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtConsulta, GroupLayout.DEFAULT_SIZE, 453, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnAceptar)))
					.addGap(73))
		);
		gl_pnlPrincipal.setVerticalGroup(
			gl_pnlPrincipal.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlPrincipal.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_pnlPrincipal.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAceptar)
						.addComponent(txtConsulta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblConsulta, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
					.addGap(60)
					.addComponent(txtInfo, GroupLayout.PREFERRED_SIZE, 318, GroupLayout.PREFERRED_SIZE)
					.addGap(37)
					.addComponent(btnCancelar)
					.addContainerGap(73, Short.MAX_VALUE))
		);
		pnlPrincipal.setLayout(gl_pnlPrincipal);
		//pnlPrincipal.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{txtConsulta, btnAceptar, btnCancelar, lblConsulta, txtInfo}));

			
		

		
		
	}
}