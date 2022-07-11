
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Scanner;

public class Administracion {
	private final String JDBC_DRIVER= "com.mysql.cj.jdbc.Driver";
	private final String DB_URL= "jdbc:mysql://localhost:3306/cine";
	private final String USER= "root";
	private final String PASS= "Dan41019755Dan";
	private Connection conn=null;
	
	public void crearSala() {
		Scanner ingreso=new Scanner(System.in);
		int nroS,cap;
		System.out.println("Ingrese el numero de la sala: ");
		nroS=ingreso.nextInt();
		Sala sala=new Sala(nroS);
		System.out.println("Ingrese el numero de la sala: ");
		cap=ingreso.nextInt();
		sala.setCapacidad(cap);
		sala.generarSala();
		sala.generarButacas();
		
		Pelicula pelicula=new Pelicula(nroS);
		pelicula.cargarDatosPelicula();
		pelicula.generarPelicula();
	}
	
	public void modificarSala() {
		Scanner ingreso=new Scanner(System.in);
		System.out.println("Ingrese el numero de la sala a actualizar: ");
		int nro;
		nro=ingreso.nextInt();
		Sala sala=new Sala(nro);
		sala.actualizarSala();
	}
	public void modificarSalaButacas() {
		Scanner ingreso=new Scanner(System.in);
		System.out.println("Ingrese el numero de la sala (para reestablecer la disponibilidad de las butacas): ");
		int nro;
		nro=ingreso.nextInt();
		Sala sala=new Sala(nro);
		sala.reestablecerDisponibilidadButacas();
	}
	public void actualizarDescuentos() {
		Descuento des=new Descuento();
		des.actualizarDescuentosBD();
	}
	public void actualizarPrecioEntrada() {
		Descuento des=new Descuento();
		des.actualizarPrecioBD();
	}
	
	public void verReservasClientes() {
		ArrayList<String> lista=new ArrayList<>();
		Statement stmt=null;
		
		try {
			Class.forName(JDBC_DRIVER);
			this.conn=DriverManager.getConnection(DB_URL,USER,PASS);
			stmt= this.conn.createStatement();
			
			String sql="SELECT nombreUsuario FROM Cliente";
			ResultSet rs= stmt.executeQuery(sql);
			
			while(rs.next()) {
				
				String nick= rs.getString("nombreUsuario");
				lista.add(nick);
				
				 Historial historial=new Historial(nick);
				 historial.mostrarReservas(); 
				 
				
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
		
		for(int i=0;i<lista.size();i++) {
			Historial h=new Historial(lista.get(i));
			h.mostrarReservas();
		}
	}

	public void mostrarResvasCliente() {
		ArrayList<String> nicks= new ArrayList<>();
		
		Statement stmt=null;
		try {
			
			Class.forName(JDBC_DRIVER);
			this.conn=DriverManager.getConnection(DB_URL,USER,PASS);
			
			stmt= this.conn.createStatement();
			String sql="select  nombreUsuario,nombre, apellido from Cliente join Usuario on Cliente.correo=Usuario.correo;";
			ResultSet rs= stmt.executeQuery(sql);
			int op=0;
			while(rs.next()) {
				op++;
				String nick= rs.getString("nombreUsuario");
				nicks.add(nick);
				String nombre= rs.getString("nombre");
				String apellido= rs.getString("apellido");
				
				System.out.println(op+"- "+nombre+" "+apellido+" "+nick);
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
		System.out.println("\n Elija una opcion: ");
		Scanner ingreso=new Scanner(System.in);
		int pos=ingreso.nextInt();
		
		
		Historial historial=new Historial(nicks.get(pos-1));
		historial.mostrarReservas();
		
	}
	
}