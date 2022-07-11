import java.util.ArrayList;
import java.util.Scanner;
import java.time.*;
import java.time.format.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;


public class Cliente {
	private Usuario usuario;
	private String nick;
	private boolean tieneTarjeta;
	private Historial historial; 
	
	private final String JDBC_DRIVER= "com.mysql.cj.jdbc.Driver";
	private final String DB_URL= "jdbc:mysql://localhost:3306/cine";
	private final String USER= "root";
	private final String PASS= "marce282";
	private Connection conn=null;
	//PARA REGISTRO
	public Cliente(Usuario usuario, String nick) {
		this.usuario=usuario;
		this.nick= nick;
		this.tieneTarjeta=false;
		this.historial = new Historial(nick);
	}
	//PARA LOGIN
	public Cliente(Usuario usuario) {
		this.usuario = usuario;
		this.nick ="";
		this.tieneTarjeta = false;
		this.historial = new Historial(nick);
		this.consultarBD();
	}
	public void mostrarMenu() {
		DayOfWeek fechaActual = LocalDate.now().getDayOfWeek();
		System.out.println("Bienvenido/a " + usuario.getNombre() + "!");
		System.out.println("----------------------------------------");
		System.out.println("1 Ver cartelera \n 2 Consultar historial de reserva \n 3 Modificar reserva");
		System.out.print ("Seleccione la función a ejecutar:");
		Scanner sc = new Scanner(System.in);
		int opcion = sc.nextInt();
		switch (opcion) {
		case 1 :
			Cartelera cartelera = new Cartelera();
			cartelera.consultarPeliculas();
			cartelera.mostrarPeliculas();
			System.out.println("Seleccione la pelicula que quiere ver: ");
			int op = sc.nextInt();
			Pelicula peli = cartelera.retornarPeli(op);
			Sala sala = new Sala(peli.getSala());
			sala.consultarButacas();
			sala.mostrarButacasDisponibles();
			System.out.println("Seleccione la butaca que quiera: ");
			int posButaca = sc.nextInt();
			Butaca butaca = new Butaca(sala.getNro_Sala(),posButaca);
			butaca.actualizarDisponibilidad(true);
			Descuento desc = new Descuento(fechaActual.toString());
			Reserva reserva = new Reserva(peli.getHorario(),sala.getNro_Sala(),butaca.getPos(),peli.getTitulo(),desc.getPrecio(), this.nick);
			reserva.generarReservaBD();
			
				
			break;
		case 2: 
			this.consultarReservaBD(nick);
			break;
		case 3:
				System.out.println("Ingrese el id de la reserva a modificar: ");
				int id = sc.nextInt();
				Reserva modificaReserva = new Reserva(this.nick,id);
				modificaReserva.actualizarDatosBaseDatos(id);
		}
	};
	
	public void consultarReservaBD(String nombreUsuario) {
		Historial historial = new Historial(nombreUsuario);
		historial.mostrarReservas();
	}
	private void consultarBD() {
		Statement stmt=null;
		
		try {
			Class.forName(JDBC_DRIVER);
			this.conn=DriverManager.getConnection(DB_URL,USER,PASS);
			stmt= this.conn.createStatement();
			
			String sql="Select nombreUsuario,tarjeta from Pelicula where Correo='"+this.usuario.getCorreo()+"';";
			ResultSet rs= stmt.executeQuery(sql);
			rs.next();
			
			this.nick= rs.getString("nombreUsuario");
			this.tieneTarjeta = rs.getBoolean("tarjeta");
			
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
	public void generarCliente() {
		PreparedStatement stmt=null;
		
		try {
			Class.forName(JDBC_DRIVER);
			conn=DriverManager.getConnection(DB_URL,USER,PASS);
			
			String query="insert into Cliente (nombreUsuario,tarjeta,Correo) (?,?,?);";
			stmt = conn.prepareStatement(query);
			stmt.setString(1, this.nick);
			stmt.setBoolean(2, this.tieneTarjeta);
			stmt.setString(3, this.usuario.getCorreo());
			
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
	
	//Getters y Setters


	public boolean isTieneTarjeta() {
		return tieneTarjeta;
	}


	public void setTieneTarjeta(boolean tieneTarjeta) {
		this.tieneTarjeta = tieneTarjeta;
	}


	public Historial getHistorial() {
		return historial;
	}


	public void setHistorial(Historial historial) {
		this.historial = historial;
	}
	
}

