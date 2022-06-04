package ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Desktop;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.SystemColor;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.mysql.jdbc.ResultSetMetaData;

import clasesAuxiliares.Conexion;
import clasesAuxiliares.RedonderBorder;
import clasesAuxiliares.VentanaCarroDatos;
import clasesAuxiliares.VentanaClientesDatos;
import clasesAuxiliares.VentanaServicios;

import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileOutputStream;

import com.toedter.calendar.JDateChooser;
import javax.swing.JComboBox;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MenuPrincipal extends JFrame {
	
	//instancias
	private VentanaClientesDatos vent = new VentanaClientesDatos();
	private VentanaCarroDatos ventCarro = new VentanaCarroDatos();
	private VentanaServicio ventServi = new VentanaServicio();

	private JPanel contentPane;
	private JTextField txtCodigo;
	private JTextField txtNomProducto;
	private JTextField txtPrecio;
	private JTextField txtCantidad;
	
	//botons
	private JButton btnAgregar;
	private JButton btnModificar;
	private JButton btnBorrar;
	
	//botones
	private JTabbedPane tabbedPane;
	private JTable tlbProductos;
	private JTextField txtNomCliente;
	private JTextField txtTelelefono;
	private JTable tablaVenta;
	private JTable table_1;
	/**
	 * Launch the application.
	 */
	
	//Sentencias SQL
	
	Conexion conex =  new Conexion();
	
	Connection con;
	PreparedStatement preparacion;
	ResultSet rs;
	private JTextField txtId_PiezaCodigo;
	private JTextField txtModeloC;
	private JTable table_C;
	private JTextField txtApellido;
	private JTextField txtId_Cliente;
	private JTextField txtIdCarro;
	private JTextField txtID_Venta;
	private JTextField txtId_VentaBorrar;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuPrincipal frame = new MenuPrincipal();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	public MenuPrincipal() {
		setResizable(false);
		setTitle("Menu Principal");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1269, 737);
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel(". : Inventario y Servicios : .");
		lblNewLabel.setForeground(Color.ORANGE);
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setFont(new Font("Lucida Console", Font.BOLD, 26));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(241, 0, 1010, 126);
		contentPane.add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(12, 13, 222, 677);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnServicio = new JButton("Servicio");
		btnServicio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(1);
			}
		});
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBackground(Color.WHITE);
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\kreed\\Desktop\\Universidad\\Quinto Semestre\\Taller de BD\\Proyecto\\InventarioTallerAutomotriz\\src\\imagenes\\home-free-icon-font (2).png"));
		lblNewLabel_1.setBounds(12, 24, 216, 177);
		panel.add(lblNewLabel_1);
		btnServicio.setBackground(new Color(255, 255, 255));
		btnServicio.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnServicio.setIcon(new ImageIcon("C:\\Users\\kreed\\Desktop\\Universidad\\Quinto Semestre\\Taller de BD\\Proyecto\\InventarioTallerAutomotriz\\src\\imagenes\\dolar (2).png"));
		btnServicio.setBounds(34, 352, 149, 41);
		panel.add(btnServicio);
		
		//acciones de los botones
		JButton btnInventario = new JButton("Inventario");
		btnInventario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(0);
			}
		});
		btnInventario.setBackground(new Color(255, 255, 255));
		btnInventario.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnInventario.setIcon(new ImageIcon("C:\\Users\\kreed\\Desktop\\Universidad\\Quinto Semestre\\Taller de BD\\Proyecto\\InventarioTallerAutomotriz\\src\\imagenes\\carga-de-camiones.png"));
		btnInventario.setBounds(34, 245, 149, 41);
		panel.add(btnInventario);
		
		JButton btnClientes = new JButton("Clientes");
		btnClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(2);
			}
		});
		btnClientes.setBackground(new Color(255, 255, 255));
		btnClientes.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnClientes.setIcon(new ImageIcon("C:\\Users\\kreed\\Desktop\\Universidad\\Quinto Semestre\\Taller de BD\\Proyecto\\InventarioTallerAutomotriz\\src\\imagenes\\directorio.png"));
		btnClientes.setBounds(34, 452, 149, 41);
		panel.add(btnClientes);
		
		JButton btnNewButton = new JButton("Carros");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(3);
			}
		});
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton.setBounds(34, 542, 149, 41);
		panel.add(btnNewButton);
		
		
		//componente donde se puede administar por varias pestañas
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(241, 139, 1010, 551);
		
		//pestaña una que es el de administrar producto
		JPanel op1 = new JPanel();
		
		//zona donde se le agrega las pestañas a la ventana o componente que los administrara
		tabbedPane.add("Administrar Inventario", op1);
		op1.setLayout(null);
		
		JLabel lblCodigo = new JLabel("Codigo :");
		lblCodigo.setBounds(47, 8, 60, 19);
		lblCodigo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCodigo.setHorizontalAlignment(SwingConstants.RIGHT);
		op1.add(lblCodigo);
		
		txtCodigo = new JTextField();
		
		//validacion de numeros
		txtCodigo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(!Character.isDigit(c) || txtCodigo.getText().length() == 13){
					e.consume();
					JOptionPane.showMessageDialog(null, "Solo se admiten numeros");
				}
			}
		});
		
		txtCodigo.setBounds(112, 5, 136, 25);
		txtCodigo.setFont(new Font("Tahoma", Font.BOLD, 15));
		op1.add(txtCodigo);
		txtCodigo.setColumns(10);
		
		JLabel lblNomProducto = new JLabel("Nombre Producto :");
		lblNomProducto.setBounds(253, 8, 139, 19);
		lblNomProducto.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNomProducto.setHorizontalAlignment(SwingConstants.RIGHT);
		op1.add(lblNomProducto);
		
		txtNomProducto = new JTextField();
		txtNomProducto.setBounds(397, 5, 136, 25);
		txtNomProducto.setFont(new Font("Tahoma", Font.BOLD, 15));
		op1.add(txtNomProducto);
		txtNomProducto.setColumns(10);
		
		JLabel lblPrecio = new JLabel("Precio :");
		lblPrecio.setBounds(538, 8, 57, 19);
		lblPrecio.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPrecio.setHorizontalAlignment(SwingConstants.RIGHT);
		op1.add(lblPrecio);
		
		txtPrecio = new JTextField();
		
		//validacion de solo numeros
		txtPrecio.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				
				if(!Character.isDigit(c)){
					e.consume();
					JOptionPane.showMessageDialog(null, "Solo se admiten numeros");
				}
			}
		});
		txtPrecio.setBounds(600, 5, 136, 25);
		txtPrecio.setFont(new Font("Tahoma", Font.BOLD, 15));
		op1.add(txtPrecio);
		txtPrecio.setColumns(10);
		
		JLabel lblCantidad = new JLabel("Cantidad :");
		lblCantidad.setBounds(741, 8, 75, 19);
		lblCantidad.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCantidad.setHorizontalAlignment(SwingConstants.RIGHT);
		op1.add(lblCantidad);
		
		txtCantidad = new JTextField();
		txtCantidad.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				
				if(!Character.isDigit(c)){
					e.consume();
					JOptionPane.showMessageDialog(null, "Solo se admiten numeros");
				}
				
			}
		});
		txtCantidad.setBounds(821, 5, 136, 25);
		txtCantidad.setFont(new Font("Tahoma", Font.BOLD, 15));
		op1.add(txtCantidad);
		txtCantidad.setColumns(10);
		
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//agregarProductos();
				for(int i = 0; i < tlbProductos.getRowCount(); i++){
					if(tlbProductos.getValueAt(i, 1).equals(txtNomProducto.getText())){
						JOptionPane.showMessageDialog(null, "Los datos ya existen");
					}else{
						agregarProductos();
					}
				}
			}
		});
		
		
		btnAgregar.setBounds(180, 71, 119, 25);
		btnAgregar.setIcon(new ImageIcon("C:\\Users\\kreed\\Desktop\\Universidad\\Quinto Semestre\\Taller de BD\\Proyecto\\InventarioTallerAutomotriz\\src\\imagenes\\agregar.png"));
		op1.add(btnAgregar);
		
		
		//boton de modificar los datos
		btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificarProductos();
			}
		});
		
		
		btnModificar.setBounds(311, 71, 119, 25);
		btnModificar.setIcon(new ImageIcon("C:\\Users\\kreed\\Desktop\\Universidad\\Quinto Semestre\\Taller de BD\\Proyecto\\InventarioTallerAutomotriz\\src\\imagenes\\lapiz.png"));
		op1.add(btnModificar);
		
		
		btnBorrar = new JButton("Borrar");
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminarProducto();
			}
		});
		btnBorrar.setBounds(442, 71, 119, 25);
		btnBorrar.setIcon(new ImageIcon("C:\\Users\\kreed\\Desktop\\Universidad\\Quinto Semestre\\Taller de BD\\Proyecto\\InventarioTallerAutomotriz\\src\\imagenes\\menos.png"));
		op1.add(btnBorrar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarCampos();
			}
		});
		btnCancelar.setBounds(573, 71, 119, 25);
		btnCancelar.setIcon(new ImageIcon("C:\\Users\\kreed\\Desktop\\Universidad\\Quinto Semestre\\Taller de BD\\Proyecto\\InventarioTallerAutomotriz\\src\\imagenes\\circulo-cruzado.png"));
		op1.add(btnCancelar);
		
		//clik para cargar los datos en los JTextField
		tlbProductos = new JTable();
		tlbProductos.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tlbProductos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				PreparedStatement ps;
				ResultSet rs;
				
				try{
					
					int fila = tlbProductos.getSelectedRow();
					int codigo = Integer.parseInt(tlbProductos.getValueAt(fila, 0).toString());
					con = conex.getConexion();
					//SELECT NombreProducto, precio, stock FROM pieza WHERE codigo = '1234';
					ps = con.prepareStatement("SELECT NombreProducto, precio, stock FROM pieza WHERE codigo = ? ");
					ps.setInt(1, codigo);
					rs = ps.executeQuery();
					
					while(rs.next()){
						
						txtId_PiezaCodigo.setText(String.valueOf(codigo));
						txtCodigo.setText(String.valueOf(codigo));
						txtNomProducto.setText(rs.getString("NombreProducto"));
						txtPrecio.setText(rs.getString("precio"));
						txtCantidad.setText(rs.getString("stock"));
						
					}
					
				}catch(Exception ex){
					ex.printStackTrace();
				}
			}
		});
		
		
		tlbProductos.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Codigo", "Producto", "Precio", "Cantidad"
			}
		));
		tlbProductos.setForeground(Color.BLACK);
		tlbProductos.setBorder(new EmptyBorder(1, 1, 1, 1));
		tlbProductos.setBackground(Color.ORANGE);
		tlbProductos.setSurrendersFocusOnKeystroke(true);
		tlbProductos.setColumnSelectionAllowed(true);
		tlbProductos.setBounds(12, 148, 981, 325);
		op1.add(tlbProductos);
		
		txtId_PiezaCodigo = new JTextField();
		txtId_PiezaCodigo.setBounds(407, 36, 116, 22);
		txtId_PiezaCodigo.setVisible(true);
		op1.add(txtId_PiezaCodigo);
		txtId_PiezaCodigo.setColumns(10);
		
		JLabel lblProd = new JLabel("Producto");
		lblProd.setBackground(Color.ORANGE);
		lblProd.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblProd.setHorizontalAlignment(SwingConstants.CENTER);
		lblProd.setBounds(272, 119, 158, 25);
		op1.add(lblProd);
		
		JLabel lblCodig = new JLabel("Codigo");
		lblCodig.setBackground(Color.ORANGE);
		lblCodig.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblCodig.setHorizontalAlignment(SwingConstants.CENTER);
		lblCodig.setBounds(29, 119, 219, 24);
		op1.add(lblCodig);
		
		JLabel lblPrecio_1 = new JLabel("Precio");
		lblPrecio_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblPrecio_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrecio_1.setBounds(538, 119, 186, 25);
		op1.add(lblPrecio_1);
		
		JLabel lblCantidad_1 = new JLabel("Cantidad");
		lblCantidad_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblCantidad_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblCantidad_1.setBounds(771, 119, 186, 25);
		op1.add(lblCantidad_1);
		
		//pestañia de cliente
		JPanel op3 = new JPanel();
		tabbedPane.add("Clientes", op3);
		op3.setLayout(null);
		
		JLabel lblNomCliente = new JLabel("Nombre cliente");
		lblNomCliente.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNomCliente.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNomCliente.setBounds(29, 33, 120, 23);
		op3.add(lblNomCliente);
		
		txtNomCliente = new JTextField();
		txtNomCliente.setBounds(161, 33, 116, 22);
		op3.add(txtNomCliente);
		txtNomCliente.setColumns(10);
		
		JLabel lblApellido = new JLabel("Apellido :");
		lblApellido.setHorizontalAlignment(SwingConstants.RIGHT);
		lblApellido.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblApellido.setBounds(29, 69, 120, 23);
		op3.add(lblApellido);
		
		txtApellido = new JTextField();
		txtApellido.setBounds(161, 68, 116, 22);
		op3.add(txtApellido);
		txtApellido.setColumns(10);
		
		JLabel lblTelefono = new JLabel("Telefono:");
		lblTelefono.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTelefono.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTelefono.setBounds(29, 107, 120, 23);
		op3.add(lblTelefono);
		
		txtTelelefono = new JTextField();
		//--------- V A L I D A C I Ó N --- P A R A --- Q U E --- N O --- P E R M I T A --- N U M E R O S 
		txtTelelefono.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char key = e.getKeyChar();
				
				if(!Character.isDigit(key) || txtTelelefono.getText().length() == 10){
					e.consume();
				}
				
				if(txtTelelefono.getText().trim().length() > 8){
					JOptionPane.showMessageDialog(null, "Numero telefonico " + txtTelelefono.getText() );
					e.consume();
				}
			}
		});
		txtTelelefono.setBounds(155, 111, 116, 22);
		op3.add(txtTelelefono);
		txtTelelefono.setColumns(10);
		
		JButton btnAgregarC = new JButton("Agregar");
		btnAgregarC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vent.agregarCliente(txtNomCliente.getText(), txtApellido.getText(), txtTelelefono.getText(), table_1);
				txtNomCliente.setText("");
				txtTelelefono.setText("");
				txtApellido.setText("");
				
				//comboBoxClientes.removeAllItems();
				
				//combo.llenarComboBoxCliente(comboBoxClientes);
				
			}
		});
		
		txtId_Cliente = new JTextField();
		txtId_Cliente.setEditable(false);
		txtId_Cliente.setBounds(924, 416, 49, 22);
		txtId_PiezaCodigo.setVisible(false);
		op3.add(txtId_Cliente);
		txtId_Cliente.setColumns(10);
		btnAgregarC.setBounds(41, 245, 108, 23);
		op3.add(btnAgregarC);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vent.eliminarCliente(txtId_Cliente, table_1);
				txtNomCliente.setText("");
				txtTelelefono.setText("");
				txtApellido.setText("");
				
				//comboBoxClientes.removeAll();
				
				//combo.llenarComboBoxCliente(comboBoxClientes);
				
			}
		});
		btnEliminar.setBounds(188, 245, 108, 23);
		op3.add(btnEliminar);
		
		table_1 = new JTable();
		table_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		table_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				vent.IdCliente(table_1, txtId_Cliente, txtNomCliente, txtTelelefono, txtApellido);
			}
		});
		table_1.setFillsViewportHeight(true);
		table_1.setCellSelectionEnabled(true);
		table_1.setShowVerticalLines(true);
		table_1.setShowHorizontalLines(true);
		table_1.setRowSelectionAllowed(true);
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
				{"id", "nombre", "apellido", "telefono"},
			},
			new String[] {
				"Id", "Nombre", "Apellido", "Telefono"
			}
		));
		table_1.setSurrendersFocusOnKeystroke(true);
		table_1.setForeground(Color.BLACK);
		table_1.setEnabled(true);
		table_1.setColumnSelectionAllowed(true);
		table_1.setBorder(new EmptyBorder(1, 1, 1, 1));
		table_1.setBackground(Color.ORANGE);
		table_1.setBounds(306, 68, 667, 348);
		op3.add(table_1);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombre.setBounds(495, 33, 108, 23);
		op3.add(lblNombre);
		
		JLabel lblTelefonoT = new JLabel("Telefono");
		lblTelefonoT.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblTelefonoT.setHorizontalAlignment(SwingConstants.CENTER);
		lblTelefonoT.setBounds(824, 36, 108, 16);
		op3.add(lblTelefonoT);
		
		JLabel lbl_ID = new JLabel("ID Cliente");
		lbl_ID.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbl_ID.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_ID.setBounds(332, 35, 88, 19);
		op3.add(lbl_ID);
		
		JLabel lblApellido_1 = new JLabel("Apellido");
		lblApellido_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblApellido_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblApellido_1.setBounds(650, 36, 88, 16);
		op3.add(lblApellido_1);
		contentPane.add(tabbedPane);
		
		//ZONA DE SERVICIO
		JPanel op2 = new JPanel();
		tabbedPane.add("Servicio", op2);
		op2.setLayout(null);
		
		tablaVenta = new JTable();
		tablaVenta.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent ev) {
				PreparedStatement ps;
				ResultSet rs;
				
				try{
					int fila = tablaVenta.getSelectedRow();
					int codigo = Integer.parseInt(tablaVenta.getValueAt(fila, 0).toString());
					con = conex.getConexion();
					ps = con.prepareStatement("SELECT id_venta, id_cliente, fecha_entrega FROM serviciocarro WHERE id_venta = ? ");
					ps.setInt(1, codigo);
					rs = ps.executeQuery();
					
					while(rs.next()){
						txtId_VentaBorrar.setText(String.valueOf(codigo));
						txtID_Venta.setText(String.valueOf(rs.getString("fecha_entrega").trim() + rs.getString("id_cliente").trim()));
					}
					
				}catch(Exception ex){
				}
			}
		});
		
		tablaVenta.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tablaVenta.setModel(new DefaultTableModel(
			new Object[][] {
				{"idVenta", "idCliente", "Captura", "Entrega"},
			},
			new String[] {
				"Id_Venta", "id_Cliente", "f_captura", "f_entrega"
			}
		));
		tablaVenta.setSurrendersFocusOnKeystroke(true);
		tablaVenta.setForeground(Color.BLACK);
		tablaVenta.setColumnSelectionAllowed(true);
		tablaVenta.setBorder(new EmptyBorder(1, 1, 1, 1));
		tablaVenta.setBackground(Color.ORANGE);
		tablaVenta.setBounds(65, 182, 903, 296);
		op2.add(tablaVenta);
		
		JButton btnAgregarV = new JButton("Agregar");
		btnAgregarV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ventServi.setVisible(true);
				ventServi.obtenerDatos(tablaVenta);
				mostrarDatos();
			}
		});
		
		txtID_Venta = new JTextField();
		txtID_Venta.setBounds(347, 100, 116, 22);
		txtID_Venta.setVisible(false);
		op2.add(txtID_Venta);
		txtID_Venta.setColumns(10);
		btnAgregarV.setBounds(170, 65, 97, 25);
		op2.add(btnAgregarV);
		
		JButton btnBorrarV = new JButton("Borrar");
		btnBorrarV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminarVenta();
				ventServi.obtenerDatos(tablaVenta);
			}
		});
		btnBorrarV.setBounds(583, 65, 97, 25);
		op2.add(btnBorrarV);
		
		JLabel lblAgregarServicio = new JLabel("AGREGAR SERVICIO");
		lblAgregarServicio.setBackground(Color.ORANGE);
		lblAgregarServicio.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblAgregarServicio.setHorizontalAlignment(SwingConstants.CENTER);
		lblAgregarServicio.setBounds(133, 30, 176, 32);
		op2.add(lblAgregarServicio);
		
		JLabel lblBorrarServicio = new JLabel("BORRAR SERVICIO");
		lblBorrarServicio.setHorizontalAlignment(SwingConstants.CENTER);
		lblBorrarServicio.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblBorrarServicio.setBackground(Color.ORANGE);
		lblBorrarServicio.setBounds(533, 30, 176, 32);
		op2.add(lblBorrarServicio);
		
		JLabel lblIdVenta = new JLabel("ID VENTA");
		lblIdVenta.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblIdVenta.setHorizontalAlignment(SwingConstants.CENTER);
		lblIdVenta.setBounds(95, 152, 97, 16);
		op2.add(lblIdVenta);
		
		JLabel lblIdCliente = new JLabel("ID CLIENTE");
		lblIdCliente.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblIdCliente.setHorizontalAlignment(SwingConstants.CENTER);
		lblIdCliente.setBounds(347, 152, 97, 16);
		op2.add(lblIdCliente);
		
		JLabel lblCaptura = new JLabel("CAPTURA");
		lblCaptura.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblCaptura.setHorizontalAlignment(SwingConstants.CENTER);
		lblCaptura.setBounds(583, 152, 97, 16);
		op2.add(lblCaptura);
		
		JLabel lblEntrega = new JLabel("ENTREGA");
		lblEntrega.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblEntrega.setHorizontalAlignment(SwingConstants.CENTER);
		lblEntrega.setBounds(812, 153, 97, 16);
		op2.add(lblEntrega);
		
		//zona de los carros
		JPanel op4 = new JPanel();
		tabbedPane.add("Carro", op4);
		op4.setLayout(null);
		
		JLabel lblModeloCarro = new JLabel("Modelo Carro :");
		lblModeloCarro.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblModeloCarro.setHorizontalAlignment(SwingConstants.RIGHT);
		lblModeloCarro.setBounds(32, 53, 129, 25);
		op4.add(lblModeloCarro);
		
		txtModeloC = new JTextField();
		txtModeloC.setBounds(173, 53, 128, 25);
		op4.add(txtModeloC);
		txtModeloC.setColumns(10);
		
		JButton btnAgregar_1 = new JButton("Agregar");
		btnAgregar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventCarro.agregarCarro(txtModeloC.getText(), table_C);
				txtModeloC.setText("");
				//comboxModeloCar.removeAllItems();
				
				//combo.LlenarComboBoxCarro(comboxModeloCar);
				
				
			}
		});
		btnAgregar_1.setBounds(307, 107, 97, 25);
		op4.add(btnAgregar_1);
		
		JButton btnEliminar_1 = new JButton("Eliminar");
		btnEliminar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventCarro.eliminarModelo(txtIdCarro.getText(), table_C);
				txtModeloC.setText("");
				
				//comboxModeloCar.removeAllItems();
				
				//combo.LlenarComboBoxCarro(comboxModeloCar);
				
			}
		});
		btnEliminar_1.setBounds(77, 107, 97, 25);
		op4.add(btnEliminar_1);
		
		txtIdCarro = new JTextField();
		txtIdCarro.setBounds(77, 166, 116, 22);
		txtIdCarro.setVisible(false);
		op4.add(txtIdCarro);
		txtIdCarro.setColumns(10);
		
		table_C = new JTable();
		table_C.setFillsViewportHeight(true);
		table_C.setCellSelectionEnabled(true);
		table_C.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ventCarro.IdCarro(table_C, txtIdCarro, txtModeloC);
			}
		});
		table_C.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null},
			},
			new String[] {
				"Id", "Modelo"
			}
		));
		table_C.setSurrendersFocusOnKeystroke(true);
		table_C.setForeground(Color.BLACK);
		table_C.setColumnSelectionAllowed(true);
		table_C.setBorder(new EmptyBorder(1, 1, 1, 1));
		table_C.setBackground(Color.ORANGE);
		table_C.setBounds(269, 212, 724, 222);
		op4.add(table_C);
		
		JLabel lblId_1 = new JLabel("ID");
		lblId_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblId_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblId_1.setBounds(347, 183, 97, 16);
		op4.add(lblId_1);
		
		JLabel lblModelo_1 = new JLabel("Modelo");
		lblModelo_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblModelo_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblModelo_1.setBounds(804, 183, 97, 16);
		op4.add(lblModelo_1);
		
		JButton btnActualizarTabla = new JButton("ACTUALIZAR TABLA");
		btnActualizarTabla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventServi.obtenerDatos(tablaVenta);
				JOptionPane.showMessageDialog(null, "Tabla ACTUALIZADO");
			}
		});
		btnActualizarTabla.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnActualizarTabla.setBounds(760, 115, 170, 25);
		op2.add(btnActualizarTabla);
		
		//apartado de mostrar datos
		mostrarDatos();
		vent.mostrarDatosClientes(table_1);
		ventCarro.mostrarDatosCarro(table_C);
		ventServi.obtenerDatos(tablaVenta);
		
		JButton btnMostarVenta = new JButton("Mostar Venta");
		btnMostarVenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				try{
					String dat = txtID_Venta.getText().trim();
					//File file = new File("src/pdf/pdf"+concat+".pdf");
					File file = new File("src/pdf/pdf"+dat+".pdf");
					Desktop.getDesktop().open(file);
				}catch(Exception ex){
					System.out.println(ex.getMessage());
				}
			}
		});
		btnMostarVenta.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnMostarVenta.setBounds(44, 115, 148, 25);
		op2.add(btnMostarVenta);
		
		txtId_VentaBorrar = new JTextField();
		txtId_VentaBorrar.setBounds(475, 100, 116, 22);
		txtId_VentaBorrar.setVisible(false);
		op2.add(txtId_VentaBorrar);
		txtId_VentaBorrar.setColumns(10);
	}
	
	//agregar
	public void agregarProductos(){

		String sql = "INSERT INTO pieza(codigo, NombreProducto, precio, stock) VALUES (?, ?, ?, ?) ";
		
		try{
			
			int codigo = Integer.parseInt(txtCodigo.getText());
			String produc = txtNomProducto.getText();
			int pres = Integer.parseInt(txtPrecio.getText());
			int conti = Integer.parseInt(txtCantidad.getText());
			
			con = conex.getConexion();
			
			if(txtCodigo.getText().isEmpty()){
				JOptionPane.showMessageDialog(null, "El campo de Codigo no tiene valores", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
			}else{
				preparacion = con.prepareStatement(sql);
				preparacion.setInt(1, codigo);
				preparacion.setString(2, produc);
				preparacion.setInt(3, pres);
				preparacion.setInt(4, conti);
				
				preparacion.executeUpdate();
						
				JOptionPane.showMessageDialog(null, "Datos Almacenados", "Informacion", JOptionPane.INFORMATION_MESSAGE);
				limpiarCampos();
				mostrarDatos();
			}
			
		}catch(Exception ex){
			JOptionPane.showMessageDialog(null, "ERROR, problema con la base de datos o los campos estan vacios");
		}
	}
	
	//modificar
	public void modificarProductos(){
		
		int codigo = Integer.parseInt(txtId_PiezaCodigo.getText().trim());
		String nombreProducto = txtNomProducto.getText().trim();
		int precio = Integer.parseInt(String.valueOf(txtPrecio.getText()));
		int cantidad = Integer.parseInt(txtCantidad.getText().trim());
		
		String sql = "UPDATE pieza SET NombreProducto = ?, precio = ?, stock = ? WHERE codigo=?";
		//'FROM NombreProducto = 30, precio = 'rinDeportivo', stock = 3180 WHERE codigo=30'
		try{
			con = conex.getConexion();
			preparacion = con.prepareStatement(sql);
			preparacion.setString(1, nombreProducto);
			preparacion.setInt(2, precio);
			preparacion.setInt(3, cantidad);
			preparacion.setInt(4, codigo);
			
			preparacion.executeUpdate();
			
			JOptionPane.showMessageDialog(null, "Registros modificado", "Informacion", JOptionPane.INFORMATION_MESSAGE);
			limpiarCampos();
			mostrarDatos();
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	//eliminar
	public void eliminarProducto(){
		int codigo = Integer.parseInt(txtId_PiezaCodigo.getText());
		
		String sql = "DELETE FROM pieza WHERE codigo=?";
		
		try{
			con = conex.getConexion();
			preparacion = con.prepareStatement(sql);
			preparacion.setInt(1, codigo);
			preparacion.executeUpdate();
			JOptionPane.showMessageDialog(null, "Registros Eliminado", "Informacion", JOptionPane.INFORMATION_MESSAGE);
			limpiarCampos();
			mostrarDatos();
		}catch(Exception ex){
			System.out.println(ex.toString());
		}
	}
	
	public void eliminarVenta(){
		int id_vent = Integer.parseInt(txtId_VentaBorrar.getText());
		
		String sql = "DELETE FROM serviciocarro WHERE id_venta = ?";
		
		try{
			con = conex.getConexion();
			preparacion = con.prepareStatement(sql);
			preparacion.setInt(1, id_vent);
			preparacion.executeUpdate();
			JOptionPane.showMessageDialog(null, "Registros Eliminado", "Informacion", JOptionPane.INFORMATION_MESSAGE);
			ventServi.obtenerDatos(tablaVenta);;
		}catch(Exception ex){
			System.out.println(ex.toString());
		}
		
	}
	
	//limpiar los datos
	public void limpiarCampos(){
		txtCodigo.setText("");
		txtNomProducto.setText("");
		txtPrecio.setText("");
		txtCantidad.setText("");
	}
		
	//mostrar datos
	public void mostrarDatos(){
		DefaultTableModel modeloTabla = (DefaultTableModel) tlbProductos.getModel();
		modeloTabla.setRowCount(0);
		
		PreparedStatement ps;
		ResultSet rs;
		java.sql.ResultSetMetaData rstmd;
		int columnas;
		
		try{
			con = conex.getConexion();
			ps = con.prepareStatement("SELECT codigo, NombreProducto, precio, stock FROM pieza");
			rs = ps.executeQuery();
			
			rstmd = rs.getMetaData();
			columnas = rstmd.getColumnCount();
			
			while(rs.next()){
				Object[] fila = new Object[columnas];
				for(int indice = 0; indice < columnas; indice++){
					fila[indice] = rs.getObject(indice+1);
				}
				modeloTabla.addRow(fila);
			}
			
			
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
		
	}
}
