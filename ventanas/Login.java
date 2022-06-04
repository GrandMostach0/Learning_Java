package ventanas;

import imagenes.imagen.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mysql.jdbc.Statement;

import clasesAuxiliares.Conexion;
import clasesAuxiliares.LoginConex;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class Login extends JFrame {
	
	protected static final JPanel Login = null;
	private String usuario;
	private AltaUser alt;
	private final String contra = "12345";
	
	private JPanel contentPane;
	
	//ajustar imagen a un JLabel
	private ImageIcon imagen;
	private Icon icono;
	private JTextField txtUsuario;
	private JPasswordField pswrdLogin;
	
	Conexion conex = new Conexion();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	
	//Metodo para ajustar la imagen a un JLabel
	public void ajustarImagen(JLabel lb, String ruta){
		this.imagen = new ImageIcon(ruta);
		this.icono = new ImageIcon(this.imagen.getImage().getScaledInstance(lb.getWidth(), lb.getHeight(), Image.SCALE_DEFAULT));
		lb.setIcon(icono);
		this.repaint();
	}
	
	public Login() {
		setTitle("LOGIN");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1023, 643);
		contentPane = new JPanel();
		contentPane.setBackground(Color.ORANGE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		panel.setForeground(Color.BLACK);
		panel.setBounds(0, 0, 867, 608);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\kreed\\Desktop\\Universidad\\Quinto Semestre\\Taller de BD\\Proyecto\\InventarioTallerAutomotriz\\src\\imagenes\\user-free-icon-font.png"));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(383, 66, 146, 155);
		panel.add(lblNewLabel);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblUsuario.setForeground(Color.WHITE);
		lblUsuario.setHorizontalAlignment(SwingConstants.LEFT);
		lblUsuario.setBounds(308, 234, 178, 36);
		panel.add(lblUsuario);
		
		txtUsuario = new JTextField();
		txtUsuario.setToolTipText("Ingrese su nombre de usuario");
		txtUsuario.setBounds(309, 271, 306, 27);
		panel.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		JLabel lblLblcontrasenia = new JLabel("Contrase\u00F1a");
		lblLblcontrasenia.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblLblcontrasenia.setForeground(Color.WHITE);
		lblLblcontrasenia.setBounds(308, 344, 178, 27);
		panel.add(lblLblcontrasenia);
		
		pswrdLogin = new JPasswordField();
		pswrdLogin.setToolTipText("Ingrese su Contrase\u00F1a");
		pswrdLogin.setBounds(308, 384, 306, 27);
		panel.add(pswrdLogin);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				validarUsuarios();
			}
		});
		btnAceptar.setForeground(Color.BLACK);
		btnAceptar.setBackground(Color.YELLOW);
		btnAceptar.setBounds(383, 460, 146, 36);
		panel.add(btnAceptar);
		
		JButton btnNuevoUsuario = new JButton("Nuevo Usuario");
		btnNuevoUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ps = JOptionPane.showInputDialog(null, "Ingrese CLAVE", "Nuevo Ususario", JOptionPane.DEFAULT_OPTION);
				alt = new AltaUser();
				if(ps.equals(contra)){
					JOptionPane.showMessageDialog(null, "Bienvenido al panel de ALTA USUARIO");
					alt.setVisible(true);
				}else{
					JOptionPane.showMessageDialog(null, "INCORRECTO");
				}
			}
		});
		btnNuevoUsuario.setBackground(Color.YELLOW);
		btnNuevoUsuario.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNuevoUsuario.setBounds(12, 568, 146, 27);
		panel.add(btnNuevoUsuario);
		
		
	}
	
	//validacion de la informacion
	public void validarUsuarios(){
		
		//obtencion de datos
		String usuario = txtUsuario.getText();
		String pass = String.valueOf(pswrdLogin.getPassword());
		
		int resultado = 0;
		
		//SELECT * FROM usuarios WHERE usuario = 'jorge' AND passwrd = '123'; 
		String sql1 = "SELECT * FROM usuarios WHERE usuario = '" + usuario +"' AND passwrd = '" + pass + "' ";
		PreparedStatement pr;
		ResultSet rs;
		Connection con;
		
		if(!usuario.isEmpty() && !pass.isEmpty()){
			
			try{
				con = conex.getConexion();
				pr = con.prepareStatement(sql1);
				rs = pr.executeQuery();
				
				if(rs.next()){
					
					resultado = 1;
					
					if(resultado==1){
						MenuPrincipal menu = new MenuPrincipal();
						menu.setVisible(true);
						this.dispose();
					}
				}else{
					JOptionPane.showMessageDialog(null, "No se encontro considencias en la base de datos", "Advertencia", JOptionPane.WARNING_MESSAGE);
				}
				
			}catch(Exception e){
				JOptionPane.showMessageDialog(null, "NO se pudo conectar a la base de datos", "Error", JOptionPane.ERROR_MESSAGE);
			}
			
		}else{
			JOptionPane.showMessageDialog(null, "Campos vacios, primero llene los campos");
		}
		
	}
	
	public void setUsuario(){
		this.usuario = txtUsuario.getText();
	}
	
	public String getUsuario(){
		return usuario;
	}
	
}
