package main;

import java.util.Scanner;


import modelo.intefaz.IntefazCoche;

public class MainCoche {

	public static void main(String[] args) {
		 
		Scanner scanner = new Scanner(System.in);

        System.out.println("Bienvenido a la aplicación de gestión de coches.");
        
        // Llamada al método menu de la clase que contiene la lógica del menú
        boolean continuar = IntefazCoche.menu(scanner);

        // Verificar el resultado de la ejecución del menú
        if (!continuar) {
            System.out.println("Programa finalizado.");
        }

        scanner.close();
    }
	}


