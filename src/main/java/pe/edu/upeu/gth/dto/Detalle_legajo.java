package pe.edu.upeu.gth.dto;

public class Detalle_legajo {
	private String id_detalle_legajo;
	private String id_legajo;
	private String descripcion;
	private String no_archivo;
	private String ti_archivo;
	private String fecha_registro;
	public Detalle_legajo() {
		super();
	}
	public String getId_detalle_legajo() {
		return id_detalle_legajo;
	}
	public void setId_detalle_legajo(String id_detalle_legajo) {
		this.id_detalle_legajo = id_detalle_legajo;
	}
	public String getId_legajo() {
		return id_legajo;
	}
	public void setId_legajo(String id_legajo) {
		this.id_legajo = id_legajo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getNo_archivo() {
		return no_archivo;
	}
	public void setNo_archivo(String no_archivo) {
		this.no_archivo = no_archivo;
	}
	public String getTi_archivo() {
		return ti_archivo;
	}
	public void setTi_archivo(String ti_archivo) {
		this.ti_archivo = ti_archivo;
	}
	public String getFecha_registro() {
		return fecha_registro;
	}
	public void setFecha_registro(String fecha_registro) {
		this.fecha_registro = fecha_registro;
	}
	
	
}
