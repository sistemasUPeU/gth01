package pe.edu.upeu.gth.dto;

public class Rechazo {
	private String id_rechazo;
	private String id_renaban;	
	private String observaciones;
	private String fecha_rechazo;
	
	public Rechazo() {
		super();
	}
	public Rechazo(String id_rechazo, String id_renaban, String observaciones, String fecha_rechazo) {
		super();
		this.id_rechazo = id_rechazo;
		this.id_renaban = id_renaban;
		this.observaciones = observaciones;
		this.fecha_rechazo = fecha_rechazo;
	}
	public Rechazo(String id_renaban, String observaciones, String fecha_rechazo) {
		super();
		this.id_renaban = id_renaban;
		this.observaciones = observaciones;
		this.fecha_rechazo = fecha_rechazo;
	}
	public String getId_rechazo() {
		return id_rechazo;
	}
	public void setId_rechazo(String id_rechazo) {
		this.id_rechazo = id_rechazo;
	}
	public String getId_renaban() {
		return id_renaban;
	}
	public void setId_renaban(String id_renaban) {
		this.id_renaban = id_renaban;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	public String getFecha_rechazo() {
		return fecha_rechazo;
	}
	public void setFecha_rechazo(String fecha_rechazo) {
		this.fecha_rechazo = fecha_rechazo;
	}
	
	
	
	
}