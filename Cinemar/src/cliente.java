import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class cliente extends Usuario{

	private boolean tieneTarjeta;
	private int historial;
	public cliente(String nombre, String apellido, int edad, String correo, String usuario, String contraseña,
			boolean tieneTarjeta, int historial) {
		super(nombre, apellido, edad, correo, usuario, contraseña);
		this.tieneTarjeta = tieneTarjeta;
		this.historial = historial;
	}
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
	
	public void verHistorial() {
		int historial = getHistorial();
		System.out.println(this.Nombre + ", tu historial de peliculas vistas es de: " + historial);
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
}
