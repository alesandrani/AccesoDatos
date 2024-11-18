package modelo.intefaz;

import java.util.List;
import java.util.Scanner;

import modelo.dao.DaoCoche;
import modelo.entidad.Coche;

public class IntefazCoche {
	
	public static boolean menu(Scanner scanner) {
		Scanner sc = new Scanner(System.in);
		DaoCoche cocheDAO = new DaoCoche();
		boolean continuar = true;

		while (continuar) {
			System.out.println("===== Menú Principal =====");
			System.out.println("1. Insertar un nuevo coche");
			System.out.println("2. Dar de baja el coche(Eliminar de BBDD)");
			System.out.println("3. Modificar el coche ");
			System.out.println("4. Buscar el coche por id");
			System.out.println("5. Buscar el coche por marca");
			System.out.println("6. Listar todos los coches");
			
			System.out.println("0. Salir");
			System.out.print("Elige una opción: ");

			int opcion = sc.nextInt();
			sc.nextLine();

			switch (opcion) {
			case 1:
				System.out.print("Introduce id del coche: ");
				String id = sc.nextLine();
				
				System.out.print("Introduce la marca del coche: ");
				String marca = sc.nextLine();

				System.out.print("Introduce el modelo del coche: ");
				String modelo = sc.nextLine();

				System.out.print("Introduce el tipo de motor del coche: ");
				String tipoMotor = sc.nextLine();

				System.out.print("Introduce los kilómetros del coche: ");
				int kilometros = sc.nextInt();
				sc.nextLine(); 

				if (marca.isEmpty() || modelo.isEmpty() || tipoMotor.isEmpty() || kilometros < 0) {
					System.out.println("No se pueden guardar datos vacíos o inválidos.");
				} else {
					Coche coche = new Coche(id,marca,modelo,tipoMotor,kilometros);
					cocheDAO.insertar(coche);
				}
				break;
			case 2:
				System.out.println("Introduzca el id de coche a eliminar");
				String idEliminar = sc.nextLine();
                 if (cocheDAO.borrar(idEliminar)) {
                     System.out.println("Coche eliminado con éxito.");
                 }
                 break;
			case 3:
				 System.out.print("Introduce el ID del coche a modificar: ");
                 String idModificar = sc.nextLine();
                 sc.nextLine();
                 System.out.print("Introduce la nueva marca: ");
                 String nuevaMarca = sc.nextLine();
                 System.out.print("Introduce el nuevo modelo: ");
                 String nuevoModelo = sc.nextLine();
                 System.out.print("Introduce el nuevo tipo de motor: ");
                 String nuevoMotor = sc.nextLine();
                 System.out.print("Introduce los nuevos kilómetros: ");
                 int nuevosKilometros = sc.nextInt();

                 if (cocheDAO.modificar(new Coche(idModificar, nuevaMarca, nuevoModelo, nuevoMotor, nuevosKilometros))) {
                     System.out.println("Coche modificado con éxito.");
                 }
                 break;
			case 4:
				 System.out.print("Introduce el ID del coche a buscar: ");
                 String idBuscar = sc.nextLine();
                 Coche coche = cocheDAO.buscarId(idBuscar);
                 if (coche != null) {
                     System.out.println("Coche encontrado: " + coche.getMarca() + " " + coche.getModelo());
                 }
                 break;
			case 5:
				System.out.print("Introduce la marca a buscar: ");
                String marcaBuscar = sc.nextLine();
                List<Coche> cochesPorMarca = cocheDAO.buscarPorMarca(marcaBuscar);
                if (!cochesPorMarca.isEmpty()) {
                    System.out.println("Coches encontrados:");
                    for (Coche c : cochesPorMarca) {
                        System.out.println(c.getId() + " - " + c.getMarca() + " " + c.getModelo());
                    }
                } else {
                    System.out.println("No se encontraron coches con la marca especificada.");
                }
                break;
			case 6:
				 List<Coche> coches = cocheDAO.listar();
                 if (!coches.isEmpty()) {
                     System.out.println("Listado de coches:");
                     for (Coche c : coches) {
                         System.out.println(c.getId() + " - " + c.getMarca() + " " + c.getModelo());
                     }
                 } else {
                     System.out.println("No hay coches registrados.");
                 }
                 break;

			case 0:
				continuar = false;
				System.out.println("Saliendo del programa...");
				break;

			default:
				System.out.println("Opción no válida. Por favor, elige de nuevo.");
			}
		}

		sc.close();
		return continuar;
	}
}
