package pe.edu.upeu.gth.dto;

public class Renuncia {
	private String id_renuncia;
	private String id_contrato;
	private String otros;
	private String fecha;
	private String estado;
	private String no_archivo;
	private String ti_archivo;
	private Long tam_archivo;
	private String id_motivo;
	private String id_usuario;
	private String tipo;
	private String usu_mod;
	
	
	public Renuncia() {
		super();
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(String id_usuario) {
		this.id_usuario = id_usuario;
	}

	public String getId_motivo() {
		return id_motivo;
	}

	public void setId_motivo(String id_motivo) {
		this.id_motivo = id_motivo;
	}

	private byte[] pixel;

	public String getId_renuncia() {
		return id_renuncia;
	}

	public void setId_renuncia(String id_renuncia) {
		this.id_renuncia = id_renuncia;
	}

	public String getId_contrato() {
		return id_contrato;
	}

	public void setId_contrato(String id_contrato) {
		this.id_contrato = id_contrato;
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

	public String getUsu_mod() {
		return usu_mod;
	}

	public void setUsu_mod(String usu_mod) {
		this.usu_mod = usu_mod;
	}
	
	

}
