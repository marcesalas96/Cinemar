import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class test {

	public static void main(String[] args) {
		DayOfWeek fechaActual = LocalDate.now().getDayOfWeek();
		System.out.println(fechaActual);
		
	}
	
	public static String validarString(String dato) {
		System.out.println(dato);
		do {
			System.out.println("El dato ingresado es incorrecto");
			Scanner input = new Scanner(System.in);
			System.out.println("Por favor intente de nuevo:  ");
			dato = input.next();
			System.out.println(dato);
		}
		while(!(dato.matches("[a-zA-z]+([ '-][a-zA-Z]+)*"))); 
		System.out.println("termine");
		return dato;
	}
	public static String validarEmail(String mail) {
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


