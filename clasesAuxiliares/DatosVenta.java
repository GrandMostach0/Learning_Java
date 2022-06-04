package clasesAuxiliares;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import javax.swing.JTextField;

public class DatosVenta {
	
	Conexion con = new Conexion();
	Connection conex;
	ResultSet resultado;
	PreparedStatement preparacion;
	
	public void BuscarProductosPieza(int cod, JTextField pieza, JTextField presUni, JTextField stock){
		
		String sql = "SELECT NombreProducto, precio, stock FROM pieza WHERE codigo = ?";
		try{
			conex = con.getConexion();
			preparacion = conex.prepareStatement(sql);
			preparacion.setInt(1, cod);
			resultado = preparacion.executeQuery();
			
			if(resultado.next()){
				pieza.setText(resultado.getString("NombreProducto"));
				presUni.setText(resultado.getString("precio"));
				stock.setText(resultado.getString("stock"));
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
	}
	
	public void BuscarCliente(int idCliente, JTextField nombre, JTextField apellido, JTextField telefono){
		
		String sql = "SELECT NombreCliente, apellido_cliente, telefono FROM clientes WHERE id_Cliente = ?";
		try{
			conex = con.getConexion();
			preparacion = conex.prepareStatement(sql);
			preparacion.setInt(1, idCliente);
			resultado = preparacion.executeQuery();
			
			if(resultado.next()){
				nombre.setText(resultado.getString("NombreCliente"));
				apellido.setText(resultado.getString("apellido_cliente"));
				telefono.setText(resultado.getString("telefono"));
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
	}
	
	public void BuscarModelo(int id_modelo, JTextField modelo){
		
		String sql = "SELECT ModeloCarro FROM carro WHERE id_Modelo = ?";
		try{
			conex = con.getConexion();
			preparacion = conex.prepareStatement(sql);
			preparacion.setInt(1, id_modelo);
			resultado = preparacion.executeQuery();
			
			if(resultado.next()){
				modelo.setText(resultado.getString("ModeloCarro"));
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
	}

	
}
