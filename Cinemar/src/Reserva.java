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
	private String nombreCliente;
	private String apellidoCliente;
	private double precioPeliculaTotal;
	private String emailCliente;
	private int idReserva;
	
	
	private final String JDBC_DRIVER= "com.mysql.cj.jdbc.Driver";
	private final String DB_URL= "jdbc:mysql://localhost:3306/cine";
	private final String USER= "root";
	private final String PASS= "marce282";
	private Connection conn=null;
	
	public Reserva(Time horaDePelicula, int nroSala, int posButaca, String nombrePelicula, String nombreCliente, String apellidoCliente, double precioPelicula, String email) {
		this.fechaDeReserva = LocalDate.now();
		this.horaDeReserva = LocalTime.now();
		this.horaDePelicula = horaDePelicula;
		this.nroSala = nroSala;
		this.posButaca = posButaca;
		this.nombrePelicula = nombrePelicula;
		this.nombreCliente = nombreCliente;
		this.apellidoCliente = apellidoCliente;
		this.precioPeliculaTotal = precioPeliculaTotal;
		this.emailCliente = emailCliente;
	
	}
	public Reserva(int idReserva) {
		this.fechaDeReserva = LocalDate.now();
		this.horaDeReserva = LocalTime.now();
		this.horaDePelicula = new Time(0);
		this.nroSala = 0;
		this.posButaca = 0;
		this.nombrePelicula = "";
		this.nombreCliente = "";
		this.apellidoCliente = "";
		this.precioPeliculaTotal = 0;
		this.emailCliente = "";
		this.idReserva = idReserva;
		
	}
	public void generarReservaBD() { // CARGA LA RESERVA A LA BASE DE DATOS
		PreparedStatement stmt=null;
		
		try {
			Class.forName(JDBC_DRIVER);
			conn=DriverManager.getConnection(DB_URL,USER,PASS);
			
			String query="insert into Reserva (id,fechaReserva,horaReserva,horaPelicula, fechaPelicula,nroSala,posButaca,nombrePelicula,nombreCliente,apellidoCliente,precioTotal) (?,?,?,?,?,?,?,?,?,?);";
			stmt = conn.prepareStatement(query);
			stmt.setString(1, null);
			stmt.setString(2, this.fechaDeReserva.toString());
			stmt.setString(3, this.horaDeReserva.toString());
			stmt.setTime(4, this.horaDePelicula);
			stmt.setInt(5,this.nroSala);
			stmt.setInt(6, this.posButaca);
			stmt.setString(7, this.nombrePelicula);
			stmt.setString(8, this.nombreCliente);
			stmt.setString(9, this.apellidoCliente);
			stmt.setDouble(10, this.precioPeliculaTotal);
			
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
}
