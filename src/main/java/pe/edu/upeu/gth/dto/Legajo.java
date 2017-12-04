package pe.edu.upeu.gth.dto;

public class Legajo {
	private String idlegajo;
	private String idtrabajador;
	private String idrenuncia;
	private String fecha_legajo;
	private String id_tipo_doc;
	private String no_archivo;
	private String ti_archivo;
	private String fecha_registro;
	private String otros;
	private String detalle_otros;

	
	
	public String getIdrenuncia() {
		return idrenuncia;
	}

	public void setIdrenuncia(String idrenuncia) {
		this.idrenuncia = idrenuncia;
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

	public String getFecha_legajo() {
		return fecha_legajo;
	}

	public void setFecha_legajo(String fecha_legajo) {
		this.fecha_legajo = fecha_legajo;
	}

	public String getId_tipo_doc() {
		return id_tipo_doc;
	}

	public void setId_tipo_doc(String id_tipo_doc) {
		this.id_tipo_doc = id_tipo_doc;
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
