package clasesAuxiliares;

import java.sql.*;

public class Conexion {
	
	//datos necesarios
	static final String drive = "com.mysql.jdbc.Driver";
	static final String base = "jdbc:mysql://localhost/sistemaprueba";
	private static final String usuario = "prueba";
	private static final String pass = "123456789";
	
	Connection con;
	
	public Connection getConexion(){
		try{
			Class.forName(drive);
			con = DriverManager.getConnection(base, usuario, pass);
			return con;
		}catch(SQLException | ClassNotFoundException ex){
			System.out.println(ex.toString() + "Hay error aqui en Conexion");
		}
		return null;
	}
	
}
