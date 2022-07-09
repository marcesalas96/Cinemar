package Version_2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.Scanner;

public class Pelicula {
	private int sala;
	private String titulo;
	private String productor;
	private Time duracion;
	private String idioma;
	private Time horario;
	private String genero;
	private String tipo;
	
	private final String JDBC_DRIVER= "com.mysql.cj.jdbc.Driver";
	private final String DB_URL= "jdbc:mysql://localhost:3306/cine";
	private final String USER= "root";
	private final String PASS= "Dan41019755Dan";
	private Connection conn=null;
	
	public Pelicula(int nroSala) {
		this.sala=nroSala;
		this.titulo="";
		this.productor="";
		this.duracion=new Time(0);
		this.idioma="";
		this.horario=new Time(0);
		this.genero="";
		this.tipo="";
	}
	
	public void cargarDatosPelicula() { // SE CARGA LOS ATRIBUTOS DE LA CLASE PELICULA
		Scanner ingresoS=new Scanner(System.in);
		Scanner ingresoN=new Scanner(System.in);
		
		System.out.println("Ingrese el titulo: ");
		this.titulo=ingresoS.nextLine();
		System.out.println("Ingrese el nombre del productor: ");
		this.productor=ingresoS.nextLine();
		
		System.out.println("Ingreso de la duracion de la pelicula ");
		int h,m;
		System.out.println("Hora: ");
		h=ingresoN.nextInt();
		System.out.println("Minutos: ");
		m=ingresoN.nextInt();
		this.duracion=new Time(0);
		this.duracion.setHours(h);
		this.duracion.setMinutes(m);
		this.duracion.setSeconds(00);
		
		System.out.println("Ingrese el idioma: ");
		this.idioma=ingresoS.nextLine();
		
		System.out.println("Ingreso del horario de la pelicula ");
		this.horario=new Time(0);
		System.out.println("Hora: ");
		h=ingresoN.nextInt();
		System.out.println("Minutos: ");
		m=ingresoN.nextInt();
		horario.setHours(h);
		horario.setMinutes(m);
		horario.setSeconds(00);
		
		System.out.println("Ingrese el genero: ");
		this.genero=ingresoS.nextLine();
		System.out.println("Ingrese el tipo (2D o 3D): ");
		this.tipo=ingresoS.nextLine();
	}
	
	public void generarPelicula() { // CON LOS DATOS PREVIAMENTE CARGADOS, SE CARGA LA PELICULA A LA BASE DE DATOS
		PreparedStatement stmt=null;
		
		try {
			Class.forName(JDBC_DRIVER);
			System.out.println("Connecting to database...");
			conn=DriverManager.getConnection(DB_URL,USER,PASS);
			
			String query="INSERT INTO pelicula (id, titulo, productor, duracion, idioma, horario, genero, tipo) VALUES(?,?,?,?,?,?,?,?);";
			
			stmt = conn.prepareStatement(query);
			
			stmt.setInt(1, this.sala);
			stmt.setString(2, this.titulo);
			stmt.setString(3, this.productor);
			stmt.setTime(4, this.duracion);
			stmt.setString(5, this.idioma);
			stmt.setTime(6, this.horario);
			stmt.setString(7, this.genero);
			stmt.setString(8, this.tipo);
			
			stmt.executeUpdate();
			
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
		System.out.println("Se ha guardado la pelicula.");
	}
	
	public void actualizarDatosBaseDatos() { // SE MODIFICAN LOS DATOS DE LA PELICULA ( DE LA BASE DE DATOS)
		this.cargarDatosPelicula();
		PreparedStatement stmt=null;
		
		try {
			String tit,prod,dur,idi,hor,gen,tip;
			tit="titulo="+this.titulo;
			prod="productor="+this.productor;
			dur="duracion="+this.duracion;
			idi="idioma="+this.idioma;
			hor="horario="+this.horario;
			gen="genero="+this.genero;
			tip="tipo="+this.tipo;
			
			String query="update Pelicula set "+tit+", "+prod+", "+dur+", "+idi+", "+hor+", "+gen+", "+tip+" where id="+this.sala;
			stmt = conn.prepareStatement(query);
			stmt.executeUpdate();
			
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
		System.out.println("Se ha guardado la pelicula.");
	}
	
	public void consultarDatosBD() { // LOS ATRIBUTOS DE LA CLASE SE CARGAN CON LOS DATOS DE LA BASE DE DATOS (CON EL NRO DE SALA)
		Statement stmt=null;
		
		try {
			Class.forName(JDBC_DRIVER);
			this.conn=DriverManager.getConnection(DB_URL,USER,PASS);
			stmt= this.conn.createStatement();
			
			String sql="Select titulo,productor,duracion,idioma,horario,genero,tipo from Pelicula where id="+this.sala;
			ResultSet rs= stmt.executeQuery(sql);
				
			
			this.titulo = rs.getString("titulo");
			this.productor = rs.getString("productor");
			this.duracion = rs.getTime("duracion");
			this.idioma = rs.getString("idioma");
			this.horario = rs.getTime("horario");
			this.genero = rs.getString("genero");
			this.tipo = rs.getString("tipo");
				
			
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
	
	public String toString() { // DEVUELVE UN STRING CON LA INFORMACION DE LA PELICULA
		String cad="";
		cad+="titulo: "+this.titulo+"\n";
		cad+="productor: "+this.productor+"\n";
		cad+="duracion: "+this.duracion+"\n";
		cad+="idioma: "+this.idioma+"\n";
		cad+="horario: "+this.horario+"\n";
		cad+="genero: "+this.genero+"\n";
		cad+="tipo: "+this.tipo+"\n";
		
		return cad;
	}
}
