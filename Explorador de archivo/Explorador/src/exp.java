import java.awt.EventQueue;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Color;
import javax.swing.BoxLayout;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

public class exp {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					exp window = new exp();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public exp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	JFileChooser fc = new JFileChooser();
	private JTextField textField;
	private FileNameExtensionFilter fitro;
	private JTextField textNombre;
	private String ruta ;
	private String rutaCreacion;
	private JTextField textDirectorio;
	
	private void initialize() {
		frame = new JFrame();
		
		frame.setBounds(100, 100, 672, 472);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		textField = new JTextField();
		textField.setBounds(10, 30, 438, 35);
		textField.setEditable(false);
		textField.setColumns(10);
		panel.add(textField);
		
		JTextArea textArea = new JTextArea();
		
		
		
		JButton btnNewButton = new JButton("Seleccionar archivo");
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setBounds(458, 30, 188, 35);
		
		fitro = new FileNameExtensionFilter("Archivos pdf y word","pdf","docx");
		fc.setFileFilter(fitro);
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				fc.setAcceptAllFileFilterUsed(true);
				fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
				fc.showDialog(btnNewButton,"Seleccionar");
				
				File archivo = fc.getSelectedFile();
				
				fc.setCurrentDirectory(archivo);
				rutaCreacion = archivo.getParent() ;
				ruta = archivo.getAbsolutePath();
				textField.setText(archivo.getAbsolutePath());
			}
		});
		panel.add(btnNewButton);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBounds(10, 122, 636, 168);
		panel.add(panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));
		
		panel_1.add(textArea);
		
		JButton btnCargar = new JButton("Cargar a un archivo");
		btnCargar.setBackground(Color.WHITE);
		btnCargar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File archivo = new File((textDirectorio.getText()+"\\"+textNombre.getText()+".txt"));
				
				try {
					FileWriter escritor = new FileWriter(archivo.getAbsolutePath(),true);
					escritor.write(textArea.getText()+"\n");
					escritor.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnCargar.setBounds(10, 341, 636, 38);
		panel.add(btnCargar);
		
		textNombre = new JTextField();
		textNombre.setToolTipText("Escribir nombre del archivo");
		textNombre.setBounds(10, 301, 636, 29);
		panel.add(textNombre);
		textNombre.setColumns(10);
		
		JButton btnCargarAUn = new JButton("Abrir archivo");
		btnCargarAUn.setBackground(Color.WHITE);
		btnCargarAUn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProcessBuilder p = new ProcessBuilder();
				p.command("cmd.exe","/c",textField.getText());
				
				try {
					p.start();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnCargarAUn.setBounds(10, 384, 636, 38);
		panel.add(btnCargarAUn);
		
		JButton btnDirectorio = new JButton("Seleccionar directorio");
		btnDirectorio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				fc.setAcceptAllFileFilterUsed(false);
				File carpeta = fc.getCurrentDirectory();
				
				fc.showDialog(btnDirectorio, "Seleccionar carpeta");
				textDirectorio.setText(fc.getSelectedFile().toString());
			}
		});
		btnDirectorio.setBackground(Color.WHITE);
		btnDirectorio.setBounds(458, 76, 188, 35);
		panel.add(btnDirectorio);
		
		textDirectorio = new JTextField();
		textDirectorio.setEditable(false);
		textDirectorio.setColumns(10);
		textDirectorio.setBounds(10, 76, 438, 35);
		panel.add(textDirectorio);
		File f = new File(System.getProperty("user.home"));
		
		JScrollPane jp = new JScrollPane();
		
	}
}
