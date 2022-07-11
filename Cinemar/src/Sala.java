

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class Sala {
	private int nro_Sala;
	private int capacidad;
	private Pelicula pelicula;
	private ArrayList<Butaca> butacas;
	
	private final String JDBC_DRIVER= "com.mysql.cj.jdbc.Driver";
	private final String DB_URL= "jdbc:mysql://localhost:3306/cine";
	private final String USER= "root";
	private final String PASS= "Dan41019755Dan";
	private Connection conn=null;
	
	public Sala(int n) { // CONTRUCTOR
		this.nro_Sala=n;
		this.capacidad=0;
		this.pelicula=new Pelicula(this.nro_Sala);
		this.butacas=new ArrayList<>();
	}
	
	public int getNro_Sala() {
		return nro_Sala;
	}
	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}
	public int getCapacidad() {
		return capacidad;
	}
	public void generarSala() { // CARGA LA TABLA SALA DE LA BASE DE DATOS
		PreparedStatement stmt=null;
		
		try {
			Class.forName(JDBC_DRIVER);
			System.out.println("Connecting to database...");
			conn=DriverManager.getConnection(DB_URL,USER,PASS);
					
			System.out.println("Creating statement...");
			
			String query="insert into Sala (nro_Sala,capacidad) values (?,?);";
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, this.nro_Sala);
			stmt.setInt(2, this.capacidad);
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

        this.pelicula.cargarDatosPelicula();
        this.pelicula.generarPelicula();
	}
	public void actualizarSala() { 
		this.pelicula.actualizarDatosBaseDatos();
	}
	public void generarButacas() { // CARGA LAS BUTACAS EN LA BASE DE DATOS
		Butaca butaca;
		for(int i=0;i<this.capacidad;i++) {
			butaca=new Butaca(this.nro_Sala,i+1);
			butaca.generarButacaBD();
			this.butacas.add(butaca);
		}
	}
	public void consultarButacas() { // TRAE DE LA BASE DE DATOS LAS BUTACAS Y LAS CARAGA EN EL ARRAY DE BUTACAS
		Butaca butaca;
		Statement stmt=null;
		
		try {
			Class.forName(JDBC_DRIVER);
			this.conn=DriverManager.getConnection(DB_URL,USER,PASS);
			
			stmt= this.conn.createStatement();
			
			String sql="Select pos, ocupado from Butaca where nro_Sala="+this.nro_Sala+" order by pos;";
			ResultSet rs= stmt.executeQuery(sql);
			
			while(rs.next()) {
				int posicion = rs.getInt("pos");
				boolean ocupado= rs.getBoolean("ocupado");
				butaca=new Butaca(this.nro_Sala,posicion,ocupado);
				this.butacas.add(butaca);
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
	public void reestablecerDisponibilidadButacas() { // MODIFICA TODAS LAS BUTACAS A DESOCUPADAS (GUARDANDO LOS CAMBIOS EN LA BASE DE DATOS)
		PreparedStatement stmt=null;
		try {
			String ocup;
			ocup="ocupado=false";
			
			String query="update Pelicula set "+ocup+" where nro_Sala="+this.nro_Sala;
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
	}
	public void mostrarButacasDisponibles() { // MUESTRA POR CONSOLA LAS BUTACAS DESOCUPADAS
		System.out.println("Butacas Disponibles ");
		for(int i=0;i<this.butacas.size();i++) {
			if(this.butacas.get(i).getOcupado()==false) {
				System.out.println("Posicion: "+this.butacas.get(i).getPos());
			}
		}
	}
}
