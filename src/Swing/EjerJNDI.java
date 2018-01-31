package Swing;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;

public class EjerJNDI extends JFrame {

	private JPanel contentPane;
	private JComboBox comboBox;
	private JEditorPane editorPane1;
	private JEditorPane editorPane2;
	private JEditorPane editorPane3;
	private boolean ya=false;
	private boolean primera=false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EjerJNDI frame = new EjerJNDI();
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
	public EjerJNDI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		comboBox = new JComboBox();
		comboBox.setModel(
				new DefaultComboBoxModel(new String[] { "file:///C:\\Users\\guillermo.tuvilla\\Desktop\\ejemplo" }));

		JLabel lblRuta = new JLabel("Ruta");

		JLabel lbl1 = new JLabel("Archivo1");

		editorPane1 = new JEditorPane();

		JLabel lbl2 = new JLabel("Archivo2");

		editorPane2 = new JEditorPane();

		JLabel lbl3 = new JLabel("Archivo3");

		editorPane3 = new JEditorPane();

		JButton btnBuscar = new JButton("BUSCAR");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					busca();
				} catch (NamingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(93, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 244, GroupLayout.PREFERRED_SIZE)
							.addGap(87))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblRuta, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
							.addGap(171))))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(189)
					.addComponent(lbl1)
					.addContainerGap(193, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addGap(188)
					.addComponent(lbl3, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
					.addComponent(btnBuscar)
					.addGap(46))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(156)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(editorPane3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(editorPane2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(editorPane1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(162, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(188)
					.addComponent(lbl2, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(179, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(14)
					.addComponent(lblRuta, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lbl1)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(editorPane1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lbl2)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(editorPane2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lbl3)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(editorPane3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnBuscar))
					.addContainerGap(22, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}

	protected void busca() throws NamingException {
		String[] array;
		array = new String[4];

		array[0] = comboBox.getSelectedItem().toString();
		array[1] = editorPane1.getText().toString();
		array[2] = editorPane2.getText().toString();
		array[3] = editorPane3.getText().toString();

		Properties p = new Properties();
		// definimos la clase del driver

		p.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.fscontext.RefFSContextFactory");

		p.put(Context.PROVIDER_URL, array[0]);
		Context ctx = new InitialContext(p);

		// busca los ficheros dentro del contexto
		if (editorPane1.getText().toString().equals("") || editorPane2.getText().toString().equals("") || editorPane3.getText().toString().equals("") ) {
			
			JOptionPane.showMessageDialog(null,"Introduce datos en todos los campos!");
			this.ya=true;
			this.primera=false;
			EjerJNDI frame = new EjerJNDI();
			
		
		
	}
	else {
		primera=true;
	}
	if(primera) {
		for (int i = 1; i < array.length; i++) {
			
			try {
				 if(editorPane1.getText().toString()!=null){
					ctx.lookup(array[i]);
					System.out.println(array[i] + "  ENCONTRADO!!");
				}
				else if(editorPane2.getText().toString()!=null){
					ctx.lookup(array[i]);
					System.out.println(array[i] + "  ENCONTRADO!!");
				}
				else if(editorPane3.getText().toString()!=null){
					ctx.lookup(array[i]);
					System.out.println(array[i] + "  ENCONTRADO!!");
				}
				
				 
			} catch (NamingException ex) {
				System.out.println(array[i] + "  NO EXISTE");
			}
		}
	}
	}// fin main

	// TODO Auto-generated method stub

}
