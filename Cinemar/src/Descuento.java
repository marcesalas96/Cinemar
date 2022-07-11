import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Descuento {
	private String dia;
    private double porcentaje1;
    private double porcentaje2;
    private double porcentaje3;
    private double precio;
    
    private final String JDBC_DRIVER= "com.mysql.cj.jdbc.Driver";
	private final String DB_URL= "jdbc:mysql://localhost:3306/cine";
	private final String USER= "root";
	private final String PASS= "Dan41019755Dan";
	private Connection conn=null;

    public Descuento(String dia) {
        this.dia = dia;
        this.porcentaje1 = 0;
        this.porcentaje2 = 0;
        this.porcentaje3 = 0;
        this.precio = 0;
        this.consultarDescuentoBD();
    }
   public Descuento() {
	   this.dia = "";
       this.porcentaje1 = 0;
       this.porcentaje2 = 0;
       this.porcentaje3 = 0;
       this.precio = 0;
       this.consultarDescuentoBD();
   }
    
    private void consultarDescuentoBD() { 
		Statement stmt=null;
		
		try {
                       
			Class.forName(JDBC_DRIVER);
			this.conn=DriverManager.getConnection(DB_URL,USER,PASS);
			stmt=this.conn.createStatement();
			
			String sql="select porcentaje1, porcentaje2, porcentaje3, precioPeli from Descuento;";
			ResultSet rs= stmt.executeQuery(sql);
			rs.next();
		
           
			this.porcentaje1 =  rs.getDouble("porcentaje1");
			this.porcentaje2 =  rs.getDouble("porcentaje2");
			this.porcentaje3 =  rs.getDouble("porcentaje3");
			this.precio = rs.getDouble("precioPeli");
			
			
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
    public void actualizarDescuentosBD() { // MODIFICA LOS DESCUENTOS 
		Scanner ingreso=new Scanner(System.in);
		
		double por1,por2,por3;
		
		System.out.println("Ingrese el nuevo valor del primer porcentaje de descuento: ");
		por1=ingreso.nextDouble();
		this.porcentaje1=por1;
		
		System.out.println("Ingrese el nuevo valor del segundo porcentaje de descuento: ");
		por2=ingreso.nextDouble();
		this.porcentaje2=por2;
		
		System.out.println("Ingrese el nuevo valor del tercer porcentaje de descuento: ");
		por3=ingreso.nextDouble();
		this.porcentaje3=por3;
		
		this.conn=null;
		PreparedStatement stmt=null;
		
		try {
		
		String p1,p2,p3;
		p1="porcentaje1="+this.porcentaje1;
		p2="porcentaje2="+this.porcentaje2;
		p3="porcentaje3="+this.porcentaje3;
		
		String query="update Descuento set porcentaje1="+p1+", porcentaje2="+p2+", porcentaje3="+p3+" where id=1;";
		stmt = this.conn.prepareStatement(query);
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
    public void actualizarPrecioBD() { // MODIFICA EL PRECIO
		Scanner ingreso=new Scanner(System.in);
		
		double pre;
		
		System.out.println("Ingrese el nuevo precio de la entrada: ");
		pre=ingreso.nextDouble();
		this.precio=pre;
		
		
		this.conn=null;
		PreparedStatement stmt=null;
		
		try {
		
		String pF;
		pF="precioPeli="+this.precio;
		
		String query="update Descuento set precioPeli="+pF+" where id=1;";
		stmt = this.conn.prepareStatement(query);
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

    public double precioTotal() { // CALCULA EL PRECIO
    	switch (this.dia) {
    	case "MONDAY": case "WEDNESDAY":
    		return ( this.precio - (this.precio*(this.porcentaje1/100)) );
    	case "TUESDAY": case "THURSDAY":
    		return ( this.precio - (this.precio*(this.porcentaje2/100)) );
    	//case "FRIDAY": case "SATURDAY": case "SUNDAY":
    	default:
    		return ( this.precio - (this.precio*(this.porcentaje3/100)) );
    	}
    }
    public double getPrecio() {
    	return this.precio;
    }
	
	public String toString() {
		return "Dia="+dia+", porcentaje1="+porcentaje1+", porcentaje2="+porcentaje2+", porcentaje3="+porcentaje3+", precio="+precio;
	}

}