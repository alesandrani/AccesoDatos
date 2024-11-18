package modelo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import modelo.entidad.Coche;

public class DaoCoche {
	
	public boolean insertar(Coche coche) {
		String url = "jdbc:mysql://localhost:3306/coches";
		String user = "root";
		String pass = "";
		
		System.out.println("Probando conexión a BBDD");
		
		try (Connection con = DriverManager.getConnection(url, user, pass);){
			System.out.println("La conexión ha sido establecida");
			
		
			String sql = "INSERT INTO Coche (id,marca,modelo,tipo_motor,kilometros) VALUES "
					+ "(?,?,?,?,?)";
			
			System.out.println("Se va a ejecutar la siguinte sentencia sql " + sql);
			PreparedStatement sentencia;
			sentencia = con.prepareStatement(sql);
			
			sentencia.setString(1, coche.getId());
			sentencia.setString(2, coche.getMarca());
			sentencia.setString(3, coche.getModelo());
			sentencia.setString(4, coche.getTipoMotor());
			sentencia.setDouble(5, coche.getKilometros());
			
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
	
	public boolean modificar(Coche coche) {
		String url = "jdbc:mysql://localhost:3306/coches";
		String user = "root";
		String pass = "";
		
		try (Connection conn = DriverManager.getConnection(url, user, pass);){
			System.out.println("La conexión ha sido establecida");
			
			String query = "UPDATE Coche SET marca=?,modelo=?,tipo_motor=?,kilometros=? WHERE id=?";
			
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, coche.getMarca());
			ps.setString(2, coche.getModelo());
			ps.setString(3, coche.getTipoMotor());
			ps.setDouble(4, coche.getKilometros());
			ps.setString(5, coche.getId());
			
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
		String url = "jdbc:mysql://localhost:3306/coches";
		String user = "root";
		String pass = "";
		
		try (Connection conn = DriverManager.getConnection(url, user, pass);){
			System.out.println("La conexión ha sido establecida");
			
			String query = "SELECT * FROM Coche WHERE id=?";
			
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, idBuscar);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				String id = rs.getString(1);
				String marca = rs.getString(2);
				String modelo = rs.getString(3);
				String tipo_motor = rs.getString(4);
				double kilometros = rs.getDouble(5);
				System.out.println("Registro: " + id + " " + marca + " " + modelo + " " + tipo_motor + " " + kilometros);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

		
		
	}
	public List<Coche> buscarPorMarca(String marca) {
	    String url = "jdbc:mysql://localhost:3306/coches";
	    String user = "root";
	    String pass = "";

	    List<Coche> coches = new ArrayList<>();

	    try (Connection conn = DriverManager.getConnection(url, user, pass)) {
	        System.out.println("La conexión ha sido establecida");

	        String query = "SELECT * FROM Coche WHERE MARCA = ?";
	        PreparedStatement ps = conn.prepareStatement(query);
	        ps.setString(1, marca);

	        ResultSet rs = ps.executeQuery();
	        while (rs.next()) {
	            coches.add(new Coche(
	                    rs.getString("ID"), // Si `ID` es un String en tu tabla
	                    rs.getString("MARCA"),
	                    rs.getString("MODELO"),
	                    rs.getString("TIPO_MOTOR"),
	                    rs.getInt("KILOMETROS")
	            ));
	        }
	    } catch (SQLException e) {
	        System.err.println("Error al buscar coches por marca: " + e.getMessage());
	    }

	    return coches;
	}
	
	public boolean borrar(String idEliminar) {
		String url = "jdbc:mysql://localhost:3306/coches";
		String user = "root";
		String pass = "";
		
		try (Connection conn = DriverManager.getConnection(url, user, pass);){
			System.out.println("La conexión ha sido establecida");
			
			String query = "DELETE FROM Coche WHERE id=?";
			
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
	    String url = "jdbc:mysql://localhost:3306/coches";
	    String user = "root";
	    String pass = "";

	    List<Coche> coches = new ArrayList<>();

	    try (Connection conn = DriverManager.getConnection(url, user, pass);
	         Statement stmt = conn.createStatement();
	         ResultSet rs = stmt.executeQuery("SELECT * FROM Coche")) {

	        System.out.println("La conexión ha sido establecida");

	        while (rs.next()) {
	            coches.add(new Coche(
	                    rs.getString("id"),      
	                    rs.getString("marca"),
	                    rs.getString("modelo"),
	                    rs.getString("tipo_motor"),
	                    rs.getDouble("kilometros")  
	            ));
	        }

	    } catch (SQLException e) {
	        System.err.println("Error al listar todos los coches: " + e.getMessage());
	    }

	    return coches; 
	}
		}

