package pe.edu.upeu.gth.dto;

public class Rechazo {
	private String id_rechazo;
	private String id_renuncia;	
	private String observaciones;
	private String fecha_rechazo;
	
	
	public Rechazo() {
		super();
	}
	public Rechazo(String id_renuncia, String observaciones) {
		super();
		this.id_renuncia = id_renuncia;
		this.observaciones = observaciones;
	}
	public String getId_rechazo() {
		return id_rechazo;
	}
	public void setId_rechazo(String id_rechazo) {
		this.id_rechazo = id_rechazo;
	}
	public String getId_renuncia() {
		return id_renuncia;
	}
	public void setId_renuncia(String id_renuncia) {
		this.id_renuncia = id_renuncia;
	}
	public String getFecha_rechazo() {
		return fecha_rechazo;
	}
	public void setFecha_rechazo(String fecha_rechazo) {
		this.fecha_rechazo = fecha_rechazo;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	
	
}