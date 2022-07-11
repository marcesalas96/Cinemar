import java.util.Scanner;
import java.time.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
	
	    
	    System.out.println("Fecha actual ==> " + dtf.format(date_aux));
	public static void main(String[] args) {
		
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
			InicioSesion login = new InicioSesion();
			int isAdmin = login.inicio();
			if(isAdmin == 1) {
				//Administracion
			}
			else if (isAdmin == 0) {
				Usuario user = new Usuario(login.getCorreo());
				user.consultarBD();
				Cliente cliente = new Cliente(user);
				cliente.mostrarMenu();
			}	else {
				 System.out.println ("¡El nombre de usuario o la contraseña son incorrectos! ¡Vuelva a iniciar sesión!");
			}
			break;
		case "2":
			 // Función de registro
			System.out.println("-----------Registro-----------");
			System.out.print ("Introduzca su nombre: ");
			String Nombre = validarString(sc.nextLine());
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
			Registro register = new Registro(Nombre, Apellido, Edad, Correo, Contraseña);
			System.out.println ("¡Registrado correctamente!");
			 // Escribir información de registro
			 Cliente cliente = register.getCliente();
			 cliente.mostrarMenu();
			break;
		case "3":
		default:
			System.out.println("-----------Gracias por elegirnos!-----------");
			System.exit(0);
			break;
		}
	}


	public static String validarString(String dato) {
		do {
			System.out.println("El dato ingresado es incorrecto");
			Scanner input = new Scanner(System.in);
			System.out.println("Por favor intente de nuevo:  ");
			dato = input.next();
		}
		while(!(dato.matches("[a-zA-z]+([ '-][a-zA-Z]+)*"))); 
		return dato;
	}
	
	
	public static  String validarEmail(String mail) {
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