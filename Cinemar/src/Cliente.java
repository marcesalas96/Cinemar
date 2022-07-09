import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.time.*;
import java.time.format.*;

public class Cliente extends Usuario{
	private boolean tieneTarjeta;
	private int historial; 
	
	
	
	public void registrarCliente() {
		try (Scanner input = new Scanner(System.in)) {
			
			System.out.println("Ingresa tu nombre de usuario: ");
			this.usuario = input.next();
			
			System.out.println("Ingresa tu nombre: ");
			this.nombre = input.next();
			validarString(this.nombre);

			System.out.println("Ingresa tu apellido: ");
			this.apellido = input.next();
			validarString(this.apellido);
			
			System.out.println("Ingresa tu edad: ");
			this.edad = input.nextInt();
			
			System.out.println("Ingresa tu email: ");
			this.correo = input.next();
			validarEmail(this.correo);
			
			System.out.println("Ingresa tu contraseña: ");
			this.contraseña = input.next();
		}
		
		this.historial = 0 ;
		this.tieneTarjeta = false;
		System.out.println(this.nombre + ", tu usuario fue creado con exito!");
	}
	
	
	public void validarString(String dato) {
		if(!(dato.matches("[a-zA-z]+([ '-][a-zA-Z]+)*"))) {
			System.out.println("El dato ingresado es incorrecto");
			Scanner input = new Scanner(System.in);
			System.out.println("Por favor intente de nuevo:  ");
			String name = input.next();
			setNombre(name);
		}
	}
	
	public void validarEmail(String mail) {
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


	public void verHistorial() {
		int historial = getHistorial();
		System.out.println(this.nombre + ", tu historial de peliculas vistas es de: " + historial);
	}
	public void aumentarHistorial() {
		LocalDate horaActual = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MM-uuuu");
		String ld = horaActual.format(formatter);
		int historial = getHistorial();
		historial++;
		setHistorial(historial);
		String nuevaCompra = ld + historial; 
		System.out.println(nuevaCompra);
		
		
	}



	public void revisarTarjeta() {
		
	}
	
	//Getters y Setters


	public boolean isTieneTarjeta() {
		return tieneTarjeta;
	}


	public void setTieneTarjeta(boolean tieneTarjeta) {
		this.tieneTarjeta = tieneTarjeta;
	}


	public int getHistorial() {
		return historial;
	}


	public void setHistorial(int historial) {
		this.historial = historial;
	}
	
}

