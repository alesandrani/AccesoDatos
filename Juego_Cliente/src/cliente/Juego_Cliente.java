package cliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class Juego_Cliente {
    
    public static final int PUERTO = 2034;
    public static final String IP_SERVER = "172.26.100.209";
    private static InetSocketAddress direccionServidor = new InetSocketAddress(IP_SERVER, PUERTO);
    private static Scanner sc = new Scanner(System.in);
    
    public static void main(String[] args) {
        System.out.println("        APLICACIÓN CLIENTE         ");
        System.out.println("-----------------------------------");
        
        int rondasJugadas = 0;
        boolean juegoTerminado = false;
        
        try {
            Socket socketAlServidor = new Socket();
            System.out.println("CLIENTE: Conectando con el servidor...");
            socketAlServidor.connect(direccionServidor); 
            System.out.println("CLIENTE: Conexión establecida con " + IP_SERVER + " en el puerto " + PUERTO);

           
            PrintStream salida = new PrintStream(socketAlServidor.getOutputStream());
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socketAlServidor.getInputStream()));
            
            while (!juegoTerminado && rondasJugadas < 5) {
                String eleccionCliente;

                
                while (true) {
                    System.out.println("Elige PIEDRA, PAPEL o TIJERA");
                    eleccionCliente = sc.nextLine().trim();
                    if (eleccionCliente.equalsIgnoreCase("PIEDRA") ||
                        eleccionCliente.equalsIgnoreCase("PAPEL") ||
                        eleccionCliente.equalsIgnoreCase("TIJERA")) {
                        break;
                    } else {
                        System.out.println("Por favor, ingresa una opción válida: PIEDRA, PAPEL o TIJERA.");
                    }
                }
                salida.println(eleccionCliente); 
                
               
                String respuestaServidor = entrada.readLine();
                System.out.println("CLIENTE: Resultado de la ronda: " + respuestaServidor);
         
                if (respuestaServidor.contains("final")) {
                    juegoTerminado = true;
                }
                rondasJugadas++;
                
            }
        
            socketAlServidor.close();
            System.out.println("CLIENTE: Conexión cerrada. Juego terminado.");
            
        } catch (IOException e) {
            System.err.println("CLIENTE: Error de entrada/salida");
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("CLIENTE: Error -> " + e);
            e.printStackTrace();
        }
    }
}
