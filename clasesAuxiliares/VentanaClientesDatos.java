package clasesAuxiliares;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.ResultSetMetaData;

public class VentanaClientesDatos {
	
	DefaultTableModel modelo;
	
	//requerimientos de la base de datos
	Conexion conex = new Conexion();
	Connection con;
	ResultSet resultado;
	PreparedStatement preparacion;
	ResultSetMetaData rstmd;
	
	private int setIdCliente = 0;
	
	//agregarCliente
	public void agregarCliente(String nombre, String apellido, String telefono, JTable model){
		
		//INSERT INTO `clientes`(`id_Cliente`, `NombreCliente`, `apellido_cliente`, `telefono`) 
		//VALUES ('[value-1]','[value-2]','[value-3]','[value-4]')
		
		String sql = "INSERT INTO clientes(NombreCliente, apellido_cliente, telefono) VALUES (?, ?, ?)";
		
		int tel = Integer.parseInt(telefono);
		
		try{
			
			con = conex.getConexion();
			preparacion = con.prepareStatement(sql);
			
			//agregando en la tabla de base de datos
			preparacion.setString(1, nombre);
			preparacion.setString(2, apellido);
			preparacion.setInt(3, tel);
			
			preparacion.executeUpdate();
					
			JOptionPane.showMessageDialog(null, "Datos Almacenados", "Informacion", JOptionPane.INFORMATION_MESSAGE);
			mostrarDatosClientes(model);
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	//mostrar los datos
	public void mostrarDatosClientes(JTable modelo){
		DefaultTableModel mode = (DefaultTableModel) modelo.getModel();
		mode.setRowCount(0);
		
		java.sql.ResultSetMetaData rstmd;
		int columnas;
		
		String sql2 = "SELECT id_Cliente, NombreCliente, apellido_cliente, telefono FROM clientes";
		
		try{
			con = conex.getConexion();
			preparacion = con.prepareStatement(sql2);
			resultado = preparacion.executeQuery();
			
			rstmd = resultado.getMetaData();
			columnas = rstmd.getColumnCount();
			
			while(resultado.next()){
				Object[] fila = new Object[columnas];
				for(int indice = 0; indice < columnas; indice++){
					fila[indice] = resultado.getObject(indice+1);
				}
				mode.addRow(fila);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
	}//metodo mostrar
	
	//evento de la tabla cliente
	public void IdCliente(JTable model, JTextField id, JTextField  Nombre, JTextField telefono, JTextField apellido){
		try{
			int fila = model.getSelectedRow();
			int id_client = Integer.parseInt(model.getValueAt(fila, 0).toString());
			
			con = conex.getConexion();
			preparacion = con.prepareStatement("SELECT id_Cliente, NombreCliente, apellido_cliente, telefono FROM clientes WHERE id_Cliente = ?");
			preparacion.setInt(1, id_client);
			resultado = preparacion.executeQuery();
			
			while(resultado.next()){
				id.setText(String.valueOf(id_client));
				Nombre.setText(resultado.getString("NombreCliente"));
				telefono.setText(resultado.getString("telefono"));
				apellido.setText(resultado.getString("apellido_cliente"));
			}
			
		}catch(Exception ex){
			
		}
	}
	
	//metodo eliminar
	public void eliminarCliente(JTextField id, JTable model){
		
		int id_cliente = Integer.parseInt(id.getText());
		
		String sql3 = "DELETE FROM clientes WHERE id_Cliente = ?";
		
		try{
			con = conex.getConexion();
			preparacion = con.prepareStatement(sql3);
			
			preparacion.setInt(1, id_cliente);
			preparacion.executeUpdate();
			
			JOptionPane.showMessageDialog(null, "Registros Eliminado", "Informacion", JOptionPane.INFORMATION_MESSAGE);
			mostrarDatosClientes(model);
		}catch(Exception ex){
			System.out.println(ex.toString());
		}
		
	}
	
}
