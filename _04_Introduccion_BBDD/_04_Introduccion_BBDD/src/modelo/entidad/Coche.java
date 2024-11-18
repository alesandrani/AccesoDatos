package modelo.entidad;



public class Coche {
	private String id;
	private String marca;
	private String modelo;
	private String tipoMotor;
	private double kilometros;
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
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
	public String getTipoMotor() {
		return tipoMotor;
	}
	public void setTipoMotor(String tipoMotor) {
		this.tipoMotor = tipoMotor;
	}
	public double getKilometros() {
		return kilometros;
	}
	public void setKilometros(double kilometros) {
		this.kilometros = kilometros;
	}
	public Coche(String id, String marca, String modelo, String tipoMotor, double kilometros) {
		super();
		this.id = id;
		this.marca = marca;
		this.modelo = modelo;
		this.tipoMotor = tipoMotor;
		this.kilometros = kilometros;
	}
	
	
}
