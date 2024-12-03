package modelo.entidad;

public class Coche {
	
	private int id;
	private String marca;
	private String modelo;
	private String anio_fabricacion;
	private double km;
	
	@Override
	public String toString() {
		return "Coche [id=" + id + ", marca=" + marca + ", modelo=" + modelo + ", anio_fabricacion=" + anio_fabricacion
				+ ", km=" + km + "]";
	}
	public Coche( String marca, String modelo, String anio_fabricacion, double km) {
		super();
		
		this.marca = marca;
		this.modelo = modelo;
		this.anio_fabricacion = anio_fabricacion;
		this.km = km;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public String getAnio_fabricacion() {
		return anio_fabricacion;
	}
	public void setAnio_fabricacion(String anio_fabricacion) {
		this.anio_fabricacion = anio_fabricacion;
	}
	public double getKm() {
		return km;
	}
	public void setKm(double km) {
		this.km = km;
	}
	
	
}
