import java.sql.SQLException;
import java.util.Scanner;

public class UsuarioLogReg extends Usuario(1){		
	public UsuarioLogReg(String nombre, String apellido, int edad, String correo, String usuario, String contraseña) {
		super(nombre, apellido, edad, correo, usuario, contraseña);
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
			UsuarioLogReg us = new UsuarioLogReg(Apellido, Nombre, Edad, Correo, Contraseña, null);
	 
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
					 System.out.print ("Introduzca el nombre de usuario:");
					String correo = sc.nextLine();
					 System.out.print ("Introduzca la contraseña:");
					String contraseña = sc.nextLine();
	 
					if (us.isLogin(correo, contraseña)) {
						 System.out.println ("¡Inicio de sesión exitoso!");
						 sc.close();
						System.exit(0);
					} else {
						 System.out.println ("¡El nombre de usuario o la contraseña son incorrectos! ¡Vuelva a iniciar sesión!");
					}
					break;
				case "2":
					 // Función de registro
					System.out.println("-----------Registro-----------");
					System.out.print ("Introduzca su nombre: ");
					String Nombre = sc.nextLine();
					System.out.print ("Introduzca su apellido: ");
					String Apellido = sc.nextLine();
					System.out.print ("Introduzca su edad: ");
					String Edad = sc.nextLine();
					System.out.print ("Introduzca su correo: ");
					String Correo = sc.nextLine();
					System.out.print ("Introduzca la contraseña:");
					String Contraseña = sc.nextLine();
				
					 // Escribir información de registro
					 System.out.println ("¡Registrado correctamente!");
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
	}

