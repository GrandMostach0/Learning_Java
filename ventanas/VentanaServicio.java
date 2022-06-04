package ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import java.awt.Desktop;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.jdesktop.swingx.autocomplete.ObjectToStringConverter;

import clasesAuxiliares.Conexion;
import clasesAuxiliares.DatosVenta;

import javax.swing.JButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaServicio extends JFrame {

	private JPanel contentPane;
	private JTextField txtPieza;
	private JTextField txtPresuni;
	private JTextField txtCanitdad;
	private JTextField txtStock;
	private JTextField txtNomcliente;
	private JTextField txtApellido;
	private JTextField txtModelo;
	private JTable tblPieza;
	private JTextField txtTotal;
	private JTextField txtFalla;
	private JTextField txtCodigo;
	
	//instancia
	Conexion conex = new Conexion();
	DatosVenta buscar = new DatosVenta();
	
	Connection con;
	ResultSet resultado;
	PreparedStatement preparacion;
	ResultSetMetaData resultadoMeta;
	
	private JTextField txtIdcliente;
	private JTextField txtIdmodelo;
	
	private int item = 0;
	private int total = 0;
	private int stockActual = 0;
	private int id_ventaR = 0;
	DefaultTableModel modelo;
	private JDateChooser dateEntrega;
	private JTable tabla;
	private JTextField txtTelefono;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaServicio frame = new VentanaServicio();
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
	public VentanaServicio() {
		setTitle("Servicio");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 998, 698);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblTitulo = new JLabel(". : SERVICIO : .");
		lblTitulo.setBackground(Color.ORANGE);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBounds(0, 0, 982, 82);
		panel.add(lblTitulo);
		
		JLabel lblCodigoPieza = new JLabel("Codigo Pieza");
		lblCodigoPieza.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCodigoPieza.setHorizontalAlignment(SwingConstants.CENTER);
		lblCodigoPieza.setBounds(37, 139, 110, 40);
		panel.add(lblCodigoPieza);
		
		txtCodigo = new JTextField();
		txtCodigo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					try{
						if(!"".equals(txtCodigo.getText())){
							buscar.BuscarProductosPieza(Integer.parseInt(txtCodigo.getText()), txtPieza, txtPresuni, txtStock);
							txtCanitdad.requestFocus();
						}else{
							JOptionPane.showMessageDialog(null, "No se encontro coincidencias en la base de datos");
						}
					}catch(Exception ex){
						JOptionPane.showMessageDialog(null, "No se encontro coincidencias en la base de datos");
					}
				}
			}
		});
		txtCodigo.setBounds(37, 196, 116, 22);
		panel.add(txtCodigo);
		txtCodigo.setColumns(10);
		
		JLabel lblpieza = new JLabel("Pieza");
		lblpieza.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblpieza.setHorizontalAlignment(SwingConstants.CENTER);
		lblpieza.setBounds(171, 139, 129, 40);
		panel.add(lblpieza);
		
		txtPieza = new JTextField();
		txtPieza.setHorizontalAlignment(SwingConstants.CENTER);
		txtPieza.setEditable(false);
		txtPieza.setBounds(181, 193, 119, 29);
		panel.add(txtPieza);
		txtPieza.setColumns(10);
		
		JLabel lblPrecioUnitario = new JLabel("Precio Unitario");
		lblPrecioUnitario.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrecioUnitario.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPrecioUnitario.setBounds(336, 139, 130, 40);
		panel.add(lblPrecioUnitario);
		
		txtPresuni = new JTextField();
		txtPresuni.setHorizontalAlignment(SwingConstants.CENTER);
		txtPresuni.setEditable(false);
		txtPresuni.setBounds(336, 192, 130, 29);
		panel.add(txtPresuni);
		txtPresuni.setColumns(10);
		
		JLabel lblCantidad = new JLabel("Cantidad");
		lblCantidad.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCantidad.setHorizontalAlignment(SwingConstants.CENTER);
		lblCantidad.setBounds(509, 139, 116, 40);
		panel.add(lblCantidad);
		
		txtCanitdad = new JTextField();
		txtCanitdad.addKeyListener(new KeyAdapter() {
			int cantidad = 0;
			int total = 0;
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					if(!"".equals(txtCanitdad.getText())){
						String codigo = txtCodigo.getText();
						String nombrePieza = txtPieza.getText();
						int presUnitario = Integer.parseInt(txtPresuni.getText());
						int stockDispo = Integer.parseInt(txtStock.getText());
						
						try{
							cantidad = Integer.parseInt(txtCanitdad.getText());
							total = presUnitario * cantidad;
						}catch(Exception ex){
							ex.printStackTrace();
						}
						
						if(stockDispo >= cantidad){
							item = item +1;
							modelo = (DefaultTableModel) tblPieza.getModel();
							
							//validacion
							for(int i = 0; i < tblPieza.getRowCount();i++){
								if(tblPieza.getValueAt(i, 1).equals(txtPieza.getText())){
									JOptionPane.showMessageDialog(null, "Datos ya existentes en la tabla");
									return;
								}
							}
							
							ArrayList lista = new ArrayList();
							lista.add(item);
							lista.add(codigo);
							lista.add(nombrePieza);
							lista.add(presUnitario);
							lista.add(cantidad);
							lista.add(total);
							Object[] ob = new Object[5];
							ob[0] = lista.get(1);
							ob[1] = lista.get(2);
							ob[2] = lista.get(3);
							ob[3] = lista.get(4);
							ob[4] = lista.get(5);
							
							modelo.addRow(ob);
							tblPieza.setModel(modelo);
							totalPagar();
							limpiar();
							txtCodigo.requestFocus();
							
						}else{
							JOptionPane.showMessageDialog(null, "Stock No disponible", "Advertencia", JOptionPane.WARNING_MESSAGE);
						}
					}else{
						JOptionPane.showMessageDialog(null, "Ingresa una cantidad");
					}
				}
			}
		});
		txtCanitdad.setBounds(509, 192, 116, 29);
		panel.add(txtCanitdad);
		txtCanitdad.setColumns(10);
		
		JLabel lblStock = new JLabel("Stock");
		lblStock.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblStock.setHorizontalAlignment(SwingConstants.CENTER);
		lblStock.setBounds(675, 146, 90, 27);
		panel.add(lblStock);
		
		txtStock = new JTextField();
		txtStock.setHorizontalAlignment(SwingConstants.CENTER);
		txtStock.setEditable(false);
		txtStock.setBounds(669, 192, 116, 29);
		panel.add(txtStock);
		txtStock.setColumns(10);
		
		JLabel lblIdCliente = new JLabel("Id Cliente");
		lblIdCliente.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblIdCliente.setHorizontalAlignment(SwingConstants.CENTER);
		lblIdCliente.setBounds(37, 244, 110, 40);
		panel.add(lblIdCliente);
		
		txtIdcliente = new JTextField();
		txtIdcliente.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					try{
						if(!"".equals(txtIdcliente.getText())){
							buscar.BuscarCliente(Integer.parseInt(txtIdcliente.getText()), txtNomcliente, txtApellido, txtTelefono);
							txtIdmodelo.requestFocus();
						}else{
							JOptionPane.showMessageDialog(null, "No se encontro coincidencias en la base de datos");
						}
					}catch(Exception ex){
						JOptionPane.showMessageDialog(null, "No se encontro coincidencias en la base de datos");
					}
				}
			}
		});
		txtIdcliente.setBounds(37, 297, 116, 22);
		panel.add(txtIdcliente);
		txtIdcliente.setColumns(10);
		
		JLabel lblNombreCliente = new JLabel("Nombre Cliente");
		lblNombreCliente.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNombreCliente.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombreCliente.setBounds(159, 250, 141, 28);
		panel.add(lblNombreCliente);
		
		txtNomcliente = new JTextField();
		txtNomcliente.setHorizontalAlignment(SwingConstants.CENTER);
		txtNomcliente.setEditable(false);
		txtNomcliente.setBounds(171, 293, 123, 29);
		panel.add(txtNomcliente);
		txtNomcliente.setColumns(10);
		
		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setHorizontalAlignment(SwingConstants.CENTER);
		lblApellido.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblApellido.setBounds(322, 251, 119, 27);
		panel.add(lblApellido);
		
		txtApellido = new JTextField();
		txtApellido.setHorizontalAlignment(SwingConstants.CENTER);
		txtApellido.setEditable(false);
		txtApellido.setBounds(322, 290, 119, 29);
		panel.add(txtApellido);
		txtApellido.setColumns(10);
		
		txtTelefono = new JTextField();
		txtTelefono.setVisible(false);
		txtTelefono.setBounds(840, 149, 116, 22);
		panel.add(txtTelefono);
		txtTelefono.setColumns(10);
		
		JLabel lblIdModelo = new JLabel("Id Modelo");
		lblIdModelo.setHorizontalAlignment(SwingConstants.CENTER);
		lblIdModelo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblIdModelo.setBounds(463, 250, 110, 29);
		panel.add(lblIdModelo);
		
		txtIdmodelo = new JTextField();
		txtIdmodelo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					try{
						if(!"".equals(txtIdmodelo.getText())){
							buscar.BuscarModelo(Integer.parseInt(txtIdmodelo.getText()), txtModelo);
						}else{
							JOptionPane.showMessageDialog(null, "No se encontro coincidencias en la base de datos");
						}
					}catch(Exception ex){
						JOptionPane.showMessageDialog(null, "No se encontro coincidencias en la base de datos");
					}
				}
			}
		});
		txtIdmodelo.setBounds(473, 296, 116, 22);
		panel.add(txtIdmodelo);
		txtIdmodelo.setColumns(10);
		
		JLabel lblModelo = new JLabel("Modelo");
		lblModelo.setHorizontalAlignment(SwingConstants.CENTER);
		lblModelo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblModelo.setBounds(624, 251, 90, 27);
		panel.add(lblModelo);
		
		txtModelo = new JTextField();
		txtModelo.setHorizontalAlignment(SwingConstants.CENTER);
		txtModelo.setEditable(false);
		txtModelo.setBounds(606, 291, 129, 26);
		panel.add(txtModelo);
		txtModelo.setColumns(10);
		
		tblPieza = new JTable();
		tblPieza.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Codigo", "Producto", "presUni", "cantidad", "Total"
			}
		));
		tblPieza.setForeground(Color.BLACK);
		tblPieza.setBorder(new EmptyBorder(1, 1, 1, 1));
		tblPieza.setBackground(Color.WHITE);
		tblPieza.setSurrendersFocusOnKeystroke(true);
		tblPieza.setColumnSelectionAllowed(true);
		tblPieza.setBounds(26, 357, 930, 257);
		panel.add(tblPieza);
		
		JLabel lblTotal = new JLabel("Total :");
		lblTotal.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTotal.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotal.setBounds(742, 624, 76, 16);
		panel.add(lblTotal);
		
		txtTotal = new JTextField();
		txtTotal.setEditable(false);
		txtTotal.setBounds(830, 622, 116, 22);
		panel.add(txtTotal);
		txtTotal.setColumns(10);
		
		JLabel lblFalla = new JLabel("Falla");
		lblFalla.setHorizontalAlignment(SwingConstants.CENTER);
		lblFalla.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblFalla.setBounds(818, 244, 76, 34);
		panel.add(lblFalla);
		
		txtFalla = new JTextField();
		txtFalla.setBounds(799, 279, 119, 51);
		panel.add(txtFalla);
		txtFalla.setColumns(10);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modelo = (DefaultTableModel) tblPieza.getModel();
				modelo.removeRow(tblPieza.getSelectedRow());
				totalPagar();
				txtCodigo.requestFocus();
			}
		});
		btnEliminar.setBackground(Color.WHITE);
		btnEliminar.setBounds(830, 194, 110, 27);
		panel.add(btnEliminar);
		
		JButton btnCapturar = new JButton("Capturar");
		btnCapturar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//System.out.println(getFechaDevolver());
				//System.out.println(getFechaSistema());
				registrarDatos();
				pdf();
				ActualizarStockDeLaTabla();
				limpiarCampos();
				limpiartextos();
				dispose();
			}
		});
		btnCapturar.setBackground(Color.WHITE);
		btnCapturar.setBounds(37, 621, 97, 25);
		panel.add(btnCapturar);
		
		JLabel lblCodigo = new JLabel("Codigo");
		lblCodigo.setHorizontalAlignment(SwingConstants.CENTER);
		lblCodigo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCodigo.setBounds(59, 332, 88, 16);
		panel.add(lblCodigo);
		
		JLabel lblPieza = new JLabel("Pieza");
		lblPieza.setHorizontalAlignment(SwingConstants.CENTER);
		lblPieza.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPieza.setBounds(244, 335, 90, 16);
		panel.add(lblPieza);
		
		JLabel lblPrecioUnitario_1 = new JLabel("Precio Unitario");
		lblPrecioUnitario_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrecioUnitario_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPrecioUnitario_1.setBounds(418, 335, 129, 16);
		panel.add(lblPrecioUnitario_1);
		
		JLabel lblCantidad_1 = new JLabel("Cantidad");
		lblCantidad_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblCantidad_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCantidad_1.setBounds(616, 335, 98, 16);
		panel.add(lblCantidad_1);
		
		JLabel lblTotal_1 = new JLabel("Total");
		lblTotal_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotal_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTotal_1.setBounds(818, 335, 100, 16);
		panel.add(lblTotal_1);
		
		JLabel lblEntregaCarro = new JLabel("Entrega Carro :");
		lblEntregaCarro.setHorizontalAlignment(SwingConstants.CENTER);
		lblEntregaCarro.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblEntregaCarro.setBounds(37, 113, 129, 29);
		panel.add(lblEntregaCarro);
		
		dateEntrega = new JDateChooser();
		dateEntrega.setBounds(171, 120, 106, 22);
		panel.add(dateEntrega);
	}
	
	private void setTabla(JTable tabla){
		this.tabla = tabla;
	}
	
	private void totalPagar(){
		total = 0;
		int num = 0;
		int nFila = tblPieza.getRowCount();
		for(int i = 0; i < nFila; i++){
			System.out.println(tblPieza.getModel().getValueAt(i, 4));
			num = Integer.parseInt(String.valueOf(tblPieza.getModel().getValueAt(i, 4)));
			total = total + num;
		}
		txtTotal.setText(String.valueOf(total));
	}
	
	private void limpiar(){
		txtCodigo.setText("");
		txtPieza.setText("");
		txtStock.setText("");
		txtPresuni.setText("");
		txtCanitdad.setText("");
	}
	
	private Date getFechaSistema(){
		java.util.Date date = new java.util.Date();
		long d = date.getTime();
		java.sql.Date fecha = new java.sql.Date(d);
		
		return fecha;
	}
	
	private Date getFechaDevolver(){
		java.util.Date date = dateEntrega.getDate();
		long d = date.getTime();
		java.sql.Date fecha = new java.sql.Date(d);
		return fecha;
	}
	
	private void registrarDatos(){
		
		//datos
		int idCliente = Integer.parseInt(txtIdcliente.getText());
		String falla = txtFalla.getText();
		int idModelo = Integer.parseInt(txtIdmodelo.getText());
		int total = Integer.parseInt(txtTotal.getText());
		Date fechaEntrega = getFechaDevolver();
		Date fechaCapturada = getFechaSistema();
		
		String sql = "INSERT INTO serviciocarro(id_cliente, falla, id_modelo, total, fecha_entrega, fecha_capturada) VALUES (?, ?, ?, ?, ?, ?)";
		
		try{
			con = conex.getConexion();
			preparacion = con.prepareStatement(sql);
			preparacion.setInt(1, idCliente);
			preparacion.setString(2, falla);
			preparacion.setInt(3, idModelo);
			preparacion.setInt(4, total);
			preparacion.setDate(5, fechaEntrega);
			preparacion.setDate(6, fechaCapturada);
			
			preparacion.execute();
			
			JOptionPane.showMessageDialog(null, "Datos Almacenados", "Informacion", JOptionPane.INFORMATION_MESSAGE);
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
	}
	
	//actualizar stock del inventario
	public boolean ActualizarStock(int cant, int codigo){
		String sql = "UPDATE pieza SET stock = ? WHERE codigo = ?";
		try{
			con = conex.getConexion();
			preparacion = con.prepareStatement(sql);
			preparacion.setInt(1, cant);
			preparacion.setInt(2, codigo);
			preparacion.execute();
			return true;
		}catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
	}
	
	public void getStock(int codigo){
		String sql = "SELECT stock FROM pieza WHERE codigo = ?";
		try{
			con = conex.getConexion();
			preparacion = con.prepareStatement(sql);
			preparacion.setInt(1, codigo);
			resultado = preparacion.executeQuery();
			
			while(resultado.next()){
				stockActual = Integer.parseInt(resultado.getString("stock"));
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public void ActualizarStockDeLaTabla(){
		for(int i = 0; i < tblPieza.getRowCount(); i++){
			String cod = tblPieza.getValueAt(i, 0).toString();
			int cant = Integer.parseInt(tblPieza.getValueAt(i, 3).toString());
			
			//System.out.println("columna 0 [  codigo  ]---->" + tblPieza.getValueAt(i, 0).toString());
			//System.out.println("columna 2 [ cantidad ]---->" + tblPieza.getValueAt(i, 3).toString());
			
			buscar.BuscarProductosPieza(Integer.parseInt(cod), txtPieza, txtPresuni, txtStock);
			getStock(Integer.parseInt(cod));
			int actu = stockActual - cant;
			ActualizarStock(actu, Integer.parseInt(cod));
		}
	}
	
	//limpiar los campos
	private void limpiarCampos(){
		modelo = (DefaultTableModel) tblPieza.getModel();
		int fila = modelo.getRowCount();
		for(int i = 0; i < fila; i++){
			modelo.removeRow(0);
		}		
	}
	
	private void limpiartextos(){
		//fecha
		dateEntrega.setDate(null);
		
		//pieza
		txtCodigo.setText("");
		txtPieza.setText("");
		txtPresuni.setText("");
		txtStock.setText("");
		txtCanitdad.setText("");
		
		//cliente
		txtIdcliente.setText("");
		txtNomcliente.setText("");
		txtApellido.setText("");
		
		//carro
		txtModelo.setText("");
		txtIdmodelo.setText("");
		txtFalla.setText("");
	}
	
	void obtenerDatos(JTable tabla){
		
		int columnas = 0;
		
		modelo = (DefaultTableModel) tabla.getModel();
		modelo.setRowCount(0);
		
		try{
			con = conex.getConexion();
			preparacion = con.prepareStatement("SELECT id_venta, id_cliente, fecha_capturada, fecha_entrega FROM serviciocarro");//
			resultado = preparacion.executeQuery();
			
			resultadoMeta = resultado.getMetaData();
			columnas = resultadoMeta.getColumnCount();
			
			while(resultado.next()){
				Object [] fila = new Object[columnas];
				for(int indice = 0; indice < columnas; indice++){
					fila[indice] = resultado.getObject(indice + 1);
				}
				modelo.addRow(fila);
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
	}
	
	//pdf
	private void pdf(){
			try{
				String entreg = getFechaDevolver().toString().trim();
				String idCli = txtIdcliente.getText().trim();
				
				String concat = entreg + idCli;
				
				File directorio = new File("C:/Users/Desktop/directorio_pdfs");
				if(!directorio.exists()){
				}else{
					System.out.println("directorio creado");
				}
				
				FileOutputStream archivo;
				File file = new File("C:/Users/kreed/Desktop/pdf"+concat+".pdf");
				archivo = new FileOutputStream(file);
				Document doc = new Document();
				PdfWriter.getInstance(doc, archivo);
				doc.open();
				
				//parrafo y estilo
				Paragraph fecha = new Paragraph();
				//com.itextpdf.text.Font negrita = new com.itextpdf.text.Font(FontFamily.TIMES_ROMAN, 12, Font.BOLD, BaseColor.BLUE);
				fecha.add(Chunk.NEWLINE);
				java.util.Date date = new java.util.Date();
				fecha.add("Factura:"+concat+"\n"+"Fecha :" + new SimpleDateFormat("yyyy-MM-dd").format(date)+ "\n" + "\nFecha Entrega Carro: " + getFechaDevolver().toString());
				
				//tabla NOMBRE DE LA EMPRESA
				PdfPTable encabezado = new PdfPTable(3);
				encabezado.setWidthPercentage(100);
				encabezado.getDefaultCell().setBorder(0);
				float[] columnaEncabezado = new float[]{30f, 70f, 40f};
				encabezado.setWidths(columnaEncabezado);
				encabezado.setHorizontalAlignment(Element.ALIGN_CENTER);
				
				String nombreEmpresa = "Transmisiones automaticas".toUpperCase();
				String telefono = "+52 9999252135";
				String Localidad = "García Ginerés 247 31 x 34, C. 31 247, García Ginerés, 97070 Mérida, Yuc.";
				
				encabezado.addCell("");
				encabezado.addCell("Nombre: " + nombreEmpresa + "\nTelefono: " + telefono + "\nLocalidad: " + Localidad);
				encabezado.addCell(fecha);
				doc.add(encabezado);
				
				//parrafo para clientes
				Paragraph cliente = new Paragraph();
				cliente.add(Chunk.NEWLINE);
				cliente.add("Datos de los clientes \n\n");
				doc.add(cliente);
				
				PdfPTable tablecliente = new PdfPTable(3);
				tablecliente.setWidthPercentage(100);
				tablecliente.getDefaultCell().setBorder(0);
				float[] columna_c1 = new float[]{50f, 30f, 40f};
				tablecliente.setWidths(columna_c1);
				tablecliente.setHorizontalAlignment(Element.ALIGN_LEFT);
				PdfPCell cl1 = new PdfPCell(new Phrase("Nombre"));
				PdfPCell cl2 = new PdfPCell(new Phrase("Telefono"));
				PdfPCell cl3 = new PdfPCell(new Phrase("Apellido"));
				cl1.setBorder(0);
				cl2.setBorder(0);
				cl3.setBorder(0);
				cl1.setBackgroundColor(BaseColor.LIGHT_GRAY);
				cl2.setBackgroundColor(BaseColor.LIGHT_GRAY);
				cl3.setBackgroundColor(BaseColor.LIGHT_GRAY);
				tablecliente.addCell(cl1);
				tablecliente.addCell(cl2);
				tablecliente.addCell(cl3);
				tablecliente.addCell(txtNomcliente.getText());
				tablecliente.addCell(txtTelefono.getText());
				tablecliente.addCell(txtApellido.getText());
				doc.add(tablecliente);
				
				Paragraph producto = new Paragraph();
				producto.add(Chunk.NEWLINE);
				producto.setAlignment(Element.ALIGN_CENTER);
				producto.add("Lista De los productos \n\n");
				doc.add(producto);
				
				//producto
				PdfPTable tableProducto = new PdfPTable(4);
				tableProducto.setWidthPercentage(100);
				tableProducto.getDefaultCell().setBorder(0);
				float[] columna_Pro = new float[]{20f, 50f, 30f, 40f};
				tableProducto.setWidths(columna_Pro);
				tableProducto.setHorizontalAlignment(Element.ALIGN_LEFT);
				PdfPCell pro1 = new PdfPCell(new Phrase("Pieza"));
				PdfPCell pro2 = new PdfPCell(new Phrase("Precio Unitario"));
				PdfPCell pro3 = new PdfPCell(new Phrase("Cantidad"));
				PdfPCell pro4 = new PdfPCell(new Phrase("total"));
				pro1.setBorder(0);
				pro2.setBorder(0);
				pro3.setBorder(0);
				pro4.setBorder(0);
				pro1.setBackgroundColor(BaseColor.LIGHT_GRAY);
				pro2.setBackgroundColor(BaseColor.LIGHT_GRAY);
				pro3.setBackgroundColor(BaseColor.LIGHT_GRAY);
				pro4.setBackgroundColor(BaseColor.LIGHT_GRAY);
				tableProducto.addCell(pro1);
				tableProducto.addCell(pro2);
				tableProducto.addCell(pro3);
				tableProducto.addCell(pro4);
				for(int i = 0; i < tblPieza.getRowCount(); i++){
					String Pieza = tblPieza.getValueAt(i, 1).toString();
					String PrecioUni = tblPieza.getValueAt(i, 2).toString();
					String cantidad = tblPieza.getValueAt(i, 3).toString();
					String total = tblPieza.getValueAt(i, 4).toString();
					tableProducto.addCell(Pieza);
					tableProducto.addCell(PrecioUni);
					tableProducto.addCell(cantidad);
					tableProducto.addCell(total);
				}
				doc.add(tableProducto);
				
				Paragraph info = new Paragraph();
				info.add(Chunk.NEWLINE);
				info.add("Total a pagar " + txtTotal.getText());
				info.setAlignment(Element.ALIGN_LEFT);
				doc.add(info);
				
				Paragraph falla = new Paragraph();
				falla.add(Chunk.NEWLINE);
				falla.add("Falla del Carro\n"+txtFalla.getText());
				falla.setAlignment(Element.ALIGN_LEFT);
				doc.add(falla);
				
				Paragraph firma = new Paragraph();
				firma.add(Chunk.NEWLINE);
				firma.add("\n\n\nCancelacion y Firma \n\n");
				firma.add("-------------------------");
				firma.setAlignment(Element.ALIGN_CENTER);
				doc.add(firma);

				Paragraph mensaje = new Paragraph();
				mensaje.add(Chunk.NEWLINE);
				mensaje.add("Gracias por su preferencia");
				mensaje.setAlignment(Element.ALIGN_CENTER);
				doc.add(mensaje);
				
				doc.close();
				archivo.close();
				Desktop.getDesktop().open(file);
			}catch(Exception ex ){
				System.out.println(ex.toString());
			}
		}

}
