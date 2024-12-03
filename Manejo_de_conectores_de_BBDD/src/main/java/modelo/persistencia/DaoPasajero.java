package modelo.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import modelo.entidad.Pasajero;
import utils.Configuracion;

public class DaoPasajero {
	private static DaoPasajero instance;

	private DaoPasajero() {
		
	}
	public static DaoPasajero getInstance() {
		if(instance == null) {
			instance = new DaoPasajero();
		}
		return instance;
	}
	
	private Configuracion conf = Configuracion.getInstance();

	    
	    public boolean insertar(Pasajero pasajero, int idCoche) {
	    	String url = conf.getProperty("bbdd.url");
			String user = conf.getProperty("bbdd.user");
			String pass = conf.getProperty("bbdd.password");
	        String sql = "INSERT INTO Pasajeros (nombre, edad, peso, id_coche) VALUES (?, ?, ?, ?)";
	        try (Connection con = DriverManager.getConnection(url, user, pass);
	             PreparedStatement ps = con.prepareStatement(sql)) {
	            ps.setString(1, pasajero.getNombre());
	            ps.setInt(2, pasajero.getEdad());
	            ps.setDouble(3, pasajero.getPeso());
	            ps.setInt(4, idCoche); 

	            int filas = ps.executeUpdate();
	            return filas == 1;
	        } catch (SQLException e) {
	            System.err.println("Error al insertar pasajero: " + e.getMessage());
	        }
	        return false;
	    }

	  
	    public  boolean borrar(int id) {
	    	String url = conf.getProperty("bbdd.url");
			String user = conf.getProperty("bbdd.user");
			String pass = conf.getProperty("bbdd.password");
	        String sql = "DELETE FROM Pasajeros WHERE id = ?";
	        try (Connection con = DriverManager.getConnection(url, user, pass);
	             PreparedStatement ps = con.prepareStatement(sql)) {
	            ps.setInt(1, id);

	            int filas = ps.executeUpdate();
	            return filas == 1;
	        } catch (SQLException e) {
	            System.err.println("Error al eliminar pasajero: " + e.getMessage());
	        }
	        return false;
	    }

	
	    public boolean modificar(Pasajero pasajero) {
	    	String url = conf.getProperty("bbdd.url");
			String user = conf.getProperty("bbdd.user");
			String pass = conf.getProperty("bbdd.password");
	        String sql = "UPDATE Pasajeros SET nombre = ?, edad = ?, peso = ? WHERE id = ?";
	        try (Connection con = DriverManager.getConnection(url, user, pass);
	             PreparedStatement ps = con.prepareStatement(sql)) {
	            ps.setString(1, pasajero.getNombre());
	            ps.setInt(2, pasajero.getEdad());
	            ps.setDouble(3, pasajero.getPeso());
	            ps.setInt(4, pasajero.getId());

	            int filas = ps.executeUpdate();
	            return filas > 0;
	        } catch (SQLException e) {
	            System.err.println("Error al modificar pasajero: " + e.getMessage());
	        }
	        return false;
	    }

	    
	    public  Pasajero buscarId(int id) {
	    	String url = conf.getProperty("bbdd.url");
			String user = conf.getProperty("bbdd.user");
			String pass = conf.getProperty("bbdd.password");
	        String sql = "SELECT * FROM Pasajeros WHERE id = ?";
	        try (Connection con = DriverManager.getConnection(url, user, pass);
	             PreparedStatement ps = con.prepareStatement(sql)) {
	            ps.setInt(1, id);

	            ResultSet rs = ps.executeQuery();
	            if (rs.next()) {
	                return new Pasajero(
	                       rs.getString("nombre"),
	                        rs.getInt("edad"),
	                        rs.getDouble("peso")
	                );
	            }
	        } catch (SQLException e) {
	            System.err.println("Error al buscar pasajero por ID: " + e.getMessage());
	        }
	        return null;
	    }

	   
	    public  List<Pasajero> listar() {
	    	String url = conf.getProperty("bbdd.url");
			String user = conf.getProperty("bbdd.user");
			String pass = conf.getProperty("bbdd.password");
	        List<Pasajero> pasajeros = new ArrayList<>();
	        String sql = "SELECT * FROM Pasajeros";
	        try (Connection con = DriverManager.getConnection(url, user, pass);
	             Statement stmt = con.createStatement();
	             ResultSet rs = stmt.executeQuery(sql)) {

	            while (rs.next()) {
	                pasajeros.add(new Pasajero(
	                		rs.getString("nombre"),
	                        rs.getInt("edad"),
	                        rs.getDouble("peso")
	                ));
	            }
	        } catch (SQLException e) {
	            System.err.println("Error al listar pasajeros: " + e.getMessage());
	        }
	        return pasajeros;
	    }

	   
	    public  boolean asociarACoche(int pasajeroId, int cocheId) {
	    	String url = conf.getProperty("bbdd.url");
			String user = conf.getProperty("bbdd.user");
			String pass = conf.getProperty("bbdd.password");
	        String sql = "INSERT INTO CochePasajero (coche_id, pasajero_id) VALUES (?, ?)";
	        try (Connection con = DriverManager.getConnection(url, user, pass);
	             PreparedStatement ps = con.prepareStatement(sql)) {
	            ps.setInt(1, cocheId);
	            ps.setInt(2, pasajeroId);

	            int filas = ps.executeUpdate();
	            return filas == 1;
	        } catch (SQLException e) {
	            System.err.println("Error al asociar pasajero a coche: " + e.getMessage());
	        }
	        return false;
	    }

	
	    public  boolean desasociarDeCoche(int pasajeroId) {
	    	String url = conf.getProperty("bbdd.url");
			String user = conf.getProperty("bbdd.user");
			String pass = conf.getProperty("bbdd.password");
	        String sql = "DELETE FROM CochePasajero WHERE pasajero_id = ?";
	        try (Connection con = DriverManager.getConnection(url, user, pass);
	             PreparedStatement ps = con.prepareStatement(sql)) {
	            ps.setInt(1, pasajeroId);

	            int filas = ps.executeUpdate();
	            return filas == 1;
	        } catch (SQLException e) {
	            System.err.println("Error al desasociar pasajero de coche: " + e.getMessage());
	        }
	        return false;
	    }

	 
	    public List<Pasajero> listarPorCoche(int cocheId) {
	    	String url = conf.getProperty("bbdd.url");
			String user = conf.getProperty("bbdd.user");
			String pass = conf.getProperty("bbdd.password");
	        List<Pasajero> pasajeros = new ArrayList<>();
	        String sql = "SELECT p.* FROM Pasajeros p INNER JOIN CochePasajero cp ON p.id = cp.pasajero_id WHERE cp.coche_id = ?";
	        try (Connection con = DriverManager.getConnection(url, user, pass);
	             PreparedStatement ps = con.prepareStatement(sql)) {
	            ps.setInt(1, cocheId);

	            ResultSet rs = ps.executeQuery();
	            while (rs.next()) {
	                pasajeros.add(new Pasajero(
	                        rs.getString("nombre"),
	                        rs.getInt("edad"),
	                        rs.getDouble("peso")
	                ));
	            }
	        } catch (SQLException e) {
	            System.err.println("Error al listar pasajeros por coche: " + e.getMessage());
	        }
	        return pasajeros;
	    }
}
