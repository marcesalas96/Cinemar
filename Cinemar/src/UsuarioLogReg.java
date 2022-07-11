
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UsuarioLogReg extends Usuario{		
	public UsuarioLogReg(String nombre, String apellido, int edad, String correo, String usuario, String contraseña) {
		super(nombre, apellido, edad, correo, usuario, contraseña);
		}
		
		private final String JDBC_DRIVER= "com.mysql.cj.jdbc.Driver";
		private final String DB_URL= "jdbc:mysql://localhost:3306/cine";
		private final String USER= "root";
		private final String PASS= "marce282";
		private Connection conn=null;


	public void mostrarMenu(String CorreoChecker) {
			
	 
			while (true) {
				 System.out.println("-----------Bienvenido(a)-----------");
				 System.out.println ("1 Iniciar de sesión");
				 System.out.println ("2 Registrar Usuario");
				 System.out.println ("3 Salir");
				 System.out.print ("Seleccione la función a ejecutar:");
				Scanner sc = new Scanner(System.in);
				String opcion = sc.nextLine();
				switch (opcion) {
				case "1":
					 // Función de inicio de sesión
					System.out.println("-----------Login-----------");
					 System.out.print ("Introduzca su correo:");
					String correo = sc.nextLine();
					 System.out.print ("Introduzca la contraseña:");
					String contraseña = sc.nextLine();
					Usuario usuario = new Usuario(correo);
					usuario.consultarBD();
					if(usuario.getContraseña().equals(contraseña)) {
						Cliente cliente = new Cliente(usuario);
						cliente.mostrarMenu();
						
					} else {
						 System.out.println ("¡El nombre de usuario o la contraseña son incorrectos! ¡Vuelva a iniciar sesión!");
					}
					break;
				case "2":
					 // Función de registro
					System.out.println("-----------Registro-----------");
					System.out.print ("Introduzca su nombre: ");
					String Nombre = sc.nextLine();
					validarString(Nombre);
					System.out.print ("Introduzca su apellido: ");
					String Apellido = sc.nextLine();
					validarString(Apellido);
					System.out.print ("Introduzca su edad: ");
					int Edad = sc.nextInt();
					System.out.print ("Introduzca su correo: ");
					String Correo = sc.nextLine();
					validarEmail(Correo);
					System.out.print ("Introduzca la contraseña:");
					String Contraseña = sc.nextLine();
				
					 // Escribir información de registro
					 System.out.println ("¡Registrado correctamente!");
					 Usuario usuarioRegister = new Usuario(Nombre, Apellido, Edad, Correo, Contraseña);
					 usuarioRegister.generarUsuario();
					 System.out.println("Ingresa el nombre de usuario que vas a usar a partir de ahora: ");
					 String nick = sc.nextLine();
					 Cliente cliente = new Cliente(usuarioRegister,nick);
					 cliente.mostrarMenu();
					break;
				case "3":
				default:
					System.out.println("-----------Bye~-----------");
					System.exit(0);
					break;
				}
			}
		}

		private boolean isLogin(String correo, String contraseña) {
			// TODO Auto-generated method stub
			return true;
		}
		public static void validarString(String dato) {
			do {
				System.out.println("El dato ingresado es incorrecto");
				Scanner input = new Scanner(System.in);
				System.out.println("Por favor intente de nuevo:  ");
				String nuevoValor = input.next();
			}
			while(!(dato.matches("[a-zA-z]+([ '-][a-zA-Z]+)*"))); 
		}
		
		public static void validarEmail(String mail) {
			 ArrayList<String> email = new ArrayList<String>(); 
			 email.add(mail);
			 String regx = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
			 Pattern pattern = Pattern.compile(regx);
			 for(String email1 : email) {
				 Matcher matcher = pattern.matcher(email1);
				 if(matcher.matches()==false) {
					 Scanner input = new Scanner(System.in);
					 System.out.println("El email ingresado no es valido");
					 System.out.println("Por favor ingrese otro email: ");
					 String newMail = input.next();
					 setCorreo(newMail);
				 }
				 break;
			 }
		}
		/*public void guardarClienteBD() {
			try {
				Class.forName(JDBC_DRIVER);
				conn=DriverManager.getConnection(DB_URL,USER,PASS);
				String query = "insert into Usuario"
		}*/
	}
