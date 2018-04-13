package pe.edu.upeu.gth.dto;

public class Justificacion {
	private String id_justificacion;
	private String id_renaban;	
	private String observaciones;
	private String fecha_justificacion;
	public Justificacion() {
		super();
	}
	public Justificacion(String id_justificacion, String id_renaban, String observaciones, String fecha_justificacion) {
		super();
		this.id_justificacion = id_justificacion;
		this.id_renaban = id_renaban;
		this.observaciones = observaciones;
		this.fecha_justificacion = fecha_justificacion;
	}
	public Justificacion(String id_renaban, String observaciones, String fecha_justificacion) {
		super();
		this.id_renaban = id_renaban;
		this.observaciones = observaciones;
		this.fecha_justificacion = fecha_justificacion;
	}
	public String getId_justificacion() {
		return id_justificacion;
	}
	public void setId_justificacion(String id_justificacion) {
		this.id_justificacion = id_justificacion;
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
	public String getFecha_justificacion() {
		return fecha_justificacion;
	}
	public void setFecha_justificacion(String fecha_justificacion) {
		this.fecha_justificacion = fecha_justificacion;
	}
	
	
}
