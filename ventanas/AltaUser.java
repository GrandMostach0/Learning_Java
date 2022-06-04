package ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

import clasesAuxiliares.Conexion;

public class AltaUser extends JFrame {

	private JPanel contentPane;
	private JPanel pa;
	private JTextField txtUser;
	private JTextField txtPasw;
	
	PreparedStatement preparacion;
	Connection con;
	Conexion conex = new Conexion();
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AltaUser frame = new AltaUser();
					frame.setLocationRelativeTo(null);
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
	
	public AltaUser() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 340);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblUsuer = new JLabel("Usuer : ");
		lblUsuer.setBounds(35, 32, 110, 32);
		panel.add(lblUsuer);
		
		txtUser = new JTextField();
		txtUser.setBounds(157, 37, 156, 27);
		panel.add(txtUser);
		txtUser.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password :");
		lblPassword.setBounds(35, 88, 110, 32);
		panel.add(lblPassword);
		
		txtPasw = new JTextField();
		txtPasw.setBounds(157, 93, 156, 27);
		panel.add(txtPasw);
		txtPasw.setColumns(10);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!txtUser.getText().isEmpty() && !txtPasw.getText().isEmpty()){
					agregarUsuario();
					JOptionPane.showMessageDialog(null, "Agregado correctamente");
					dispose();
				}else{
					JOptionPane.showMessageDialog(null, "Datos no validos");
				}
			}
		});
		btnAgregar.setBounds(48, 193, 97, 25);
		panel.add(btnAgregar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Proceso Cancelado");
				dispose();
			}
		});
		btnCancelar.setBounds(272, 193, 97, 25);
		panel.add(btnCancelar);
	}
	
	//METODO PARA AGREGAR UN USUARIO
	public void agregarUsuario(){
		
		String usuer = txtUser.getText();
		String pasw = txtPasw.getText();
		
		String sql = " INSERT INTO usuarios(usuario, passwrd) VALUES (?, ?) ";
		
		try{
			con = conex.getConexion();
			preparacion = con.prepareStatement(sql);
			preparacion.setString(1, usuer);
			preparacion.setString(2, pasw);
			
			preparacion.executeUpdate();
			
			JOptionPane.showMessageDialog(null, "El usuario agregado", "Informacion", JOptionPane.INFORMATION_MESSAGE);
			
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
	}

}
