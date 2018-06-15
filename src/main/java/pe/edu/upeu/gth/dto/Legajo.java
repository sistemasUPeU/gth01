package pe.edu.upeu.gth.dto;

public class Legajo {
	private String idlegajo;
	private String idtrabajador;
	private String idcontrato;
	private String idrenuncia;
	private String fecha_legajo;
	private String descripcion;
	private String no_archivo;
	private String ti_archivo;
	private String fecha_registro;
	private String otros;
	private String detalle_otros;
	public Legajo() {
		super();
	}
	public Legajo(String idtrabajador, String idcontrato, String idrenuncia, String fecha_legajo, String descripcion,
			String no_archivo, String ti_archivo, String fecha_registro, String otros, String detalle_otros) {
		super();
		this.idtrabajador = idtrabajador;
		this.idcontrato = idcontrato;
		this.idrenuncia = idrenuncia;
		this.fecha_legajo = fecha_legajo;
		this.descripcion = descripcion;
		this.no_archivo = no_archivo;
		this.ti_archivo = ti_archivo;
		this.fecha_registro = fecha_registro;
		this.otros = otros;
		this.detalle_otros = detalle_otros;
	}
	public Legajo(String idlegajo, String idtrabajador, String idcontrato, String idrenuncia, String fecha_legajo,
			String descripcion, String no_archivo, String ti_archivo, String fecha_registro, String otros,
			String detalle_otros) {
		super();
		this.idlegajo = idlegajo;
		this.idtrabajador = idtrabajador;
		this.idcontrato = idcontrato;
		this.idrenuncia = idrenuncia;
		this.fecha_legajo = fecha_legajo;
		this.descripcion = descripcion;
		this.no_archivo = no_archivo;
		this.ti_archivo = ti_archivo;
		this.fecha_registro = fecha_registro;
		this.otros = otros;
		this.detalle_otros = detalle_otros;
	}
	public String getIdlegajo() {
		return idlegajo;
	}
	public void setIdlegajo(String idlegajo) {
		this.idlegajo = idlegajo;
	}
	public String getIdtrabajador() {
		return idtrabajador;
	}
	public void setIdtrabajador(String idtrabajador) {
		this.idtrabajador = idtrabajador;
	}
	public String getIdcontrato() {
		return idcontrato;
	}
	public void setIdcontrato(String idcontrato) {
		this.idcontrato = idcontrato;
	}
	public String getIdrenuncia() {
		return idrenuncia;
	}
	public void setIdrenuncia(String idrenuncia) {
		this.idrenuncia = idrenuncia;
	}
	public String getFecha_legajo() {
		return fecha_legajo;
	}
	public void setFecha_legajo(String fecha_legajo) {
		this.fecha_legajo = fecha_legajo;
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
	public String getOtros() {
		return otros;
	}
	public void setOtros(String otros) {
		this.otros = otros;
	}
	public String getDetalle_otros() {
		return detalle_otros;
	}
	public void setDetalle_otros(String detalle_otros) {
		this.detalle_otros = detalle_otros;
	}

	
	

}
