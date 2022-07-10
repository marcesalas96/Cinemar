
public class Usuario {
	protected static String Nombre;
	protected static String Apellido;
	protected static String Correo;
	protected static int Edad;
	protected static String Usuario;
	protected static String Contraseña;
	public Object get;
	
	public Usuario(String nombre, String apellido, int edad, String correo, String usuario, String contraseña) {
		
		Nombre = nombre;
		Apellido = apellido;
		Correo = correo;
		Usuario = usuario;
		Contraseña = contraseña;
		Edad = edad;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public String getApellido() {
		return Apellido;
	}

	public void setApellido(String apellido) {
		Apellido = apellido;
	}

	public String getCorreo() {
		return Correo;
	}

	public void setCorreo(String correo) {
		Correo = correo;
	}
	
	
	
	public int getEdad() {
		return Edad;
	}

	public void setEdad(int edad) {
		Edad = edad;
	}

	public String getUsuario() {
		return Usuario;
	}

	public void setUsuario(String usuario) {
		Usuario = usuario;
	}

	public String getContraseña() {
		return Contraseña;
	}

	public void setContraseña(String contraseña) {
		Contraseña = contraseña;
	}
	
	
	
	@Override
	public String toString() {
		return "Usuario [\nNombre = " + Nombre + "\nApellido = " + Apellido +"\nEdad = "+Edad+"\nCorreo = " + Correo + "\nUsuario = " + Usuario
				+ "\nContraseña = " + Contraseña + "]";
	}
	
	
	
}
