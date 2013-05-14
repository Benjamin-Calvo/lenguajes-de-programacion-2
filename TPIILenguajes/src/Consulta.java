import java.awt.BorderLayout;
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


public class Consulta extends JFrame {

	private JPanel contentPane;
	private	JPanel		topPanel;
	private	JTable		table;
	private	JScrollPane scrollPane;
	private JPanel pnlPrincipal;
	private JLabel lblNiños;
	private JRadioButton rdbtnAccionBuena;
	private JRadioButton rdbtnMalosBuenos;
	private JRadioButton rdbtnAccionMala;
	private JButton btnNewButton;
	private JButton btnNewButton_1;

	
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
		String columnNames[] = { "Nombre", "Nacionalidad", "Edad",
				 "Regalos", "Presupuesto"};

		// Create some data
		Object[][] dataValues = {
				 {"Mary", "Campione", "Esquiar", new Integer(5), new Boolean(false)},
				 {"Lhucas", "Huml", "Patinar", new Integer(3), new Boolean(true)},
				 {"Kathya", "Walrath", "Escalar", new Integer(2), new Boolean(false)},
				 {"Marcus", "Andrews", "Correr", new Integer(7), new Boolean(true)},
				 {"Angela", "Lalth", "Nadar", new Integer(4), new Boolean(false)}
				 };
		
		lblNiños = new JLabel("Consultar ni\u00F1os por: ");
		lblNiños.setHorizontalAlignment(SwingConstants.CENTER);
		
		rdbtnAccionMala = new JRadioButton("Accion mala");
		
		rdbtnMalosBuenos = new JRadioButton("Malos y Buenos");
		
		rdbtnAccionBuena = new JRadioButton("Accion buena");
		// Create a new table instance
		table = new JTable( dataValues, columnNames );
		table.setPreferredScrollableViewportSize(new Dimension(500, 70));
		// Add the table to a scrolling pane
		scrollPane = new JScrollPane( table );
		
		JRadioButton rdbtnRegalosSolicitados = new JRadioButton("Regalos mas solicitados");
		
		btnNewButton = new JButton("New button");
		
		btnNewButton_1 = new JButton("New button");
		GroupLayout gl_pnlPrincipal = new GroupLayout(pnlPrincipal);
		gl_pnlPrincipal.setHorizontalGroup(
			gl_pnlPrincipal.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_pnlPrincipal.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(lblNiños, GroupLayout.PREFERRED_SIZE, 387, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_pnlPrincipal.createParallelGroup(Alignment.LEADING)
						.addComponent(rdbtnMalosBuenos, GroupLayout.PREFERRED_SIZE, 387, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_pnlPrincipal.createParallelGroup(Alignment.LEADING)
							.addComponent(rdbtnAccionBuena, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 387, GroupLayout.PREFERRED_SIZE)
							.addComponent(rdbtnAccionMala, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 387, GroupLayout.PREFERRED_SIZE)
							.addComponent(rdbtnRegalosSolicitados, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 387, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
				.addGroup(Alignment.LEADING, gl_pnlPrincipal.createSequentialGroup()
					.addGap(27)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 724, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(33, Short.MAX_VALUE))
				.addGroup(gl_pnlPrincipal.createSequentialGroup()
					.addContainerGap(243, Short.MAX_VALUE)
					.addComponent(btnNewButton)
					.addGap(124)
					.addComponent(btnNewButton_1)
					.addGap(245))
		);
		gl_pnlPrincipal.setVerticalGroup(
			gl_pnlPrincipal.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlPrincipal.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_pnlPrincipal.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlPrincipal.createSequentialGroup()
							.addComponent(rdbtnMalosBuenos)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(rdbtnAccionBuena)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(rdbtnAccionMala)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(rdbtnRegalosSolicitados))
						.addComponent(lblNiños, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
					.addGap(82)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 235, GroupLayout.PREFERRED_SIZE)
					.addGap(48)
					.addGroup(gl_pnlPrincipal.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton)
						.addComponent(btnNewButton_1))
					.addGap(58))
		);
		pnlPrincipal.setLayout(gl_pnlPrincipal);

			
		

		
		
	}
}