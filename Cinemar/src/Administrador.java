import java.util.Scanner;

public class Administrador {
	private Usuario user;
	private Administracion admin;
	
	public Administrador(Usuario us) {
		this.user=us;
	}
	private int opciones() {
		Scanner ing=new Scanner(System.in);
		int op;
		do {
		System.out.println("1- Ver las reservas de todos los clientes");
		System.out.println("2- Ver las reservas de un cliente");
		System.out.println("3- Crear una sala y una pelicula");
		System.out.println("4- Modificar una Sala (pelicula)");
		System.out.println("5- Restablecer la disponibilidad de las butacas");
		System.out.println("6- Modificar descuentos");
		System.out.println("7- Modificar precio de la entrada");
		System.out.println("0- Salir");
		System.out.println("Opcion: ");
		op=ing.nextInt();
		}while(op<0 || op>7);
		return op;
	}
	public void menu() {
		int op;
		do {
			op=this.opciones();
			switch(op) {
				case 1:
					this.admin.verReservasClientes();
					break;
				case 2:
					this.admin.mostrarResvasCliente();
					break;
				case 3:
					this.admin.crearSala();
					break;
				case 4:
					this.admin.modificarSala();
					break;
				case 5:
					this.admin.modificarSalaButacas();
					break;
				case 6:
					this.admin.actualizarDescuentos();
					break;
				case 7:
					this.admin.actualizarPrecioEntrada();
					break;
				default: 
					System.out.println("Cerrando el programa...");
					break;
			}
		}while(op!=0);
	}
}