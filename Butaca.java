package Version_2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Butaca {
	private int nro_Sala;
	private int pos;
	private boolean ocupado;
	
	private final String JDBC_DRIVER= "com.mysql.cj.jdbc.Driver";
	private final String DB_URL= "jdbc:mysql://localhost:3306/cine";
	private final String USER= "root";
	private final String PASS= "Dan41019755Dan";
	private Connection conn=null;
	
	public Butaca(int sala,int pos) {
		this.nro_Sala=sala;
		this.pos=pos;
		this.ocupado=false;
	}
	public Butaca(int sala,int pos,boolean ocupado) {
		this.nro_Sala=sala;
		this.pos=pos;
		this.ocupado=ocupado;
	}

	public int getNro_Sala() {
		return nro_Sala;
	}
	public int getPos() {
		return pos;
	}
	public boolean getOcupado() {
		return ocupado;
	}
	
	public void generarButacaBD() { // CARGA LA BUTACA A LA BASE DE DATOS
		PreparedStatement stmt=null;
		
		try {
			Class.forName(JDBC_DRIVER);
			conn=DriverManager.getConnection(DB_URL,USER,PASS);
			
			String query="insert into Butaca (id,pos,nro_Sala,ocupado) values (?,?,?,?);";
			stmt = conn.prepareStatement(query);
			stmt.setString(1, null);
			stmt.setInt(2, this.pos);
			stmt.setInt(3, this.nro_Sala);
			stmt.setBoolean(4, this.ocupado);
			
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
	
	public String toString() { // DEVUELVE UN STRING CON LA INFORMACION DE LA BUTACA (SI ES QUE ES NECESARIO)
		String cad="";
		cad+="Numero de sala: "+this.nro_Sala+", Posicion: "+this.pos;
		return cad;
	}
	
}
