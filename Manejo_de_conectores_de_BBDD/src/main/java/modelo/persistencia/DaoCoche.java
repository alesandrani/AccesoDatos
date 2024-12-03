package modelo.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import modelo.entidad.Coche;
import utils.Configuracion;

public class DaoCoche {
	
	private static DaoCoche instance;
	
	private DaoCoche() {
		
	}
	public static DaoCoche getInstance() {
		if(instance == null) {
			instance = new DaoCoche();
		}
		
		return instance;
	}
	
	
	private Configuracion conf = Configuracion.getInstance();

	public boolean insertar(Coche coche) {
				
		String url = conf.getProperty("bbdd.url");
		String user = conf.getProperty("bbdd.user");
		String pass = conf.getProperty("bbdd.password");
		
		System.out.println("Probando conexión a BBDD");
		
		try (Connection con = DriverManager.getConnection(url, user, pass);){
			System.out.println("La conexión ha sido establecida");
			
		
			String sql = "INSERT INTO Coches (marca,modelo,anio_fabricacion,km) VALUES "
					+ "(?,?,?,?)";
			
			System.out.println("Se va a ejecutar la siguinte sentencia sql " + sql);
			PreparedStatement sentencia;
			sentencia = con.prepareStatement(sql);
			
			
			sentencia.setString(1, coche.getMarca());
			sentencia.setString(2, coche.getModelo());
			sentencia.setString(3, coche.getAnio_fabricacion());
			sentencia.setDouble(4, coche.getKm());
			
			int afectados = sentencia.executeUpdate();
			if(afectados==1) {
				return true;
			}
			
			System.out.println("Sentencia sql ejecutada con exito");
			System.out.println("registros afectados : " + afectados);
			
		} catch (SQLException e) {
			System.out.println("Error al añadir coche");
			System.out.println(e.getMessage());
			
		}
		return false;

	}
	
	public boolean eliminar(String id) {
		
		String url = conf.getProperty("bbdd.url");
		String user = conf.getProperty("bbdd.user");
		String pass = conf.getProperty("bbdd.password");
		
		
		try (Connection conn = DriverManager.getConnection(url, user, pass);){
			System.out.println("La conexión ha sido establecida");
			
			
			String query = "DELETE FROM Coches WHERE ID=? ";
					
			
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, id);
			
			int filas = ps.executeUpdate();
			if(filas == 1) {
				System.out.println("borrado");
				return true;
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return false;
	}


	public boolean modificar(Coche coche) {
		String url = conf.getProperty("bbdd.url");
		String user = conf.getProperty("bbdd.user");
		String pass = conf.getProperty("bbdd.password");
		
		try (Connection conn = DriverManager.getConnection(url, user, pass);){
			System.out.println("La conexión ha sido establecida");
			
			String query = "UPDATE Coches SET marca=?,modelo=?,anio_fabricacion=?,km=? WHERE id=?";
			
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, coche.getMarca());
			ps.setString(2, coche.getModelo());
			ps.setString(3, coche.getAnio_fabricacion());
			ps.setDouble(4, coche.getKm());
			ps.setInt(5, coche.getId());
			
			int rows = ps.executeUpdate();
			if(rows !=0) {
				System.out.println("Se ha dado modificado");
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}


	public Coche buscarId(String idBuscar) {
		String url = conf.getProperty("bbdd.url");
		String user = conf.getProperty("bbdd.user");
		String pass = conf.getProperty("bbdd.password");
		try (Connection conn = DriverManager.getConnection(url, user, pass);){
			System.out.println("La conexión ha sido establecida");
			
			String query = "SELECT * FROM Coches WHERE id=?";
			
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, idBuscar);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				int id = rs.getInt(1);
				String marca = rs.getString(2);
				String modelo = rs.getString(3);
				String anio_fabricacion = rs.getString(4);
				double km = rs.getDouble(5);
				System.out.println("Registro: " + id + " " + marca + " " + modelo + " " + anio_fabricacion + " " + km);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	public List<Coche> buscarPorMarca(String marca) {
		String url = conf.getProperty("bbdd.url");
		String user = conf.getProperty("bbdd.user");
		String pass = conf.getProperty("bbdd.password");

	    List<Coche> coches = new ArrayList<>();

	    try (Connection conn = DriverManager.getConnection(url, user, pass)) {
	        System.out.println("La conexión ha sido establecida");

	        String query = "SELECT * FROM Coches WHERE MARCA = ?";
	        PreparedStatement ps = conn.prepareStatement(query);
	        ps.setString(1, marca);

	        ResultSet rs = ps.executeQuery();
	        while (rs.next()) {
	            coches.add(new Coche(
	            		rs.getString("MARCA"),
	                    rs.getString("MODELO"),
	                    rs.getString("Anio_Fabricacion"),
	                    rs.getDouble("KILOMETROS")
	            ));
	        }
	    } catch (SQLException e) {
	        System.err.println("Error al buscar coches por marca: " + e.getMessage());
	    }

	    return coches;
	}


	public boolean borrar(String idEliminar) {
		String url = conf.getProperty("bbdd.url");
		String user = conf.getProperty("bbdd.user");
		String pass = conf.getProperty("bbdd.password");
		try (Connection conn = DriverManager.getConnection(url, user, pass);){
			System.out.println("La conexión ha sido establecida");
			
			String query = "DELETE FROM Coches WHERE id=?";
			
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, idEliminar);
			
			int filas = ps.executeUpdate();
			if(filas == 1) {
				String id = idEliminar;
				System.out.println("Hemos borrado a el coche con el id " + id);
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
		
	}


	public List<Coche> listar() {
		String url = conf.getProperty("bbdd.url");
		String user = conf.getProperty("bbdd.user");
		String pass = conf.getProperty("bbdd.password");

		    List<Coche> coches = new ArrayList<>();

		    try (Connection conn = DriverManager.getConnection(url, user, pass);
		         Statement stmt = conn.createStatement();
		         ResultSet rs = stmt.executeQuery("SELECT * FROM Coches")) {

		        System.out.println("La conexión ha sido establecida");

		        while (rs.next()) {
		            coches.add(new Coche(
		                    rs.getString("marca"),
		                    rs.getString("modelo"),
		                    rs.getString("anio_fabricacion"),
		                    rs.getDouble("km")  
		            ));
		        }

		    } catch (SQLException e) {
		        System.err.println("Error al listar todos los coches: " + e.getMessage());
		    }

		    return coches; 
	}
	

	}
