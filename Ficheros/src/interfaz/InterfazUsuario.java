package interfaz;

import java.util.Scanner;

import modelo.entidades.Usuario;
import modelo.negocio.GestorUsuario;

public class InterfazUsuario {
	
	private GestorUsuario gu = null;
	
	public void mostrarInterfaz() {
		Usuario usuario = pedirDatos();
		gu = new GestorUsuario();
		int respuesta = gu.validar(usuario);
		
		int contador = 0;
		boolean validado = false;
		
		while(contador < 3 ||!validado) {
			respuesta = gu.validar(usuario);
			switch (respuesta) {
			case 0:
				System.out.println("Usuario no existe");
				break;
			case 1: 
				System.out.println("Usuario correcto. Bienvenido a la app");
				break;
			case 2:
				System.out.println("Usuario y Password no coincide");
				contador++;
				break;
			case 666:
				System.out.println("Errorde acceso. Intentelo mas tarde.");
				break;
			
			}
			
		}
		iniciarAplicacion();
	}

	private void iniciarAplicacion() {
		System.out.println("Dame 70 pavos");
		
	}

	private Usuario pedirDatos() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Introduzca el nombre: ");
		String nombre = sc.nextLine();
		System.out.println("Introduzca el password: ");
		String pass = sc.nextLine();
		Usuario u = new Usuario();
		u.setNombre(nombre);
		u.setPassword(pass);
		return u;
	}
}
