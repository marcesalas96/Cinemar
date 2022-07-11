
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Cartelera {
	private ArrayList<Pelicula> peliculas;
	
	private final String JDBC_DRIVER= "com.mysql.cj.jdbc.Driver";
	private final String DB_URL= "jdbc:mysql://localhost:3306/cine";
	private final String USER= "root";
	private final String PASS= "marce282";
	private Connection conn=null;
	
	public Cartelera() {
		this.peliculas=new ArrayList<>();
	}
	
	public void consultarPeliculas() {
		Statement stmt=null;
		ArrayList<Integer> salas=new ArrayList<>();
		
		try {
			Class.forName(JDBC_DRIVER);
			this.conn=DriverManager.getConnection(DB_URL,USER,PASS);
			stmt= this.conn.createStatement();
			
			String sql="Select nro_Sala FROM Sala";
			ResultSet rs= stmt.executeQuery(sql);
			
			while(rs.next()) {
				salas.add( rs.getInt("nro_Sala") );
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
		for(int i=0;i<salas.size();i++) {
			Pelicula pelicula=new Pelicula(salas.get(i));
			pelicula.consultarDatosBD();
			this.peliculas.add(pelicula);
		}
	}
	
	public void mostrarPeliculas() {
		for(int i=0;i<this.peliculas.size();i++) {
			System.out.println((i+1)+"- "+this.peliculas.get(i).toString()+"\n");
		}
	}
	public Pelicula retornarPeli(int op) {
		return this.peliculas.get(op);
	}
	
}
