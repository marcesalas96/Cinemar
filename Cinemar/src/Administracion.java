package Version_2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
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
		
		Pelicula pelicula=new Pelicula(nroS);
		pelicula.cargarDatosPelicula();
		pelicula.generarPelicula();
	}
	
	public void ModificarSala() {
		Scanner ingreso=new Scanner(System.in);
		System.out.println("Ingrese el numero de la sala a actualizar: ");
		int nro,cap;
		nro=ingreso.nextInt();
		Sala sala=new Sala(nro);
		sala.actualizarSala();
	}
	public void ModificarSalaButacas() {
		Scanner ingreso=new Scanner(System.in);
		System.out.println("Ingrese el numero de la sala a actualizar: ");
		int nro,cap;
		nro=ingreso.nextInt();
		Sala sala=new Sala(nro);
		sala.reestablecerDisponibilidadButacas();
	}
	/*************************************************************** EN PROCESO ***************************************************************/
	/*
	public void verReservasClientes() {// DEPENDERA DEL METODO "MOSTRAR RESERVAS CLIENTE"
		Statement stmt=null;
		
		try {
			Class.forName(JDBC_DRIVER);
			
			System.out.println("Connecting to database...");
			this.conn=DriverManager.getConnection(DB_URL,USER,PASS);
					
			System.out.println("Creating statement...");
			stmt= this.conn.createStatement();
			
			String sql="SELECT Name, Code, Population FROM country";
			ResultSet rs= stmt.executeQuery(sql);
			
			while(rs.next()) {
				
				int population = rs.getInt("Population");
				String Name= rs.getString("Name");
				String CountryCode= rs.getString("Code");
				
				System.out.println("Pai: "+Name);
				System.out.println(", Codigo Pais: "+CountryCode);
				System.out.println(", Poblacion: "+population+" habitantes");
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
		System.out.println("Goodbye");
	}
	public void mostrarResvasCliente(int id) {// REVISAR COMO SERA LA NUEVA TABLA DE RELACIONES
		Statement stmt=null;
		try {
			
			Class.forName(JDBC_DRIVER);
			
			System.out.println("Connecting to database...");
			this.conn=DriverManager.getConnection(DB_URL,USER,PASS);
					
			System.out.println("Creating statement...");
			stmt= this.conn.createStatement();
			String sql="SELECT * FROM country";
			ResultSet rs= stmt.executeQuery(sql);
			
			while(rs.next()) {
				
				int population = rs.getInt("Population");
				String Name= rs.getString("Name");
				String CountryCode= rs.getString("Code");
				
				System.out.println("Pai: "+Name);
				System.out.println(", Codigo Pais: "+CountryCode);
				System.out.println(", Poblacion: "+population+" habitantes");
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
		System.out.println("Goodbye");
	}
	*/
	/******************************************************************************************************************************************/
}
