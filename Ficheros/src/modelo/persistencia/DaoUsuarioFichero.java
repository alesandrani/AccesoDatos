package modelo.persistencia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

import modelo.entidades.Usuario;

public class DaoUsuarioFichero {
	private static final String NOMBRE_FICHERO = "usuarios.dat";
	/**
	 * Metodo que dado un nombre pasado por parametro busca su coincidencia
	 * el el fichero "usuarios.dat" y en el caso que lo encuentre lo devuelve
	 * junto con su password
	 * @param nombre a buscar en el fichero
	 * @return usuario en caso de que este en el fichero,null en caso contrario
	 * @throws Exception,en caso de que haya algun problema e el fichero
	 * de entrada salida
	 */
	public Usuario getByName(String nombre) throws Exception {
		Usuario usuario = null;
		
		try(FileReader fr = new FileReader(NOMBRE_FICHERO);
				BufferedReader br =new BufferedReader(fr)) {
			String cadena = br.readLine();//USUARIO/PASSWORD
			while(cadena != null) {
				String [] cadenaPartida = cadena.split("/");
				String nombreUsuario = cadenaPartida[0];
				String passwordUsuario = cadenaPartida[1];
				if(nombre.equals(nombreUsuario)) {
					usuario = new Usuario();
					usuario.setNombre(nombreUsuario);
					usuario.setPassword(passwordUsuario);
					return usuario;
				}
				cadena = br.readLine();
			}
			return null;
		}catch(Exception e) {
			throw e;
		}
	}
	/**
	 * Metodo que dado un usuario lo persista en el fichero "usuarios.dat".
	 * Se añadira a la ultima linea. Se persistira en el formato "NOMBRE/PASSWORD"
	 * @param u es el usuario que quremos persistir
	 * @throws Exception,en csod que haya algun problema e el fichero
	 * de entrada salida
	 */
	public void registrar(Usuario u) throws Exception{
		try(FileWriter fw = new FileWriter(NOMBRE_FICHERO,true);
				BufferedWriter bw = new BufferedWriter(fw)) {
				bw.write(u.toString());
		} catch (Exception e) {
			throw e;
		}
	}
}