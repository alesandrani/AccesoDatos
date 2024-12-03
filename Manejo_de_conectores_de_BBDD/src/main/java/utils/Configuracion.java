package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configuracion {
	
	private static Configuracion instance;

	private Properties properties;
	
	private Configuracion() {
		//Esta manera es cuando queremos acceder a un fichero properties que esta
		//dentro de nuestro "classpath"
		try (InputStream ficheroPropiedades = Configuracion.class.getClassLoader()
				.getResourceAsStream("propiedades.properties");){
			// Cargamos el properties
			// tiene que estar dentro de una ruta de classpath
			properties = new Properties();
			//Cargamos en el objeto properties, todos los valores del fichero
			properties.load(ficheroPropiedades);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Configuracion getInstance() {
		if(instance == null) {
			instance = new Configuracion();
		}
		
		return instance;
	}
	
	

	/**
	 * Método que devuelve un valor de una propiedad a partir de la clave
	 * @param key la clave de la propiedad
	 * @return valor de la propiedad. Null en caso de que no exista.
	 */
	public String getProperty(String key) {
		return properties.getProperty(key);
	}
}