package main;

import java.util.Scanner;

import modelo.interfaz.InterfazCoche;

public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Bienvenido a la aplicación de gestión de coches.");
		boolean continuar = InterfazCoche.menu(scanner);
		if (!continuar) {
			System.out.println("Programa finalizado.");
		}

		scanner.close();

	}

}
