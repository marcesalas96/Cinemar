import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InicioSesion {
	private String correo;
	private boolean admi;
	
	private Cliente cliente;
	private Administrador administrador;
	
	private final String JDBC_DRIVER= "com.mysql.cj.jdbc.Driver";
	private final String DB_URL= "jdbc:mysql://localhost:3306/cine";
	private final String USER= "root";
	private final String PASS= "Dan41019755Dan";
	private Connection conn=null;
	
	public InicioSesion() {
		this.correo="";
		this.admi=false;
		this.cliente=null;
		this.administrador=null;
	}
	
	
	public int inicio() {
		Scanner ingreso=new Scanner(System.in);
		int op;
		String correo,contra;
		do {
			System.out.println("Ingrese el correo; ");
			correo=validarEmail(ingreso.nextLine());
			System.out.println("Ingrese su contrasenia; ");
			contra=ingreso.nextLine();
			
			this.verificacionCorreo(correo);
			
			if(this.correo.equals(correo)) {
				if(this.admi) {
					op= 1;
				}
				else {
					op= 0;
				}
			}
			else
				op= -1;
		}while(op==-1);
		return op;
	}
	
	public String getCorreo() {
		return this.correo;
	}
	
	private void verificacionCorreo(String correo) {
		Statement stmt=null;
		try {
			Class.forName(JDBC_DRIVER);
			this.conn=DriverManager.getConnection(DB_URL,USER,PASS);
			stmt= this.conn.createStatement();
			
			String sql="Select correo,admi from Usuario where id="+correo;
			ResultSet rs= stmt.executeQuery(sql);
			rs.next();
			
			this.correo= rs.getString("correo");
			this.admi= rs.getBoolean("admi");
			
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
	public  String validarEmail(String mail) {
		ArrayList<String> email = new ArrayList<String>(); 
		email.add(mail);
		String regx = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
		Pattern pattern = Pattern.compile(regx);
		for(String item : email) {
			Matcher matcher = pattern.matcher(item);
			do {
				Scanner input = new Scanner(System.in);
				System.out.println("El email ingresado no es valido");
				System.out.println("Por favor ingrese otro email: ");
				item = input.next();		
				matcher = pattern.matcher(item);
			}
			while(matcher.matches()==false);
			mail = item;
		}
		return mail;
	}
	
	
}