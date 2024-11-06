package cliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class SocketCliente {
    public static final int PUERTO = 2017;
    public static final String IP_SERVER = "localhost";
    
    public static void main(String[] args) {
        System.out.println("        APLICACIÓN CLIENTE         ");
        System.out.println("-----------------------------------");
        
        InetSocketAddress direccionServidor = new InetSocketAddress(IP_SERVER, PUERTO);
        
        try (Scanner sc = new Scanner(System.in);
             Socket socketAlServidor = new Socket()) {
            System.out.println("CLIENTE: Introduzca los números para realizar la operación:");
            String numero1 = sc.nextLine();
            String numero2 = sc.nextLine();
            
            System.out.println("------------MENU CALCULADORA----------------");
            System.out.println("1. Sumar");
            System.out.println("2. Restar");
            System.out.println("3. Multiplicar");
            System.out.println("4. Dividir");
            System.out.println("5. Salir");
            
            System.out.print("Elija una opción: ");
            String opcion = sc.nextLine();
            
            String operandos = numero1 + "_" + numero2 + "_" + opcion;
            System.out.println("CLIENTE: Conectando con el servidor...");
            socketAlServidor.connect(direccionServidor);            
            System.out.println("CLIENTE: Conexión establecida con " + IP_SERVER + " en el puerto " + PUERTO);
            
            try (PrintStream salida = new PrintStream(socketAlServidor.getOutputStream());
                 BufferedReader bf = new BufferedReader(new InputStreamReader(socketAlServidor.getInputStream()))) {
                
                // Enviamos los operandos y la operación al servidor
                salida.println(operandos);
                
                System.out.println("CLIENTE: Esperando al resultado del servidor...");
                String resultado = bf.readLine();
                
                // Cambiar el mensaje dependiendo de la opción seleccionada
                String operacion;
                switch (opcion) {
                    case "1": operacion = "suma"; break;
                    case "2": operacion = "resta"; break;
                    case "3": operacion = "multiplicación"; break;
                    case "4": operacion = "división"; break;
                    case "5": operacion = "salida"; break;
                    default: operacion = "operación no válida"; break;
                }
                
                System.out.println("CLIENTE: El resultado de la " + operacion + " es: " + resultado);
            }
            
        } catch (UnknownHostException e) {
            System.err.println("CLIENTE: No se encuentra el servidor en la dirección " + IP_SERVER);
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("CLIENTE: Error de entrada/salida");
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("CLIENTE: Error -> " + e);
            e.printStackTrace();
        }
        
        System.out.println("CLIENTE: Fin del programa");
    }
}
