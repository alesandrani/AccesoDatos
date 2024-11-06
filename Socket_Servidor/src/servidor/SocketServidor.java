package servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServidor {
	
    public static final int PUERTO = 2017;
    
    public static void main(String[] args) {
        System.out.println("      APLICACIÓN DE SERVIDOR      ");
        System.out.println("----------------------------------");

        InetSocketAddress direccion = new InetSocketAddress(PUERTO);

        try (ServerSocket serverSocket = new ServerSocket()) {
            serverSocket.bind(direccion);
            int peticion = 0;
            
            while (true) {
                System.out.println("SERVIDOR: Esperando petición por el puerto " + PUERTO);
                
                try (Socket socketAlCliente = serverSocket.accept();
                     InputStreamReader entrada = new InputStreamReader(socketAlCliente.getInputStream());
                     BufferedReader bf = new BufferedReader(entrada);
                     PrintStream salida = new PrintStream(socketAlCliente.getOutputStream())) {
                     
                    System.out.println("SERVIDOR: Petición número " + ++peticion + " recibida");
                    String stringRecibido = bf.readLine();
                    System.out.println("SERVIDOR: Me ha llegado del cliente: " + stringRecibido);

                    String[] operadores = stringRecibido.split("_");
                    int n1 = Integer.parseInt(operadores[0]);
                    int n2 = Integer.parseInt(operadores[1]);
                    String opcion = operadores[2];
                    String resultado;

                    switch (opcion) {
                        case "1":
                            resultado = String.valueOf(n1 + n2);
                            System.out.println("Resultado de la suma: " + resultado);
                            break;
                        case "2":
                            resultado = String.valueOf(n1 - n2);
                            System.out.println("Resultado de la resta: " + resultado);
                            break;
                        case "3":
                            resultado = String.valueOf(n1 * n2);
                            System.out.println("Resultado de la multiplicación: " + resultado);
                            break;
                        case "4":
                            if (n2 != 0) {
                                resultado = String.valueOf(n1 / n2);
                                System.out.println("Resultado de la división: " + resultado);
                            } else {
                                resultado = "Error: No se puede dividir por cero.";
                                System.out.println(resultado);
                            }
                            break;
                        case "5":
                            resultado = "El cliente ha solicitado salir.";
                            System.out.println(resultado);
                            break;
                        default:
                            resultado = "Opción no válida.";
                            System.out.println(resultado);
                            break;
                    }

                    // Enviar el resultado de vuelta al cliente
                    salida.println(resultado);
                    socketAlCliente.close();

                } catch (IOException e) {
                    System.err.println("SERVIDOR: Error al procesar la petición del cliente");
                    e.printStackTrace();
                } catch (NumberFormatException e) {
                    System.err.println("SERVIDOR: Error en el formato de los números recibidos");
                    e.printStackTrace();
                }
            }
            
        } catch (IOException e) {
            System.err.println("SERVIDOR: Error de entrada/salida");
            e.printStackTrace();
        }
    }
}
