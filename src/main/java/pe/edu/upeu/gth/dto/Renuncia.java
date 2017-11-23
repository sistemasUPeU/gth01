package pe.edu.upeu.gth.dto;

public class Renuncia {
	private String id_renuncia;
	private String id_trabajador;
	private String otros;
	private String fecha;
	private String estado;
	private String no_archivo;
	private String ti_archivo;
	private Long tam_archivo;
	private byte[] pixel;

	public String getId_renuncia() {
		return id_renuncia;
	}

	public void setId_renuncia(String id_renuncia) {
		this.id_renuncia = id_renuncia;
	}

	public String getId_trabajador() {
		return id_trabajador;
	}

	public void setId_trabajador(String id_trabajador) {
		this.id_trabajador = id_trabajador;
	}

	public String getOtros() {
		return otros;
	}

	public void setOtros(String otros) {
		this.otros = otros;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
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

	public Long getTam_archivo() {
		return tam_archivo;
	}

	public void setTam_archivo(Long tam_archivo) {
		this.tam_archivo = tam_archivo;
	}

	public byte[] getPixel() {
		return pixel;
	}

	public void setPixel(byte[] pixel) {
		this.pixel = pixel;
	}
	
	

}
