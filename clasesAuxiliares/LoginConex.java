package clasesAuxiliares;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ventanas.Login;

public class LoginConex {
	Connection con;
	PreparedStatement orden;
	ResultSet resultado;
	
	//instancia de la clase
	Conexion conex = new Conexion();
	
	/*public Login log(String usuario, String pass){
		Login lo = new Login();
		String sql2 = "SELECT * FROM usuarios WHERE usuario = ? AND pass = ?";
		try{
			con = conex.getConexion();
			orden = con.prepareStatement(sql2);
			orden.setString(1, usuario);
			orden.setString(2, pass);
			resultado = orden.executeQuery();
			if(resultado.next()){
				lo.setUsuario(resultado.getString("usuarios"));
				lo.setPass(resultado.getString("pass"));
			}//fin del if
		}catch(SQLException e){
			System.out.println(e.getMessage()+"Aqui hay error en LoginConex");
		}
		return lo;
	}//fin del metodo log
	*/
	
}
