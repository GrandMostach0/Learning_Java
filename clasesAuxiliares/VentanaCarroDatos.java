package clasesAuxiliares;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.ResultSetMetaData;

public class VentanaCarroDatos {
	
	DefaultTableModel modelo;
	
	//requerimientos de la base de datos
	Conexion conex = new Conexion();
	Connection con;
	ResultSet resultado;
	PreparedStatement preparacion;
	ResultSetMetaData rstmd;
	
	public void agregarCarro(String Modelo, JTable model){
		
		String sql = "INSERT INTO carro(ModeloCarro) VALUES (?)";
		
		try{
			con = conex.getConexion();
			preparacion = con.prepareStatement(sql);
			preparacion.setString(1, Modelo);
			
			preparacion.executeUpdate();
			
			JOptionPane.showMessageDialog(null, "Datos Almacenados", "Informacion", JOptionPane.INFORMATION_MESSAGE);
			mostrarDatosCarro(model);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void mostrarDatosCarro(JTable modelo){
		DefaultTableModel mode = (DefaultTableModel) modelo.getModel();
		mode.setRowCount(0);
		
		java.sql.ResultSetMetaData rstmd;
		int columnas;
		
		String sql2 = "SELECT id_Modelo, ModeloCarro FROM carro";
		
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
	}
	
	//evento de la tabla carro
	public void IdCarro(JTable model, JTextField id, JTextField  Modelo){
		
			try{
				int fila = model.getSelectedRow();
				int id_Modelo = Integer.parseInt(model.getValueAt(fila, 0).toString());
				
				con = conex.getConexion();
				preparacion = con.prepareStatement("SELECT id_Modelo, ModeloCarro FROM carro WHERE id_Modelo = ?");
				preparacion.setInt(1, id_Modelo);
				resultado = preparacion.executeQuery();
				
				while(resultado.next()){
					id.setText(String.valueOf(id_Modelo));
					Modelo.setText(resultado.getString("ModeloCarro"));
				}
				
			}catch(Exception ex){
				
			}
		}
		
		//metodo eliminar
		public void eliminarModelo(String id, JTable model){
			int id_carro = Integer.parseInt(id);
			
			String sql3 = "DELETE FROM carro WHERE id_Modelo = ?";
			
			try{
				con = conex.getConexion();
				preparacion = con.prepareStatement(sql3);
				preparacion.setInt(1, id_carro);
				preparacion.executeUpdate();
				JOptionPane.showMessageDialog(null, "Registros Eliminado", "Informacion", JOptionPane.INFORMATION_MESSAGE);
				mostrarDatosCarro(model);
			}catch(Exception ex){
				ex.printStackTrace();
			}
			
		}
		
	}
