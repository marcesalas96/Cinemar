import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Historial { 
	private String nombreUsuario;
	private ArrayList<Reserva> reservas;
	
	public Historial(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
		this.reservas = new ArrayList<Reserva>();
	}
	private final String JDBC_DRIVER= "com.mysql.cj.jdbc.Driver";
	private final String DB_URL= "jdbc:mysql://localhost:3306/cine";
	private final String USER= "root";
	private final String PASS= "Dan41019755Dan";
	private Connection conn=null;
	
	
	public void mostrarReservas() {
		;
		Statement stmt=null;
		
		try {
			Class.forName(JDBC_DRIVER);
			this.conn=DriverManager.getConnection(DB_URL,USER,PASS);
			
			stmt= this.conn.createStatement();
			
			String sql="Select * from Reservas where correo="+this.nombreUsuario+" order by idReservas DESC;";
			ResultSet rs= stmt.executeQuery(sql);
			
			while(rs.next()) {
				System.out.println("ID: " + rs.getString(1) + "\n" + "Fecha de reserva: "+ rs.getString(2)+"\n" + "Hora de reserva: "+ rs.getString(3) + "\n" + "Hora de pelicula: " + rs.getTime(4) + "\n" + "Numero de sala: " + rs.getInt(5) + "\n" + "Butaca seleccionada: " + rs.getInt(6) + "\n" + "Pelicula: " + rs.getString(7)+ "\n" + "Precio: " + rs.getDouble(8) + "\n" + "Nombre de usuario: " + rs.getString(9));
			}
			
			rs.close();
			stmt.close();
			conn.close();
		}catch(SQLException se) {
			se.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(stmt!=null)
					stmt.close();
			}catch(SQLException se2) {
			}
			try {
				if(conn!=null)
					conn.close();
			}catch(SQLException se) {
				se.printStackTrace();
				}
			}
		}
	}


