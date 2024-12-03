package modelo.interfaz;

import java.util.List;
import java.util.Scanner;

import modelo.entidad.Coche;
import modelo.entidad.Pasajero;
import modelo.persistencia.DaoCoche;
import modelo.persistencia.DaoPasajero;

public class InterfazCoche {
	
	public static boolean menu(Scanner sc) {
		Scanner sca = new Scanner(System.in);
		
		DaoCoche cocheDAO = DaoCoche.getInstance();
		DaoPasajero daoPasajero =  DaoPasajero.getInstance();
		boolean continuar = true;
		
		while(continuar) {
			System.out.println("===== Menú Principal =====");
			System.out.println("1. Insertar un nuevo coche");
			System.out.println("2. Dar de baja el coche(Eliminar de BBDD)");
			System.out.println("3. Buscar el coche por id");
			System.out.println("4.Modificar coche por id");
			System.out.println("5. Listar todos los coches");
			System.out.println("6.Gestion pasajeros");
			
			System.out.println("0. Salir");
			System.out.print("Elige una opción: ");

			int opcion = sca.nextInt();
			sca.nextLine();
			switch (opcion) {
			case 1:
				
				System.out.print("Introduce la marca del coche: ");
				String marca = sc.nextLine();

				System.out.print("Introduce el modelo del coche: ");
				String modelo = sc.nextLine();

				System.out.print("Introduce el año de fabricacion del coche: ");
				String anio_fabricacion= sc.nextLine();

				System.out.print("Introduce los kilómetros del coche: ");
				double km = sc.nextDouble();
				sc.nextLine(); 

				if (marca.isEmpty() || modelo.isEmpty() || marca.isEmpty() || km < 0) {
					System.out.println("No se pueden guardar datos vacíos o inválidos.");
				} else {
					Coche coche = new Coche(marca,modelo,anio_fabricacion,km);
					cocheDAO.insertar(coche);
				}
				break;
			case 2:
				System.out.println("Introduzca el id de coche a eliminar");
				String idEliminar = sca.nextLine();
                 if (cocheDAO.borrar(idEliminar)) {
                     System.out.println("Coche eliminado con éxito.");
                 }
                 break;
			case 3:
				 System.out.print("Introduce el ID del coche a buscar: ");
                String idBuscar = sca.nextLine();
                Coche coche = cocheDAO.buscarId(idBuscar);
                if (coche != null) {
                    System.out.println("Coche encontrado: " + coche.getMarca() + " " + coche.getModelo());
                }
                break;
			case 4:
				 System.out.print("Introduce el ID del coche a modificar: ");
                String idModificar = sca.nextLine();
                sc.nextLine();
                System.out.print("Introduce la nueva marca: ");
                String nuevaMarca = sca.nextLine();
                System.out.print("Introduce el nuevo modelo: ");
                String nuevoModelo = sca.nextLine();
                System.out.print("Introduce el nuevo tipo de motor: ");
                String nuevoMotor = sca.nextLine();
                System.out.print("Introduce los nuevos kilómetros: ");
                int nuevosKilometros = sca.nextInt();

                if (cocheDAO.modificar(new Coche( nuevaMarca, nuevoModelo, nuevoMotor, nuevosKilometros))) {
                    System.out.println("Coche modificado con éxito.");
                }
                break;
			case 5:
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
			case 6:
				boolean gestionPasajeros = true;
			    while (gestionPasajeros) {
			        System.out.println("===== Gestión de Pasajeros =====");
			        System.out.println("a. Crear nuevo pasajero");
			        System.out.println("b. Borrar pasajero por ID");
			        System.out.println("c. Consultar pasajero por ID");
			        System.out.println("d. Listar todos los pasajeros");
			        System.out.println("e. Añadir pasajero a coche");
			        System.out.println("f. Eliminar pasajero de un coche");
			        System.out.println("g. Listar todos los pasajeros de un coche");
			        System.out.println("t. Volver al menú principal");
			        System.out.print("Elige una opción: ");
			        
			        char opcionPasajeros = sca.nextLine().toLowerCase().charAt(0);

			        switch (opcionPasajeros) {
			            case 'a': 
			                System.out.println("Introduce el nombre del pasajero:");
			                String nombre = sca.nextLine();
			                System.out.println("Introduce la edad del pasajero:");
			                int edad = sca.nextInt();
			                System.out.println("Introduce el peso del pasajero:");
			                double peso = sca.nextDouble();
			                sca.nextLine(); 

			                System.out.print("Introduce el ID del coche al que se asociará el pasajero: ");
			                int idCoche = sc.nextInt();
			                sc.nextLine(); 

			                Pasajero pasajero = new Pasajero(nombre, edad, peso);
			                if (daoPasajero.insertar(pasajero, idCoche)) {
			                    System.out.println("Pasajero creado con éxito y asociado al coche con ID " + idCoche);
			                } else {
			                    System.out.println("Error al crear el pasajero.");
			                }
			                break;
			            case 'b':
			                System.out.println("Introduce el ID del pasajero a borrar:");
			                int idBorrar = sca.nextInt();
			                sca.nextLine();
			                if (daoPasajero.borrar(idBorrar)) {
			                    System.out.println("Pasajero eliminado con éxito.");
			                }
			                break;

			            case 'c': 
			                System.out.println("Introduce el ID del pasajero a consultar:");
			                int idConsultar = sca.nextInt();
			                sca.nextLine();
			                Pasajero pasajeroConsultado = daoPasajero.buscarId(idConsultar);
			                if (pasajeroConsultado != null) {
			                    System.out.println("Pasajero encontrado: " + pasajeroConsultado);
			                }
			                break;

			            case 'd': 
			                List<Pasajero> pasajeros = daoPasajero.listar();
			                if (!pasajeros.isEmpty()) {
			                    System.out.println("Listado de pasajeros:");
			                    for (Pasajero p : pasajeros) {
			                        System.out.println(p);
			                    }
			                } else {
			                    System.out.println("No hay pasajeros registrados.");
			                }
			                break;

			            case 'e': 
			                System.out.println("Introduce el ID del pasajero:");
			                int idPasajero = sca.nextInt();
			                System.out.println("Introduce el ID del coche:");
			                int idCoches = sca.nextInt();
			                sca.nextLine();
			                if (daoPasajero.asociarACoche(idPasajero, idCoches)) {
			                    System.out.println("Pasajero añadido al coche con éxito.");
			                }
			                break;

			            case 'f':
			                System.out.println("Introduce el ID del pasajero a eliminar del coche:");
			                int idPasajeroEliminar = sca.nextInt();
			                sca.nextLine();
			                if (daoPasajero.desasociarDeCoche(idPasajeroEliminar)) {
			                    System.out.println("Pasajero eliminado del coche con éxito.");
			                }
			                break;

			            case 'g': 
			                System.out.println("Introduce el ID del coche:");
			                int idCocheListar = sca.nextInt();
			                sca.nextLine();
			                List<Pasajero> pasajerosCoche = daoPasajero.listarPorCoche(idCocheListar);
			                if (!pasajerosCoche.isEmpty()) {
			                    System.out.println("Pasajeros del coche " + idCocheListar + ":");
			                    for (Pasajero p : pasajerosCoche) {
			                        System.out.println(p);
			                    }
			                } else {
			                    System.out.println("No hay pasajeros asociados a este coche.");
			                }
			                break;

			            case 't': 
			                gestionPasajeros = false;
			                break;

			            default:
			                System.out.println("Opción no válida. Por favor, elige de nuevo.");
			        }
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
