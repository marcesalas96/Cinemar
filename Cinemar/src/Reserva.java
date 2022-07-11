import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.time.*;


public class Reserva {
	private LocalDate fechaDeReserva;
	private LocalTime horaDeReserva;
	private Time horaDePelicula;
	private String fechaDePelicula;
	private int nroSala;
	private int posButaca;
	private String nombrePelicula;
	private double precioPeliculaTotal;
	private String nombreUsuario;
	
	
	private final String JDBC_DRIVER= "com.mysql.cj.jdbc.Driver";
	private final String DB_URL= "jdbc:mysql://localhost:3306/cine";
	private final String USER= "root";
	private final String PASS= "marce282";
	private Connection conn=null;
	private int id;
	
	public Reserva(Time horaDePelicula, int nroSala, int posButaca, String nombrePelicula, double precioPelicula, String nombreUsuario) {
		this.fechaDeReserva = LocalDate.now();
		this.horaDeReserva = LocalTime.now();
		this.horaDePelicula = horaDePelicula;
		this.nroSala = nroSala;
		this.posButaca = posButaca;
		this.nombrePelicula = nombrePelicula;
		this.precioPeliculaTotal = precioPelicula;
		this.nombreUsuario=nombreUsuario;
		this.id=0;
		
	}
		
	
	public Reserva(String nick,int id) {
		this.fechaDeReserva = LocalDate.now();
		this.horaDeReserva = LocalTime.now();
		this.horaDePelicula = new Time(0);
		this.nroSala = 0;
		this.posButaca = 0;
		this.nombrePelicula = "";
		this.precioPeliculaTotal = 0;
		this.nombreUsuario=nick;
		this.id=id;
		
	}
	
	public void generarReservaBD() { // CARGA LA RESERVA A LA BASE DE DATOS
		PreparedStatement stmt=null;
		
		try {
			Class.forName(JDBC_DRIVER);
			conn=DriverManager.getConnection(DB_URL,USER,PASS);
			
			String query="insert into Reserva (id,fechaReserva,horaReserva,horaPelicula, fechaPelicula,nroSala,posButaca,nombrePelicula,precioTotal,nombreUsuario) (?,?,?,?,?,?,?,?,?);";
			stmt = conn.prepareStatement(query);
			stmt.setString(1, null);
			stmt.setString(2, this.fechaDeReserva.toString());
			stmt.setString(3, this.horaDeReserva.toString());
			stmt.setTime(4, this.horaDePelicula);
			stmt.setInt(5,this.nroSala);
			stmt.setInt(6, this.posButaca);
			stmt.setString(7, this.nombrePelicula);
			stmt.setDouble(8, this.precioPeliculaTotal);
			
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
	public void actualizarReservaBD() {
		Scanner input = new Scanner(System.in);
		
		System.out.println("Ingresa la nueva fecha en la que vas a ver la pelicula: (dia-mes-año)");
		this.fechaDePelicula = input.next();
		
		
	}
	
	public void actualizarDatosBaseDatos(int idReserva) { // SE MODIFICAN LOS DATOS DE LA RESERVA ( DE LA BASE DE DATOS)
		this.actualizarReservaBD();
		PreparedStatement stmt=null;
		
		try {
			String fPel;
			fPel="fechaPelicula="+this.fechaDePelicula;
			
			String query="update Pelicula set "+fPel+", "+" where id="+idReserva;
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
	private void consultaBD() {
		Statement stmt=null;
		
		try {
			Class.forName(JDBC_DRIVER);
			this.conn=DriverManager.getConnection(DB_URL,USER,PASS);
			stmt= this.conn.createStatement();
			
			String sql="Select fechaReserva,horaReserva,horaPelicula,fechaPelicula,nroSala,posButaca,nombrePelicula,precioTotal from Pelicula where idReservas="+this.id;
			ResultSet rs= stmt.executeQuery(sql);
			rs.next();
			
			this.fechaDeReserva = LocalDate.parse(rs.getString("fechaReserva"));
			this.horaDeReserva = LocalTime.parse(rs.getString("horaReserva"));
			this.horaDePelicula = rs.getTime("horaPelicula");
			this.fechaDePelicula = rs.getString("fechaPelicula");
			this.nroSala = rs.getInt("nroSala");
			this.posButaca = rs.getInt("posButaca");
			this.nombrePelicula = rs.getString("nombrePelicula");
			this.precioPeliculaTotal= rs.getDouble("precioTotal");
			
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
