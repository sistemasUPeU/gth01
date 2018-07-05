package pe.edu.upeu.gth.dto;

public class Detalle_motivo {
	private String id_detalle_motivo;
	private String idrenuncia;
	private String idmotivo;
	private String otros;

	public String getId_detalle_motivo() {
		return id_detalle_motivo;
	}

	public void setId_detalle_motivo(String id_detalle_motivo) {
		this.id_detalle_motivo = id_detalle_motivo;
	}

	public String getIdrenuncia() {
		return idrenuncia;
	}

	public void setIdrenuncia(String idrenuncia) {
		this.idrenuncia = idrenuncia;
	}

	public String getIdmotivo() {
		return idmotivo;
	}

	public void setIdmotivo(String idmotivo) {
		this.idmotivo = idmotivo;
	}

	public String getOtros() {
		return otros;
	}

	public void setOtros(String otros) {
		this.otros = otros;
	}

}
