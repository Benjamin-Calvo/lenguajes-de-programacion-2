import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JInternalFrame;
import javax.swing.JLayeredPane;
import javax.swing.JTabbedPane;


public class ProgramaPrincipal extends JFrame {

	private JPanel contentPane;
	private JMenuItem mntmSalir;
	private JPanel pnlMantenimiento = new JPanel();
	private JPanel pnlConsulta = new JPanel();
	private Consulta frmConsulta;
	private mantenimiento frmMantenimiento;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProgramaPrincipal frame = new ProgramaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ProgramaPrincipal() {
		setTitle("Base de Datos Polo Norte - Tarea Programada II Lenguajes  ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnMenu = new JMenu("Archivo");
		menuBar.add(mnMenu);
		
		JMenuItem mntmConsultas = new JMenuItem("Consultas");
		mntmConsultas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				frmConsulta = new Consulta();
				frmConsulta.setVisible(true);
			}
		});
		mnMenu.add(mntmConsultas);
		
		JMenuItem mntmMantenimiento = new JMenuItem("Mantenimiento");

		mntmMantenimiento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				frmMantenimiento = new mantenimiento();
				frmMantenimiento.setVisible(true);
			}
		});
		mnMenu.add(mntmMantenimiento);
		
		mntmSalir = new JMenuItem("Salir");
		mntmSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mnMenu.add(mntmSalir);
		
		JMenu mnNewMenu = new JMenu("Información");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmAcercaDe = new JMenuItem("Acerca de...");
		mntmAcercaDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,"Instituto Tecnológico de Costa Rica\n\tLenguajes de Programación\n\tTarea Programada II\n\tEstudiantes:\n\t\tAndrew Araya\n\t\tAlexander Durán\n\t\tBenjamín Calvo\n");
			}
		});
		mnNewMenu.add(mntmAcercaDe);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(135, 206, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);			
	}

}
